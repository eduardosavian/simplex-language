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
	case LexerError:   msg = "Lexing "
	case ParserError:  msg = "Syntax "
	case CheckerError: msg = "Semantic "
	case: msg = " "
	}
	return msg
}

emit_error :: proc(e: Error, format: string = "", args: ..any) -> Error {
	assert(e != nil, "Cannot emmit a nil error")
	if len(format) > 0 {
		f := fmt.tprintf("\e[1;31m%sError\e[0m: %s", error_name(e), format)
		fmt.printf(f, ..args)
	}
	else {
		fmt.printf("%s: %s", error_name(e), e)
	}
	return e
}

