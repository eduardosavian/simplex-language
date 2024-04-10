package lang

import "core:fmt"

SRC : string : #load("example.ki")

main :: proc(){
	tokens := tokenize(SRC)
	print_tokens(tokens)
}

