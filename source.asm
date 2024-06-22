.data
__runtime.input_int: .string "(int) << "
__runtime.input_str: .string "(string) << "
__runtime.output: .string ">> "
__runtime.newline: .string "\n"

.align 2
n_i_eb0c3431: .space 4
.align 0
_S_lit_00000003: .string "Fizz"
.align 0
_S_lit_00000004: .string "Buzz"
.align 2
ok_i_2cb1a143: .space 4

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

	
	# Push n_i_eb0c3431
	la s0, n_i_eb0c3431
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
	FOR_00000001:
	
	# Push n_i_eb0c3431
	la s0, n_i_eb0c3431
	addi sp, sp, -4
	sw s0, (sp)
	
	# Load
	lw s0, (sp)
	lw s1, (s0)
	sw s1, (sp)
	
	# Push 30
	li s0, 30
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
	
	# Push ok_i_2cb1a143
	la s0, ok_i_2cb1a143
	addi sp, sp, -4
	sw s0, (sp)
	
	# Push 0
	li s0, 0
	addi sp, sp, -4
	sw s0, (sp)
	
	# Store 
	lw s0, (sp)
	lw s1, 4(sp)
	sw s0, (s1)
	addi sp, sp, 8
	
	# Push n_i_eb0c3431
	la s0, n_i_eb0c3431
	addi sp, sp, -4
	sw s0, (sp)
	
	# Load
	lw s0, (sp)
	lw s1, (s0)
	sw s1, (sp)
	
	# Push 3
	li s0, 3
	addi sp, sp, -4
	sw s0, (sp)
	
	# Mod 
	lw s0, (sp)
	lw s1, 4(sp)
	rem s0, s1, s0
	addi sp, sp, 4
	sw s0, (sp)
	
	# Push 0
	li s0, 0
	addi sp, sp, -4
	sw s0, (sp)
	
	# Equal 
	lw s0, (sp)
	lw s1, 4(sp)
	xor s0, s1, s0
	seqz s0, s0
	addi sp, sp, 4
	sw s0, (sp)
	
	# BranchZero 
	lw s0, (sp)
	addi sp, sp, 4
	beqz s0, ENDIF_00000002
	IF_00000002:
	
	# Push _S_lit_00000003
	la s0, _S_lit_00000003
	addi sp, sp, -4
	sw s0, (sp)
	
	# Call_Builtin __write_string
	call __runtime.write_string
	
	# Push ok_i_2cb1a143
	la s0, ok_i_2cb1a143
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
	j ENDIF_00000002
	ENDIF_00000002:
	
	# Push n_i_eb0c3431
	la s0, n_i_eb0c3431
	addi sp, sp, -4
	sw s0, (sp)
	
	# Load
	lw s0, (sp)
	lw s1, (s0)
	sw s1, (sp)
	
	# Push 5
	li s0, 5
	addi sp, sp, -4
	sw s0, (sp)
	
	# Mod 
	lw s0, (sp)
	lw s1, 4(sp)
	rem s0, s1, s0
	addi sp, sp, 4
	sw s0, (sp)
	
	# Push 0
	li s0, 0
	addi sp, sp, -4
	sw s0, (sp)
	
	# Equal 
	lw s0, (sp)
	lw s1, 4(sp)
	xor s0, s1, s0
	seqz s0, s0
	addi sp, sp, 4
	sw s0, (sp)
	
	# BranchZero 
	lw s0, (sp)
	addi sp, sp, 4
	beqz s0, ENDIF_00000003
	IF_00000003:
	
	# Push _S_lit_00000004
	la s0, _S_lit_00000004
	addi sp, sp, -4
	sw s0, (sp)
	
	# Call_Builtin __write_string
	call __runtime.write_string
	
	# Push ok_i_2cb1a143
	la s0, ok_i_2cb1a143
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
	j ENDIF_00000003
	ENDIF_00000003:
	
	# Push ok_i_2cb1a143
	la s0, ok_i_2cb1a143
	addi sp, sp, -4
	sw s0, (sp)
	
	# Load
	lw s0, (sp)
	lw s1, (s0)
	sw s1, (sp)
	
	# Push 0
	li s0, 0
	addi sp, sp, -4
	sw s0, (sp)
	
	# Equal 
	lw s0, (sp)
	lw s1, 4(sp)
	xor s0, s1, s0
	seqz s0, s0
	addi sp, sp, 4
	sw s0, (sp)
	
	# BranchZero 
	lw s0, (sp)
	addi sp, sp, 4
	beqz s0, ENDIF_00000004
	IF_00000004:
	
	# Push n_i_eb0c3431
	la s0, n_i_eb0c3431
	addi sp, sp, -4
	sw s0, (sp)
	
	# Load
	lw s0, (sp)
	lw s1, (s0)
	sw s1, (sp)
	
	# Call_Builtin __write_int
	call __runtime.write_int
	j ENDIF_00000004
	ENDIF_00000004:
	
	# Call_Builtin __write_newline
	call __runtime.write_newline
	
	# Push n_i_eb0c3431
	la s0, n_i_eb0c3431
	addi sp, sp, -4
	sw s0, (sp)
	
	# Push n_i_eb0c3431
	la s0, n_i_eb0c3431
	addi sp, sp, -4
	sw s0, (sp)
	
	# Load
	lw s0, (sp)
	lw s1, (s0)
	sw s1, (sp)
	
	# Push 1
	li s0, 1
	addi sp, sp, -4
	sw s0, (sp)
	
	# Add 
	lw s0, (sp)
	lw s1, 4(sp)
	add s0, s1, s0
	addi sp, sp, 4
	sw s0, (sp)
	
	# Store 
	lw s0, (sp)
	lw s1, 4(sp)
	sw s0, (s1)
	addi sp, sp, 8
	j FOR_00000001
	ENDFOR_00000001:
	
