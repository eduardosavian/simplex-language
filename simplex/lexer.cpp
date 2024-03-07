#include "common.hpp"
#include "lexer.hpp"

#include <string>

static
bool is_alpha(char c){
	return ((c >= 'a') && (c <= 'z')) ||
	((c >= 'A') && (c <= 'Z'));
}

static
bool is_decimal(char c){
	return (c >= '0') && (c <= '9');
}

static
bool is_hex(char c){
	auto hex_lower = (c >= 'a') && (c <= 'f');
	auto hex_upper = (c >= 'A') && (c <= 'F');
	return is_decimal(c) || hex_lower || hex_upper;
}


bool Lexer::at_end() const {
	return current >= source.size();
}

void Lexer::push(Token const& tk){
	tokens.push_back(tk);
}

char Lexer::peek(i64 delta) const {
	auto pos = current + delta;
	if(pos >= source.size()){
		return 0;
	}
	return source[pos];
}

bool Lexer::consume_on_match(char expected){
	if(peek(1) == expected){
		current += 1;
		return true;
	}
	return false;
}

void Lexer::reset(){
	current = 0;
	previous = 0;
	line = 0;
	source = "";
	tokens.clear();
}

Vector<Token> Lexer::scan(String source){
	using K = TokenKind;
	this->source = source;
	while(!at_end()){
		char c = source[current];

		switch(c){
			case '{': { push(Token(K::SquirlyOpen)); } break;
			case '}': { push(Token(K::SquirlyClose)); } break;
			case '(': { push(Token(K::ParenOpen)); } break;
			case ')': { push(Token(K::ParenClose)); } break;
			case '[': { push(Token(K::SquareOpen)); } break;
			case ']': { push(Token(K::SquareClose)); } break;

			case '.': { push(Token(K::Dot)); } break;
			case ':': { push(Token(K::Colon)); } break;
			case ';': { push(Token(K::Semicolon)); } break;
			case ',': { push(Token(K::Comma)); } break;

			case '&': { push(Token(K::And)); } break;
			case '|': { push(Token(K::Or)); } break;
			case '~': { push(Token(K::Xor)); } break;

			case '+': { push(Token(K::Plus)); } break;
			case '-': { push(Token(K::Minus)); } break;
			case '*': { push(Token(K::Star)); } break;

			case '/': {
				if(peek(1) == '*'){
					panic("TODO: Implement comment");
				} else {
					push(Token(K::Slash));
				}
			} break;

			case '=': {
				if(consume_on_match('=')){
					push(Token(K::EqualEqual));
				}
				else {
					push(Token(K::Equal));
				}
			} break;

			case '>': {
				if(consume_on_match('=')){
					push(Token(K::GreaterEqual));
				}
				else if (consume_on_match('>')) {
					push(Token(K::ShiftRight));
				}
				else {
					push(Token(K::Greater));
				}
			} break;

			case '<': {
				if(consume_on_match('=')){
					push(Token(K::LesserEqual));
				}
				else if (consume_on_match('<')) {
					push(Token(K::ShiftLeft));
				}
				else {
					push(Token(K::Lesser));
				}
			} break;

			case '!': {
				if(consume_on_match('=')){
					push(Token(K::NotEqual));
				}
				else {
					push(Token(K::BoolNot));
				}
			} break;

			// Ignore whitespace
			case '\n': case '\r': case ' ': break ;

			default: {
				if(is_alpha(c) || c == '_'){
					// std::cout << "CUR: " << c << "\n";
					push(consume_identifier());
					// current -= 1;
				}
				else if(is_decimal(c)){
					push(consume_number());
				}
				else if(c == '"'){
					push(consume_string());
				}
			} break;
		}
		// Regular advance
		current += 1;

	}

	auto tks = std::move(tokens);
	reset();
	return tks;
}

Token Lexer::consume_identifier(){
	constexpr auto valid_id = [](char c){
		return is_alpha(c) || is_decimal(c) || (c == '_');
	};

	previous = current;
	while(!at_end()){
		char c = source[current];
		if(valid_id(c)){
			current += 1;
		} else {
			break;
		}
	}

	auto lexeme = source.substr(previous, current - previous);
	current -= 1;

	auto kind = TokenKind::Identifier;
	for(auto const& p : reserved_words){
		if(lexeme == p.a){
			kind = p.b;
			break;
		}
	}

	return Token(kind, lexeme);
}

[[nodiscard]]
Token Lexer::consume_number(){
	int base = 10;
	{ // Discover int base
		char c0 = peek(0);
		char c1 = peek(1);
		if((c0 == '0') && is_alpha(c1)){
			if(c1 == 'x'){
				base = 16;
			}
			else if (c1 == 'b'){
				base = 2;
			}
			// Invalid base prefix
			else {
				std::printf("Prefix:%c%c\n", c0,c1);
				panic("Invalid base prefix");
				base = -1;
			}
		}
	}

	switch(base){
		case 2: {
			return consume_integer_bin();
		} break;
		case 16: {
			return consume_integer_hex();
		} break;
		case 10: {
			return consume_number_decimal();
		} break;
	}

	return Token(TokenKind::BadToken);
}

Token Lexer::consume_number_decimal(){
	return Token(TokenKind::Integer);
}

Token Lexer::consume_integer_hex(){
	auto valid_digit = [](char c){
		return is_hex(c) || (c == '_');
	};

	previous = current;
	// Advance to account for 0x prefix
	current += 2;
	auto digits = std::string();
	while(!at_end()){
		char c = source[current];
		if(valid_digit(c)){
			current += 1;
			if(c != '_'){
				digits.push_back(c);
			}
		} else {
			break;
		}
	}

	auto lexeme = source.substr(previous, current - previous);
	current -= 1;

	i64 n = std::stoll(digits, 0, 16);

	auto payload = Token::Payload{};
	payload.integer = n;
	return Token(TokenKind::Integer, lexeme, payload);
}

Token Lexer::consume_integer_bin(){
	return Token(TokenKind::Integer);
}

[[nodiscard]]
Token Lexer::consume_string(){
	// TODO: Escaping quotes
	current += 1;
	previous = current;
	while(source[current] != '"'){
		current += 1;
	}
	return Token(TokenKind::String, source.substr(previous, current - previous));
}
