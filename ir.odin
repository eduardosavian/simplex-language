package lang
// TODO:
// - Numeric types
// - Strings
// - Arrays
// - Pointers
// - Branching
// - Functions

Word :: distinct Int

import "core:fmt"
import "core:hash"
import "core:strings"

WORD_SIZE :: size_of(Word)

Opcode :: enum {
	NoOp = 0,

	Push, Pop,

	Add, Sub, Mul, Div, Mod,
	And, Or, Xor, Not,

	Store, Load,
	Store_Imm, Load_Imm,
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
		case Pointer: append(&buf, 'P')
		}
	}

	p := t.primitive.(BuiltinType)
	switch p {
	case .Bool: append(&buf, 'b')
	case .Int: append(&buf, 'i')
	case .Real: append(&buf, 'f')
	case .Rune: append(&buf, 'c')
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
}

@(private="file")
OPCODE_UNARY_MAP := map[TokenKind]Opcode {
	.Plus = .NoOp,
	.Minus = .Sub,
}

generate_expression_ir :: proc(progbuf: ^[dynamic]Instruction, expr: ^Expression) -> (err: Error){
	switch val in expr.value {
	case Binary:
		generate_expression_ir(progbuf, val.left_side)
		generate_expression_ir(progbuf, val.right_side)
		op, ok := OPCODE_BIN_MAP[val.operator]
		if !ok {
			err = emit_error(.UnknownOperator, "Unknown operator: ", val.operator)
			return
		}
		append(progbuf, Instruction{ opcode = op })

	case Unary:
		op, ok := OPCODE_UNARY_MAP[val.operator]
		if !ok {
			err = emit_error(.UnknownOperator, "Unknown operator: ", val.operator)
			return
		}

		if op == .Sub {
			// Unary minus is the same as (0 - x)
			append(progbuf, Instruction{ opcode = .Push, immediate = Word(0)})
		}

		generate_expression_ir(progbuf, val.operand)

		append(progbuf, Instruction{ opcode = op })

	case Indexing: unimplemented()
	case FunctionCall: unimplemented()
	case Primary:
		switch val in val {
		case Int:
			append(progbuf, Instruction{
				opcode = .Push,
				immediate = Word(val),
			})
		case Identifier: unimplemented()
		case String: unimplemented()
		case Real: unimplemented()
		case Bool: unimplemented()
		case Rune: unimplemented()
		}
	case Group:
		generate_expression_ir(progbuf, val.inner)
	}

	return
}



type_size :: proc(t: Type) -> int {
	size := 0
	switch t.primitive.(BuiltinType) {
	case .Int: size = size_of(Word)
	case .Bool: size = size_of(Word) /* Should have been just a byte, but I dont want to bother with alignment right now */
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
