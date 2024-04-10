package lang

import "core:fmt"

print_tokens :: proc(tokens: []Token){
	for tk in tokens {
		if is_keyword(tk.lexeme) {
			fmt.printf("%s ", tk.lexeme)
			continue
		}
		fmt.print(tk.kind)
		if tk.kind == .Identifier {
			fmt.printf("(%s)", tk.lexeme)
		}
		fmt.print(" ")
	}
}

is_keyword :: proc(s: string) -> bool {
	return s in keywords
}
