package lang

import "core:log"
import "core:fmt"

LexerError :: enum byte {
	BadStringLiteral,
	InvalidBase,
	UnknownRune,
	InvalidEscape,
	NoExpectedRune,
	ConversionError,
}

ParserError :: enum byte {
	NoExpectedToken,
	UnexpectedToken,
	BadExpression,
	SizeMismatch,
}

CheckerError :: enum byte {
	DisallowedOnToplevel,
	DisallowedOnInnerScope,
	Redefinition,
	NotDefined,
	NonCallable,
	NonIndexable,
	ArgMismatch,
	MismatchedTypes,
	NonAssignable,
}

Error :: union {
	LexerError, ParserError, CheckerError,
}

@(private="file")
error_name :: proc(e: Error) -> string {
	msg : string
	switch _ in e {
	case LexerError:   msg = "Lexing Error"
	case ParserError:  msg = "Syntax Error"
	case CheckerError: msg = "Semantic Error"
	case: msg = "Error"
	}
	return msg
}

emit_error :: proc(e: Error, format: string = "", args: ..any, loc := #caller_location) -> Error {
	assert(e != nil, "Cannot emmit a nil error")
	if len(format) > 0 {
		f := fmt.tprintf("%s: %s", error_name(e), format)
		log.errorf(f, ..args, location = loc)
	}
	else {
		log.errorf("%s: %s", error_name(e), e, location = loc)
	}
	return e
}

