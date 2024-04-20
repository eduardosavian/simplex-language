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

// A builtin type is a named type with a predefined layout and semantics
BultinType :: enum {
	String,
	Integer,
	Real,
	Rune,
	Bool,
}

FunctionType :: struct {
	return_type: ^Type,
	args: []Type,
}

IndirectType :: struct {
	qualifiers: []Qualifier,
	core_type: BultinType, // TODO: Support arbitrary named types
}

// nil == Untyped
Type :: union {
	BultinType,
	IndirectType,
	FunctionType,
	NoType,
}

// Return type of functions without return
NoType :: struct {}

TypeFlag :: enum {
	Bultin,
}

TypeFlags :: bit_set[TypeFlag]

SymbolInfo :: struct {
	kind: enum {
		Variable, Function,
	},
	type: Type,
}

Environment :: map[Identifier]SymbolInfo

CheckerContext :: struct {
	env_stack: [dynamic]Environment,
}

BUILTIN_TYPE_NAMES := map[Identifier]BultinType {
	"int"    = .Integer,
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
	same_variant := reflect.union_variant_typeid(a) == reflect.union_variant_typeid(b)
	if !same_variant {
		return false
	}

	#no_type_assert switch _ in a {
	case BultinType:
		return a.(BultinType) == b.(BultinType)
	case IndirectType:
		same_quali := slice.equal(a.(IndirectType).qualifiers, b.(IndirectType).qualifiers)
		same_core := a.(IndirectType).core_type == b.(IndirectType).core_type
		return same_quali && same_core
	case FunctionType:
		same_ret := a.(FunctionType).return_type == b.(FunctionType).return_type
		unimplemented("Equality for function args")

	case NoType:
		return false
	}

	return false
}

ARITH_OPERATOR_SUPPORT := map[TokenKind][]BultinType {
	.Plus = {.Integer, .Real},
	.Minus = {.Integer, .Real},
	.Star = {.Integer, .Real},
	.Slash = {.Integer, .Real},
	.Modulo = {.Integer},

	.BitAnd = {.Integer},
	.BitOr = {.Integer},
	.BitXor = {.Integer},
}

is_operator_supported_for_type :: proc(operator: TokenKind, type: Type) -> bool {
	if operator not_in ARITH_OPERATOR_SUPPORT {
		return false;
	}
	
	builtin, ok := type.(BultinType)
	if !ok {
		return false
	}

	return slice.contains(ARITH_OPERATOR_SUPPORT[operator], builtin)
}

type_expression :: proc(expr: ^Expression) -> Type {
	if expr.type != nil {
		return expr.type
	}

	switch e in expr.value {
	case Binary:
		left := type_expression(e.left_side)
		right := type_expression(e.right_side)
		if type_equal(left, right){
		}
	case Unary:
	case Primary:
	case Indexing:
	case Group:
	case FunctionCall:
	}

	panic("Cannot find type for expression")
}

check_expression :: proc(expr: ^Expression) -> (err: Error){
	if expr.type == nil {
		switch e in expr.value {
		case Binary:
		case Unary:
		case Primary:
		case Indexing:
		case Group:
		case FunctionCall:
		}
	}

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

init_scopes :: proc(current: ^Scope, previous: ^Scope){
	current.parent = previous
	current.env = env_make()

	for statement in current.body {
		switch &scoped in statement {
		case Scope:
			init_scopes(&scoped, current)
		case Function:
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

check_toplevel :: proc(scope: Scope) -> (err: Error) {
	ok := true
	for statement in scope.body {
		#partial switch _ in statement {
		case Function: continue

		case InlineStatement:
			#partial switch _ in statement.(InlineStatement) {
			case VarDeclaration: continue
			}
		}

		ok = false
	}

	if !ok {
		err = emit_error(.DisallowedOnToplevel, "This type of statement is not allowed in the file scope")
	}
	return err
}
