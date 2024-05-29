package lang

Word :: distinct Int

Opcode :: enum {
	Push, Pop,

	Add, Sub, Mul, Div, Mod,

	And, Or, Xor, Not,
}

Instruction :: struct {
	opcode: Opcode,
	immediate: Word,
}
