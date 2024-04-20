package lang

import "core:reflect"
import "core:slice"

Identifier :: distinct string
NilType    :: struct {}
String     :: string
Integer    :: i64
Real       :: f64
Rune       :: rune
Bool       :: bool

Type :: struct {
	definition: union {
		FunctionType,
		PrimitiveType,
		IndirectType,
		NoType,
	},
	flags: TypeFlags,
}

PrimitiveType :: enum {
	Int,
	Real,
	Rune,
	Bool,
	String,
}

IndirectType :: struct {
	modifiers: []Modifier,
	backing_type: union {
		FunctionType,
		PrimitiveType,
	},
}

FunctionType :: struct {
	args: []Type,
	returns: ^Type,
}

// Return type of functions without return
NoType :: struct {}

TypeFlags :: bit_set[TypeFlag]
TypeFlag :: enum {
	BuiltIn,
	Nullable,
}

SymbolKind :: enum {
	Variable, Function, Type,
}

SymbolInfo :: struct {
	kind: SymbolKind,
	type: Type,
}

Environment :: map[Identifier]SymbolInfo

CheckerContext :: struct {
	env_stack: [dynamic]Environment,
}

BUILTIN_TYPE_NAMES := map[Identifier]PrimitiveType {
	"int"    = .Int,
	"real"   = .Real,
	"rune"   = .Rune,
	"string" = .String,
	"bool"   = .Bool,
}

env_make :: proc() -> Environment {
	env := make(map[Identifier]SymbolInfo)
	return env
}

env_destroy :: proc(env: ^Environment){
	delete(env^)
}

check_ast :: proc(global_scope: ^Scope) -> (err: Error) {
	check_toplevel(global_scope^) or_return
	init_scopes(global_scope, nil)
	init_global_scope(global_scope)

	return 
}

type_equal :: proc(a, b: Type) -> bool {
	return false
}

@private
init_global_scope :: proc(global: ^Scope){
	for name, type in BUILTIN_TYPE_NAMES {
		global.env[name] = SymbolInfo{
			type = Type{
				definition = type,
				flags = {.BuiltIn},
			},
		}
	}
}

init_scopes :: proc(current: ^Scope, previous: ^Scope){
	current.parent = previous
	current.env = env_make()

	for statement in current.body {
		switch &scoped in statement {
		case Scope:
			init_scopes(&scoped, current)
		case FunctionDef:
			init_scopes(&scoped.scope, current)
		case If:
			init_scopes(&scoped.scope, current)
		case For:
			init_scopes(&scoped.scope, current)
		case InlineStatement, TypeDef: continue
		}
	}
}

search_for_identifier :: proc(scope: ^Scope, id: Identifier) -> (sym: SymbolInfo, found: bool) {
	if scope == nil {
		return
	}

	sym, found = scope.env[id]
	if !found {
		return search_for_identifier(scope.parent, id)
	}
	else {
		return
	}
}

check_toplevel :: proc(scope: Scope) -> (err: Error) {
	return
}
