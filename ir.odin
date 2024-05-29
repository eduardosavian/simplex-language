package lang
// TODO:
// - Numeric types
// - Strings
// - Arrays
// - Pointers
// - Branching
// - Functions

Word :: distinct Int

WORD_SIZE :: size_of(Word)

Opcode :: enum {
	Push, Pop,

	Add, Sub, Mul, Div, Mod,
	And, Or, Xor, Not,

	Store, Load,
	Store_Imm, Load_Imm,
}

Instruction :: struct {
	opcode: Opcode,
	immediate: Word,
}

Machine :: struct {
	stack: []Word,
	program: []Instruction,
	sp: int,
	ip: int,
}

// Push space for all variables in env
// Map each variable of env to a particular offset
map_scope_variables :: proc(scope: ^Scope) {
	previous_offset := 0
	for id in scope.env {
		info := scope.env[id]
		if info.kind != .Variable { continue }
		info.stack_offset += type_size(info.type)
		scope.env[id] = info
	}
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
		case Slice: size += 2 * size_of(Word)
		case Array: size *= mod.size
		case Pointer: size += size_of(Word)
		}
	}

	return size
}
