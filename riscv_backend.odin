package lang

import "core:fmt"
import str "core:strings"

@(private="file")
PUSH_LABEL :: `
# %v
la s0, %v
addi sp, sp, -4
sw s0, (sp)
`

@(private="file")
POP_STACK :: `
# Pop
addi sp, sp, 4
`

@(private="file")
PUSH_IMMEDIATE :: `
# %v
li s0, %v
addi sp, sp, -4
sw s0, (sp)
`

@(private="file")
ARITH_OP :: `
# %v
lw s0, (sp)
lw s1, 4(sp)
%v s0, s0, s1
addi sp, sp, 4
sw s0, (sp)
`

@(private="file")
STORE :: `
# %v
lw s0, (sp)
lw s1, 4(sp)
sw s0, (s1)
addi sp, sp, 8
`

@(private="file")
LOAD :: `
# Load
lw s0, (sp)
lw s1, (s0)
sw s1, (sp)
`

OPCODE_MAP := map[Opcode]string {
	.Add = "add",
	.Sub = "sub",
	.Mul = "mul",
	.Div = "div",
	.ShiftLeft = "sll",
	.ShiftRight = "srl",
	.And = "and",
	.Or = "or",
	.Xor = "xor",
}

rv32_generate_text_section :: proc(prog: []Instruction) -> string {
	sb := str.builder_make()
	for inst in prog {
		defer free_all(context.temp_allocator)
		comment := format_instruction(inst)

		switch inst.opcode {
		case .Push:
			if len(inst.label) > 0 {
				fmt.sbprintf(&sb, PUSH_LABEL, comment, inst.label)
			}
			else {
				fmt.sbprintf(&sb, PUSH_IMMEDIATE, comment, inst.immediate)
			}

		case .Pop:
			fmt.sbprint(&sb, POP_STACK)

		case .Add, .Sub, .Mul, .Div, .Mod, .And, .Or, .Xor, .Not, .ShiftLeft, .ShiftRight:
			op, ok := OPCODE_MAP[inst.opcode]
			assert(ok, "Unsupported instruction")
			fmt.sbprintf(&sb, ARITH_OP, comment, op)

		case .Load:
			fmt.sbprintf(&sb, LOAD)

		case .Store:
			fmt.sbprintf(&sb, STORE, comment)

		case .NoOp:
			fmt.sbprintf(&sb, "# NoOp")
			continue
		}
	}
	resize(&sb.buf, len(sb.buf))
	return string(sb.buf[:])
}

rv32_generate_data_section :: proc(table: StaticDataTable) -> string {
	sb := str.builder_make()
	for label, space in table {
		fmt.sbprintfln(&sb, "%v: .space %v", label, space)
	}
	resize(&sb.buf, len(sb.buf))
	return string(sb.buf[:])
}

