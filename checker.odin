package lang

import "core:slice"
import "core:log"

// TODO: Make literals untyped

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
}

SymbolKind :: enum {
	Variable, Parameter, Function, Type,
}

Environment :: map[Identifier]SymbolInfo

define_symbol :: proc(scope: ^Scope, name: Identifier, value: SymbolInfo) -> (err: Error){
	_, exists := search_symbol(scope, name)
	if exists {
		err = emit_error(.Redefinition, "Identifier '%v' already in use.", name)
		return
	}

	scope.env[name] = value
	return
}

search_symbol :: proc(scope: ^Scope, name: Identifier) -> (sym: SymbolInfo, found: bool) {
	if scope == nil { return }

	if name in scope.env {
		return scope.env[name], true
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

// Initialize environments in scopes, does not typecheck, only defines the symbols
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
			body := &stmt.scope
			for arg in stmt.args {
				t := eval_parser_type(scope, arg.type) or_return
				define_symbol(body, arg.name, SymbolInfo{
					kind = .Parameter,
					type = t,
				})
			}
			init_scopes(body, scope) or_return

		case If:
			init_scopes(&stmt.scope, scope) or_return
			current := stmt.else_branch
			for current != nil {
				#partial switch &branch in current {
				case If:
					init_scopes(&branch.scope, scope) or_return
					current = branch.else_branch
				case Scope:
					log.debug("Init else branch")
					init_scopes(&branch, scope) or_return
					current = nil

				case: unreachable()
				}
			}

		case For:
			if stmt.post_stmt != nil || stmt.pre_stmt != nil {
				log.warnf("Deal with triple for loop definition creation & checking")
			}
			else {
				init_scopes(&stmt.scope, scope) or_return
			}

		case InlineStatement:
			switch &in_stmt in stmt {
			case Assignment:
				// Only allows assigning to indexing or identifier
				for &left, i in in_stmt.left_side {
					right := in_stmt.right_side[i]
					eval_expression_type(scope, right) or_return
					check_lvalue(left) or_return
					eval_expression_type(scope, left) or_return
				}
				log.warn("assign compat")

			case VarDeclaration:
				for id, i in in_stmt.identifiers {
					t := eval_parser_type(scope, in_stmt.type) or_return
					define_symbol(scope, id, SymbolInfo{
						kind = .Variable,
						type = t,
					})
				}
				if len(in_stmt.expressions) > 0 {
					for &exp, i in in_stmt.expressions {
						eval_expression_type(scope, exp) or_return
						sym, _ := search_symbol(scope, in_stmt.identifiers[i])
						if !same_type(sym.type, exp.type){
							return emit_error(.MismatchedTypes, "Cannot assign symbol of type %v to value of type %v", sym.type, exp.type)
						}
					}
				}

			case Return:
				log.warn("return")

			case ExpressionStatement: log.warn("exprstmt")
			case Break, Continue: continue
			}
		}
	}

	return
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


@(require_results)
eval_expression_type :: proc(scope: ^Scope, expr: ^Expression) -> (err: Error) {
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
			return emit_error(.MismatchedTypes, "Cannot apply binary operation to non primitive types: %v and %v", lhs.type, rhs.type)
		}

		operator_supported := contains(compat_types, lhs.type.primitive) && contains(compat_types, rhs.type.primitive)

		if operator_supported {
			// Ensure same type
			if !same_type(lhs.type, rhs.type){
				return emit_error(.MismatchedTypes, "Cannot apply binary operation to operands of types: %v and %v", lhs.type, rhs.type)
			}
			expr.type = lhs.type
			// log.debug("Binary expression has type: ", expr.type.primitive)
		}
		else {
			return emit_error(.MismatchedTypes, "Cannot apply binary operation to operands of types: %v and %v", lhs.type, rhs.type)
		}

	case Indexing:
		/*
		   Index must be an integer, Indexed object must be a slice.
		 */
		eval_expression_type(scope, expression.index) or_return
		if !is_valid_index(expression.index.type){
			return emit_error(.MismatchedTypes, "Index must be an integer.")
		}
		eval_expression_type(scope, expression.object) or_return

		mod, ok := pop_mod(expression.object.type)
		if !ok || mod != .Slice {
			return emit_error(.NonIndexable, "Cannot index type")
		}
		expr.type = expression.object.type
		mod_count := len(expr.type.modifiers)
		expr.type.modifiers = []Modifier{} if mod_count < 2 else expr.type.modifiers[1:]

	// TODO: Function expression
	case FunctionCall:
		p, ok := expression.func.value.(Primary)
		if !ok {
			err = emit_error(.NonCallable, "Non primary expression is not callable.")
			return
		}
		id, ok2 := p.(Identifier)
		if !ok2 {
			err = emit_error(.NonCallable, "Non identifier is not callable")
		}

		fn, found := search_symbol(scope, id)
		if !found {
			err = emit_error(.NotDefined, "Unknown function: %v", id)
		}

		if fn.kind == .Function {
			log.warnf("TODO: Check args")
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
			info, ok := search_symbol(scope, id)
			if !ok {
				err = emit_error(.NotDefined, "Undefined identifier: %v", id)
				return
			}
			expr.type = info.type
		}
	}

	return
}

check_ast :: proc(scope: ^Scope) -> (err: Error){
	init_global_env(scope)
	init_scopes(scope, nil) or_return
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

BUILTIN_TYPES := map[Identifier]BuiltinType{
	"int"    = .Int,
	"real"   = .Real,
	"bool"   = .Bool,
	"rune"   = .Rune,
	"string" = .String,
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
