parser grammar SimplexParser;

options { tokenVocab=SimplexLexer; }

program
	: statement*
;

inlineStatement
	: returnStatement
	| breakStatement
	| continueStatement
	| exprStatement
	| varDeclaration
	| varAssignment
;

returnStatement
	: RETURN expression?
;

breakStatement
	: BREAK
;

continueStatement
	: CONTINUE
;

statement
	: inlineStatement EOS
	| simpleFor
	| tripleFor
	| doFor
	| ifStatement
	| funcDeclaration
	| scope
	| debuger
;

debuger
	: DEBUG
;

varDeclaration
	: identifierList COLON typeExpression (EQ expressionList)?
;

identifierList
	: ID (COMMA ID)*
;

varAssignment
	: expressionList EQ expressionList
;

sliceIndicator
	: SQUARE_OPEN SQUARE_CLOSE
;

typeExpression
	: sliceIndicator typeExpression
	| CARET typeExpression
	| ID
;

funcDeclaration
	: FUN ID PAREN_OPEN fieldList? PAREN_CLOSE (RIGHT_ARROW typeExpression)? scope
;

field
	: ID COLON typeExpression
;

fieldList
	: field (COMMA field)*
;

scope
	: CURLY_OPEN statement* CURLY_CLOSE
;

simpleFor
	: FOR expression scope
;

tripleFor
	: FOR inlineStatement EOS expression EOS inlineStatement scope
;
	
doFor
	: DO scope FOR inlineStatement EOS
;

ifStatement
	: IF expression scope (ELSE ifStatement)* (ELSE scope)?
;

exprStatement
	: expression
;

expression
	: logicDisjunction
;

logicDisjunction
	: logicConjunction ((LOGIC_OR | LOGIC_XOR) logicConjunction)*
;

logicConjunction
	: comparison ((LOGIC_AND) comparison)*
;

comparison
	: term ((EQ_EQ | NOT_EQ | GT | GT_EQ | LT | LT_EQ) term)*
;

term
	: factor ((PLUS | MINUS | OR | XOR) factor)*
;

factor
	: bitShift ((STAR | SLASH | MOD | AND) bitShift)*
;

bitShift
	: unary ((SHIFT_LEFT | SHIFT_RIGHT) unary)*
;

unary
	: ((MINUS | PLUS | XOR | LOGIC_NOT) unary)
	| primary
;

primary
	: indexing
	| ID
	| integer
	| real
	| LITERAL_STRING
	| LITERAL_RUNE
	| TRUE
	| FALSE
	| NIL
	| group
	| functionCall
;

group
	: PAREN_OPEN expression PAREN_CLOSE
;

functionCall
	: ID PAREN_OPEN expressionList PAREN_CLOSE
	| ID PAREN_OPEN PAREN_CLOSE
;

expressionList
	: expression (COMMA expression)*
;

integer
	: LITERAL_HEX
	| LITERAL_BIN
	| LITERAL_INT
;

real
	: LITERAL_FLOAT
;

indexing
	: ID SQUARE_OPEN expression SQUARE_CLOSE
;
