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
	Nil,
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
	//check_toplevel(global_scope^) or_return

	init_scopes(global_scope, nil)

	check_scope(global_scope)

	return 
}

@private
init_global_scope :: proc(global: ^Scope){
	for name, type in BUILTIN_TYPE_NAMES {
		global.env[name] = SymbolInfo{
			type = type,
		}
	}
}

@require_results
define_function ::  proc(scope: ^Scope, def: FunctionDef) -> (err: Error) {
	type := evaluate_type(def)
	_, found := search_for_identifier(scope, def.name)
	if found {
		err = emit_error(.Redefinition, "Redefinition of symbol: %v", def.name)
		return
	}

	scope.env[def.name] = SymbolInfo {
		type = type,
		kind = .Function,
	}

	return
}

@require_results
define_variable :: proc(scope: ^Scope, id: Identifier, type: Type) -> (err: Error){
	_, found := search_for_identifier(scope, id)
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
	ret: Type
	if fd.return_type.name == "" {
		ret = Type {
			backing_type = PrimitiveType.Nil,
		}
	}
	else {
		ret = evaluate_type(fd.return_type)
	}
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
		log.warn(pt.name)
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

is_assignable :: proc(e: ^Expression) -> bool {
	// TODO: Pointer deref
	if _, ok := e.value.(Primary).(Identifier); ok {
		return true
	}

	if idx, ok := e.value.(Indexing); ok {
		obj := idx.object
		if _, ok := obj.value.(Primary).(Identifier); ok {
			return true;
		}
	}

	return false
}

type_equal :: proc(a, b: Type) -> bool {
	same_mods := slice.equal(a.modifiers, b.modifiers)

	if fn_a, ok := a.backing_type.(FunctionType); ok {
		unimplemented("Compare function types")
	}
	else {
		aa, aok := a.backing_type.(PrimitiveType)
		bb, bok := b.backing_type.(PrimitiveType)
		if !aok || !bok {
			return false
		}
		return aa == bb
	}

}

check_scope :: proc(scope: ^Scope) -> (err: Error){
	for statement in scope.body {
		switch &stmt in statement {
		case InlineStatement:
			switch &in_stmt in stmt {
			case VarDeclaration:
				t := evaluate_type(in_stmt.type)
				for id in in_stmt.identifiers {
					define_variable(scope, id, t) or_return
				}

			case Assignment:
				for _, i in in_stmt.left_side {
					left := in_stmt.left_side[i]
					right := in_stmt.right_side[i]
					// TODO: is_lvalue
					check_expression(scope, left) or_return
					check_expression(scope, right) or_return
					log.debugf("L: %v, R: %v", left.type, right.type)
					if !type_equal(left.type, right.type){
						err = emit_error(.MismatchedTypes, "Mismatched types for assignment")
					}
				}

			case ExpressionStatement:
			case Break: unimplemented()
			case Continue: unimplemented()
			case Return:
			   // TODO: Mark when the scope is part of a function body
				unimplemented()
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
			define_function(&stmt.scope, stmt) or_return
			log.debug("name:", stmt.name, "args:", len(stmt.args))
			for arg, i in stmt.args {
				log.debug(i, arg.type)
				t := evaluate_type(arg.type)
				define_variable(&stmt.scope, arg.name, t) or_return
			}
			check_scope(&stmt.scope) or_return
		}
	}
	return
}

check_expression :: proc(scope: ^Scope, expr: ^Expression) -> (err: Error){
	switch &v in expr.value {
	case Indexing:
		unimplemented()

	case Group:
		check_expression(scope, v.inner)

	case Binary:
		unimplemented()

	case Unary:
		unimplemented()

	case FunctionCall:
		_, ok := v.func.value.(Primary).(Identifier)
		err = emit_error(.NonCallable, "Cannot call non-function object")

	case Primary:
		switch x in v {
		case Identifier:
			sym, ok := search_for_identifier(scope, x)
			if !ok {
				err = emit_error(.NotDefined, "Symbol %v not defined", x)
				return
			}
			expr.type = sym.type
		case Integer:
			expr.type = Type { backing_type = .Int, }
		case Real:
			expr.type = Type { backing_type = .Real, }
		case Rune:
			expr.type = Type { backing_type = .Rune, }
		case String:
			expr.type = Type { backing_type = .String, }
		case Bool:
			expr.type = Type { backing_type = .Bool, }
		case NilType:
			unimplemented()
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
			init_scopes(&scoped.scope, current)
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

