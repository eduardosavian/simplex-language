// TODO:
// - Strings
// - Arrays
// - Pointers
// - Branching
// - Functions
package lang

Word :: distinct Int

import "core:log"
import "core:fmt"
import "core:hash"
import "core:strings"

WORD_SIZE :: size_of(Word)

Opcode :: enum {
	NoOp = 0,

	Push, Pop,

	Add, Sub, Mul, Div, Mod,

	And, Or, Xor, Not,
	ShiftLeft, ShiftRight,

	Store, Load,
	Store_Imm, Load_Imm,

	Index,

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

generate_ir :: proc(root: ^Scope){
	mangle_names(root)
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
			resize(&sb.buf, len(sb.buf))
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

	resize(&buf, len(buf))
	return string(buf[:])
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
}

@(private="file")
OPCODE_UNARY_MAP := map[TokenKind]Opcode {
	.Plus = .NoOp,
	.Minus = .Sub,
	.BitXor = .Xor,
}

@(require_results)
generate_scope_ir :: proc(progbuf: ^[dynamic]Instruction, scope: ^Scope) -> (err: Error) {
	for &statement in scope.body {
		switch &statement in statement {
		case InlineStatement:
			switch inline_stmt in statement {
			case ExpressionStatement:
				generate_expression_ir(progbuf, scope, inline_stmt.inner) or_return
			case VarDeclaration:
				generate_var_declaration_ir(progbuf, scope, inline_stmt)
			case Assignment:
				generate_assignment_ir(progbuf, scope, inline_stmt)
			case Return: unimplemented()
			case Break: unimplemented()
			case Continue: unimplemented()
			}
		case Scope:
			generate_scope_ir(progbuf, &statement) or_return
		case If: unimplemented()
		case For: unimplemented()
		case FunctionDef: unimplemented()
		}
	}
	return
}

generate_var_declaration_ir :: proc(progbuf: ^[dynamic]Instruction, scope: ^Scope, decl: VarDeclaration) -> (err: Error) {
	if len(decl.expressions) <= 0 { return }

	for rhs, i in decl.expressions {
		id := decl.identifiers[i]
		info, ok := search_symbol(scope, id)
		assert(ok, "Undefined symbol")
		generate_expression_ir(progbuf, scope, rhs) or_return
		append(progbuf, Instruction{
			opcode = .Store_Imm,
			label = info.static_section_name,
		})
	}

	return
}

generate_assignment_ir :: proc(progbuf: ^[dynamic]Instruction, scope: ^Scope, assign: Assignment) -> (err: Error) {
	for lhs, i in assign.left_side {
		rhs := assign.right_side[i]

		#partial switch lhs in lhs.value {
		case Primary:
			id := lhs.(Identifier)
			info, ok := search_symbol(scope, id)
			assert(ok, "Undefined symbol")
			generate_expression_ir(progbuf, scope, rhs) or_return
			append(progbuf, Instruction{
				opcode = .Store_Imm,
				label = info.static_section_name,
			})

		case Indexing:
			// generate_expression_ir(progbuf, scope, lhs.object) or_return
			// generate_expression_ir(progbuf, scope, lhs.index) or_return
			// append(progbuf, Instruction{
			// 	opcode = .Index,
			// })

			// This means a base address is being indexed, note that the only
			// primary expression that can be indexed are identifiers.
			// Indexing in the rhs of assignment -> add a load instruction
			// Indexing in the lhs of assignment -> add a store instruction

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
generate_indexed_access :: proc(progbuf: ^[dynamic]Instruction, scope: ^Scope, indexing: Indexing) -> (err: Error){
	unimplemented()
}

@(require_results)
generate_expression_ir :: proc(progbuf: ^[dynamic]Instruction, scope: ^Scope, expr: ^Expression) -> (err: Error){
	switch val in expr.value {
	case Binary:
		generate_expression_ir(progbuf, scope, val.left_side) or_return
		generate_expression_ir(progbuf, scope, val.right_side) or_return
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

		if op == .Sub {
			append(progbuf, Instruction{ opcode = .Push, immediate = Word(0)})
		}

		generate_expression_ir(progbuf, scope, val.operand) or_return

		// NOTE: Unary xor == unary not
		if op == .Xor {
			append(progbuf, Instruction{ opcode = .Not })
		}
		else if op != .NoOp {
			append(progbuf, Instruction{ opcode = op })
		}

	case Indexing:
		log.debug("INDEXED T:", val.object.type)

		generate_expression_ir(progbuf, scope, val.object) or_return
		generate_expression_ir(progbuf, scope, val.index) or_return

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


		if len(expr.type.modifiers) == 0 {
			append(progbuf, Instruction{
				opcode = .Load,
			})
		}


	case FunctionCall: unimplemented()
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
				opcode = .Load_Imm,
				label = info.static_section_name,
			})
		case String: unimplemented()
		case Real: unimplemented()
		case Bool: unimplemented()
		case Rune: unimplemented()
		}
	case Group:
		generate_expression_ir(progbuf, scope, val.inner) or_return
	}

	return
}

type_size :: proc(t: Type) -> int {
	size := 0
	switch t.primitive.(BuiltinType) {
	case .Int: size = size_of(Word)
	case .Bool: size = size_of(Word) // Should have been just a byte, but I dont want to bother with alignment right now
	case .Real: size = size_of(Real)
	case .Rune: size = 4
	case .String: size = size_of(Word) * 2
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

