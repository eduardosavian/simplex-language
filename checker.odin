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
init_scopes :: proc(scope: ^Scope, previous: ^Scope) -> (err: Error){
	if scope == nil { return }

	if scope.env == nil {
		scope.env = env_create()
	}

	scope.parent = previous

	for &entry in scope.body {
		switch &stmt in entry {
		case Scope:
			init_scopes(&stmt, scope)

		case FunctionDef:
			body := &stmt.scope
			for arg in stmt.args {
				t := eval_parser_type(scope, arg.type) or_return
				define_symbol(body, arg.name, SymbolInfo{
					kind = .Parameter,
					type = t,
				})
			}
			init_scopes(body, scope)

		case If: log.warn("if")
		case For: log.warn("for")
		case InlineStatement:
			decl, ok := stmt.(VarDeclaration)
			if !ok { continue }
			log.debugf("Current scope: %p", scope)
			for id, i in decl.identifiers {
				t := eval_parser_type(scope, decl.type) or_return
				define_symbol(scope, id, SymbolInfo{
					kind = .Variable,
					type = t,
				})
			}
		}
	}

	return
}

check_ast :: proc(scope: ^Scope) -> (err: Error){
	init_global_env(scope)
	init_scopes(scope, nil)
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
