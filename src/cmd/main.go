package main

import (
	"flag"
	"fmt"
)

var flagVar string

func init() {
	flag.StringVar(&flagVar, "function", "", "Please use 'lexer'")
}

func main() {
	fmt.Println("The Simplex Language")

	flag.Parse()
	// if argsLength < 2 || argsLength > 2 {
	// 	fmt.Println("Use simplex --[arg] <file>.sp")
	// }

	// funcArg := args[0]
	// switch funcArg {
	// case lexer:

	fmt.Println("flagvar has value", flagVar)

}
