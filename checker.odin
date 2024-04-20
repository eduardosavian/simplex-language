package lang

import "core:log"
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
	modifiers: []Modifier,
	backing_type: union {
		PrimitiveType,
		FunctionType,
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
	Parameter,
}

SymbolKind :: enum {
	Variable, Function,
}

SymbolInfo :: struct {
	kind: SymbolKind,
	type: Type,
}

Environment :: map[Identifier]SymbolInfo

BUILTIN_TYPE_NAMES := map[Identifier]Type {
	"int"    = {backing_type = .Int},
	"real"   = {backing_type = .Real},
	"rune"   = {backing_type = .Rune},
	"string" = {backing_type = .String},
	"bool"   = {backing_type = .Bool},
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

	check_scope(global_scope)

	return 
}

type_equal :: proc(a, b: Type) -> bool {
	return false
}

@private
init_global_scope :: proc(global: ^Scope){
	for name, type in BUILTIN_TYPE_NAMES {
		global.env[name] = SymbolInfo{
			type = type,
		}
	}
}

define_variable :: proc(scope: ^Scope, id: Identifier, type: Type) -> (err: Error){
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

evaluate_type :: proc{
	evaluate_parser_type,
	evaluate_func_type,
}

evaluate_func_type :: proc(fd: FunctionDef) -> (type: Type) {
	ret := evaluate_type(fd.return_type)
	ft := FunctionType {
		returns = new(Type),
		args = make([]Type, len(fd.args))
	}

	ft.returns^ = ret
	for arg, i in fd.args {
		ft.args[i] = evaluate_type(arg.type)
		ft.args[i].flags = {.Parameter}
	}

	type = Type {
		backing_type = ft,
		flags = {}
	}
	return
}

evaluate_parser_type :: proc(pt: ParserType) -> (type: Type){
	if pt.name not_in BUILTIN_TYPE_NAMES {
		unimplemented("TODO: Type aliasing")
	}

	if len(pt.modifiers) == 0 {
		type = BUILTIN_TYPE_NAMES[pt.name]
		return
	}
	else {
		type = Type {
			backing_type = BUILTIN_TYPE_NAMES[pt.name].backing_type,
			modifiers = pt.modifiers,
		}
		return
	}
}

check_scope :: proc(scope: ^Scope) -> (err: Error){
	for statement in scope.body {
		switch &stmt in statement {
		case InlineStatement:
			var_decl, ok := stmt.(VarDeclaration)
			if ok {
				t := evaluate_type(var_decl.type)
				for id in var_decl.identifiers {
					define_variable(scope, id, t) or_return
				}
			}
		case Scope:
			check_scope(&stmt) or_return
		case If:
			// TODO: Check the condition
			check_scope(&stmt.scope) or_return
		case For:
			// TODO: Check the condition
			check_scope(&stmt.scope) or_return
		case FunctionDef:
			check_scope(&stmt.scope) or_return
		}
	}
	return
}

init_scopes :: proc(current: ^Scope, previous: ^Scope){
	current.parent = previous
	current.env = env_make()

	// log.debugf("Current scope is %p, parent is %p", current, current.parent)
	for &statement in current.body {
		switch &scoped in statement {
		case Scope:
			init_scopes(&scoped, current)
		case FunctionDef:
			// log.debugf("(fn) Scoped parent is %p", scoped.scope.parent)
			init_scopes(&scoped.scope, current)
			log.debugf("(fn) Scoped parent is %p", scoped.scope.parent)
		case If:
			init_scopes(&scoped.scope, current)
		case For:
			init_scopes(&scoped.scope, current)
		case InlineStatement: continue
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
