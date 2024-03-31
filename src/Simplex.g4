grammar Simplex; // The Simplex Language or SLANG

// Entry point for a Simplex program
init : 'hello';

// Whitespace rule to skip spaces, tabs, and line breaks
WS : [ \t\r\n]+ -> skip;

// Rule for identifying identifiers (variable names, function names, etc.)
ID : [a-zA-Z][a-zA-Z0-9_]*;

// Primitive data types
INT : ID 'int';
LONG : ID 'long';
FLOAT : ID 'float';
DOUBLE : ID 'double';
CHAR : ID 'char';

// Branching keywords
IF : ID 'if';
ELSE : ID 'else';
SWITCH : ID 'switch';
CASE : ID 'case';

// Looping keywords
WHILE : ID 'while';
FOR : ID 'for';
BREAK : ID 'break';
RETURN : ID 'return';

// Function and type declaration keywords
PROC : ID 'proc';
TYPE : ID 'type';
STRUCT : ID 'struct';
STRING : ID 'string';

// Numeric literal representations
LITERAL_HEX : '0x'[0-9a-fA-F]+;
LITERAL_BIN : '0b'[0-1]+;
LITERAL_INT : [0-9]+;
LITERAL_FLOAT : [0-9]+'.'[0-9]+;
LITERAL_STRING : '".*?"';

// Delimiters
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

// Arithmetic operators
MINUS : '-';
PLUS : '+';
DIV : '/';
MUL : '*';
MOD : '%';

// Comparison operators
LT : '<';
GT : '>';
LT_EQ : '>=';
GT_EQ : '<=';
EQ : '==';
DIFF : '!=';

// Assignment and logical operators
ASSIGN : '=';
AND : '&&';
OR : '||';
NOT : '!';

// Bitwise operators
BIT_SHL : '<<';
BIT_SHR : '>>';
BIT_AND : '&';
BIT_OR : '|';
BIT_XOR : '^';
BIT_NOT : '~';

// Comments
SL_COMMENT : '//' ~[\r\n]* -> skip; // Single-line comments
ML_COMMENT : '/*' .*? '*/' -> skip; // Multi-line comments
