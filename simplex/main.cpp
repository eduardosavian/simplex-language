#include "common.hpp"

#include "file.hpp"
#include "lexer.hpp"

#include "tests/lexer.test.cpp"

using x::panic;

int main(int argc, char** argv){
	if(argc < 2){
		std::fprintf(stderr, "Usage: %s [FILE]\n", argv[0]);
		return 1;
	}

	auto [filedata, ok] = read_file(argv[1]);
	Defer(delete[] filedata.data());

	if(!ok){
		panic("Could not open file");
	}

	auto lex = Lexer();
	auto [tokens, errors] = lex.tokenize(filedata);
	pretty_print_tokens(tokens);
	std::printf("\n\n");
	std::printf("Tokens processed: %zu\n"
		        "Errors Encountered: %d\n", tokens.size(), errors);

	return 0;
}

