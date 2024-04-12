//+private
package lang

import intr "base:intrinsics"

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

Primary :: struct {
	Identifier,
	Integer,
	Real,
}

Identifier :: distinct string
Integer    :: distinct int
Real       :: distinct f64

parse_expression :: proc(using parser: ^Parser) -> ^Expression {
	unimplemented()
}

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

parse_binary :: proc(using parser: ^Parser, left: ^Expression, min_precedence: i16) -> ^Expression {
	left := left
	lookahead := Token{}
	unimplemented()
}

associativity :: proc(tk: $T) -> Associativity {
	#assert(type_of(tk) == Token || type_of(tk) == TokenKind, "Not a operator type")
	key := tk.kind when T == Token else tk
	info, ok := OPERATOR_TABLE[key]
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
