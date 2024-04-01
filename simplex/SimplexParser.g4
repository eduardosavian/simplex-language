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

// ARRAY
type_expression : ID
                | type_prefix ID;

type_prefix : slice_prefix type_prefix*
            | CARET type_prefix*;

slice_prefix : BRACKET_BEGIN BRACKET_END;

// Variable Declaration
variable_declaration : ID COLON type_expression (ASSIGN expression)? SEMICOLON;

// Function Declaration
function_declaration : FUNCTION ID PARENTHESES_BEGIN parameter_list? PARENTHESES_END ARROY ID block;

// Parameter List
parameter_list : parameter (COMMA parameter_list)*;

// Parameter
parameter : ID COLON ID;

// Block
block : BRACES_BEGIN statement* BRACES_END;

// If Statement
if_statement : IF PARENTHESES_BEGIN expression PARENTHESES_END block;

// If Else Statement
if_else_statement : IF PARENTHESES_BEGIN expression PARENTHESES_END block ELSE block;

// Loop Statements
loop_statement : while_loop | for_loop | do_while_loop;

while_loop : WHILE expression block;

for_loop : FOR for_initializer? SEMICOLON expression? SEMICOLON for_update? block;

for_initializer : variable_declaration | assignment_statement;

for_update : assignment_statement;

do_while_loop : DO block WHILE expression SEMICOLON;

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


// Arithmetic Operators
arithmetic_operator : PLUS | MINUS | MUL | DIV | MOD;

// Comparison Operators
comparison_operator : LT | GT | LT_EQ | GT_EQ | EQ | DIFF;

// Logical Operators
logical_operator : AND | OR | NOT;