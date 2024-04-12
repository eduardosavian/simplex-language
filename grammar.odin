//+private
package lang

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


