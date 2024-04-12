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

parse :: proc(tokens : []Token) -> ^Expression {
	parser_tokens := make([dynamic]Token)
	defer delete(parser_tokens)

	filter_tokens: {
		for tk in tokens {
			if !should_ignore(tk){
				append(&parser_tokens, tk)
			}
		}
		resize(&parser_tokens, len(tokens))
	}

	parser := Parser {
		tokens = parser_tokens[:],
	}

	exp := parse_expression(&parser)
	return exp
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

parser_expect_consume :: proc(using parser: ^Parser, expect: TokenKind) -> (Token, bool){
	tk := parser_peek(parser, 0)
	if tk.kind == expect {
		parser_advance(parser)
		return tk, true
	}
	log.errorf("Expected: `%v` Found: `%v`", expect, tk.kind)
	panic("Parser error, do parser_sync() later.")
	// return tk, false
}

