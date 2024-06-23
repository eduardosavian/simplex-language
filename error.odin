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
	OtherLexError,
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
	DisallowedOnForLoop,
	Redefinition,
	NotDefined,
	NonCallable,
	NonIndexable,
	NonAssignable,
	ArgMismatch,
	MismatchedTypes,
}

IRError :: enum byte {
	UnknownOperator,
}

Error :: union {
	LexerError, ParserError, CheckerError, IRError,
}

@(private="file")
error_name :: proc(e: Error) -> string {
	msg : string
	switch _ in e {
	case LexerError:   msg = "Lexing "
	case ParserError:  msg = "Syntax "
	case CheckerError: msg = "Semantic "
	case IRError: msg = "IR "
	case: msg = " "
	}
	return msg
}

emit_error :: proc(e: Error, format: string, args: ..any, loc := #caller_location) -> Error {
	assert(e != nil, "Cannot emmit a nil error")
	f := fmt.tprintf("[%v]\e[1;31m%sError\e[0m: %s", loc, error_name(e), format)
	fmt.printfln(f, ..args)
	return e
}

emit_warning :: proc(format: string, args: ..any){
	f := fmt.tprintf("\e[1;33mWarning\e[0m: %s", format)
	fmt.printfln(f, ..args)
}

