//+private
package lang

import intr "base:intrinsics"
import "core:log"

Statement :: union {
	InlineStatement,
	If,
	For,
	Scope,
	FunctionDef,
}

InlineStatement :: union {
	ExpressionStatement,
	VarDeclaration,
	Assignment,
	Return,
	Break,
	Continue,
}

Identifier :: distinct string
Int :: i64
Real :: f64
String :: string
Rune :: rune
Bool :: bool

// TypeDef :: struct {
// 	name: Identifier,
// 	what: ParserType,
// }

Scope :: struct {
	body: []Statement,

	parent: ^Scope,
	env: Environment,
}

If :: struct {
	condition: ^Expression,
	else_branch: ^Statement, // NOTE: Can only be Scope or If

	scope: Scope,
}

For :: struct {
	condition: ^Expression,
	pre_stmt:  ^Statement,
	post_stmt: ^Statement,

	scope: Scope,
}

ExpressionStatement :: distinct ^Expression

Break :: struct {}

Continue :: struct {}

Return :: struct {
	value: ^Expression,
}

FunctionDef :: struct {
	name: Identifier,
	args: []Field,
	return_type: ParserType,
	scope: Scope,
}

VarDeclaration :: struct {
	identifiers: []Identifier,
	type: ParserType,
	expressions: []^Expression, // NOTE: Only for decl+assign
}

// Can be the field of a struct or argument to a function
Field :: struct {
	name: Identifier,
	type: ParserType,
}

// A parser type has no useful semantic meaning, it is merely a way to make the
// subsequent typechecking easier
ParserType :: struct {
	name: Identifier,
	modifiers: []Modifier,
}

Modifier :: enum i8 {
	Slice,
	Pointer,
}

Assignment :: struct {
	left_side: []^Expression,
	right_side: []^Expression,
}

Expression :: struct {
	type: Type,
	value: union{
		Primary,
		Indexing,
		FunctionCall,
		Group,
		Unary,
		Binary,
	}
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
	Int,
	String,
	Real,
	Rune,
	Bool,
}

is_top_level_statement :: proc(stmt: Statement) -> bool {
	v := false
	switch body in stmt {
	case FunctionDef: v = true
	case If, For, Scope: v = false
	case InlineStatement:
		switch _ in body {
		case Break, Continue, Return, Assignment, ExpressionStatement: v = false
		case VarDeclaration: v = true;
		}
	}
	return v
}

look_for_var_declaration :: proc(parser: ^Parser) -> bool {
	restore := parser.current
	defer parser.current = restore

	for !parser_end(parser^){
		tk := parser_advance(parser)
		if tk.kind == .Colon {
			return true
		}
		if tk.kind == .Semicolon {
			break
		}
	}

	return false
}

// True: Complex for
// False: Simple for
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

parse_return :: proc(parser: ^Parser) -> (statement: Statement, err: Error){
	ret := Return {
		value = parse_expression(parser) or_return,
	}
	statement = InlineStatement(ret)
	return
}

parse_for_block :: proc(parser: ^Parser) -> (statement: Statement, err: Error){
	complex_for := disambiguate_for_loop_type(parser)

	if complex_for {
		pre := parse_inline_statement(parser) or_return
		condition := parse_expression(parser) or_return
		if _, ok := parser_expect_consume(parser, .Semicolon); !ok {
			err = .NoExpectedToken
			return
		}
		post := parse_inline_statement(parser, force_semicolon = false) or_return

		body := parse_scope(parser) or_return

		for_block := For {
			condition = condition,
			pre_stmt = new(Statement),
			post_stmt = new(Statement),
			scope = body,
		}
		for_block.pre_stmt^ = pre
		for_block.post_stmt^ = post

		statement = for_block
	}
	else {
		condition := parse_expression(parser) or_return
		body := parse_scope(parser) or_return
		for_block := For {
			condition = condition,
			scope = body,
		}

		statement = for_block
	}

	return
}

parse_function_definition :: proc(parser: ^Parser) -> (func: FunctionDef, err: Error){
	args: []Field
	return_type: ParserType

	name, ok := parser_expect_consume(parser, .Identifier)
	if !ok {
		err = emit_error(.NoExpectedToken, "Expected identifier")
		return
	}

	if tk, ok := parser_expect_consume(parser, .ParenOpen); !ok {
		err = emit_error(.UnexpectedToken, "Expected `(`, found %v", tk.kind)
		return
	}

	if parser_peek(parser).kind != .ParenClose {
		args = parse_field_list(parser, allow_trailing_on = .ParenClose) or_return
	}

	if tk, ok := parser_expect_consume(parser, .ParenClose); !ok {
		err = .NoExpectedToken
		return
	}

	if _, ok := parser_match_consume(parser, .Arrow); ok {
		return_type = parse_type(parser) or_return
	}
	else {
		return_type = ParserType{}
	}

	log.debug(parser.tokens[parser.current])
	body := parse_scope(parser) or_return

	func = FunctionDef {
		args = args,
		name = Identifier(name.lexeme),
		return_type = return_type,
		scope = body,
	}
	return
}

parse_inline_statement :: proc(parser: ^Parser, force_semicolon := true) -> (statement: Statement, err: Error){
	if tk, ok := parser_match_consume(parser, .Break); ok{
		statement = InlineStatement(Break{})
	}
	else if tk, ok := parser_match_consume(parser, .Continue); ok{
		statement = InlineStatement(Continue{})
	}
	else if tk, ok := parser_match_consume(parser, .Return); ok{
		statement = parse_return(parser) or_return
	}
	else if look_for_var_declaration(parser) {
		decl := parse_var_declaration(parser) or_return
		statement = InlineStatement(decl)
	}
	else if look_for_assignment(parser){
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

		// New Sub-Scope
		if parser_peek(parser, 0).kind == .CurlyOpen {
			log.debug("Parsing an anonynmous scope")
			stmt = parse_scope(parser) or_return
			log.debug("Anon:", stmt)
			append_elem(&statements, stmt)
			continue
		}

		// Close current scope
		if _, ok := parser_match_consume(parser, .CurlyClose); ok {
			closed = true
			break
		}

		// FunctionDef definition
		if _, ok := parser_match_consume(parser, .Func); ok {
			stmt = parse_function_definition(parser) or_return
			append_elem(&statements, stmt)
			continue
		}

		// If
		if _, ok := parser_match_consume(parser, .If); ok {
			stmt = parse_if_block(parser) or_return
			append_elem(&statements, stmt)
			continue
		}

		// For
		if _, ok := parser_match_consume(parser, .For); ok {
			stmt = parse_for_block(parser) or_return
			append_elem(&statements, stmt)
			continue
		}

		// Inline statement
		{
			stmt = parse_inline_statement(parser) or_return
			append_elem(&statements, stmt)
		}
	}

	if !closed {
		err = emit_error(.NoExpectedToken, "Unclosed scope.")
		return
	}

	resize(&statements, len(statements))
	//log.debug("S: ", statements)

	scope = Scope {
		body = statements[:],
	}
	return
}

look_for_assignment :: proc(parser: ^Parser) -> bool {
	restore := parser.current
	defer parser.current = restore

	for !parser_end(parser^) {
		tk := parser_advance(parser)
		if tk.kind == .Equal {
			return true
		}

		if tk.kind == .Semicolon {
			break
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
	type: ParserType
	exprs: []^Expression

	if _, ok := parser_expect_consume(parser, .Colon); ok {
		type = parse_type(parser) or_return
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



parse_field_entry :: proc(parser: ^Parser) -> (field: Field, err: Error){
	name, ok := parser_expect_consume(parser, .Identifier)
	if !ok {
		err = .NoExpectedToken
		return
	}
	if _, ok := parser_expect_consume(parser, .Colon); !ok {
		err = .NoExpectedToken
		return
	}
	type := parse_type(parser) or_return

	field = Field {
		name = Identifier(name.lexeme),
		type = type,
	}
	return
}

parse_field_list :: proc(parser: ^Parser, allow_trailing_on := TokenKind.EndOfFile) -> (list: []Field, err: Error){
	fields := make([dynamic]Field)

	first := parse_field_entry(parser) or_return
	append_elem(&fields, first)

	for !parser_end(parser^) {
		if _, ok := parser_match_consume(parser, .Comma); ok {
			if parser_peek(parser).kind == allow_trailing_on {
				break
			}
			else {
				field := parse_field_entry(parser) or_return
				append_elem(&fields, field)
			}
		}
		else {
			// Nothing more to parse
			break
		}
	}

	resize(&fields, len(fields))
	list = fields[:]
	return
}

// parse_type_definition :: proc(parser: ^Parser) -> (typedef: TypeDef, err: Error){
// 	name, ok := parser_expect_consume(parser, .Identifier)
// 	if !ok {
// 		err = .NoExpectedToken
// 		return
// 	}
//
// 	type := parse_type(parser) or_return
//
// 	typedef = TypeDef {
// 		name = Identifier(name.lexeme),
// 		what = type,
// 	}
//
// 	return
// }

parse_type :: proc(parser: ^Parser) -> (type: ParserType, err: Error) {
	// ("[]" | "^")* id
	modifiers := make([dynamic]Modifier)
	name: Identifier
	for !parser_end(parser^){
		tk := parser_advance(parser)
		if tk.kind == .SquareOpen {
			if _, ok := parser_expect_consume(parser, .SquareClose); ok {
				append_elem(&modifiers, Modifier.Slice)
			}
		}
		else if tk.kind == .Caret {
			append_elem(&modifiers, Modifier.Pointer)
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
	resize(&modifiers, len(modifiers))

	type = ParserType {
		name = name,
		modifiers = modifiers[:],
	}

	return
}

parse_identifier_list :: proc(parser: ^Parser) -> (list: []Identifier, err: Error) {
	ids := make([dynamic]Identifier)

	if tk, ok := parser_expect_consume(parser, .Identifier); ok {
		append_elem(&ids, Identifier(tk.lexeme))
	}
	else {
		err = .NoExpectedToken
		return
	}

	for !parser_end(parser^){
		if _, ok := parser_match_consume(parser, .Comma); ok {
			if id, ok := parser_expect_consume(parser, .Identifier); ok {
				append_elem(&ids, Identifier(id.lexeme))
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
	append_elem(&exprs, leading)

	for !parser_end(parser^){
		if _, ok := parser_match_consume(parser, .Comma); ok {
			if parser_peek(parser).kind == allow_trailing_on {
				break
			}
			else {
				exp := parse_expression(parser) or_return
				append_elem(&exprs, exp)
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
	// log.debug("Began parsing expression on: ", parser_peek(parser, 0))
	e := parse_unary(parser) or_return
	expression = parse_binary(parser, e, 0) or_return
	return
}

parse_indexing :: proc(parser: ^Parser, object: ^Expression) -> (expression: ^Expression, err:Error) {
	// expr "[" expr "]"
	log.debug("IDX IN: ", parser_peek(parser, 0))
	index := parse_expression(parser) or_return
	log.debug("IDX OUT: ", parser_peek(parser, 0))

	if _, ok := parser_expect_consume(parser, .SquareClose); !ok {
		err = .NoExpectedToken
		return
	}

	expression = new(Expression)
	expression.value = Indexing {
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
	expression.value = FunctionCall {
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
			expression.value = Binary {
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
		expression.value = Unary {
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
		expression.value = Primary(id)
		return
	}

	if tk, ok := parser_match_consume(parser, .ParenOpen); ok {
		inner := parse_expression(parser) or_return
		left, ok := parser_expect_consume(parser, .ParenClose)
		if !ok {
			panic("TODO: Parser sync()")
		}

		expression = new(Expression)
		expression.value = Group {
			inner = inner,
		}
		return
	}

	log.warn("Unexpected Token:", parser_peek(parser, 0))
	log.debugf("Current: %v/%v", parser.current, len(parser.tokens))
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

