#include "test.hpp"
#include "../lexer.hpp"
#include "../utils.hpp"

constexpr
void print_arr(Vector<Token> const& arr){
	std::printf("( ");
	for(auto const& e : arr){
		std::printf("%s ", token_name(e).c_str());
	}
	std::printf(")\n");
};

bool test_lexer(){
	auto t = testing::Test("Lexer");
	using K = TokenKind;

	{ // Operators
		auto lexer = Lexer();
		constexpr String source =
			"+ - / * "
			"& | ~  << >> "
			"! && || ~~ "
			"== >= <= > < "
		;

		constexpr auto tklist = Array<Token, 18>{
			Token(K::Plus),
			Token(K::Minus),
			Token(K::Slash),
			Token(K::Star),
			Token(K::And),
			Token(K::Or),
			Token(K::Xor),
			Token(K::ShiftLeft),
			Token(K::ShiftRight),
			Token(K::LogicNot),
			Token(K::LogicAnd),
			Token(K::LogicOr),
			Token(K::LogicXor),
			Token(K::EqualEqual),
			Token(K::GreaterEqual),
			Token(K::LesserEqual),
			Token(K::Greater),
			Token(K::Lesser),
		};

		auto tokens = lexer.tokenize(source);
		auto expect = Vector<Token>();
		for(auto e : tklist){ expect.push_back(e); }

		for(usize i = 0; i < tokens.size(); i += 1){
			auto correct = tklist[i];
			Expect(t, tokens[i] == correct);
		}
	}

	{ // Identifiers
	}


	return t.report();
}
