// Random assortment of utility functions

#ifndef _utils_cpp_include_
#define _utils_cpp_include_

#include "common.hpp"
#include "lexer.hpp"

constexpr
std::string token_name(Token const& tk) {
	using K = TokenKind;
	auto wrap = [](String prefix, String contents){
		auto s = std::string(prefix);
		s += "<";
		s += contents;
		s += ">";
		return s;
	};

	auto wrap_with_payload = [](String prefix, String contents, TokenKind kind, Token::Payload payload){
		auto s = std::string(prefix);
		s += "<";
		s += contents;

		switch(kind){
			case TokenKind::Integer: {
				s += ", ";
				s += std::to_string(payload.integer);
			} break;
			default: break;
		}

		s += ">";
		return s;
	};

	std::string n = "?";
	switch (tk.kind) {
		case K::Identifier: n = wrap("Id", tk.lexeme); break;
		case K::If:         n = "if"; break;
		case K::Else:       n = "else";  break;
		case K::For:        n = "for";  break;
		case K::Proc:       n = "proc"; break;
		case K::Type:       n = "type"; break;
		case K::Return:     n = "return"; break;

		case K::ParenOpen:    n = "("; break;
		case K::ParenClose:   n = ")"; break;
		case K::SquareOpen:   n = "["; break;
		case K::SquareClose:  n = "]"; break;
		case K::SquirlyOpen:  n = "{"; break;
		case K::SquirlyClose: n = "}"; break;

		case K::Dot:       n = "."; break;
		case K::Colon:     n = ":"; break;
		case K::Semicolon: n = ";"; break;
		case K::Comma:     n = ","; break;
		case K::Equal:     n = "="; break;

		case K::EqualEqual:   n = "=="; break;
		case K::NotEqual:     n = "!="; break;
		case K::Greater:      n = ">"; break;
		case K::Lesser:       n = "<"; break;
		case K::GreaterEqual: n = ">="; break;
		case K::LesserEqual:  n = "<="; break;

		case K::String:  n = wrap("String", tk.lexeme); break;
		case K::Real:    n = wrap("Real", tk.lexeme); break;
		case K::Integer: n = wrap_with_payload("Int", tk.lexeme, K::Integer, tk.payload); break;

		case K::True:  n = "true"; break;
		case K::False: n = "false"; break;
		case K::Nil:   n = "nil"; break;

		case K::Plus:       n = "+"; break;
		case K::Minus:      n = "-"; break;
		case K::Star:       n = "*"; break;
		case K::Slash:      n = "/"; break;
		case K::And:        n = "&"; break;
		case K::Or:         n = "|"; break;
		case K::Xor:        n = "~"; break;
		case K::ShiftLeft:  n = "<<"; break;
		case K::ShiftRight: n = ">>"; break;
		case K::BoolNot:    n = "!"; break;

		case K::EndOfFile: n = "<EOF>"; break;
		case K::BadToken:  n = "<BAD TOKEN>"; break;
	}

	return n;
}


#endif /* Include guard */
