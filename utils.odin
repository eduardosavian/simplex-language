package lang

import "core:fmt"

print_type :: proc(t: TypeExpression){
	for q in t.qualifiers {
		switch q {
		case .Pointer:
			fmt.print("pointer to ")
		case .Slice:
			fmt.print("slice of ")
		}
	}
	fmt.print(t.name)
}

print_scope :: proc(scope: Scope){
	for stmt in scope.body {
		switch s in stmt {
		case InlineStatement:
			switch v in s {
			case ExpressionStatement:
				print_expression(cast(^Expression)v)
			case Break:
				fmt.println("break")
			case Continue:
				fmt.println("continue")
			case Assignment:
			case VarDeclaration:
				fmt.println("var")
				if len(v.expressions) == 0 {
					for id in v.identifiers {
						fmt.printf("  %s: ", id)
						print_type(v.type)
						fmt.println()
					}
				}
				else {
					for id, i in v.identifiers {
						fmt.printf("  %s: ", id)
						print_type(v.type)
						fmt.printf(" = ")
						print_expression(v.expressions[i])
						fmt.println()
					}
				}
			}
			fmt.println(";")

		case If:
		case For:
		case Scope:
			print_scope(s)
		}
	}
}

print_expression :: proc(expr: ^Expression){
	if expr == nil { return }

	switch e in expr {
	case Primary:
		#partial switch _ in e{
		case String, Rune:
			fmt.printf("%q",  e)
		case:
			fmt.printf("%v",  e)
		}
	case Unary:
		op, ok := token_kind_to_string[e.operator]
		assert(ok, "Unkown op")
		fmt.printf("(%v ", op)
		print_expression(e.operand)
		fmt.print(")")
	case FunctionCall:
		fmt.printf("(")
		print_expression(e.func)
		if len(e.args) > 0 {
			fmt.print(" ")
			for r in e.args[:len(e.args) - 1] {
				print_expression(r)
				fmt.print(" ")
			}
			print_expression(e.args[len(e.args) - 1])
		}
		fmt.print(")")
	case Indexing:
		fmt.print("([] ")
		print_expression(e.object)
		fmt.print(" ")
		print_expression(e.index)
		fmt.print(")")
	case Binary:
		op, ok := token_kind_to_string[e.operator]
		assert(ok, "Unkown op")
		fmt.printf("(%v ", op)
		print_expression(e.left_side)
		fmt.print(" ")
		print_expression(e.right_side)
		fmt.print(")")
	case Group:
		fmt.print("(")
		print_expression(e.inner)
		fmt.print(")")
	}
}

print_tokens :: proc(tokens: []Token){
	indent_level := 0

	lookahead := Token{}

	for tk, i in tokens {
		lookahead = (i + 1) >= len(tokens) ? Token{} : tokens[i+1]
		if tk.kind == .CurlyOpen {
			indent_level += 1
		}

		if is_keyword(tk.lexeme) {
			fmt.printf("%s ", tk.lexeme)
			continue
		}

		name, ok := token_kind_to_string[tk.kind]
		if ok {
			if tk.kind == .CurlyClose || tk.kind == .CurlyOpen {
				fmt.printf("%s", name)
			}
			else {
				fmt.printf(" %s ", name)
			}
			continue
		}

		#partial switch tk.kind {
		case .Identifier:
			fmt.printf("$%s", tk.lexeme)
		case .String:
			fmt.printf("String(%q)", tk.payload.(string))
		case .Rune:
			fmt.printf("Rune(%q)", tk.payload.(rune))
		case .Int:
			fmt.printf("Int(%v)", tk.payload.(i64))
		case .Real:
			fmt.printf("Real(%v)", tk.payload.(f64))
		case .Comment:
			fmt.printf("Comment(%v)", tk.lexeme)
		case .LineBreak:
			fmt.println()
			if lookahead.kind == .CurlyClose {
				indent_level -= 1
			}
			for _ in 0..<indent_level{
				fmt.print("    ")
			}
		case: fmt.print(tk.kind)
		}
	}
}

@private
token_kind_to_string := map[TokenKind]string {
	.ParenOpen   = "(",
	.ParenClose  = ")",
	.SquareOpen  = "[",
	.SquareClose = "]",
	.CurlyOpen   = "{",
	.CurlyClose  = "}",

	.Comma     = ",",
	.Dot       = ".",
	.Colon     = ":",
	.Semicolon = ";",
	.Equal     = "=",
	.Caret     = "^",

	.EqualEqual   = "==",
	.NotEqual     = "!=",
	.Greater      = ">",
	.Lesser       = "<",
	.GreaterEqual = ">=",
	.LesserEqual  = "<=",

	.Plus   = "+",
	.Minus  = "-",
	.Star   = "*",
	.Slash  = "/",
	.Modulo = "%",

	.BitAnd     = "&",
	.BitOr      = "|",
	.BitXor     = "~",
	.ShiftLeft  = "<<",
	.ShiftRight = ">>",

	.LogicNot = "!",
	.LogicAnd = "&&",
	.LogicOr  = "||",
	.LogicXor = "~~",
}

is_keyword :: proc(s: string) -> bool {
	return s in keywords
}
