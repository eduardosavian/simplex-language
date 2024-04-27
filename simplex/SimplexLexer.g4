lexer grammar SimplexLexer;

// Whitespace rule to skip spaces, tabs, and line breaks
WS : [ \t\r\n]+ -> skip;


ECHO : 'ECHO';

// Branching keywords
IF : 'if';
ELSE : 'else';
ELIF : 'elif';
SWITCH : 'switch';
CASE : 'case';
DEFAULT : 'default';

// Looping keywords
CONTINUE : 'continue';
FOR : 'for';
BREAK : 'break';
RETURN : 'return';
DO : 'do' ;

// Function and type declaration keywords
FUN : 'fun';
RIGHT_ARROW : '->';
TYPE : 'type';
STRUCT : 'struct';

// Numeric literal representations
LITERAL_HEX : '0x'[0-9a-fA-F]+;
LITERAL_BIN : '0b'[0-1]+;
LITERAL_INT : [0-9]+;
LITERAL_FLOAT : [0-9]+'.'[0-9]+;
LITERAL_STRING : '"' (~["])* '"';
LITERAL_RUNE : '\\' ('\\' | 't' | 'b' | 'n' | '\u001B' | 'r' | .) ;

// Delimiters
DOT : '.';
COMMA : ',';
COLON : ':';
EOS : ';';
SQUARE_OPEN : '[';
SQUARE_CLOSE : ']';
CURLY_OPEN : '{';
CURLY_CLOSE : '}';
PAREN_OPEN : '(';
PAREN_CLOSE : ')';
CARET : '^';

TRUE : 'true';
FALSE : 'false';


NIL : 'nil';
// Arithmetic operators
MINUS : '-';
PLUS : '+';
SLASH : '/';
STAR : '*';
MOD : '%';

// Comparison operators
LT : '<';
GT : '>';
LT_EQ : '>=';
GT_EQ : '<=';
EQ_EQ : '==';
NOT_EQ : '!=';

// Assignment and logical operators
LOGIC_AND : '&&';
LOGIC_OR : '||';
LOGIC_NOT : '!';
LOGIC_XOR : '~~';

OR: '|';
EQ : '=';
AND : '&';
XOR : '~';

// Bitwise operators
SHIFT_LEFT : '<<';
SHIFT_RIGHT : '>>';

// Comments
SL_COMMENT : '//' ~[\r\n]* -> skip; // Single-line comments
ML_COMMENT : '/*' .*? '*/' -> skip; // Multi-line comments

// Rule for identifying identifiers (variable names, function names, etc.)
ID : [_a-zA-Z][a-zA-Z0-9_]*;