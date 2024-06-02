package lang

import "core:fmt"
import str "core:strings"

sum :: proc(s: $S/[]$E) -> E {
	acc := E{}
	for e in s {
		acc += e
	}
	return acc
}

contains :: proc(s: $T/[]$E, e: E) -> bool {
	for x in  s {
		if x == e {
			return true
		}
	}
	return false
}

format_type :: proc(t: Type) -> string {
	sb := str.builder_make(allocator = context.temp_allocator)
	for mod in t.modifiers {
		switch mod in mod {
		case Pointer: fmt.sbprint(&sb, "^")
		case Array: fmt.sbprintf(&sb, "[%v]", mod.size)
		}
	}
	fmt.sbprintf(&sb, "%v", t.primitive)
	resize(&sb.buf, len(sb.buf))
	return string(sb.buf[:])
}

print_type :: proc(t: Type){
	for mod in t.modifiers {
		switch mod in mod {
		case Pointer: fmt.print("^")
		case Array: fmt.printf("[%v]", mod.size)
		}
	}
	fmt.printf("%v", t.primitive)
}

print_env :: proc(scope: ^Scope, mangled_names := false){
	print_env_rec(scope, 0, "<Global>", mangled_names)
}

print_env_rec :: proc(scope: ^Scope, n: int, header := "", mangled_names := false){
	if len(header) == 0 {
		printf(n, "---| <Scope> |---\n", header)
	}
	else {
		printf(n, "---| %v |---\n", header)
	}

	for name, sym in scope.env {
		if sym.kind == .Type {
			printf(n, "%v: type\n", name)
			continue
		}

		if sym.kind == .Function {
			printf(n, "%v: func(", name)
			for arg in sym.args {
				print_type(arg)
				printf(0, " ")
			}

			printf(0, ") -> ")
			print_type(sym.type)
			fmt.println()
			continue
		}

		if sym.kind == .Parameter {
			printf(n, "(arg) %v: ", name)
			print_type(sym.type)
			fmt.println()
			continue
		}

		if mangled_names {
			printf(n, "%v (%v): ", name, sym.static_section_name)
		}
		else {
			printf(n, "%v: ", name)
		}
		print_type(sym.type)
		fmt.println()
	}
	printf(n, "----------\n")

	for stmt in scope.body {
		#partial switch &v in stmt {
		case For:
			print_env_rec(&v.scope, n + 1, "<For>", mangled_names = mangled_names)
		case If:
			print_env_rec(&v.scope, n + 1, "<If>",mangled_names =  mangled_names)
			current := v.else_branch
			for current != nil {
				if_stmt, is_if := current.(If)
				else_stmt, is_else := current.(Scope)
				if is_if {
					print_env_rec(&if_stmt.scope, n + 1, "<Elif>",mangled_names =  mangled_names)
					current = if_stmt.else_branch
				}
				else if is_else {
					print_env_rec(&else_stmt, n + 1, "<Else>",mangled_names =  mangled_names)
					current = nil
				}
				else {
					unreachable()
				}
			}

		case FunctionDef:
			print_env_rec(&v.scope, n + 1, string(stmt.(FunctionDef).name),mangled_names =  mangled_names)
		case Scope:
			print_env_rec(&v, n + 1,mangled_names =  mangled_names)
		}
	}
}

print_parser_type :: proc(type: ParserType){
	for q in type.modifiers {
		switch q in q {
		case Pointer:
			fmt.print("pointer to ")
		case Array:
			fmt.printf("array of %v ", q.size)
		}
	}
	fmt.print(type.name)
}

printf :: proc(level: int, format: string, args: ..any){
	for _ in 0..<level {
		fmt.print("    ")
	}
	fmt.printf(format, ..args)
}

print_inline_stmt :: proc(s: InlineStatement, n: int){
	switch v in s {
	case ExpressionStatement:
		printf(n, "")
		print_expression(v.inner)
		fmt.print(";\n")
	case Return:
		printf(n, "return ")
		print_expression(v.value)
		fmt.print(";\n")
	case Break:
		printf(n, "break;\n")
	case Continue:
		printf(n, "continue;\n")
	case Assignment:
		printf(n, "assign\n")
		for _, i in v.left_side {
			printf(n + 1, "")
			print_expression(v.left_side[i])
			fmt.print(" = ")
			print_expression(v.right_side[i])
			fmt.println()
		}
		printf(n, ";\n")

	case VarDeclaration:
		printf(n, "var\n")
		if len(v.expressions) == 0 {
			for id in v.identifiers {
				printf(n + 1, "%s: ", id)
				print_parser_type(v.type)
				fmt.println()
			}
		}
		else {
			for id, i in v.identifiers {
				printf(n + 1, "%s: ", id)
				print_parser_type(v.type)
				fmt.printf(" = ")
				print_expression(v.expressions[i])
				fmt.println()
			}
		}
		printf(n, ";\n")
	}
}


print_ir :: proc(prog: []Instruction){
	for instruction, i in prog {
		if instruction.opcode in IMMEDIATE_OPS {
			if len(instruction.label) == 0 {
				fmt.printfln("%06x    %v %v", i, instruction.opcode, instruction.immediate)
			}
			else {
				fmt.printfln("%06x    %v %v", i, instruction.opcode, instruction.label)
			}
		}
		else {
			fmt.printfln("%06x    %v", i, instruction.opcode)
		}
	}
}

print_scope :: proc(scope: Scope, n := 0){
	if n > 0 {
		printf(n - 1, "{{\n")
	}
	defer if n > 0 {
		printf(n - 1, "}}\n")
	}

	for stmt in scope.body {
		switch s in stmt {
		case InlineStatement:
			print_inline_stmt(s, n)

		case FunctionDef:
			printf(n, "func %v ( ", s.name)
			for a in s.args {
				printf(n, "")
				print_parser_type(a.type)
				fmt.print(" ")
			}
			fmt.print(") -> ")
			print_parser_type(s.return_type)
			fmt.print("\n")
			print_scope(s.scope, n + 1)

		case If:
			printf(n, "if ")
			print_expression(s.condition)
			fmt.println()
			print_scope(s.scope, n + 1)

			cur := s.else_branch
			for cur != nil {
				#partial switch blk in cur {
				case If:
					printf(n, "else if ")
					print_expression(s.condition)
					print_scope(blk.scope, n + 1)
					cur = cur.(If).else_branch
				case Scope:
					printf(n, "else ")
					print_scope(blk, n + 1)
					cur = nil
				}
			}

		case For:
			if s.pre_stmt != nil {
				printf(n, "for\n")
				print_inline_stmt(s.pre_stmt.(InlineStatement), n+1)
				printf(n+1, "")
				print_expression(s.condition)
				fmt.print("\n")
				print_inline_stmt(s.post_stmt.(InlineStatement), n+1)
				print_scope(s.scope, n + 1)
			}
			else {
				printf(n, "for ")
				print_expression(s.condition)
				fmt.println()
				print_scope(s.scope, n + 1)
			}

		case Scope:
			print_scope(s, n + 1)

		}
	}
}

print_expression :: proc(expr: ^Expression){
	if expr == nil { return }

	switch e in expr.value {
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
			fmt.printf("Int(%v)", tk.payload.(Int))
		case .Real:
			fmt.printf("Real(%v)", tk.payload.(Real))
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
	.Arrow     = "->",

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
