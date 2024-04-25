package lang

import "core:unicode"
import "core:strconv"
import utf "core:unicode/utf8"

// TODO: Prohibit multi line strings

Lexer :: struct {
	current: int,
	mark: int,
	source: []byte,
	error_count: int,

	using loc: Location,
}

Location :: struct {
	file: string,
	line: i32,
}

TokenPayload :: union {
	i64, f64, string, rune,
}

Token :: struct {
	kind: TokenKind,
	lexeme: string,
	payload: TokenPayload,
}

keywords := map[string]TokenKind {
	"if"       = .If,
	"else"     = .Else,
	"for"      = .For,
	"func"     = .Func,
	"type"     = .Type,
	"struct"   = .Struct,
	"break"    = .Break,
	"continue" = .Continue,
	"return"   = .Return,
}

TokenKind :: enum i8 {
	// Tokens which are not grammar significant, but are tracked for
	// better error messages and debugging.
	Comment = 0, LineBreak,

	// Id and reserved words
	Identifier, If, Else, For, Func, Type, Struct, Break, Continue, Return, 

	// Binary and unary operators
	Plus, Minus, Star, Slash, Modulo,

	ShiftLeft, ShiftRight, BitAnd, BitOr, BitXor,

	LogicAnd, LogicNot, LogicXor, LogicOr,

	Greater, Lesser, GreaterEqual, LesserEqual, NotEqual, EqualEqual,

	// Delimiters
	ParenOpen, ParenClose,
	CurlyOpen, CurlyClose,
	SquareOpen, SquareClose,
	Dot, Comma, Colon, Equal, Semicolon, Caret, Arrow,

	// Literals
	True, False, Int, Real, String, Rune, Nil,

	// Error & specials
	EndOfFile = -1,
	BadToken = -2,
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


lexer_match_consume :: proc(using lex: ^Lexer, accept: ..rune) -> (rune, int, bool){
	r, n := lexer_peek(lex)
	for a in accept {
		if r == a {
			lexer_advance(lex)
			return r, n, true
		}
	}

	return 0, 0, false
}

tokenize :: proc(source: string, filename: string = "") -> ([]Token, bool) {
	tokens := make([dynamic]Token)

	lex := &Lexer {
		source = transmute([]byte)source,
		file = filename,
		line = 1,
	}

	for !lexer_end(lex) {
		r, n := lexer_advance(lex)

		switch r {
		// Ignore whitespace, LineBreak is just for pretty printing
		case '\n': append_elem(&tokens, Token{kind = .LineBreak})
		case '\t', ' ', '\r': continue

		case '(': append_elem(&tokens, Token{kind = .ParenOpen})
		case ')': append_elem(&tokens, Token{kind = .ParenClose})
		case '[': append_elem(&tokens, Token{kind = .SquareOpen})
		case ']': append_elem(&tokens, Token{kind = .SquareClose})
		case '{': append_elem(&tokens, Token{kind = .CurlyOpen})
		case '}': append_elem(&tokens, Token{kind = .CurlyClose})

		case ':': append_elem(&tokens, Token{kind = .Colon})
		case ';': append_elem(&tokens, Token{kind = .Semicolon})
		case '.': append_elem(&tokens, Token{kind = .Dot})
		case ',': append_elem(&tokens, Token{kind = .Comma})
		case '^': append_elem(&tokens, Token{kind = .Caret})

		case '+': append_elem(&tokens, Token{kind = .Plus})
		case '-':
			switch r, _ := lexer_peek(lex); r {
			case '-':
				append_elem(&tokens, Token{kind = .Minus})
			case '>':
				append_elem(&tokens, Token{kind = .Arrow})
				lexer_advance(lex)
			}

		case '*': append_elem(&tokens, Token{kind = .Star})
		case '%': append_elem(&tokens, Token{kind = .Modulo})
		case '/':
			switch r, _ := lexer_peek(lex); r {
			case '/':
				lex.current -= n
				append_elem(&tokens, tokenize_line_comment(lex))
				append_elem(&tokens, Token{ kind = .LineBreak })
			case:
				append_elem(&tokens, Token{kind = .Slash})
			}

		case '&':
			if _, _, ok := lexer_match_consume(lex, '&'); ok {
				append_elem(&tokens, Token{kind = .LogicAnd})
			}
			else {
				append_elem(&tokens, Token{kind = .BitAnd})
			}

		case '|':
			if _, _, ok := lexer_match_consume(lex, '|'); ok {
				append_elem(&tokens, Token{kind = .LogicOr})
			}
			else {
				append_elem(&tokens, Token{kind = .BitOr})
			}

		case '~':
			if _, _, ok := lexer_match_consume(lex, '~'); ok {
				append_elem(&tokens, Token{kind = .LogicXor})
			}
			else {
				append_elem(&tokens, Token{kind = .BitXor})
			}

		case '>':
			if _, _, ok := lexer_match_consume(lex, '>'); ok {
				append_elem(&tokens, Token{kind = .ShiftRight})
			}
			else if _, _, ok := lexer_match_consume(lex, '='); ok {
				append_elem(&tokens, Token{kind = .GreaterEqual})
			}
			else {
				append_elem(&tokens, Token{kind = .Greater})
			}

		case '<':
			if _, _, ok := lexer_match_consume(lex, '<'); ok {
				append_elem(&tokens, Token{kind = .ShiftLeft})
			}
			else if _, _, ok := lexer_match_consume(lex, '='); ok {
				append_elem(&tokens, Token{kind = .LesserEqual})
			}
			else {
				append_elem(&tokens, Token{kind = .Lesser})
			}

		case '=':
			if _, _, ok := lexer_match_consume(lex, '='); ok {
				append_elem(&tokens, Token{kind = .EqualEqual})
			}
			else {
				append_elem(&tokens, Token{kind = .Equal})
			}

		case '!':
			if _, _, ok := lexer_match_consume(lex, '='); ok {
				append_elem(&tokens, Token{kind = .NotEqual})
			}
			else {
				append_elem(&tokens, Token{kind = .LogicNot})
			}

		case '"':
			append_elem(&tokens, tokenize_string(lex))

		case '\'':
			append_elem(&tokens, tokenize_rune(lex))

		case:
			switch {
			case is_digit(r):
				lex.current -= n
				append_elem(&tokens, tokenize_number(lex))

			case is_identifier(r, leading = true):
				lex.current -= n
				append_elem(&tokens, tokenize_identifier(lex))
			case:
				append_elem(&tokens, BAD_TOKEN)
				emit_error(.UnknownRune, "Unknown rune: %q", r)
			}
		}
	}

	// Just to make the parser's life a bit easier in some edge cases
	append_elem(&tokens, Token{kind = .LineBreak})

	resize(&tokens, len(tokens))
	return tokens[:], lex.error_count == 0
}

tokenize_line_comment :: proc(using lex: ^Lexer) -> Token {
	mark = current
	for !lexer_end(lex){
		r, _ := lexer_advance(lex)
		if r == '\n' {
			break
		}
	}

	lexeme := string(source[mark:current-1])
	return Token {
		kind = .Comment,
		lexeme = lexeme,
	}
}

tokenize_number :: proc(using lex: ^Lexer) -> Token {
	mark = current
	r, n := lexer_advance(lex)
	next, _ := lexer_peek(lex)
	if r == '0' && is_letter(next){
		base := 0
		switch next {
		case 'b': base = 2
		case 'o': base = 8
		case 'x': base = 16
		}
		lexer_advance(lex)
		return tokenize_prefixed_int(lex, base)
	}

	// Number is non prefixed, retract
	current -= n

	digits := make([dynamic]byte)
	defer delete(digits)
	
	found_decimal := false

	for !lexer_end(lex) {
		r, n := lexer_advance(lex)

		if is_digit_of_base(r, 10){
			append_elem_encoded(&digits, r)
		}
		else if r == '.' && !found_decimal {
			append_elem_encoded(&digits, r)
			found_decimal := true
		}
		else if r == '_' {
			continue
		}
		else {
			current -= n
			break
		}
	}

	lexeme := string(source[mark:current])

	if found_decimal {
		num, ok := strconv.parse_f64(string(digits[:]))
		if !ok {
			emit_error(.ConversionError, "Could not convert %q to numeric literal", string(digits[:]))
			return BAD_TOKEN
		}
		return Token {
			kind = .Real,
			lexeme = lexeme,
			payload = num,
		}
	}
	else {
		num, ok := strconv.parse_int(string(digits[:]), 10)
		if !ok {
			emit_error(.ConversionError, "Could not convert %q to numeric literal", string(digits[:]))
			return BAD_TOKEN
		}
		return Token {
			kind = .Int,
			lexeme = lexeme,
			payload = Int(num),
		}
	}
}

tokenize_prefixed_int :: proc(using lex: ^Lexer, base: int) -> Token {
	assert(base == 2 || base == 8 || base == 16, "Invalid base.")
	mark = current

	digits := make([dynamic]byte)
	defer delete(digits)

	for !lexer_end(lex) {
		r, n := lexer_advance(lex)
		// TODO: Better warnings against things such as `0b110012`
		if is_digit_of_base(r, base) {
			append_elem_encoded(&digits, r)
		}
		else if r == '_' {
			continue
		}
		else {
			current -= n
			break
		}
	}

	lexeme := string(source[mark-2:current])
	num, ok := strconv.parse_int(string(digits[:]), base)
	if !ok {
		emit_error(.ConversionError, "Could not convert %q to numeric literal", string(digits[:]))
		return BAD_TOKEN
	}
	tk := Token {
		kind = .Int,
		payload = i64(num),
		lexeme = lexeme,
	}
	return tk
}

tokenize_identifier :: proc(using lex: ^Lexer) -> Token {
	mark = current

	for !lexer_end(lex) {
		r, n := lexer_advance(lex)
		if !is_identifier(r, leading = false){
			current -= n
			break
		}
	}

	id := string(source[mark:current])

	kind, ok := keywords[id]

	tk := Token {
		kind = ok ? kind : .Identifier,
		lexeme = id,
	}

	return tk
}

escape_sequence :: proc(r: rune) -> (rune, bool) {
	val := rune{}
	ok := false

	switch r {
	case 'e':  val, ok = '\e', true
	case 'b':  val, ok = '\b', true
	case 'v':  val, ok = '\v', true
	case 'n':  val, ok = '\n', true
	case 't':  val, ok = '\t', true
	case 'r':  val, ok = '\r', true
	case '"':  val, ok = '"',  true
	case '\'': val, ok = '\'', true
	case '\\': val, ok = '\\', true
	}

	return val, ok
}

tokenize_rune :: proc(using lex: ^Lexer) -> Token {
	codepoint: rune
	if _, _, ok := lexer_match_consume(lex, '\\'); ok {
		r, n := lexer_advance(lex)
		esc, ok := escape_sequence(r)
		if !ok {
			emit_error(.InvalidEscape, "Invalid escape sequence %q", r)
			lex.error_count += 1
			return BAD_TOKEN
		}
		codepoint = esc
	}
	else {
		r, n := lexer_advance(lex)
		if r == '\n' || r == '\t' {
			lex.error_count += 1
			emit_error(.NoExpectedRune, "Cannot use newline or tab inside rune literal, use '\\n' or '\\t' instead.")
		}
		codepoint = r
	}

	if _, _, ok := lexer_consume_expect(lex, '\''); !ok {
			lex.error_count += 1
		return BAD_TOKEN
	}

	return Token {
		kind = .Rune,
		payload = codepoint,
	}
}

lexer_consume_expect :: proc(using lex: ^Lexer, expect: rune) -> (rune, int, bool) {
	r, n, ok := lexer_match_consume(lex, expect)
	if !ok {
		emit_error(LexerError.NoExpectedRune, "Expected %q, found %q", expect, r)
		return 0, 1, false
	}
	return r, n, ok
}

tokenize_string :: proc(using lex: ^Lexer) -> Token {
	mark = current

	buf := make([dynamic]byte)

	found_end := false
	for !lexer_end(lex) {
		r, n := lexer_advance(lex)
		if r == '\\' {
			next, _ := lexer_advance(lex)
			if esc, ok := escape_sequence(next); ok {
				append_elem_encoded(&buf, esc)
			}
			else {
				emit_error(.InvalidEscape, "Invalid escape sequence: %q", esc)
				return BAD_TOKEN
			}
		}
		else if r == '"' {
			found_end = true
			break
		}
		else if r == '\n' {
			break
		}
		else {
			append_elem_encoded(&buf, r)
		}
	}

	if !found_end {
		emit_error(.BadStringLiteral, "Unterminated string literal.")
		return BAD_TOKEN
	}

	resize(&buf, len(buf))

	text := string(buf[:])
	tk := Token {
		lexeme = string(source[mark-1:current+1]),
		kind = .String,
		payload = text,
	}

	return tk
}

append_elem_encoded :: proc(arr: ^[dynamic]byte, r: rune){
	bytes, size := utf.encode_rune(r)
	append_elems(arr, ..bytes[:size])
}

lexer_end :: proc(using lex: ^Lexer) -> bool {
	return current >= len(source)
}

is_letter :: unicode.is_letter

is_digit :: unicode.is_digit

is_whitespace :: unicode.is_white_space

@(private)
is_digit_of_base :: proc(r: rune, base: int) -> bool {
	switch base {
	case 2:
		return r == '0' || r == '1'
	case 8:
		return r >= '0' && r <= '7'
	case 10:
		return r >= '0' && r <= '9'
	case 16:
		return (r >= '0' && r <= '9') || (r >= 'a' && r <= 'f') || (r >= 'A' && r <= 'F')
	}

	return false
}

is_identifier :: proc(r: rune, leading := false) -> bool {
	// NOTE: We do not allow identifiers to start with a digit
	return is_letter(r) || (!leading && is_digit(r)) || r == '_'
}

BAD_TOKEN :: Token { kind = .BadToken }
