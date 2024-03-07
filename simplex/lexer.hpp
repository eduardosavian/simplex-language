#ifndef _lexer_hpp_include_
#define _lexer_hpp_include_

#include "common.hpp"

enum struct TokenKind : i32 {
	// Identifier
	Identifier = 1,

	// Reserved
	If, Else, For, Proc, Type, Return,

	// Pairs
	ParenOpen, ParenClose,
	SquareOpen, SquareClose,
	SquirlyOpen, SquirlyClose,

	// Punctuation
	Dot, Colon, Semicolon, Comma, Equal,

	// Comparison
	EqualEqual, NotEqual,
	Greater, Lesser,
	GreaterEqual, LesserEqual,

	// Literals
	String, Real, Integer, True, False, Nil,

	// Arithmetic
	Plus, Minus, Star, Slash,

	// Bitwise
	And, Or, Xor, ShiftLeft, ShiftRight,

	// Logic
	BoolNot,

	// Errors
	EndOfFile = -1,
	BadToken,
};

constexpr auto reserved_words = Array<Pair<String, TokenKind>, 9>({
	{"if", TokenKind::If},
	{"else", TokenKind::Else},
	{"for", TokenKind::For},
	{"proc", TokenKind::Proc},
	{"type", TokenKind::Type},
	{"return", TokenKind::Return},

	{"true", TokenKind::True},
	{"false", TokenKind::False},
	{"nil", TokenKind::Nil},
});


struct Token {
	union Payload {
		i64 integer;
		f64 real;
		String str;
	};

	TokenKind kind;
	String lexeme;
	Payload payload;

	Token(TokenKind kind, String lexeme = String{}, Payload payload = {})
	: kind(kind), lexeme(lexeme), payload{payload}
	{
		using K = TokenKind;
	}
};

struct Lexer {
	String source;
	Vector<Token> tokens;
	i64 previous = 0;
	i64 current = 0;
	i64 line = 0;

	Vector<Token> scan(String source);
	bool at_end() const;
	void push(Token const& tk);
	char peek(i64 delta) const;
	bool consume_on_match(char expected);
	void reset();
	[[nodiscard]]
	Token consume_identifier();
	[[nodiscard]]
	Token consume_number();
	[[nodiscard]]
	Token consume_string();

private:
	Token consume_number_decimal();
	Token consume_integer_bin();
	Token consume_integer_hex();
};


#endif /* Include guard */
