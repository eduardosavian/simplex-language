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
	type: ^Type,
}

Environment :: map[Identifier]SymbolInfo

CheckerContext :: struct {
	env_stack: [dynamic]Environment,
}

TYPE_INT    := Type{definition = PrimitiveType.Int,    flags = {.BuiltIn}}
TYPE_REAL   := Type{definition = PrimitiveType.Real,   flags = {.BuiltIn}}
TYPE_RUNE   := Type{definition = PrimitiveType.Rune,   flags = {.BuiltIn}}
TYPE_STRING := Type{definition = PrimitiveType.String, flags = {.BuiltIn}}
TYPE_BOOL   := Type{definition = PrimitiveType.Bool,   flags = {.BuiltIn}}

BUILTIN_TYPE_NAMES := map[Identifier]^Type {
	"int"    = &TYPE_INT,
	"real"   = &TYPE_REAL,
	"rune"   = &TYPE_RUNE,
	"string" = &TYPE_STRING,
	"bool"   = &TYPE_BOOL,
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

		}
	}
}

define_variable :: proc(scope: ^Scope, id: Identifier, type: ^Type) -> (err: Error){
	info, found := search_for_identifier(scope, id)
	if found {
		err = emit_error(.Redefinition, "Redefinition of symbol: %v", id)
		return
	}
	scope.env[id] = SymbolInfo {
		kind = .Variable,
		type = type,
	}
	return
}

evaluate_type :: proc(pt: ParserType) -> (type: ^Type){
	if pt.name not_in BUILTIN_TYPE_NAMES {
		unimplemented("TODO: Type aliasing")
	}

	if len(pt.modifiers) == 0 {
		type = BUILTIN_TYPE_NAMES[pt.name]
		return
	}
	else {
		type = new(Type)
		it := IndirectType {
			modifiers = pt.modifiers,
			backing_type = BUILTIN_TYPE_NAMES[pt.name].definition.(PrimitiveType),
		}
		type^ = Type {
			definition = it,
			flags = {.Nullable},
		}
		return
	}
}

check_scope :: proc(scope: ^Scope) -> (err: Error){
	for statement in scope.body {
		switch stmt in statement {
		case InlineStatement:
			var_decl, ok := stmt.(VarDeclaration)
			// TODO: Init where possible
			if ok {
				t := evaluate_type(var_decl.type)
				for id in var_decl.identifiers {
					define_variable(scope, id, t) or_return
				}
			}
		case If: unimplemented()
		case For: unimplemented()
		case Scope: unimplemented()
		case FunctionDef: unimplemented()
		case TypeDef: unimplemented()
		}
	}
	return
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
