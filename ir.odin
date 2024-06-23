// TODO:
// - Pointers
// - Branching
// - Functions
package lang

Word :: distinct Int

import "base:intrinsics"
import "core:log"
import "core:fmt"
import "core:hash"
import "core:strings"

WORD_SIZE :: size_of(Word)

// TODO: Make this better
StaticData :: struct {
	size: int,
	align: Alignment,
	kind: StaticDataKind,

	str_data: string,
}

Alignment :: enum {
	Word = align_of(Word),
	Byte = 1,
}

StaticDataKind :: enum {
	Variable,
	String_Literal,
}

StaticDataTable :: map[string]StaticData

Opcode :: enum {
	NoOp = 0,

	Push, Pop,

	Add, Sub, Mul, Div, Mod,
	Neg,

	Lesser, Greater, LesserEqual, GreaterEqual,
	NotEqual, Equal,

	And, Or, Xor, Not,
	ShiftLeft, ShiftRight,

	LogicNot,

	Call,
	Call_Builtin,

	Jump,
	BranchNotZero, BranchZero,

	Label, // Just set a label for jumping

	Store, Load,
	// Store addr <- value
	// Load addr
}

Instruction :: struct {
	opcode: Opcode,
	immediate: Word,
	label: string, // For load/store instructions that operate on the static segment
}

Machine :: struct {
	stack: []Word,
	program: []Instruction,
	static_memory: []Word,
	sp: int,
	ip: int,
}

generate_ir :: proc(root: ^Scope) -> (program: []Instruction, data: StaticDataTable, err: Error) {
	mangle_names(root)
	buf := make([dynamic]Instruction)

	data = make_static_data_table(root)
	generate_scope_ir(&buf, &data, root, LabelId(-1)) or_return

	shrink(&buf)
	program = buf[:]
	return
}

// Does all required name-mangling recursively
mangle_names :: proc(scope: ^Scope, seed := u32(0x811c9dc5)) -> u32 {
	hash_seed := mangle_variables(scope, seed)

	for &statement in scope.body {
		switch &statement in statement {
		case Scope:
			hash_seed = mangle_names(&statement, hash_seed)
		case For:
			hash_seed = mangle_names(&statement.scope, hash_seed)
		case If:
			hash_seed = mangle_names(&statement.scope, hash_seed)
		case FunctionDef:
			hash_seed = mangle_names(&statement.scope, hash_seed)
		case InlineStatement:
			continue
		}
	}
	return hash_seed
}

mangle_variables :: proc(scope: ^Scope, seed: u32) -> u32 {
	last_hash := seed
	for id in scope.env {
		info := scope.env[id]
		if info.kind == .Variable {
			defer scope.env[id] = info
			sb := strings.builder_make()
			h := hash.fnv32a(transmute([]u8)string(id), seed = seed)

			// NOTE: Not strictly necessary, but prevents collisions even further and makes debugging easier
			mtype := mangle_type_name(info.type)
			mangled := fmt.sbprintf(&sb, "%s_%v_%08x", id, mtype, h)
			shrink(&sb.buf)
			info.static_section_name = mangled
			last_hash = h
		}
	}
	return last_hash
}

mangle_type_name :: proc(t: Type) -> string {
	buf := make([dynamic]byte)
	for mod in t.modifiers {
		switch mod in mod {
		case Array:
			num := fmt.tprintf("%d", mod.size)
			for c in num {
				append(&buf, byte(c))
			}
			append(&buf, 'V')
		case Pointer:
			append(&buf, 'P')
		}
	}

	p := t.primitive.(BuiltinType)
	switch p {
	case .Bool:   append(&buf, 'b')
	case .Int:    append(&buf, 'i')
	case .Real:   append(&buf, 'f')
	case .Rune:   append(&buf, 'c')
	case .String: append(&buf, 's')
	}

	shrink(&buf)
	return string(buf[:])
}

@(private="file")
OPCODE_UNARY_MAP := map[TokenKind]Opcode {
	.Plus   = .NoOp,
	.Minus  = .Neg,
	.BitXor = .Not,
	.LogicNot = .LogicNot, // NOTE: This only works because we assume a 0-1 representation for floats
}

builtin_function_name :: proc(s: Identifier) -> bool {
	return len(s) > 2 && s[:2] == "__"
}

@(private)
init_static_data_table_rec :: proc(cur_table: ^StaticDataTable, scope: ^Scope){
	for id, info in scope.env {
		if len(info.static_section_name) > 0 {
			using info
			cur_table[static_section_name] = StaticData {
				size = type_size(type),
				align = .Word,
				kind = .Variable,
			}
		}
	}

	for &statement in scope.body {
		switch &statement in statement {
		case If:
			init_static_data_table_rec(cur_table, &statement.scope)
		case For:
			init_static_data_table_rec(cur_table, &statement.scope)
		case FunctionDef:
			log.warn("No function")
		case Scope:
			init_static_data_table_rec(cur_table, &statement)
		case InlineStatement: continue
		}
	}
}

make_static_data_table :: proc(root: ^Scope) -> StaticDataTable {
	table := make(StaticDataTable)
	init_static_data_table_rec(&table, root)
	return table
}

LabelId :: distinct int

@(require_results)
generate_statement_ir :: proc(
	progbuf: ^[dynamic]Instruction,
	static_data: ^StaticDataTable,
	statement: ^Statement,
	scope: ^Scope,
	innermost_loop: LabelId,
) -> (err: Error){
	if statement == nil { return }

	switch &statement in statement {
	case InlineStatement:
		switch inline_stmt in statement {
		case ExpressionStatement:
			expr, is_func := inline_stmt.inner.value.(FunctionCall)
			if !is_func {
				log.warn("Expression statements are not yet supported")
				return
			}
			else {
				generate_expression_ir(progbuf, static_data, scope, inline_stmt.inner) or_return
			}
		case VarDeclaration:
			generate_var_declaration_ir(progbuf, static_data, scope, inline_stmt)
		case Assignment:
			generate_assignment_ir(progbuf, static_data, scope, inline_stmt)
		case Return:
			unimplemented()

		case Break:
			assert(innermost_loop >= 0, "Break could not possibly appear here")
			_, innermost_loop_exit := make_loop_labels(innermost_loop)
			append(progbuf, Instruction {
				opcode = .Jump,
				label = innermost_loop_exit,
			})

		case Continue:
			assert(innermost_loop >= 0, "Continue could not possibly appear here")
			innermost_loop_entry, _ := make_loop_labels(innermost_loop)
			append(progbuf, Instruction {
				opcode = .Jump,
				label = innermost_loop_entry,
			})
		}
	case Scope:
		generate_scope_ir(progbuf, static_data, &statement, innermost_loop) or_return
	case If:
		statement.label_id = generate_branch_id()

		entry_label, else_label, exit_label := make_branch_labels(statement.label_id)

		// If not 'condition', jump to 'else' block (or exit if no else exists)
		append(progbuf, Instruction { opcode = .Label, label = entry_label })
		generate_expression_ir(progbuf, static_data, scope, statement.condition) or_return
		append(progbuf, Instruction {
			opcode = .BranchZero,
			label = else_label if statement.else_branch != nil else exit_label,
		})

		// 'if' body
		generate_scope_ir(progbuf, static_data, &statement.scope, innermost_loop) or_return
		append(progbuf, Instruction {
			opcode = .Jump,
			label = exit_label,
		})

		// 'else' body
		if statement.else_branch != nil {
			append(progbuf, Instruction { opcode = .Label, label = else_label })
			generate_statement_ir(progbuf, static_data, statement.else_branch, scope, innermost_loop) or_return // WARN: I'm not sure if this scoping rule is correct
			append(progbuf, Instruction {
				opcode = .Jump,
				label = exit_label,
			})
		}

		// Exit label
		append(progbuf, Instruction {
			opcode = .Label,
			label = exit_label,
		})

	case For:
		assert(statement.pre_stmt == nil, "Composite loops are not yet supported")
		statement.label_id = generate_branch_id()

		loop_label, exit_label := make_loop_labels(statement.label_id)

		append(progbuf, Instruction { opcode = .Label, label = loop_label })
		// Check loop condition, if false, break out of it
		generate_expression_ir(progbuf, static_data, &statement.scope, statement.condition) or_return
		append(progbuf, Instruction {
			opcode = .BranchZero,
			label = exit_label,
		})

		innermost_loop := statement.label_id
		generate_scope_ir(progbuf, static_data, &statement.scope, innermost_loop) or_return
		append(progbuf, Instruction {
			opcode = .Jump,
			label = loop_label,
		})
		append(progbuf, Instruction { opcode = .Label, label = exit_label })

	case FunctionDef:
		if builtin_function_name(statement.name){
			return
		}
		else {
			unimplemented("Function definitions are not yet implemented")
		}
	}
	return
}

@(require_results)
generate_scope_ir :: proc(
	progbuf: ^[dynamic]Instruction,
	static_data: ^StaticDataTable,
	scope: ^Scope,
	innermost_loop: LabelId,
) -> (err: Error) {
	for &statement in scope.body {
		generate_statement_ir(progbuf, static_data, &statement, scope, innermost_loop) or_return
	}
	return
}

@(private)
generate_branch_id :: proc() -> LabelId {
	@static id := LabelId(0)
	intrinsics.atomic_add(&id, 1)
	return id
}

@(private)
make_loop_labels :: proc(id: LabelId) -> (loop_label: string, exit_label: string){
	buf_loop := make([]byte, MAX_LABEL_LENGTH)
	buf_exit := make([]byte, MAX_LABEL_LENGTH)
	exit_label = fmt.bprintf(buf_exit, "ENDFOR_%08d", id)
	loop_label = fmt.bprintf(buf_loop, "FOR_%08d", id)
	return
}

@(private)
make_branch_labels :: proc(id: LabelId) -> (entry_label: string, else_label: string, exit_label: string){
	buf_entry := make([]byte, MAX_LABEL_LENGTH)
	buf_else := make([]byte, MAX_LABEL_LENGTH)
	buf_exit := make([]byte, MAX_LABEL_LENGTH)

	entry_label = fmt.bprintf(buf_entry, "IF_%08d", id)
	else_label  = fmt.bprintf(buf_else, "ELSE_%08d", id)
	exit_label  = fmt.bprintf(buf_exit,  "ENDIF_%08d", id)
	return
}

generate_var_declaration_ir :: proc(progbuf: ^[dynamic]Instruction, static_data: ^StaticDataTable, scope: ^Scope, decl: VarDeclaration) -> (err: Error) {
	if len(decl.expressions) <= 0 { return }

	for rhs, i in decl.expressions {
		id := decl.identifiers[i]
		info, ok := search_symbol(scope, id)
		assert(ok, "Undefined symbol")
		append(progbuf, Instruction{
			opcode = .Push,
			label = info.static_section_name,
		})

		generate_expression_ir(progbuf, static_data, scope, rhs) or_return

		append(progbuf, Instruction{
			opcode = .Store,
		})

	}

	return
}

generate_assignment_ir :: proc(progbuf: ^[dynamic]Instruction, static_data: ^StaticDataTable, scope: ^Scope, assign: Assignment) -> (err: Error) {
	for lhs, i in assign.left_side {
		rhs := assign.right_side[i]
		left_expr := lhs

		#partial switch lhs in lhs.value {
		case Primary:
			id := lhs.(Identifier)
			info, ok := search_symbol(scope, id)
			assert(ok, "Undefined symbol")
			append(progbuf, Instruction{
				opcode = .Push,
				label = info.static_section_name,
			})

			generate_expression_ir(progbuf, static_data, scope, rhs) or_return

			append(progbuf, Instruction{
				opcode = .Store,
			})

		case Indexing:
			// To assign to an index, simply generate a load and replace it with a store
			generate_expression_ir(progbuf, static_data, scope, left_expr) or_return
			pop(progbuf) /* REMOVE TRAILING LOAD */

			generate_expression_ir(progbuf, static_data, scope, rhs) or_return

			append(progbuf, Instruction{
				opcode = .Store,
			})

		case:
			unreachable()
		}
	}
	return
}

@(private="file")
only_has_array :: proc(s: []Modifier) -> bool {
	for mod in s {
		mod, ok := mod.(Array)
		if !ok { return false }
	}
	return true
}

tail :: proc(s: []$T, n: int) -> []T {
	if n <= 0 {
		return nil
	}
	return s[len(s) - n:]
}

@(private="file")
generate_indexing_offset_calc :: proc(progbuf: ^[dynamic]Instruction, scope: ^Scope, val: Indexing){
	mods := val.object.type.modifiers
	mods = tail(mods, len(mods) - 1)
	stride := type_size(Type{
		primitive = val.object.type.primitive,
		modifiers = mods,
	})

	append(progbuf, Instruction{
		opcode = .Push,
		immediate = Word(stride),
	})
	append(progbuf, Instruction{
		opcode = .Mul,
	})
	append(progbuf, Instruction{
		opcode = .Add,
	})
}

@(require_results)
generate_expression_ir :: proc(
	progbuf: ^[dynamic]Instruction,
	static_data: ^StaticDataTable,
	scope: ^Scope,
	expr: ^Expression,
	emit_load_for_label := true,
) -> (err: Error){
	switch val in expr.value {
	case Binary:
		generate_expression_ir(progbuf, static_data, scope, val.left_side) or_return
		generate_expression_ir(progbuf, static_data, scope, val.right_side) or_return
		op, ok := OPCODE_BIN_MAP[val.operator]
		if !ok {
			err = emit_error(.UnknownOperator, "Unknown operator: %v", val.operator)
			return
		}
		append(progbuf, Instruction{ opcode = op })

	case Unary:
		op, ok := OPCODE_UNARY_MAP[val.operator]
		if !ok {
			err = emit_error(.UnknownOperator, "Unknown operator: %v", val.operator)
			return
		}

		generate_expression_ir(progbuf, static_data, scope, val.operand) or_return
		if op != .NoOp {
			append(progbuf, Instruction{ opcode = op })
		}



	case Indexing:
		next_to_primary := len(expr.type.modifiers) == 0

		generate_expression_ir(progbuf, static_data, scope, val.object, false) or_return
		generate_expression_ir(progbuf, static_data, scope, val.index) or_return

		generate_indexing_offset_calc(progbuf, scope, val)

		if next_to_primary {
			append(progbuf, Instruction{
				opcode = .Load,
			})
			// append(progbuf, Instruction{ opcode = .NoOp })
		}

	case FunctionCall:
		func_name := val.func.value.(Primary).(Identifier)
		sym, ok := search_symbol(scope, func_name)
		assert(ok, "Undefined Function")
		if builtin_function_name(func_name){
			for arg in val.args {
				generate_expression_ir(progbuf, static_data, scope, arg) or_return
			}
			append(progbuf, Instruction{
				opcode = .Call_Builtin,
				label = auto_cast func_name,
			})
		}
		else {
			unimplemented("Regular functions not supported")
		}


	case Primary:
		switch val in val {
		case Int:
			append(progbuf, Instruction{
				opcode = .Push,
				immediate = Word(val),
			})
		case Identifier:
			info, ok := search_symbol(scope, val)
			assert(ok, "Undefined symbol")
			append(progbuf, Instruction {
				opcode = .Push,
				label = info.static_section_name,
			})
			if emit_load_for_label {
				append(progbuf, Instruction {
					opcode = .Load,
				})
			}
		case String:
			n := len(static_data) + 1
			sb := strings.builder_make()
			mangled_name := fmt.sbprintf(&sb, "_S_lit_%08x", n)
			shrink(&sb.buf)
			static_data[mangled_name] = StaticData {
				size = len(val),
				align = .Byte,
				kind = .String_Literal,
				str_data = val,
			}
			append(progbuf, Instruction {
				opcode = .Push,
				label = mangled_name,
			})

		case Real: unimplemented()

		case Bool:
			append(progbuf, Instruction {
				opcode = .Push,
				immediate = Word(val),
			})

		case Rune: unimplemented()
		}
	case Group:
		generate_expression_ir(progbuf, static_data, scope, val.inner) or_return
	}

	return
}

type_size :: proc(t: Type) -> int {
	size := 0
	switch t.primitive.(BuiltinType) {
	case .Int: size = size_of(Word)
	case .Bool: size = size_of(Word) // Should have been just a byte, but I dont want to bother with alignment right now
	case .Real: size = size_of(Real)
	case .Rune: size = size_of(u32)

	// WARN: This should have been ptr + len, but it will be temporarilly downgraded to a null terminated one.
	case .String: size = size_of(Word)
	}

	for mod in t.modifiers {
		switch mod in mod {
		case Array: size = size * mod.size
		case Pointer: size = size + size_of(Word)
		}
	}

	return size
}

array_stride_factor :: proc(mods: []Modifier) -> int {
	assert(only_has_array(mods), "This function should only be used on array types")
	acc := 1

	for m in mods {
		#no_type_assert acc *= m.(Array).size
	}
	return acc
}

// Pretty format instruction for printing/debugging, uses temp_allocator
format_instruction :: proc(i: Instruction) -> string {
	op := fmt.tprint(i.opcode)
	imm : string
	if IMMEDIATE_OPS[i.opcode] {
		if len(i.label) > 0 {
			imm = fmt.tprint(i.label)
		}
		else {
			imm = fmt.tprint(i.immediate)
		}
	}


	return fmt.tprintf("%v %v", op, imm)
}

@(private) MAX_LABEL_LENGTH :: 32 // Enough to hold a keyword name + 16 digit hex number

@(private)
IMMEDIATE_OPS := map[Opcode]bool {
	.Push = true,
	.Call_Builtin = true,
}

@(private="file")
OPCODE_BIN_MAP := map[TokenKind]Opcode {
	.Plus = .Add,
	.Minus = .Sub,
	.Star = .Mul,
	.Slash = .Div,
	.Modulo = .Mod,

	.BitAnd = .And,
	.BitOr = .Or,
	.BitXor = .Xor,
	.ShiftLeft = .ShiftLeft,
	.ShiftRight = .ShiftRight,

	.EqualEqual = .Equal,
	.NotEqual = .NotEqual,
	.Greater = .Greater,
	.Lesser = .Lesser,
	.GreaterEqual = .GreaterEqual,
	.LesserEqual = .LesserEqual,
}
