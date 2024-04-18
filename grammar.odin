//+private
package lang

import intr "base:intrinsics"
import "core:log"

Statement :: union {
	InlineStatement,
	If,
	For,
	Scope,
}

Scope :: struct {
	body: []Statement,
}

If :: struct {}

For :: struct {}

InlineStatement :: union {
	ExpressionStatement,
	VarDeclaration,
	Assignment,
	Break,
	Continue,
}

ExpressionStatement :: distinct ^Expression

Break :: struct {}

Continue :: struct {}

VarDeclaration :: struct {
	identifiers: []Identifier,
	type: TypeExpression,
	expressions: []^Expression, // NOTE: Only for decl+assign
}

TypeExpression :: struct {
	name: Identifier,
	qualifiers: []Qualifier,
}

Qualifier :: enum i8 {
	Slice,
	Pointer,
}

Assignment :: struct {
	left_side: []Expression,
	right_side: []Expression,
}

Expression :: union {
	Primary,
	Indexing,
	FunctionCall,
	Group,
	Unary,
	Binary,
}

Binary :: struct {
	left_side: ^Expression,
	right_side: ^Expression,
	operator: TokenKind,
}

FunctionCall :: struct {
	func: ^Expression,
	args: []^Expression,
}

Indexing :: struct {
	object: ^Expression,
	index: ^Expression,
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

// Parses a whole file into one "file scope"
parse_file :: parse_scope

parse_scope :: proc(using parser: ^Parser) -> Scope {
	statements := make([dynamic]Statement)

	for !parser_end(parser^){
		log.debug("FILE: ", parser.tokens[current])
		stmt : Statement
		// Inline statement
		if tk, ok := parser_match_consume(parser, .Var); ok {
			#partial switch tk.kind {
			case .Var:
				stmt = InlineStatement(parse_var_declaration(parser))
			}
			if _, ok := parser_expect_consume(parser, .Semicolon); !ok {
				return Scope{}
			}
		}
		else {
			expr := ExpressionStatement(parse_expression(parser))
			stmt = InlineStatement(expr)
			if _, ok := parser_expect_consume(parser, .Semicolon); !ok {
				return Scope{}
			}
		}
		append(&statements, stmt)
	}
	resize(&statements, len(statements))

	return Scope {
		body = statements[:],
	}
}

parse_var_declaration :: proc(using parser: ^Parser) -> VarDeclaration {
	// idList: typeExpr (= exprList);
	ids := parse_identifier_list(parser)
	type: TypeExpression
	exprs: []^Expression

	if _, ok := parser_expect_consume(parser, .Colon); ok {
		type = parse_type_expression(parser)
	}
	else {
		return VarDeclaration{}
	}

	has_assign := false
	if _, ok := parser_match_consume(parser, .Equal); ok {
		has_assign = true
		exprs = parse_expression_list(parser)
	}

	decl := VarDeclaration {
		identifiers = ids,
		type = type,
		expressions = exprs,
	}

	if has_assign && len(exprs) != len(ids){
		emit_error(.SizeMismatch, "Mismatched sizes for assignment: %v = %v", len(ids), len(exprs))
	}

	return decl
}

parse_type_expression :: proc(using parser: ^Parser) -> TypeExpression {
	qualifiers := make([dynamic]Qualifier)
	name: Identifier
	for !parser_end(parser^){
		tk := parser_advance(parser)
		if tk.kind == .SquareOpen {
			if _, ok := parser_expect_consume(parser, .SquareClose); ok {
				append(&qualifiers, Qualifier.Slice)
			}
		}
		else if tk.kind == .Caret {
			append(&qualifiers, Qualifier.Pointer)
		}
		else if tk.kind == .Identifier {
			name = Identifier(tk.lexeme)
			break
		}
		else {
			emit_error(.UnexpectedToken, "Expected '^', '[]', or identifier in type expression")
		}
	}
	resize(&qualifiers, len(qualifiers))

	exp := TypeExpression {
		name = name,
		qualifiers = qualifiers[:],
	}
	return exp
}

parse_identifier_list :: proc(using parser: ^Parser) -> []Identifier {
	ids := make([dynamic]Identifier)

	if tk, ok := parser_expect_consume(parser, .Identifier); ok {
		append(&ids, Identifier(tk.lexeme))
	}
	else {
		emit_error(.NoExpectedToken, "Expected identifier.")
		return nil
	}

	for !parser_end(parser^){
		if _, ok := parser_match_consume(parser, .Comma); ok {
			if id, ok := parser_expect_consume(parser, .Identifier); ok {
				append(&ids, Identifier(id.lexeme))
			}
			else {
				return nil
			}
		}
		else {
			break
		}
	}

	resize(&ids, len(ids))
	return ids[:]
}

parse_expression_list :: proc(using parser: ^Parser, allow_trailing_on : Maybe(TokenKind) = nil) -> []^Expression {
	exprs := make([dynamic]^Expression)
	// NOTE: An expression list must have at least one element
	leading := parse_expression(parser)
	append(&exprs, leading)

	for !parser_end(parser^){
		if _, ok := parser_match_consume(parser, .Comma); ok {
			if parser_peek(parser).kind == allow_trailing_on {
				break
			}
			else {
				exp := parse_expression(parser)
				append(&exprs, exp)
			}
		}
		else {
			// Nothing more to parse
			break
		}
	}

	resize(&exprs, len(exprs))
	return exprs[:]
}

parse_expression :: proc(using parser: ^Parser) -> ^Expression {
	e := parse_unary(parser)
	return parse_binary(parser, e, 0)
}

parse_indexing :: proc(using parser: ^Parser, object: ^Expression) -> ^Expression {

	index := parse_expression(parser)
	if _, ok := parser_expect_consume(parser, .SquareClose); !ok {
		return nil
	}

	exp := new(Expression)
	exp^ = Indexing {
		object = object,
		index = index,
	}
	return exp
}

parse_function_call :: proc(using parser: ^Parser, func: ^Expression) -> ^Expression {
	log.debug("FUN", parser_peek(parser, 0))
	args: []^Expression

	if parser_peek(parser).kind != .ParenClose {
		args = parse_expression_list(parser, allow_trailing_on = .ParenClose)
		if _, ok := parser_expect_consume(parser, .ParenClose); !ok {
			emit_error(.NoExpectedToken, "Expected ')'")
			return nil
		}
	}
	else {
		// Empty function call
		parser_advance(parser)
	}

	exp := new(Expression)
	exp^ = FunctionCall {
		func = func,
		args = args,
	}
	return exp
}

parse_binary :: proc(using parser: ^Parser, left: ^Expression, min_precedence: i16) -> ^Expression {
	left := left
	right : ^Expression
	lookahead := parser_peek(parser)
	op: Token

	for is_binary_operator(lookahead) && precedence(lookahead) >= min_precedence {
		parsed_func := false
		parsed_index := false

		op = lookahead
		_ = parser_advance(parser)

		if op.kind == .ParenOpen {
			left = parse_function_call(parser, left)
			parsed_func = true
		}
		else if op.kind == .SquareOpen {
			left = parse_indexing(parser, left)
			parsed_index = true
		}
		else {
			right = parse_unary(parser)
		}

		lookahead = parser_peek(parser)

		for is_binary_operator(lookahead) &&
			(precedence(lookahead) > precedence(op) ||
				(associativity(lookahead) == .Right && precedence(lookahead) == precedence(op)))
		{
			lookahead_prec_is_greater := precedence(lookahead) > precedence(op)
			right     = parse_binary(parser, right, precedence(op) + i16(lookahead_prec_is_greater))
			lookahead = parser_peek(parser)
		}

		if parsed_func || parsed_index {
			// No need to create a binary op
			continue
		}
		else {
			exp := new(Expression)
			exp^ = Binary {
				left_side = left,
				right_side = right,
				operator = op.kind,
			}
			left = exp
		}
	}

	return left
}

parse_unary :: proc(using parser: ^Parser) -> ^Expression {
	if tk, ok := parser_match_consume(parser, .BitXor, .Minus, .Plus, .LogicNot); ok {
		operator := tk
		operand  := parse_unary(parser)

		exp := new(Expression)
		exp^ = Unary {
			operator = operator.kind,
			operand = operand,
		}
		return exp
	}

	exp := parse_primary(parser)
	return exp
}

parse_primary :: proc(using parser: ^Parser) -> ^Expression {
	if tk, ok := parser_match_consume(parser, ..LITERAL_KINDS); ok {
		exp := new_literal(tk)
		return exp
	}

	if tk, ok := parser_match_consume(parser, .Identifier); ok {
		next := parser_peek(parser, 0)
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

	log.warn(parser_peek(parser, 0))
	emit_error(.BadExpression, "Ill formed expression")
	return nil
}

associativity :: proc(tk: $T) -> Associativity {
	#assert(type_of(tk) == Token || type_of(tk) == TokenKind, "Not a operator type")
	key := tk.kind when T == Token else tk
	info, ok := OPERATOR_TABLE[key]
	// TODO: Sync parser on unknown operator
	assert(ok, "Operator not in table")
	return info.associativity
}

precedence :: proc(tk: $T) -> i16 {
	#assert(type_of(tk) == Token || type_of(tk) == TokenKind, "Not a operator type")
	key := tk.kind when T == Token else tk
	info, ok := OPERATOR_TABLE[key]
	// TODO: Sync parser on unknown operator
	assert(ok, "Operator not in table")
	return info.precedence
}

is_binary_operator :: proc(tk: $T) -> bool {
	#assert(type_of(tk) == Token || type_of(tk) == TokenKind, "Not a operator type")
	key := tk.kind when T == Token else tk
	_, ok := OPERATOR_TABLE[key]
	return ok
}

Associativity :: enum i8 {
	Left, Right,
}

OperatorInfo :: struct {
	precedence: i16,
	associativity: Associativity,
}

OPERATOR_TABLE := map[TokenKind]OperatorInfo {
	.SquareOpen = {40, .Left}, // For indexing
	.ParenOpen = {30, .Left}, // For function calls

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

