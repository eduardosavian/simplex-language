package lang

Identifier :: distinct string
NilType    :: struct {}
String     :: string
Integer    :: i64
Real       :: f64
Rune       :: rune
Bool       :: bool

// A builtin type is a named type with a predefined layout and semantics
BultinType :: enum {
	Identifier,
	NilType,
	String,
	Integer,
	Real,
	Rune,
	Bool,
}

NamedType :: distinct Identifier

FunctionType :: struct {
	return_type: ^Type,
	args: []Type,
}

IndirectType :: struct {
	qualifiers: []Qualifier,
	named_type: NamedType,
}

// nil == Untyped
Type :: union {
	NamedType,
	IndirectType,
	FunctionType,
}

TypeFlag :: enum {
	Bultin,
	Nullable,
	Primitive,
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

builtin_type_names := map[Identifier]bool {
	"int"    = true,
	"real"   = true,
	"rune"   = true,
	"string" = true,
	"bool"   = true,
}

env_make :: proc() -> Environment {
	env := make(map[Identifier]SymbolInfo)
	return env
}

env_destroy :: proc(env: ^Environment){
	delete(env^)
}

