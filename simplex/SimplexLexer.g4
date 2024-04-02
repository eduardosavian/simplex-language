lexer grammar SimplexLexer;

// Whitespace rule to skip spaces, tabs, and line breaks
WS : [ \t\r\n]+ -> skip;

// Branching keywords
IF : 'if';
ELSE : 'else';
SWITCH : 'switch';
CASE : 'case';
DEFAULT : 'default';

// Looping keywords
WHILE : 'while';
FOR : 'for';
BREAK : 'break';
RETURN : 'return';
DO : 'do' ;

// Function and type declaration keywords
FUNCTION : 'func';
ARROW : '->';
TYPE : 'type';
STRUCT : 'struct';

// Numeric literal representations
LITERAL_HEX : '0x'[0-9a-fA-F]+;
LITERAL_BIN : '0b'[0-1]+;
LITERAL_INT : [0-9]+;
LITERAL_FLOAT : [0-9]+'.'[0-9]+;
LITERAL_STRING : '".*?"';
LITERAL_CHAR : '\\' ('\\' | 't' | 'b' | 'n' | '\u001B' | 'r' | .) ;
// LITERAL_CHAR : '(\\'|\\\"|\\\\|\\t|\\b|\\n|\\e|\\r|.)'

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
CARET : '^';

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
BIT_NOT : '~';

// Comments
SL_COMMENT : '//' ~[\r\n]* -> skip; // Single-line comments
ML_COMMENT : '/*' .*? '*/' -> skip; // Multi-line comments

// Rule for identifying identifiers (variable names, function names, etc.)
ID : [_a-zA-Z][a-zA-Z0-9_]*;