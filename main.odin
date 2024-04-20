package lang

import "core:fmt"
import "core:log"
import "core:time"
import "core:mem"

SRC : string : #load("example.ki")

PARSER_MEM_POOL := [16 * mem.Megabyte]byte{}

main :: proc() {
	parser_arena: mem.Arena
	mem.arena_init(&parser_arena, PARSER_MEM_POOL[:])

	// Setup Logger
	logger_options :: log.Options{.Short_File_Path, .Line, .Terminal_Color, .Level}
	lowest_level :: log.Level.Debug when ODIN_DEBUG else log.Level.Info
	logger := log.create_console_logger(lowest_level, logger_options)
	defer log.destroy_console_logger(logger)
	context.logger = logger

	lex_begin := time.now()
	tokens, lex_ok := tokenize(SRC)
	defer delete(tokens)
	if !lex_ok {
		return
	}
	lex_time := time.since(lex_begin)

	// print_tokens(tokens)
	log.info("Lexer took:", lex_time)

	scope: Scope
	{
		context.allocator = mem.arena_allocator(&parser_arena)
		parse_begin := time.now()
		scope       = parse(tokens)
		parse_time  := time.since(parse_begin)
		log.info("Parser took:", parse_time)
	}
	print_scope(scope)

	check_begin := time.now()
	err := check_ast(&scope)
	check_time  := time.since(check_begin)
	log.info("Type Checker took:", check_time)

}


