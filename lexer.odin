package lang

import unicode "core:unicode"
import utf "core:unicode/utf8"


Lexer :: struct {
	current: int,
	mark: int,
	source: []byte,

	using loc: Location,
}

Location :: struct {
	file: string,
	line: i32,
}

TokenPayload :: union {
	int, f64, string,
}

Token :: struct {
	kind: TokenKind,
	lexeme: string,
	payload: TokenPayload,
	loc: Location,
}

TokenKind :: enum i8 {
	Identifier = 1,
	// Reserved
	If, For, Fun, Type, Struct, Break, Continue, Return, 

	// Binary and unary operators
	Plus, Minus, Star, Slash, Modulo,

	ShiftLeft, ShiftRight, BitAnd, BitOr, BitXor,

	LogicAnd, LogicNot, LogicXor, LogicOr,

	Greater, Lesser, GreaterEqual, LesserEqual, NotEqual, EqualEqual,

	// Delimiters
	ParenOpen, ParenClose,
	CurlyOpen, CurlyClose,
	SquareOpen, SquareClose,
	Dot, Comma, Colon, Equal,

	// Literals
	True, False, Int, Real, String, Nil,

	// Error
	BadToken = -1,
}

lexer_advance :: proc(using lex: ^Lexer) -> (rune, int) {
	r, n := lexer_peek(lex)
	current += n
	if r == '\n' {
		line += 1
	}

	return r, n
}

lexer_peek_previous :: proc(using lex: ^Lexer) -> (rune, int) {
	_, cur_size := lexer_peek(lex)

	current -= cur_size
	defer current += cur_size

	r, n := lexer_peek(lex)
	return r, n
}

lexer_peek :: proc(using lex: ^Lexer) -> (rune, int) {
	if current >= len(source) { return 0, 0 }
	r, n := utf.decode_rune(source[current:])
	return r, n
}

lexer_peek_next :: proc(using lex: ^Lexer) -> (rune, int) {
	_, cur_size := lexer_peek(lex)

	current += cur_size
	defer current -= cur_size

	r, n := lexer_peek(lex)
	return r, n
}

import "core:fmt"

lexer_match_consume :: proc(using lex: ^Lexer, accept: ..rune) -> (rune, int, bool){
	r, n := lexer_peek(lex)
	for a in accept {
		if r == a {
			return r, n, true
		}
	}

	return 0, 0, false
}

tokenize :: proc(source: string, filename: string = "") -> []Token {
	tokens := make([dynamic]Token)

	lex := &Lexer {
		source = transmute([]byte)source,
		file = filename,
		line = 1,
	}

	for !lexer_end(lex) {
		r, n := lexer_advance(lex)

		switch r {
		// Ignore whitespace
		case '\n', '\t', ' ', '\r': continue

		case '+': append(&tokens, Token{kind = .Plus})
		case '-': append(&tokens, Token{kind = .Minus})
		case '*': append(&tokens, Token{kind = .Star})
		case '%': append(&tokens, Token{kind = .Modulo})
		case '/':
			switch r, _ := lexer_peek(lex); r {
			case '/':
				lexer_advance(lex)
				unimplemented("Lex comment")
			case:
				append(&tokens, Token{kind = .Slash})
			}

		case '&':
			if _, _, ok := lexer_match_consume(lex, '&'); ok {
				append(&tokens, Token{kind = .LogicAnd})
			}
			else {
				append(&tokens, Token{kind = .BitAnd})
			}

		case '|':
			if _, _, ok := lexer_match_consume(lex, '|'); ok {
				append(&tokens, Token{kind = .LogicOr})
			}
			else {
				append(&tokens, Token{kind = .BitOr})
			}

		case '~':
			if _, _, ok := lexer_match_consume(lex, '~'); ok {
				append(&tokens, Token{kind = .LogicXor})
			}
			else {
				append(&tokens, Token{kind = .BitXor})
			}

		case '>':
			if _, _, ok := lexer_match_consume(lex, '>'); ok {
				append(&tokens, Token{kind = .ShiftRight})
			}
			else if _, _, ok := lexer_match_consume(lex, '='); ok {
				append(&tokens, Token{kind = .GreaterEqual})
			}
			else {
				append(&tokens, Token{kind = .Greater})
			}

		case '<':
			if _, _, ok := lexer_match_consume(lex, '<'); ok {
				append(&tokens, Token{kind = .ShiftLeft})
			}
			else if _, _, ok := lexer_match_consume(lex, '='); ok {
				append(&tokens, Token{kind = .LesserEqual})
			}
			else {
				append(&tokens, Token{kind = .Lesser})
			}

		case '=':
			if _, _, ok := lexer_match_consume(lex, '='); ok {
				append(&tokens, Token{kind = .EqualEqual})
			}
			else {
				append(&tokens, Token{kind = .Equal})
			}

		case '!':
			if _, _, ok := lexer_match_consume(lex, '='); ok {
				append(&tokens, Token{kind = .NotEqual})
			}
			else {
				append(&tokens, Token{kind = .LogicNot})
			}

		case '"':
			append(&tokens, tokenize_string(lex))

		case '\'':
			unimplemented("Tokenize rune")

		case:
			switch {
			case is_digit(r):
				lex.current -= n
				append(&tokens, tokenize_number(lex))

			case is_identifier(r):
				lex.current -= n
				append(&tokens, tokenize_identifier(lex))
			}
		}
	}

	resize(&tokens, len(tokens))
	return tokens[:]
}

tokenize_number :: proc(using lex: ^Lexer) -> Token {
	unimplemented()
}

tokenize_identifier :: proc(using lex: ^Lexer) -> Token {
	unimplemented()
}

escape_sequence :: proc(r: rune) -> (rune, bool) {
	val := rune{}
	ok := false

	switch r {
	case 'e':  val, ok = '\e', true
	case 'n':  val, ok = '\n', true
	case 't':  val, ok = '\t', true
	case 'r':  val, ok = '\r', true
	case '"':  val, ok = '"',  true
	case '\'': val, ok = '\'', true
	case '\\': val, ok = '\\', true
	}

	return val, ok
}

tokenize_string :: proc(using lex: ^Lexer) -> Token {
	mark = current

	buf := make([dynamic]byte)

	for !lexer_end(lex) {
		r, n := lexer_advance(lex)
		if r == '\\' {
			next, _ := lexer_advance(lex)
			fmt.println("NEXT: ", next)
			if esc, ok := escape_sequence(next); ok {
				append_encoded(&buf, esc)
			}
			else {
				panic("Invalid escape sequence")
			}
		}
		else if r == '"' {
			break
		}
		else {
			append_encoded(&buf, r)
		}
	}

	resize(&buf, len(buf))

	text := string(buf[:])
	tk := Token {
		lexeme = string(source[mark-1:current+1]),
		kind = .String,
		payload = text,
		loc = loc,
	}

	return tk
}

append_encoded :: proc(arr: ^[dynamic]byte, r: rune){
	bytes, size := utf.encode_rune(r)
	append(arr, ..bytes[:size])
}

lexer_end :: proc(using lex: ^Lexer) -> bool {
	return current >= len(source)
}

is_letter :: unicode.is_letter

is_digit :: unicode.is_digit

is_identifier :: proc(r: rune) -> bool {
	return is_letter(r) || r == '_';
}
