package lang

import "core:fmt"

SRC : string : #load("example.em")

main :: proc(){
	tokens := tokenize(SRC)
	// fmt.print(tokens)
	print_tokens(tokens)
}
