#include "common.hpp"
#include "file.hpp"
#include "lexer.hpp"

#include "utils.hpp"

int main(){
	auto [filedata, ok] = read_file("source.txt");
	if(!ok){
		panic("Could not open file");
	}
	auto lex = Lexer();
	auto tokens = lex.scan(filedata);

	usize i = 0;
	for(auto tk : tokens){
		auto s = token_name(tk);
		std::printf("[%zu] %s\n", i, s.c_str());
		i += 1;
	}
	std::printf("Processed %zu tokens.\n", tokens.size());
}

