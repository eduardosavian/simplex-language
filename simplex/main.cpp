#include "common.hpp"

#include "file.hpp"
#include "lexer.hpp"

#include "tests/lexer.test.cpp"

using x::panic;

int main(){
	auto [filedata, ok] = read_file("source.txt");
	Defer(delete[] filedata.data());

	if(!ok){
		panic("Could not open file");
	}

	auto lex = Lexer();
	auto tokens = lex.tokenize(filedata);


	auto b = true 
		&& test_lexer()

	;

	return int(!b);
}

