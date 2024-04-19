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

InlineStatement :: union {
	ExpressionStatement,
	VarDeclaration,
	Assignment,
	Return,
	Break,
	Continue,
}


Scope :: struct {
	body: []Statement,
}

If :: struct {
	condition: ^Expression,
	else_branch: ^Statement, // NOTE: Can only be Scope or If

	using scope: Scope,
}

For :: struct {
	condition: ^Expression,
	pre_stmt: ^Statement,
	post_stmt: ^Statement,

	using scope: Scope,
}

ExpressionStatement :: distinct ^Expression

Break :: struct {}

Continue :: struct {}

Return :: struct {
	value: ^Expression,
}

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
	left_side: []^Expression,
	right_side: []^Expression,
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

is_top_level_statement :: proc(stmt: Statement) -> bool {
	v := false
	switch body in stmt {
	case If, For, Scope: v = false
	case InlineStatement:
		switch _ in body {
		case Break, Continue, Return, Assignment, ExpressionStatement: v = false
		case VarDeclaration: v = true;
		}
	}
	return v
}

// parse_file :: proc(parser: ^Parser, tokens: []Token) -> (program: []Statement, err: Error) {
// 	statements := make([dynamic]Statement)
// 	for !parser_end(parser^){
// 	}
// }

// true: Complex for
// false: Simple for
@private
disambiguate_for_loop_type :: proc(parser: ^Parser) -> bool {
	restore := parser.current
	defer parser.current = restore

	for !parser_end(parser^){
		tk := parser_advance(parser)
		if tk.kind == .Semicolon {
			return true
		}
		if tk.kind == .CurlyOpen {
			return false
		}
	}
	return false
}

parse_for_block :: proc(parser: ^Parser) -> (statement: Statement, err: Error){
	// complex_for := disambiguate_for_loop_type(parser)
	//
	// if complex_for {
	// 	pre := parse_inline_statement(parser) or_return
	// }
	// else {
	// }
	unimplemented()
}

parse_inline_statement :: proc(parser: ^Parser, force_semicolon := true) -> (statement: Statement, err: Error){
	if tk, ok := parser_match_consume(parser, .Var); ok {
		#partial switch tk.kind {
		case .Var:
			statement = InlineStatement(parse_var_declaration(parser) or_return)
		}
		if _, ok := parser_expect_consume(parser, .Semicolon); !ok {
			err = .NoExpectedToken
		}
		return
	}

	if tk, ok := parser_match_consume(parser, .Break); ok{
		statement = InlineStatement(Break{})
		return
	}

	if tk, ok := parser_match_consume(parser, .Continue); ok{
		statement = InlineStatement(Continue{})
		return
	}

	// Expression statement
	if disambiguate_assignment_from_expression_statement(parser) {
		assign := parse_assignment(parser) or_return
		statement = InlineStatement(assign)
	}
	else {
		expr := ExpressionStatement(parse_expression(parser) or_return)
		statement = InlineStatement(expr)
	}

	if force_semicolon {
		if _, ok := parser_expect_consume(parser, .Semicolon); !ok {
			err = .NoExpectedToken
			return
		}
	}

	return
}

parse_if_block :: proc(parser: ^Parser) -> (statement: Statement, err: Error){
	cond := parse_expression(parser) or_return
	scope := parse_scope(parser) or_return

	if_block := If {
		condition = cond,
		scope = scope,
	}

	if _, ok := parser_match_consume(parser, .Else); ok{
		if _, ok := parser_match_consume(parser, .If); ok {
			tmp := parse_if_block(parser) or_return
			if_block.else_branch = new(Statement)
			if_block.else_branch^ = tmp
		}
		else {
			tmp := parse_scope(parser) or_return
			if_block.else_branch = new(Statement)
			if_block.else_branch^ = tmp
		}
	}

	statement = if_block

	return
}

parse_scope :: proc(parser: ^Parser) -> (scope: Scope, err: Error) {
	// "{" statement* "}"
	if _, ok := parser_expect_consume(parser, .CurlyOpen); !ok {
		err = .NoExpectedToken
		return
	}

	statements := make([dynamic]Statement)
	closed := false

	for !parser_end(parser^){
		stmt : Statement

		// Close current scope
		if _, ok := parser_match_consume(parser, .CurlyClose); ok {
			closed = true
			break
		}

		// New Sub-Scope
		if _, ok := parser_match_consume(parser, .CurlyOpen); ok {
			stmt = parse_scope(parser) or_return
			append(&statements)
			continue
		}

		// If
		if _, ok := parser_match_consume(parser, .If); ok {
			stmt = parse_if_block(parser) or_return
			append(&statements, stmt)
			continue
		}

		// Inline statement
		{
			stmt = parse_inline_statement(parser) or_return
			append(&statements, stmt)
		}
	}

	if !closed {
		err = emit_error(.NoExpectedToken, "Unclosed scope.")
		return
	}

	resize(&statements, len(statements))

	scope = Scope {
		body = statements[:],
	}
	return
}

// True: Assignment
// False: ExpressionStatement
disambiguate_assignment_from_expression_statement :: proc(parser: ^Parser) -> bool {
	restore := parser.current
	defer parser.current = restore

	for !parser_end(parser^) {
		tk := parser_advance(parser)
		if tk.kind == .Equal {
			return true
		}
		if tk.kind == .Semicolon {
			return false
		}
	}
	return false
}

parse_assignment :: proc(parser: ^Parser) -> (assignment: Assignment, err: Error) {
	// exprList "=" exprList
	left_values := parse_expression_list(parser) or_return

	if _, ok := parser_expect_consume(parser, .Equal); !ok {
		err = .NoExpectedToken
		return
	}

	right_values := parse_expression_list(parser) or_return

	if len(right_values) != len(left_values) {
		err = emit_error(.SizeMismatch, "Mismatched sizes for assignment %v = %v", len(right_values), len(left_values))
		return
	}

	assignment = Assignment {
		left_side = left_values,
		right_side = right_values,
	}
	return
}

parse_var_declaration :: proc(parser: ^Parser) -> (declaration: VarDeclaration, err: Error) {
	// idList: typeExpr ("=" exprList)?
	ids := parse_identifier_list(parser) or_return
	type: TypeExpression
	exprs: []^Expression

	if _, ok := parser_expect_consume(parser, .Colon); ok {
		type = parse_type_expression(parser) or_return
	}
	else {
		err = .NoExpectedToken
		return
	}

	has_assign := false
	if _, ok := parser_match_consume(parser, .Equal); ok {
		has_assign = true
		exprs = parse_expression_list(parser) or_return
	}

	if has_assign && len(exprs) != len(ids){
		err = emit_error(.SizeMismatch, "Mismatched sizes for assignment: %v = %v", len(ids), len(exprs))
		return
	}

	declaration = VarDeclaration {
		identifiers = ids,
		type = type,
		expressions = exprs,
	}

	return
}

parse_type_expression :: proc(parser: ^Parser) -> (type_expr: TypeExpression, err: Error) {
	// ("[]" | "^")* id
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
			err = emit_error(.UnexpectedToken, "Expected '^', '[]', or identifier in type expression")
			return
		}
	}
	resize(&qualifiers, len(qualifiers))

	type_expr = TypeExpression {
		name = name,
		qualifiers = qualifiers[:],
	}
	return
}

parse_identifier_list :: proc(parser: ^Parser) -> (list: []Identifier, err: Error) {
	ids := make([dynamic]Identifier)

	if tk, ok := parser_expect_consume(parser, .Identifier); ok {
		append(&ids, Identifier(tk.lexeme))
	}
	else {
		err = .NoExpectedToken
		return
	}

	for !parser_end(parser^){
		if _, ok := parser_match_consume(parser, .Comma); ok {
			if id, ok := parser_expect_consume(parser, .Identifier); ok {
				append(&ids, Identifier(id.lexeme))
			}
			else {
				err = .NoExpectedToken
				return
			}
		}
		else {
			break
		}
	}

	resize(&ids, len(ids))
	list = ids[:]
	return
}

parse_expression_list :: proc(parser: ^Parser, allow_trailing_on : Maybe(TokenKind) = nil) -> (list: []^Expression, err: Error) {
	exprs := make([dynamic]^Expression)

	// NOTE: An expression list must have at least one element
	leading := parse_expression(parser) or_return
	append(&exprs, leading)

	for !parser_end(parser^){
		if _, ok := parser_match_consume(parser, .Comma); ok {
			if parser_peek(parser).kind == allow_trailing_on {
				break
			}
			else {
				exp := parse_expression(parser) or_return
				append(&exprs, exp)
			}
		}
		else {
			// Nothing more to parse
			break
		}
	}

	resize(&exprs, len(exprs))

	list = exprs[:]
	return
}

parse_expression :: proc(parser: ^Parser) -> (expression: ^Expression, err: Error) {
	e := parse_unary(parser) or_return
	expression = parse_binary(parser, e, 0) or_return
	return
}

parse_indexing :: proc(parser: ^Parser, object: ^Expression) -> (expression: ^Expression, err:Error) {
	// expr "[" expr "]"
	index := parse_expression(parser) or_return
	if _, ok := parser_expect_consume(parser, .SquareClose); !ok {
		err = .NoExpectedToken
		return
	}

	expression = new(Expression)
	expression^ = Indexing {
		object = object,
		index = index,
	}

	return
}

parse_function_call :: proc(parser: ^Parser, func: ^Expression) -> (expression: ^Expression, err: Error) {
	args: []^Expression

	if parser_peek(parser).kind != .ParenClose {
		args = parse_expression_list(parser, allow_trailing_on = .ParenClose) or_return
		if _, ok := parser_expect_consume(parser, .ParenClose); !ok {
			err = emit_error(.NoExpectedToken, "Expected ')'")
			return
		}
	}
	else {
		// Empty function call
		parser_advance(parser)
	}

	expression = new(Expression)
	expression^ = FunctionCall {
		func = func,
		args = args,
	}
	return
}

parse_binary :: proc(parser: ^Parser, left: ^Expression, min_precedence: i16) -> (expression: ^Expression, err: Error) {
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
			left = parse_function_call(parser, left) or_return
			parsed_func = true
		}
		else if op.kind == .SquareOpen {
			left = parse_indexing(parser, left) or_return
			parsed_index = true
		}
		else {
			right = parse_unary(parser) or_return
		}

		lookahead = parser_peek(parser)

		for is_binary_operator(lookahead) &&
			(precedence(lookahead) > precedence(op) ||
				(associativity(lookahead) == .Right && precedence(lookahead) == precedence(op)))
		{
			lookahead_prec_is_greater := precedence(lookahead) > precedence(op)
			right     = parse_binary(parser, right, precedence(op) + i16(lookahead_prec_is_greater)) or_return
			lookahead = parser_peek(parser)
		}

		if parsed_func || parsed_index {
			// No need to create a binary op
			expression = left
			continue
		}
		else {
			expression = new(Expression)
			expression^ = Binary {
				left_side = left,
				right_side = right,
				operator = op.kind,
			}
			left = expression
		}
	}
	expression = left
	return
}

parse_unary :: proc(parser: ^Parser) -> (expression: ^Expression, err: Error) {
	if tk, ok := parser_match_consume(parser, .BitXor, .Minus, .Plus, .LogicNot); ok {
		operator := tk
		operand  := parse_unary(parser) or_return

		expression = new(Expression)
		expression^ = Unary {
			operator = operator.kind,
			operand = operand,
		}
		return
	}

	expression = parse_primary(parser) or_return
	return
}

parse_primary :: proc(parser: ^Parser) -> (expression: ^Expression, err: Error) {
	if tk, ok := parser_match_consume(parser, ..LITERAL_KINDS); ok {
		expression = new_literal(tk)
		return
	}

	if tk, ok := parser_match_consume(parser, .Identifier); ok {
		next := parser_peek(parser, 0)
		id := Identifier(tk.lexeme)

		expression = new(Expression)
		expression^ = Primary(id)
		return
	}

	if tk, ok := parser_match_consume(parser, .ParenOpen); ok {
		inner := parse_expression(parser) or_return
		left, ok := parser_expect_consume(parser, .ParenClose)
		if !ok {
			panic("TODO: Parser sync()")
		}

		expression = new(Expression)
		expression^ = Group {
			inner = inner,
		}
		return
	}

	log.warn(parser_peek(parser, 0))
	err = emit_error(.BadExpression, "Ill formed expression")
	return
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

