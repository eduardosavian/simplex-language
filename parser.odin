package lang

import "core:log"

Parser :: struct {
	current: int,
	tokens: []Token,
}

should_ignore :: proc(tk: Token) -> bool {
	#partial switch tk.kind {
	case .Comment, .LineBreak: return true
	case: return false
	}
}

parse :: proc(tokens : []Token) -> Scope {
	parser_tokens := make([dynamic]Token)
	defer delete(parser_tokens)

	append(&parser_tokens, Token{kind = .CurlyOpen})
	filter_tokens: {
		for tk in tokens {
			if !should_ignore(tk){

				append(&parser_tokens, tk)
			}
		}
		resize(&parser_tokens, len(parser_tokens))
	}
	append(&parser_tokens, Token{kind = .CurlyClose})

	parser := Parser {
		tokens = parser_tokens[:],
	}

	s, err := parse_scope(&parser)
	if err != nil {
		log.errorf("Parser exited with error: %v", err)
		return Scope{}
	}
	return s
}

parser_end :: proc(using parser: Parser) -> bool {
	return current >= len(tokens)
}

parser_peek :: proc(using parser: ^Parser, delta: int = 0) -> Token {
	if delta == 0 {
		if current >= len(tokens) {
			return Token{kind = .EndOfFile}
		}
		return tokens[current]
	}
	else {
		current += delta
		tk := parser_peek(parser, 0)
		current -= delta
		return tk
	}
}

parser_advance :: proc(using parser: ^Parser) -> Token {
	tk := parser_peek(parser, 0)
	current = min(current + 1, len(tokens))
	return tk
}

parser_rewind :: proc(using parser: ^Parser, n: int){
	assert(n > 0, "Can only rewind parser by a positive integer")
	current -= n;
}

parser_match_consume :: proc(using parser: ^Parser, accept: ..TokenKind) -> (Token, bool) {
	tk := parser_peek(parser, 0)
	for kind in accept {
		if tk.kind == kind {
			parser_advance(parser)
			return tk, true
		}
	}
	return tk, false
}

parser_expect_consume :: proc(using parser: ^Parser, expect: TokenKind, loc := #caller_location) -> (Token, bool){
	tk := parser_peek(parser, 0)
	if tk.kind == expect {
		parser_advance(parser)
		return tk, true
	}
	emit_error(.NoExpectedToken, "Expected: `%v` Found: `%v`", expect, tk.kind, loc = loc)
	return tk, false
}

LITERAL_KINDS :: []TokenKind {.True, .False, .Nil, .Int, .Real, .Rune, .String}

new_literal :: proc(tk: Token) -> ^Expression {
	exp := new(Expression)

	p := Primary{}
	#partial switch tk.kind {
	case .True:   p = true
	case .False:  p = false
	case .Nil:    p = nil
	case .Int:    p = tk.payload.(i64)
	case .Real:   p = tk.payload.(f64)
	case .Rune:   p = tk.payload.(rune)
	case .String: p = tk.payload.(string)
	case: panic("Not a literal")
	}

	exp.value = p
	return exp
}


