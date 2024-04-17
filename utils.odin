package lang

import "core:fmt"

print_expression :: proc(expr: ^Expression){
	if expr == nil { return }

	switch e in expr {
	case Primary:
		fmt.printf("%v",  e)
	case Unary:
		op, ok := token_kind_to_string[e.operator]
		assert(ok, "Unkown op")
		fmt.printf("(%v ", op)
		print_expression(e.operand)
		fmt.print(")")
	case FunctionCall:
		fmt.printf("(")
		print_expression(e.func)
		fmt.print(" ")
		for r in e.args {
			print_expression(r)
			fmt.print(" ")
		}
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
	.ParenOpen   = "Po",
	.ParenClose  = "Pc",
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
