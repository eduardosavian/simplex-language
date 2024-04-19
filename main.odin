package lang

import "core:fmt"
import "core:log"
import "core:time"

SRC : string : #load("example.ki")

main :: proc() {
	// Setup Logger
	logger_options :: log.Options{.Short_File_Path, .Line, .Terminal_Color, .Level}
	lowest_level :: log.Level.Debug when ODIN_DEBUG else log.Level.Warning
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

	print_tokens(tokens)
	log.info("Lexer took:", lex_time)

	// TODO: Arena here
	parse_begin := time.now()
	scope       := parse(tokens)
	parse_time  := time.since(parse_begin)

	print_scope(scope)
	log.info("Parser took:", parse_time)
}


