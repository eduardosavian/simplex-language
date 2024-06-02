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
ARITH_BIN_OP :: `
# %v
lw s0, (sp)
lw s1, 4(sp)
%v s0, s1, s0
addi sp, sp, 4
sw s0, (sp)
`

@(private="file")
ARITH_UN_OP :: `
# %v
lw s0, (sp)
%v s0, s0
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
	.Mod = "rem",
	.ShiftLeft = "sll",
	.ShiftRight = "srl",
	.And = "and",
	.Or = "or",
	.Xor = "xor",

	.Not = "not",
}

RV32_CRT_DATA :: #load("arch/riscv32/crt_data.asm", string)

RV32_CRT_TEXT :: #load("arch/riscv32/crt_text.asm", string)

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

		case .Add, .Sub, .Mul, .Div, .Mod, .And, .Or, .Xor, .ShiftLeft, .ShiftRight:
			op, ok := OPCODE_MAP[inst.opcode]
			assert(ok, "Unsupported instruction")
			fmt.sbprintf(&sb, ARITH_BIN_OP, comment, op)

		case .Not, .Neg:
			op, ok := OPCODE_MAP[inst.opcode]
			assert(ok, "Unsupported instruction")
			fmt.sbprintf(&sb, ARITH_UN_OP, comment, op)

		case .Load:
			fmt.sbprintf(&sb, LOAD)

		case .Store:
			fmt.sbprintf(&sb, STORE, comment)

		case .Jump: unimplemented()

		case .Branch: unimplemented()

		case .Call_Builtin:
			fmt.sbprintf(&sb, "\n# %v\ncall %v\n", comment, rv32_builtin_function_to_crt_label(inst.label))

		case .Call: unimplemented()

		case .NoOp:
			fmt.sbprintf(&sb, "# NoOp")
			continue
		}
	}

	inject_at(&sb.buf, 0, '\t')
	#reverse for b, i in sb.buf {
		if b == '\n' {
			inject_at(&sb.buf, i + 1, '\t')
		}
	}

	resize(&sb.buf, len(sb.buf))
	return string(sb.buf[:])
}

rv32_generate_data_section :: proc(table: StaticDataTable) -> string {
	sb := str.builder_make()
	fmt.sbprintln(&sb, ".align 2 # Word alignment")
	for label, space in table {
		fmt.sbprintfln(&sb, "%v: .space %v", label, space)
	}
	resize(&sb.buf, len(sb.buf))
	return string(sb.buf[:])
}

rv32_builtin_function_to_crt_label :: proc(name: string) -> (label: string) {
	label, _ = str.replace(name, "__", "__runtime.", 1, allocator = context.temp_allocator)
	return
}

