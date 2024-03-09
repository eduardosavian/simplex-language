#include "test.hpp"
#include "../lexer.hpp"
#include "../utils.hpp"
#include "../core.hpp"

constexpr
void print_arr(Vector<Token> const& arr){
	std::printf("( ");
	for(auto const& e : arr){
		std::printf("%s ", token_name(e).c_str());
	}
	std::printf(")\n");
};

constexpr
void print_arr(x::DynamicArray<Token> const& arr){
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
			Expect(t, tokens[i].kind == correct.kind);
		}
	}

	{ // Identifiers
		constexpr String source =
			"if;else.for,proc(type)return true\tfalse:nil\n"
			"x: int = 1;"
			"y:bool=true;"
			"proc fib_rec()"
		;

		constexpr auto idlist = Array<Token, 16>{
			Token(K::If),
			Token(K::Else),
			Token(K::For),
			Token(K::Proc),
			Token(K::Type),
			Token(K::Return),
			Token(K::True),
			Token(K::False),
			Token(K::Nil),
			Token(K::Identifier, "x"),
			Token(K::Identifier, "int"),
			Token(K::Identifier, "y"),
			Token(K::Identifier, "bool"),
			Token(K::True),
			Token(K::Proc),
			Token(K::Identifier, "fib_rec"),
		};

		auto lexer = Lexer();
		auto tokens = lexer.tokenize(source);
		auto expect = x::DynamicArray<Token>(x::std_heap_allocator());
		for(auto const& e : idlist){ expect.append(e); }

		auto filtered = x::DynamicArray<Token>(x::std_heap_allocator());

		for(auto const& tk : tokens){
			if(is_reserved_identifier(tk) || (tk.kind == TokenKind::Identifier)){
				filtered.append(tk);
			}
		}

		Expect(t, expect.size() == filtered.size());
		for(usize i = 0; i < filtered.size(); i += 1){
			Expect(t, expect[i].kind == filtered[i].kind);
		}
	}
	{
		constexpr String source =
			"// Takes the factorial of a number\n"
			"proc fact(n: int) int {"
			"    if n < 0x2 {"
			"        return 1;"
			"    }"
			"    else {"
			"        return fact(n - 1) * n;"
			"    }"
			"}"
			"proc main(){"
			"    println(\"Hello world\");"
			"    /* Multi line  "
			"         comment  */"
			"    x : int = fact(10);"
			"    ok : bool = x > 0b10_01;"
			"    if ok {"
			"        println(\"10! = \", x);"
			"    }"
			"}"
		;

		auto lexer = Lexer();
		auto tokens = lexer.tokenize(source);

		for(auto tk : tokens){
			std::printf("%s\n", token_name(tk).c_str());
			// if(tk.kind == K::SquirlyOpen
			// 	|| tk.kind == K::SquirlyClose
			// 	|| tk.kind == K::Semicolon)
			// {
			// 	std::printf("\n");
			// }
		}
	}


	return t.report();
}
