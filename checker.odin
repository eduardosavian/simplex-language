package lang

import "core:log"

// TODO: Func pointer type

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
				for id, i in in_stmt.left_side {
				}

			case VarDeclaration:
				for id, i in in_stmt.identifiers {
					t := eval_parser_type(scope, in_stmt.type) or_return
					define_symbol(scope, id, SymbolInfo{
						kind = .Variable,
						type = t,
					})
				}

			case Return: log.warn("return")
			case ExpressionStatement: log.warn("exprstmt")
			case Break, Continue: continue
			}
		}
	}

	return
}

eval_expression_type :: proc(scope: ^Scope, expr: ^Expression) -> (err: Error) {
	switch &expression in expr.value {
	case Binary: log.warn("Binary")
	case Unary: log.warn("unar")
	case Indexing: log.warn("indx")
	case FunctionCall: log.warn("fn cal")
	case Group:
		eval_expression_type(scope, expression.inner)
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
