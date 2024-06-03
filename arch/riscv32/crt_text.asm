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

	call __runtime.write_newline

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
