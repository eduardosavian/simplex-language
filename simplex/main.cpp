#include "common.hpp"

#include "file.hpp"
#include "lexer.hpp"

#include "tests/lexer.test.cpp"

using x::panic;

int main(int argc, char** argv){
	if(argc < 2){ return 1; }
	auto [filedata, ok] = read_file(argv[1]);
	Defer(delete[] filedata.data());

	if(!ok){
		panic("Could not open file");
	}

	auto lex = Lexer();
	auto [tokens, _] = lex.tokenize(filedata);
	for(auto tk : tokens){
		std::printf("%s ", token_name(tk).c_str());
	}
	return 0;

	// auto b = true 
	// 	&& test_lexer()
	// ;
	//
	// return int(!b);
}

