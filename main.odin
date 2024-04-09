package lang

import "core:fmt"

SRC :: `fun main(){`+
       `println("\tHello, \"World\"")`+
       `}`

main :: proc(){
	tokens := tokenize(SRC)
	fmt.println(tokens)
}
