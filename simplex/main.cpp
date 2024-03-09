#include "common.hpp"

#include "file.hpp"
#include "lexer.hpp"

#include "tests/lexer.test.cpp"

int main(){
	auto [filedata, ok] = read_file("source.txt");
	Defer(delete[] filedata.data());
	if(!ok){
		panic("Could not open file");
	}
	auto lex = Lexer();
	auto tokens = lex.tokenize(filedata);
	// usize i = 0;
	// for(auto tk : tokens){
	// 	auto s = token_name(tk);
	// 	std::printf("[%zu] %s\n", i, s.c_str());
	// 	i += 1;
	// }
	// std::printf("Processed %zu tokens.\n", tokens.size());

	auto b = true 
		&& test_lexer()
	;

	return int(!b);
}

