package lang

import "core:fmt"

SRC : string : #load("example.ki")

// kasj kljads kljlkajsd fjlk kalj
main :: proc() {
	tokens := tokenize(SRC)
	print_tokens(tokens)
	fmt.println(precedence(TokenKind.LogicOr))
}


