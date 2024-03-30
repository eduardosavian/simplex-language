grammar Simplex; // Define a grammar called Simplex
init : 'hello' ; // match keyword hello followed by an identifier

// Tokens
// WS : [\s\t\r\n]+ -> skip;
WS : [ \t\r\n]+ -> skip;

ID : [a-zA-Z][a-zA-Z0-9_]*;

INT : ID 'int';
LONG : ID 'long';
FLOAT : ID 'float';
DOUBLE : ID 'double';
CHAR : ID 'char';
IF : ID 'if';
ELSE : ID 'else';
SWITCH : ID 'switch';
CASE : ID 'case';
WHILE : ID 'while';
FOR : ID 'for';
BREAK : ID 'break';
RETURN : ID 'return';
PROC : ID 'proc';
TYPE : ID 'type';
STRUCT : ID 'struct';
STRING : ID 'string';

LITERAL_HEX : '0x'[0-9a-fA-F]+;
LITERAL_BIN : '0b'[0-1]+;
LITERAL_INT : [0-9]+;
LITERAL_FLOAT : [0-9]+'.'[0-9]+;
LITERAL_STRING : '".*?"';
// LITERAL_CHAR : '\''(\\'|\\\"|\\\\|\\t|\\b|\\n|\\e|\\r|.)'\'';


DOT : '.';
COMMA : ',';
COLON : ':';
SEMICOLON : ';';
BRACKET_BEGIN : '[';
BRACKET_END : ']';
BRACES_BEGIN : '{';
BRACES_END : '}';
PARENTHESES_BEGIN : '(';
PARENTHESES_END : ')';

MINUS : '-';
PLUS : '+';
DIV : '/';
MUL : '*';
MOD : '%';

LT : '<';
GT : '>';
LT_EQ : '>=';
GT_EQ : '<=';
EQ : '==';
DIFF : '!=';

ASSIGN : '=';

AND : '&&';
OR : '||';
NOT : '!';

BIT_SHL : '<<';
BIT_SHR : '>>';
BIT_AND : '&';
BIT_OR : '|';
BIT_XOR : '^';
BIT_NOT : '~';

SL_COMMENT : '//' ~[\r\n]* -> skip;
ML_COMMENT : '/*' .*? '*/' -> skip;