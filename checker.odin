package lang

Identifier :: distinct string
NilType    :: struct {}
String     :: string
Integer    :: i64
Real       :: f64
Rune       :: rune
Bool       :: bool

BultinType :: enum {
	Identifier,
	NilType,
	String,
	Integer,
	Real,
	Rune,
	Bool,
}

// TODO: unions and/or structured layout
TypeInfo :: struct {
}

VariableInfo :: struct {
	type: TypeExpression,
}

FunctionInfo :: struct {
	return_type: TypeExpression,
	args: []TypeExpression,
}

TypeFlag :: enum {
	Bultin,
	Nullable,
	Primitive,
}

TypeFlags :: bit_set[TypeFlag]

Type :: struct {
	info: union { VariableInfo, FunctionInfo, },
	size: i16,
	flags: TypeFlags,
}

Environment :: struct {
	definitions: map[Identifier]Type,
}

builtin_types := map[Identifier]TypeFlags{
	"int"    = {.Bultin, .Primitive},
	"real"   = {.Bultin, .Primitive},
	"rune"   = {.Bultin, .Primitive},
	"string" = {.Bultin},
	"bool"   = {.Bultin, .Primitive},
}

