package lang

import "core:fmt"
import str "core:strings"

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
	.Lesser = "slt",
	.Greater = "sgt",

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

		// Single instruction comparison
		case .Greater, .Lesser:
			op, ok := OPCODE_MAP[inst.opcode]
			assert(ok, "Unsupported instruction")
			fmt.sbprintf(&sb, COMPARISON1, comment, op)

		// Double instruction comparison
		case .GreaterEqual, .LesserEqual:
			// fmt.sbprintf(&sb, COMPARISON2, comment, op)
			unimplemented()

		case .Equal:
			fmt.sbprintf(&sb, COMPARISON_EQ, comment)

		case .NotEqual:
			fmt.sbprintf(&sb, COMPARISON_NEQ, comment)

		case .Load:
			fmt.sbprintf(&sb, LOAD)

		// Logic Operations
		case .LogicNot:
			// x ~ 1 -> !x for a 0/1 based representation
			fmt.sbprintf(&sb, LOGIC_UN_OP, comment, "xori", 1)

		case .Store:
			fmt.sbprintf(&sb, STORE, comment)

		case .Jump:
			fmt.sbprintf(&sb, "j %v\n", inst.label)

		case .Call_Builtin:
			fmt.sbprintf(&sb, "\n# %v\ncall %v\n", comment, rv32_builtin_function_to_crt_label(inst.label))

		case .Label:
			fmt.sbprintf(&sb, "%v:\n", inst.label)

		case .Call:
			unimplemented()

		case .BranchZero:
			fmt.sbprintf(&sb, BRANCH, comment, "beqz", "s0", inst.label)

		case .BranchNotZero:
			fmt.sbprintf(&sb, BRANCH, comment, "bnez", "s0", inst.label)

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

	shrink(&sb.buf)
	return string(sb.buf[:])
}

rv32_generate_data_section :: proc(table: StaticDataTable) -> string {
	sb := str.builder_make()


	for label, data in table {
		switch data.align {
		case .Word: 
			fmt.sbprintln(&sb, ".align 2")
		case .Byte:
			fmt.sbprintln(&sb, ".align 0")
		}

		switch data.kind {
		case .Variable:
			fmt.sbprintfln(&sb, "%v: .space %v", label, data.size)
		case .String_Literal:
			fmt.sbprintfln(&sb, "%v: .string %q", label, data.str_data)
		}

	}

	shrink(&sb.buf)
	return string(sb.buf[:])
}

rv32_builtin_function_to_crt_label :: proc(name: string) -> (label: string) {
	label, _ = str.replace(name, "__", "__runtime.", 1, allocator = context.temp_allocator)
	return
}


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
BRANCH :: `
# %v
lw s0, (sp)
addi sp, sp, 4
%v %v, %v
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

@(private="file")
COMPARISON1 :: ARITH_BIN_OP

@(private="file")
COMPARISON2 :: `
# %v
lw s0, (sp)
lw s1, 4(sp)
%v s2, s1, s0
xori s0, s0, 1
addi sp, sp, 4
sw s0, (sp)
`

@(private="file")
COMPARISON_EQ :: `
# %v
lw s0, (sp)
lw s1, 4(sp)
xor s0, s1, s0
seqz s0, s0
addi sp, sp, 4
sw s0, (sp)
`

@(private="file")
COMPARISON_NEQ :: `
# %v
lw s0, (sp)
lw s1, 4(sp)
sub s0, s1, s0
seqz s0, s0
xori s0, s0, 1
addi sp, sp, 4
sw s0, (sp)
`

@(private="file")
LOGIC_BIN_OP :: `
# %v
lw s0, (sp)
lw s1, 4(sp)
%v s0, s0, s1
addi sp, sp, 4
sw s0, (sp)
`

@(private="file")
LOGIC_UN_OP :: `
# %v
lw s0, (sp)
%v s0, s0, %v
sw s0, (sp)
`
