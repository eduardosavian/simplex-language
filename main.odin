package lang

import "core:fmt"
import "core:log"

SRC : string : #load("example.ki")

main :: proc() {
	// Setup Logger
	logger_options :: log.Options{.Short_File_Path, .Line, .Terminal_Color, .Level}
	lowest_level :: log.Level.Debug when ODIN_DEBUG else log.Level.Warning
	logger := log.create_console_logger(lowest_level, logger_options)
	defer log.destroy_console_logger(logger)
	context.logger = logger

	tokens, lex_ok := tokenize(SRC)
	defer delete(tokens)
	if !lex_ok {
		return
	}
	print_tokens(tokens)

	// TODO: Arena here
	exprs := parse(tokens)
	for e in exprs {
		print_expression(e)
		fmt.println()
	}
}


