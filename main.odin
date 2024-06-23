package lang

import "core:fmt"
import "core:log"
import "core:time"
import "core:mem"
import "core:os"
import str "core:strings"

import cli "cli_parse"

COMPILER_MEM_POOL := [128 * mem.Megabyte]byte{}

help :: proc(){
	fmt.printfln("Usage: %s <ACTION> <FILE> [OPTION]", os.args[0])
	fmt.printfln("  Actions:")
	fmt.printfln("    lex            Only run the lexer")
	fmt.printfln("    parse          Only run the parser")
	fmt.printfln("    check          Only run the typechecker")
	fmt.printfln("    ir             Only generate intermediate representation")
	fmt.printfln("    compile        Compile to RISC-V 32bit assembly")
	fmt.printfln("  Options:")
	fmt.printfln("    -out:<FILE>    Write assembly to file")
	fmt.printfln("    -verbose       Be verbose")
	fmt.printfln("    -help          Display this help message")
}

@(private="file") verbose    := false
@(private="file") only_lex   := false
@(private="file") only_parse := false
@(private="file") only_check := false
@(private="file") only_ir    := false
@(private="file") out_file   := ""
@(private="file") src_data   := ""

main :: proc() {
	// Setup Logger
	logger_options :: log.Options{.Short_File_Path, .Line, .Terminal_Color, .Level}
	lowest_level :: log.Level.Debug when ODIN_DEBUG else log.Level.Warning
	logger := log.create_console_logger(lowest_level, logger_options)
	defer log.destroy_console_logger(logger)
	context.logger = logger

	if len(os.args) < 3 {
		help()
		return
	}

	mode := os.args[1]
	file := os.args[2]

	options := make([dynamic]cli.Flag)
	if len(os.args) > 3 {
		for arg in os.args[3:] {
			f, _ := cli.parse_flag(arg)
			append(&options, f)
		}
	}

	for opt in options {
		switch opt.key {
		case "help":
			help()
			return
		case "verbose":
			verbose = true
		case "out":
			if len(opt.value) == 0 {
				help()
				return
			}
			out_file = opt.value
		case:
			fmt.printfln("Unknown option: %q", opt.key)
			return
		}
	}

	switch mode {
	case "lex": only_lex = true
	case "parse": only_parse = true
	case "check": only_check = true
	case "ir": only_ir = true
	case "compile":
	case:
		fmt.println("Unknown mode: ", mode)
		return
	}

	source, ok := os.read_entire_file(file)
	if !ok {
		fmt.panicf("Failed to read file:", file)
	}
	src_data = string(source)

	compiler_main()
}

compiler_main :: proc() -> (err: Error){
	// Setup arena
	compiler_arena: mem.Arena
	mem.arena_init(&compiler_arena, COMPILER_MEM_POOL[:])
	context.allocator = mem.arena_allocator(&compiler_arena)
	defer free_all(context.allocator)

	source := src_data

	// Timers
	lex_time, parse_time, check_time, ir_time, asm_time: time.Duration
	// defer log.info("Compiler took: ", check_time + parse_time + lex_time)

	// Tokenize
	lex_begin := time.now()
	tokens, lex_ok := tokenize(source)
	if !lex_ok { return .OtherLexError }
	lex_time = time.since(lex_begin)
	if verbose {
		print_tokens(tokens)
	}
	log.info("Lexer took:", lex_time)
	if only_lex { return }

	// Parse
	parse_begin := time.now()
	scope := parse(tokens) or_return
	parse_time = time.since(parse_begin)
	if verbose {
		print_scope(scope)
	}
	log.info("Parser took:", parse_time)
	if only_parse { return }

	// Typecheck
	check_begin := time.now()
	check_ast(&scope) or_return
	check_time = time.since(check_begin)
	if verbose {
		print_env(&scope)
	}
	log.info("Type Checker took:", check_time)
	if only_check { return }

	// IR Gen
	ir_begin := time.now()
	prog, static_data, ir_error := generate_ir(&scope)
	ir_time = time.since(ir_begin)


	log.info("IR generation took:", ir_time)
	if verbose {
		print_ir(prog)
	}
	if only_ir { return }

	// Assembly generation
	asm_begin := time.now()
	asm_time = time.since(asm_begin)
	log.info("Assembly generation took:", asm_time)

	asm_builder := str.builder_make()
	fmt.sbprintln(&asm_builder, RV32_CRT_DATA)
	fmt.sbprintln(&asm_builder, rv32_generate_data_section(static_data))

	fmt.sbprintln(&asm_builder, RV32_CRT_TEXT)
	fmt.sbprintln(&asm_builder, rv32_generate_text_section(prog))
	shrink(&asm_builder.buf)

	assembly_source := string(asm_builder.buf[:])

	if len(out_file) > 0 {
		fd, errno := os.open(out_file, os.O_WRONLY | os.O_CREATE, 0o644)
		if errno < 0 {
			fmt.panicf("Failed to open file:", out_file)
		}
		defer os.close(fd)

		n, err := os.write(fd, transmute([]byte)assembly_source)
		if errno < 0 {
			fmt.panicf("Failed to write to file:", out_file)
		}

		fmt.printf("Wrote %vB to %v\n", n, out_file)
	}
	else {
		fmt.println(assembly_source)
	}

	return
}

