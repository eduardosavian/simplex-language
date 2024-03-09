#include "common.hpp"
#include "lexer.hpp"

#include <string>

#define TERM_RED "\e[1;31m"
#define TERM_RESET "\e[0m"

using x::panic;

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
	return current >= i64(source.size());
}

void Lexer::push(Token const& tk){
	tokens.push_back(tk);
}

char Lexer::peek(i64 delta) const {
	auto pos = current + delta;
	if(pos >= i64(source.size())){
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
	tokens.clear();
	current = 0;
	previous = 0;
	line = 1;
	error_count = 0;
	source = "";
}

Pair<Vector<Token>, i32> Lexer::tokenize(String source){
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

			case '&': {

				if(consume_on_match('&')){
					push(Token(K::LogicAnd));
				} else {
					push(Token(K::And));
				}
			} break;

			case '|': {
				if(consume_on_match('|')){
					push(Token(K::LogicOr));
				} else {
					push(Token(K::Or));
				}
			} break;

			case '~': {
				if(consume_on_match('~')){
					push(Token(K::LogicXor));
				} else {
					push(Token(K::Xor));
				}
			} break;

			case '+': { push(Token(K::Plus)); } break;
			case '-': { push(Token(K::Minus)); } break;
			case '*': { push(Token(K::Star)); } break;
			case '%': { push(Token(K::Mod)); } break;

			case '/': {
				if(peek(1) == '*'){
					push(tokenize_comment_multi_line());
				}
				else if (peek(1) == '/'){
					push(tokenize_comment_single_line());
				}
				else {
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
					push(Token(K::LogicNot));
				}
			} break;

			case '\'': {
				push(tokenize_char_literal());
			} break;

			// Ignore whitespace
			case '\n': line += 1;
			case '\t': case '\r': case ' ': break ;

			default: {
				if(is_alpha(c) || c == '_'){
					push(tokenize_identifier());
				}
				else if(is_decimal(c)){
					push(tokenize_number());
				}
				else if(c == '"'){
					push(tokenize_string());
				} else {
					emit_error("Unrecognized Token");
					push(Token(K::BadToken));
				}
			} break;
		}

		// Regular advance
		current += 1;
	}

	auto result = std::move(tokens);
	auto errors = error_count;

	reset();

	return {result, errors};
}

Token Lexer::tokenize_identifier(){
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
Token Lexer::tokenize_number(){
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
				panic("Invalid base prefix");
				base = -1;
			}
		}
	}

	switch(base){
		case 2: {
			return tokenize_integer_bin();
		} break;
		case 16: {
			return tokenize_integer_hex();
		} break;
		case 10: {
			return tokenize_number_decimal();
		} break;
	}

	return Token(TokenKind::BadToken);
}

Token Lexer::tokenize_number_decimal(){
	auto valid_digit = [](char c){
		return is_decimal(c) || (c == '.') ||(c == '_');
	};

	previous = current;
	// Advance to account for 0x prefix
	// current += 2;
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

	bool is_float = false;
	for(auto c : lexeme){
		if(c == '.'){
			is_float = true;
			break;
		}
	}

	auto payload = Token::Payload{};
	auto kind = TokenKind::BadToken;

	if(is_float){
		f64 n = std::stod(digits, 0);
		payload.real = n;
		kind = TokenKind::Real;
	} else {
		i64 n = std::stoll(digits, 0, 10);
		payload.integer = n;
		kind = TokenKind::Integer;
	}

	return Token(kind, lexeme, payload);
}

static constexpr
char parse_escape_sequence(char escaped){
	char c = 0;
	switch(escaped){
		case 'e':  c = '\e'; break;
		case 'n':  c = '\n'; break;
		case 'r':  c = '\r'; break;
		case 't':  c = '\t'; break;
		case 'b':  c = '\b'; break;
		case '\\': c = '\\'; break;
		case '\'': c = '\''; break;
		case '\"': c = '\"'; break;
	}

	return c;
}

Token Lexer::tokenize_char_literal(){
	previous = current;
	current += 1;

	char c = peek(0);
	if(c == '\\'){
		char c2 = peek(1);
		char end = peek(2);
		c = parse_escape_sequence(c2);
		current += 1; // Account for backslash
		if (c == 0 || c == '"'){
			emit_error("Invalid escape sequence");
			return Token(TokenKind::BadToken);
		}
	}
	else if(c == '\''){
		emit_error("Empty char literal is not allowed");
		return Token(TokenKind::BadToken);
	}

	if(peek(1) != '\''){
		emit_error("Unterminated char literal.");
		return Token(TokenKind::BadToken);
	}

	current += 2;
	auto lexeme = source.substr(previous, current - previous);
	auto payload = Token::Payload{};

	payload.character = c;
	return Token(TokenKind::Char, lexeme, payload);

}

Token Lexer::tokenize_integer_hex(){
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

Token Lexer::tokenize_integer_bin(){
	auto valid_digit = [](char c){
		return (c == '0') || (c == '1') || (c == '_');
	};

	previous = current;
	// Advance to account for 0b prefix
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

	i64 n = std::stoll(digits, 0, 2);

	auto payload = Token::Payload{};
	payload.integer = n;
	return Token(TokenKind::Integer, lexeme, payload);
}

// TODO: Handle escaped sequences
[[nodiscard]]
Token Lexer::tokenize_string(){
	// TODO: Escaping quotes
	current += 1;
	previous = current;
	while(source[current] != '"'){
		current += 1;
	}
	return Token(TokenKind::String, source.substr(previous, current - previous));
}

[[nodiscard]]
Token Lexer::tokenize_comment_single_line(){
	previous = current;

	while(!at_end()){
		if(peek(0) == '\n'){
			break;
		}
		current += 1;
	}

	auto lexeme = source.substr(previous, current - previous);

	return Token(TokenKind::Comment, lexeme);
}

[[nodiscard]]
Token Lexer::tokenize_comment_multi_line(){
	previous = current;

	while(!at_end()){
		auto c0 = peek(0) == '*';
		auto c1 = peek(1) == '/';

		if(c0 && c1){
			break;
		}

		current += 1;
	}

	current += 2; // Account for comment closing
	auto lexeme = source.substr(previous, current - previous);

	return Token(TokenKind::Comment, lexeme);
}

void Lexer::emit_error(String msg){
	error_count += 1;
	auto tmp = std::string(msg);
	std::fprintf(stderr, "[" TERM_RED "Lexing Error" TERM_RESET "] at line %d: %s\n", line, tmp.c_str());
}
