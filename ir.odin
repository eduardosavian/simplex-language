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

VarMapping :: map[Identifier]int

// Push space for all variables in env
// Map each variable of env to a particular offset
map_scope_variables :: proc(scope: ^Scope) -> VarMapping {
	previous_offset := 0
	for id, info in scope.env {
		if info.kind != .Variable { continue }
		info.stack_offset += type_size(info.type)
	}
}

type_size :: proc(t: Type){
}
