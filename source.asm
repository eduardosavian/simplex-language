.data
__runtime.input_int: .string "(int) << "
__runtime.input_str: .string "(string) << "
__runtime.output: .string ">> "
__runtime.newline: .string "\n"

.align 2
i_i_ec0c35c4: .space 4
.align 0
_S_lit_00000002: .string " "
.align 0
_S_lit_00000003: .string "\nDone!"

.text
j __runtime.entry_point

# Write a single newline char to the console
__runtime.write_newline:
	addi sp, sp, -4 # Push ra to stack
	sw ra, (sp)

	li a7, 4
	la a0, __runtime.newline
	ecall

	lw ra, (sp) # Restore return address
	addi sp, sp, 4
	ret

# Write a null terminated string to console
__runtime.write_string:
	lw a0, (sp) # Pop argument into a0
	addi sp, sp, 4
	addi sp, sp, -4 # Push ra to stack
	sw ra, (sp)

	li a7, 4
	ecall

	lw ra, (sp) # Restore return address
	addi sp, sp, 4
	ret

# Write integer to console.
__runtime.write_int:
	lw a0, (sp) # Pop integer into a0 (arg for syscall)
	sw ra, (sp) # Push return address, for later usage

	li a7, 1  # Set system service ID
	ecall     # Perform the syscall

	lw ra, (sp)    # Restore return address by popping it off the stack
	addi sp, sp, 4
	ret

# Read integer from console, push result to stack
__runtime.read_int:
	addi sp, sp, -4 # Push return value to stack
	sw zero, (sp)
	addi sp, sp, -4 # Push ra to stack
	sw ra, (sp)

	addi sp, sp, -4             # Push input prompt
	la t0, __runtime.input_int
	sw t0, (sp)
	call __runtime.write_string # Print input prompt

	li a7, 5 # Issue readInt syscall
	ecall

	lw ra, (sp)    # Restore return address by popping it off the stack
	addi sp, sp, 4
	sw a0, (sp) # Set return value
	ret

# Program entry point
__runtime.entry_point:

	INITFOR_00000001:
	
	# Push i_i_ec0c35c4
	la s0, i_i_ec0c35c4
	addi sp, sp, -4
	sw s0, (sp)
	
	# Push 1
	li s0, 1
	addi sp, sp, -4
	sw s0, (sp)
	
	# Store 
	lw s0, (sp)
	lw s1, 4(sp)
	sw s0, (s1)
	addi sp, sp, 8
	
	# Push i_i_ec0c35c4
	la s0, i_i_ec0c35c4
	addi sp, sp, -4
	sw s0, (sp)
	
	# Load
	lw s0, (sp)
	lw s1, (s0)
	sw s1, (sp)
	
	# Push 100
	li s0, 100
	addi sp, sp, -4
	sw s0, (sp)
	
	# Lesser 
	lw s0, (sp)
	lw s1, 4(sp)
	slt s0, s1, s0
	addi sp, sp, 4
	sw s0, (sp)
	
	# BranchNotZero 
	lw s0, (sp)
	addi sp, sp, 4
	bnez s0, FORBODY_00000001
	FOR_00000001:
	
	# Push i_i_ec0c35c4
	la s0, i_i_ec0c35c4
	addi sp, sp, -4
	sw s0, (sp)
	
	# Push i_i_ec0c35c4
	la s0, i_i_ec0c35c4
	addi sp, sp, -4
	sw s0, (sp)
	
	# Load
	lw s0, (sp)
	lw s1, (s0)
	sw s1, (sp)
	
	# Push 2
	li s0, 2
	addi sp, sp, -4
	sw s0, (sp)
	
	# Mul 
	lw s0, (sp)
	lw s1, 4(sp)
	mul s0, s1, s0
	addi sp, sp, 4
	sw s0, (sp)
	
	# Store 
	lw s0, (sp)
	lw s1, 4(sp)
	sw s0, (s1)
	addi sp, sp, 8
	
	# Push i_i_ec0c35c4
	la s0, i_i_ec0c35c4
	addi sp, sp, -4
	sw s0, (sp)
	
	# Load
	lw s0, (sp)
	lw s1, (s0)
	sw s1, (sp)
	
	# Push 100
	li s0, 100
	addi sp, sp, -4
	sw s0, (sp)
	
	# Lesser 
	lw s0, (sp)
	lw s1, 4(sp)
	slt s0, s1, s0
	addi sp, sp, 4
	sw s0, (sp)
	
	# BranchZero 
	lw s0, (sp)
	addi sp, sp, 4
	beqz s0, ENDFOR_00000001
	FORBODY_00000001:
	
	# Push i_i_ec0c35c4
	la s0, i_i_ec0c35c4
	addi sp, sp, -4
	sw s0, (sp)
	
	# Load
	lw s0, (sp)
	lw s1, (s0)
	sw s1, (sp)
	
	# Call_Builtin __write_int
	call __runtime.write_int
	
	# Push _S_lit_00000002
	la s0, _S_lit_00000002
	addi sp, sp, -4
	sw s0, (sp)
	
	# Call_Builtin __write_string
	call __runtime.write_string
	j FOR_00000001
	ENDFOR_00000001:
	
	# Push _S_lit_00000003
	la s0, _S_lit_00000003
	addi sp, sp, -4
	sw s0, (sp)
	
	# Call_Builtin __write_string
	call __runtime.write_string
	
