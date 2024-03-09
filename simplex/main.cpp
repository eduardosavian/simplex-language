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
	auto [tokens, errors] = lex.tokenize(filedata);
	for(auto tk : tokens){
		std::printf("%s ", token_name(tk).c_str());
	}
	std::printf("\n");

	std::printf("Processed %zu tokens, %d errors encountered\n", tokens.size(), errors);

	return 0;
}

