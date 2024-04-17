//+private
package lang

import intr "base:intrinsics"
import "core:log"

Expression :: union {
	Binary,
	Unary,
	Primary,
	Group,
}

Binary :: struct {
	left_side: ^Expression,
	right_side: ^Expression,
	operator: TokenKind,
}

Unary :: struct {
	operand: ^Expression,
	operator: TokenKind,
}

Group :: struct {
	inner: ^Expression,
}


Primary :: union {
	Identifier,
	Integer,
	String,
	Real,
	Rune,
	Bool,
	NilType,
}

Identifier :: distinct string
NilType    :: struct {}
String     :: string
Integer    :: i64
Real       :: f64
Rune       :: rune
Bool       :: bool

Associativity :: enum i8 {
	Left, Right,
}

OperatorInfo :: struct {
	precedence: i16,
	associativity: Associativity,
}

OPERATOR_TABLE := map[TokenKind]OperatorInfo {
	.Plus   = {15, .Left},
	.Minus  = {15, .Left},
	.Star   = {20, .Left},
	.Slash  = {20, .Left},
	.Modulo = {20, .Left},

	.BitOr      = {15, .Left},
	.BitXor     = {15, .Left},
	.BitAnd     = {20, .Left},
	.ShiftLeft  = {20, .Left},
	.ShiftRight = {20, .Left},

	.EqualEqual   = {10, .Left},
	.NotEqual     = {10, .Left},
	.Greater      = {10, .Left},
	.GreaterEqual = {10, .Left},
	.Lesser       = {10, .Left},
	.LesserEqual  = {10, .Left},

	.LogicAnd = {8, .Left},
	.LogicOr  = {5, .Left},
	.LogicXor = {5, .Left},
}

parse_expression :: proc(using parser: ^Parser) -> ^Expression {
	e := parse_primary(parser)
	return parse_binary(parser, e, 0)
}

parse_binary :: proc(using parser: ^Parser, left: ^Expression, min_precedence: i16) -> ^Expression {
	left := left
	right : ^Expression
	lookahead := parser_peek(parser)
	op: Token

	for is_binary_operator(lookahead) && precedence(lookahead) >= min_precedence {
		op = lookahead
		_ = parser_advance(parser)
		right = parse_primary(parser)
		lookahead = parser_peek(parser)

		for is_binary_operator(lookahead) &&
			(precedence(lookahead) > precedence(op) ||
				(associativity(lookahead) == .Right && precedence(lookahead) == precedence(op)))
		{
			lookahead_prec_is_greater := precedence(lookahead) > precedence(op)
			right = parse_binary(parser, right, precedence(op) + i16(lookahead_prec_is_greater))
			lookahead = parser_peek(parser)
		}

		exp := new(Expression)
		exp^ = Binary {
			left_side = left,
			right_side = right,
			operator = op.kind,
		}
		left = exp
	}

	return left
}

parse_unary :: proc(using parser: ^Parser) -> ^Expression {
}

parse_primary :: proc(using parser: ^Parser) -> ^Expression {
	if tk, ok := parser_match_consume(parser, ..LITERAL_KINDS); ok {
		exp := new_literal(tk)
		return exp
	}

	if tk, ok := parser_match_consume(parser, .Identifier); ok {
		exp := new(Expression)
		id := Identifier(tk.lexeme)
		exp^ = Primary(id)
		return exp
	}

	if tk, ok := parser_match_consume(parser, .ParenOpen); ok {
		inner := parse_expression(parser)
		left, ok := parser_expect_consume(parser, .ParenClose)
		if !ok {
			panic("TODO: Parser sync()")
		}
		exp := new(Expression)
		exp^ = Group {
			inner = inner,
		}
		return exp
	}

	emit_error(.BadExpression, "Ill formed expression")
	return nil
}

associativity :: proc(tk: $T) -> Associativity {
	#assert(type_of(tk) == Token || type_of(tk) == TokenKind, "Not a operator type")
	key := tk.kind when T == Token else tk
	info, ok := OPERATOR_TABLE[key]
	log.debug(key)
	assert(ok, "Operator not in table")
	return info.associativity
}

precedence :: proc(tk: $T) -> i16 {
	#assert(type_of(tk) == Token || type_of(tk) == TokenKind, "Not a operator type")
	key := tk.kind when T == Token else tk
	info, ok := OPERATOR_TABLE[key]
	assert(ok, "Operator not in table")
	return info.precedence
}

is_binary_operator :: proc(tk: $T) -> bool {
	#assert(type_of(tk) == Token || type_of(tk) == TokenKind, "Not a operator type")
	key := tk.kind when T == Token else tk
	_, ok := OPERATOR_TABLE[key]
	return ok
}
