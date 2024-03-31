parser grammar SimplexParser;

options { tokenVocab=SimplexLexer; }

// Entry point for a Simplex program
program : statement+;

// Statement can be either a variable declaration, function declaration, or other statements
statement : variable_declaration
          | function_declaration
          | block
          | if_statement
          | if_else_statement
          | loop_statement
          | assignment_statement
          | RETURN SEMICOLON;

// Variable Declaration
// X : int = 2 + 2;
variable_declaration : ID COLON type (ASSIGN expression) SEMICOLON;

// Function Declaration
function_declaration : PROC type ID PARENTHESES_BEGIN parameter_list? PARENTHESES_END block;

// Parameter List
parameter_list : parameter (COMMA parameter)*;

// Parameter
parameter : type ID;

// Block
block : BRACES_BEGIN statement* BRACES_END;

// If Statement
if_statement : IF PARENTHESES_BEGIN expression PARENTHESES_END block;

// If Else Statement
if_else_statement : IF PARENTHESES_BEGIN expression PARENTHESES_END block ELSE block;

switch_cases : (switch_case)+;

switch_case : CASE literal COLON statement
            | DEFAULT COLON statement
            ;


// Loop Statements
loop_statement : while_loop | for_loop | do_while_loop;

while_loop : WHILE PARENTHESES_BEGIN expression PARENTHESES_END block;

for_loop : FOR PARENTHESES_BEGIN for_initializer? SEMICOLON expression? SEMICOLON for_update? PARENTHESES_END block;

for_initializer : variable_declaration | assignment_statement;

for_update : assignment_statement;

do_while_loop : DO block WHILE PARENTHESES_BEGIN expression PARENTHESES_END SEMICOLON;

// Assignment Statement
assignment_statement : ID ASSIGN expression SEMICOLON;

// Expression
expression : ID
           | literal
           | expression arithmetic_operator expression
           | expression comparison_operator expression
           | expression logical_operator expression
           | PARENTHESES_BEGIN expression PARENTHESES_END;

// Literal
literal : LITERAL_HEX
        | LITERAL_BIN
        | LITERAL_INT
        | LITERAL_FLOAT
        | LITERAL_STRING
        | LITERAL_CHAR;

// Type
type : INT | LONG | FLOAT | DOUBLE | CHAR;

// Arithmetic Operators
arithmetic_operator : PLUS | MINUS | MUL | DIV | MOD;

// Comparison Operators
comparison_operator : LT | GT | LT_EQ | GT_EQ | EQ | DIFF;

// Logical Operators
logical_operator : AND | OR | NOT;