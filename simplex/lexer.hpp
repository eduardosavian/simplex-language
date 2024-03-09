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
	String, Real, Integer, Char, True, False, Nil,

	// Arithmetic
	Plus, Minus, Star, Slash, Mod,

	// Bitwise
	And, Or, Xor, ShiftLeft, ShiftRight,

	// Logic
	LogicNot, LogicAnd, LogicOr, LogicXor,

	// Comments, still processed as tokens for convenience
	Comment,

	// Errors
	EndOfFile = -1,
	BadToken = -2,
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
		char character;
	};

	TokenKind kind;
	String lexeme;
	Payload payload;
	bool panic_mode = false;

	constexpr
	Token()
		: kind{TokenKind::EndOfFile}, lexeme{}, payload{0} {}

	constexpr
	Token(TokenKind kind, String lexeme = String{}, Payload payload = {})
		: kind(kind), lexeme(lexeme), payload{payload} {}

	constexpr
	Token(Token const& tk)
		: kind(tk.kind), lexeme(tk.lexeme), payload{tk.payload} {}

	constexpr
	void operator=(Token const& tk) {
		kind = tk.kind;
		lexeme = tk.lexeme;
		payload = tk.payload;
	}
};

// Check if token is some reserved Identifier, this includes builtins and keywords
static constexpr
bool is_reserved_identifier(Token tk){
	switch (tk.kind) {
		case TokenKind::If:
		case TokenKind::Else:
		case TokenKind::For:
		case TokenKind::Proc:
		case TokenKind::Type:
		case TokenKind::Return:
		case TokenKind::True:
		case TokenKind::False:
		case TokenKind::Nil: {
			return true;
		}break;

		default: break;
	}
	return false;
}

struct Lexer {
	String source;
	Vector<Token> tokens;
	i64 previous = 0;
	i64 current = 0;
	i64 line = 0;
	i32 error_count = 0;

	[[nodiscard]]
	Pair<Vector<Token>, bool> tokenize(String source);

private:
	char peek(i64 delta) const;
	void push(Token const& tk);
	void reset();
	bool at_end() const;
	bool consume_on_match(char expected);
	void emit_error(String msg);

	[[nodiscard]] Token tokenize_identifier();
	[[nodiscard]] Token tokenize_number();
	[[nodiscard]] Token tokenize_string();
	[[nodiscard]] Token tokenize_char_literal();
	[[nodiscard]] Token tokenize_number_decimal();
	[[nodiscard]] Token tokenize_integer_bin();
	[[nodiscard]] Token tokenize_integer_hex();
	[[nodiscard]] Token tokenize_comment_single_line();
	[[nodiscard]] Token tokenize_comment_multi_line();
};


#endif /* Include guard */
