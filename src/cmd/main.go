package main

import (
	"flag"
	"fmt"

	"xyz.simplex-language/compiler"
	_ "xyz.simplex-language/compiler"
	_ "xyz.simplex-language/utils"
)

var (
	flagFunction string
)

func main() {
	fmt.Println("The Simplex Language")

	flag.Parse()
	// if argsLength < 2 || argsLength > 2 {
	// 	fmt.Println("Use simplex --[arg] <file>.sp")
	// }

	// funcArg := args[0]
	// switch funcArg {
	// case lexer:

	// file,err := os.ReadFile()
	compiler.Scanner("a")

}
