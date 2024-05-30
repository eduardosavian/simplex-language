package lang

import "core:fmt"
import "core:log"
import "core:time"
import "core:mem"
import "core:os"

import cli "cli_parse"

COMPILER_MEM_POOL := [32 * mem.Megabyte]byte{}

help :: proc(){
	fmt.printfln("Usage: %s <FILE> <ACTION> [OPTION]", os.args[0])
	fmt.printfln("  Actions:")
	fmt.printfln("    lex      Only run the lexer")
	fmt.printfln("    parse    Only run the parser")
	fmt.printfln("    check    Only run the typechecker")
	fmt.printfln("    ir       Generate intermediate representation")
	fmt.printfln("  Options:")
	fmt.printfln("    -verbose    Be verbose")
	fmt.printfln("    -help       Display this help message")
}

@(private="file") verbose    := false
@(private="file") only_lex   := false
@(private="file") only_parse := false
@(private="file") only_check := false
@(private="file") only_ir    := false

main :: proc() {
	if len(os.args) < 3 {
		help()
		return
	}

	file := os.args[1]
	mode := os.args[2]

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
	case:
		fmt.println("Unknown mode: ", mode)
		return
	}

	source, ok := os.read_entire_file(file)
	if !ok {
		fmt.println("Failed to read file:", file)
		return
	}

	compiler_main(string(source))
}

compiler_main :: proc(source: string) -> (err: Error){
	// Setup arena
	compiler_arena: mem.Arena
	mem.arena_init(&compiler_arena, COMPILER_MEM_POOL[:])
	context.allocator = mem.arena_allocator(&compiler_arena)
	defer free_all(context.allocator)

	// Setup Logger
	logger_options :: log.Options{.Short_File_Path, .Line, .Terminal_Color, .Level}
	lowest_level :: log.Level.Debug when ODIN_DEBUG else log.Level.Info
	logger := log.create_console_logger(lowest_level, logger_options)
	defer log.destroy_console_logger(logger)
	context.logger = logger

	// Timers
	lex_time, parse_time, check_time: time.Duration
	defer log.info("Compiler took: ", check_time + parse_time + lex_time)

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
	generate_ir(&scope)
	if verbose {
		print_env(&scope, true)
	}

	if only_ir { return }

	return
}

