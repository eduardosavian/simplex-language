package lang

import "core:slice"
import "core:log"
import "core:reflect"

BuiltinType :: enum {
	Bool, Int, Real, Rune, String,
}

PrimitiveType :: union {
	BuiltinType,
	// Struct,
}

Type :: struct {
	modifiers: []Modifier,
	primitive: PrimitiveType,
}

SymbolInfo :: struct {
	kind: SymbolKind,
	type: Type,
	args: []Type,
	body: Scope,

	stack_offset: int,

	uses: int,
}

SymbolKind :: enum {
	Variable, Parameter, Function, Type,
}

Environment :: map[Identifier]SymbolInfo

define_symbol :: proc(scope: ^Scope, name: Identifier, value: SymbolInfo, increase_usage := true) -> (err: Error){
	_, exists := search_symbol(scope, name, increase_usage)
	if exists {
		return emit_error(.Redefinition, "Identifier '%v' already in use.", name)
	}

	scope.env[name] = value
	return
}

search_symbol :: proc(scope: ^Scope, name: Identifier, increase_usage := true) -> (sym: SymbolInfo, found: bool) {
	if scope == nil { return }

	if name in scope.env {
		info := scope.env[name]
		info.uses += int(increase_usage)
		scope.env[name] = info
		return info, true
	}

	return search_symbol(scope.parent, name)
}

eval_parser_type :: proc(scope: ^Scope, ptype: ParserType) -> (type: Type, err: Error){
	assert(scope != nil, "Cannot evaluate on nil scope")
	// type.primitive = ptype.name
	sym, ok := search_symbol(scope, ptype.name)

	if !ok {
		err = emit_error(.NotDefined, "Undefined type: %v", ptype.name)
		return
	}

	// NOTE: This does *NOT* handle type aliasing
	type.modifiers = ptype.modifiers
	type.primitive = sym.type.primitive

	return
}

@(require_results)
init_scopes :: proc(scope: ^Scope, previous: ^Scope) -> (err: Error){
	if scope == nil { return }

	if scope.env == nil {
		scope.env = env_create()
	}

	scope.parent = previous

	for &entry in scope.body {
		switch &stmt in entry {
		case Scope:
			init_scopes(&stmt, scope) or_return

		case FunctionDef:
			return_type : Type
			if len(stmt.return_type.name) > 0 {
				return_type = eval_parser_type(scope, stmt.return_type) or_return
			}
			else {
				return_type.primitive = nil
			}

			fn_info := SymbolInfo{
				kind = .Function,
				type = return_type
			}
			arg_types := make([dynamic]Type)

			for arg in stmt.args {
				t := eval_parser_type(scope, arg.type) or_return
				append_elem(&arg_types, t)
				define_symbol(&stmt.scope, arg.name, SymbolInfo {
					kind = .Parameter,
					type = t,
				}) or_return
			}

			resize(&arg_types, len(arg_types))
			fn_info.args = arg_types[:]

			define_symbol(scope, stmt.name, fn_info) or_return

			init_scopes(&stmt.scope, scope) or_return

		case If:
			init_scopes(&stmt.scope, scope) or_return
			current := stmt.else_branch
			for current != nil {
				#partial switch &branch in current {
				case If:
					init_scopes(&branch.scope, scope) or_return
					current = branch.else_branch
				case Scope:
					init_scopes(&branch, scope) or_return
					current = nil

				case: unreachable()
				}
			}

		case For:
			if stmt.post_stmt != nil || stmt.pre_stmt != nil {
				pre := stmt.pre_stmt.(InlineStatement)

				// Initialize environment ahead of time to allow for local index variable
				stmt.scope.env = env_create()
				stmt.scope.parent = scope

				switch &s in pre {
				case Break, Continue, Return:
					return emit_error(.DisallowedOnForLoop, "This type of statement is not allowed within a for-loop.")
				case VarDeclaration:
					check_var_declaration(&stmt.scope, s)
				case Assignment:
					check_assignment(&stmt.scope, s)
				case ExpressionStatement:
					eval_expression_type(&stmt.scope, s.inner) or_return
				}

				eval_expression_type(&stmt.scope, stmt.condition) or_return
				primitive := is_pure_primitive(stmt.condition.type)
				ok := primitive && stmt.condition.type.primitive == .Bool
				if !ok	{
					return emit_error(.MismatchedTypes, "Cannot use a non-boolean as condition")
				}

				post := stmt.post_stmt.(InlineStatement)
				switch &s in post {
				case Break, Continue, Return:
					return emit_error(.DisallowedOnForLoop, "This type of statement is not allowed within a for-loop.")
				case Assignment:
					check_assignment(&stmt.scope, s)
				case ExpressionStatement:
					eval_expression_type(&stmt.scope, s.inner) or_return
				case VarDeclaration:
					return emit_error(.DisallowedOnForLoop, "Cannot declare variable as side effect.")
				}
			}
			else { /* Simple for */
				eval_expression_type(scope, stmt.condition) or_return

				primitive := is_pure_primitive(stmt.condition.type)
				ok := primitive && stmt.condition.type.primitive == .Bool
				if !ok	{
					return emit_error(.MismatchedTypes, "Cannot use a non-boolean as condition")
				}
			}
			init_scopes(&stmt.scope, scope) or_return

		case InlineStatement:
			switch &in_stmt in stmt {
			case Assignment:
				check_assignment(scope, in_stmt)
			case VarDeclaration:
				check_var_declaration(scope, in_stmt)
			case ExpressionStatement:
				eval_expression_type(scope, in_stmt.inner) or_return
			case Break, Continue: continue
			case Return:
				log.warn("return check")
			}
		}
	}

	return
}

@(private="file")
is_comparison :: proc(op: TokenKind) -> bool {
	COMPARE_OPS :: []TokenKind{
		.EqualEqual,
		.GreaterEqual,
		.LesserEqual,
		.Lesser,
		.Greater,
		.NotEqual,
	}

	return contains(COMPARE_OPS, op)
}

@(require_results)
eval_expression_type :: proc(scope: ^Scope, expr: ^Expression, increase_usage := true) -> (err: Error) {
	switch &expression in expr.value {
	case Unary:
		compat_types := UNARY_COMPAT[expression.operator]
		eval_expression_type(scope, expression.operand) or_return

		if len(expression.operand.type.modifiers) != 0 {
			return emit_error(.MismatchedTypes, "Cannot apply unary operation to non concrete type")
		}

		// TODO: Struct
		primitive := expression.operand.type.primitive.(BuiltinType)
		if contains(compat_types, primitive) {
			expr.type = Type {
				primitive = primitive,
			}
		}
		else {
			return emit_error(.MismatchedTypes, "Cannot apply unary operation to type: %v", primitive)
		}

	case Binary:
		compat_types := BINARY_COMPAT[expression.operator]
		eval_expression_type(scope, expression.left_side) or_return
		eval_expression_type(scope, expression.right_side) or_return

		// TODO: Struct
		lhs := expression.left_side
		rhs := expression.right_side
		if !is_pure_primitive(lhs.type) || !is_pure_primitive(rhs.type){
			return emit_error(.MismatchedTypes, "Cannot apply binary operation to non primitive types: %v and %v", format_type(lhs.type), format_type(rhs.type))
		}

		operator_supported := contains(compat_types, lhs.type.primitive) && contains(compat_types, rhs.type.primitive)

		if operator_supported {
			// Ensure same type
			if !same_type(lhs.type, rhs.type){
				return emit_error(.MismatchedTypes, "Cannot apply binary operation to operands of types: %v and %v", format_type(lhs.type), format_type(rhs.type))
			}

			if is_comparison(expression.operator){
				expr.type.primitive = .Bool
			}
			else {
				expr.type = lhs.type
			}
		}
		else {
			return emit_error(.MismatchedTypes, "Cannot apply binary operation to operands of types: %v and %v", format_type(lhs.type), format_type(rhs.type))
		}

	case Indexing:
		// Index must be an integer, Indexed object must be a slice.
		eval_expression_type(scope, expression.index) or_return
		if !is_valid_index(expression.index.type){
			return emit_error(.MismatchedTypes, "Index must be an integer.")
		}
		eval_expression_type(scope, expression.object) or_return

		if type_is_indexable(expression.object.type) {
			return emit_error(.NonIndexable, "Cannot index type: %v", format_type(expression.object.type))
		}

		expr.type = expression.object.type
		mod_count := len(expr.type.modifiers)
		expr.type.modifiers = []Modifier{} if mod_count < 2 else expr.type.modifiers[1:]

	// TODO: Function expression
	case FunctionCall:
		p, ok := expression.func.value.(Primary)
		if !ok {
			return emit_error(.NonCallable, "Non primary expression is not callable.")
		}

		id, ok2 := p.(Identifier)
		if !ok2 {
			return emit_error(.NonCallable, "Non identifier is not callable")
		}

		fn, found := search_symbol(scope, id)
		if !found {
			return emit_error(.NotDefined, "Unknown function: %v", id)
		}

		// expr.type = fn.type
		if fn.kind == .Function {
			enough_args := len(expression.args) == len(fn.args)
			if !enough_args {
				return emit_error(.ArgMismatch, "Mismatched number of arguments %v = %v", len(expression.args), len(fn.args))
			}
			expr.type = fn.type

			for &arg, i in expression.args {
				eval_expression_type(scope, arg) or_return
				if !same_type(arg.type, fn.args[i]){
					return emit_error(.MismatchedTypes, "Cannot pass argument of type %v to parameter of type %v", format_type(arg.type), format_type(fn.args[i]))
				}
			}
		}
		else {
			return emit_error(.NonCallable, "Non function is not callable")
		}

	case Group:
		eval_expression_type(scope, expression.inner) or_return
		expr.type = expression.inner.type

	case Primary:
		switch _ in expression {
		case Bool:   expr.type.primitive = .Bool
		case Rune:   expr.type.primitive = .Rune
		case String: expr.type.primitive = .String
		case Int:    expr.type.primitive = .Int
		case Real:   expr.type.primitive = .Real
		case Identifier:
			id, _ := expression.(Identifier)
			info, ok := search_symbol(scope, id, increase_usage)
			if !ok {
				return emit_error(.NotDefined, "Undefined identifier: %v", id)
			}
			expr.type = info.type
		}
	}

	return
}

check_symbol_usage :: proc(scope: ^Scope){
	if scope == nil { return }
	for name, info in scope.env {
		if info.uses == 0 && info.kind == .Variable {
			emit_warning("Unused variable: %v", name)
		}
	}
	for entry in scope.body {
		switch &stmt in entry {
		case If: check_symbol_usage(&stmt.scope)
		case For: check_symbol_usage(&stmt.scope)
		case FunctionDef: check_symbol_usage(&stmt.scope)
		case Scope: check_symbol_usage(&stmt)
		case InlineStatement:
		}
	}
}

check_ast :: proc(scope: ^Scope) -> (err: Error){
	init_global_env(scope)
	init_scopes(scope, nil) or_return
	check_symbol_usage(scope)
	return
}

env_create :: proc() -> Environment {
	e, err := make(Environment)
	assert(err == nil, "Memory error on environment creation")
	return e
}

env_destroy :: proc(env: ^Environment) {
	delete(env^)
	env^ = nil
}

init_global_env:: proc(s: ^Scope){
	for name, val in BUILTIN_TYPES {
		info := SymbolInfo{kind = .Type, type = Type{primitive = val}}
		define_symbol(s, name, info)
	}
}

@(private="file")
is_valid_index :: proc(t: Type) -> bool {
	return len(t.modifiers) == 0 && t.primitive == .Int
}

@(private="file")
check_lvalue :: proc(e: ^Expression) -> Error {
	#partial switch _ in e.value {
	case Indexing:
	case Primary:
		_, ok := e.value.(Primary).(Identifier)
		if !ok {
			return emit_error(.NonAssignable, "Cannot assign to a non lvalue expression")
		}
	case:
		return emit_error(.NonAssignable, "Cannot assign to a non lvalue expression")
	}

	return nil
}

@(private="file")
same_type :: proc(a, b: Type) -> bool {
	// TODO: Struct
	same_mods := false
	if len(a.modifiers) + len(b.modifiers) == 0 {
		same_mods = true
	}
	else {
		// WARN: TEST THIS BETTER LATER!
		same_mods = slice.equal(a.modifiers, b.modifiers)
	}
	same_prim := a.primitive == b.primitive
	return  same_mods && same_prim
}


is_pure_primitive :: proc(t: Type) -> bool {
	return len(t.modifiers) == 0
}

pop_mod :: proc(t: Type) -> (Modifier, bool) {
	if len(t.modifiers) == 0 {
		return Modifier{}, false
	}
	return t.modifiers[0], true
}

check_assignment :: proc(scope: ^Scope, stmt: Assignment) -> (err: Error){
	// Only allows assigning to indexing or identifier
	for &left, i in stmt.left_side {
		right := stmt.right_side[i]
		eval_expression_type(scope, right) or_return
		check_lvalue(left) or_return
		eval_expression_type(scope, left, increase_usage = false) or_return

		if !same_type(left.type, right.type){
			return emit_error(.MismatchedTypes, "Cannot assign expression of type %v to expression of type %v", format_type(left.type), format_type(right.type))
		}

	}
	return
}

check_var_declaration :: proc(scope: ^Scope, stmt: VarDeclaration) -> (err: Error){
	t := eval_parser_type(scope, stmt.type) or_return
	for id, i in stmt.identifiers {
		define_symbol(scope, id, SymbolInfo{
			kind = .Variable,
			type = t,
			uses = 0,
		}, increase_usage = false)
	}

	if len(stmt.expressions) > 0 {
		for &exp, i in stmt.expressions {
			eval_expression_type(scope, exp) or_return
			sym, _ := search_symbol(scope, stmt.identifiers[i], false)
			if !same_type(sym.type, exp.type){
				return emit_error(.MismatchedTypes, "Cannot assign symbol of type %v to value of type %v", format_type(sym.type), format_type(exp.type))
			}
		}
	}
	return
}

@private
type_is_indexable :: proc(t: Type) -> bool {
	m := pop_mod(t) or_return
	switch _ in m {
	case Slice, Array: return true
	case Pointer:
	}
	return false
}

UNARY_COMPAT := map[TokenKind][]PrimitiveType {
	.Minus    = {.Int, .Real},
	.Plus     = {.Int, .Real},
	.BitXor   = {.Int},
	.LogicNot = {.Bool},
}

BINARY_COMPAT := map[TokenKind][]PrimitiveType {
	.EqualEqual   = {.Bool, .Int, .Real, .Rune, .String},
	.NotEqual     = {.Bool, .Int, .Real, .Rune, .String},
	.GreaterEqual = {.Bool, .Int, .Real, .Rune, .String},
	.LesserEqual  = {.Bool, .Int, .Real, .Rune, .String},
	.Greater      = {.Bool, .Int, .Real, .Rune, .String},
	.Lesser       = {.Bool, .Int, .Real, .Rune, .String},

	.BitXor     = {.Int},
	.BitAnd     = {.Int},
	.BitOr      = {.Int},
	.ShiftLeft  = {.Int},
	.ShiftRight = {.Int},

	.Plus   = {.Int, .Real},
	.Minus  = {.Int, .Real},
	.Star   = {.Int, .Real},
	.Slash  = {.Int, .Real},
	.Modulo = {.Int},

	.LogicAnd = {.Bool},
	.LogicNot = {.Bool},
	.LogicOr  = {.Bool},
	.LogicXor = {.Bool},
}

BUILTIN_TYPES := map[Identifier]BuiltinType{
	"int"    = .Int,
	"real"   = .Real,
	"bool"   = .Bool,
	"rune"   = .Rune,
	"string" = .String,
}


