parser grammar SimplexParser;

options { tokenVocab=SimplexLexer; }

// Entry point for a Simplex program
program : top_level*;

top_level : function_definition
          | variable_declaration
          | type_definition;

// Statement can be either a variable declaration, function declaration, or other statements
statement : (variable_declaration
          | assignment_statement
          | BREAK
          | RETURN expression?) SEMICOLON;

type_definition : TYPE ID (type_expression SEMICOLON
                | struct_definition);
                
// ARRAY
type_expression : ID
                | type_prefix ID;

type_prefix : slice_prefix type_prefix*
            | CARET type_prefix*;

slice_prefix : BRACKET_BEGIN BRACKET_END;

// Variable Declaration

variable_declaration : ID COLON type_expression;

variable_definition : variable_declaration (ASSIGN expression)?;

assignment_statement : ID ASSIGN expression;

struct_definition : STRUCT BRACES_BEGIN record_list? BRACES_END;

record_list : variable_declaration (COMMA variable_declaration)+
            | variable_declaration COMMA?;

// Function Declaration
function_declaration : FUNCTION ID PARENTHESES_BEGIN record_list PARENTHESES_END ARROW ID;

function_definition : function_declaration block;

// Parameter
parameter : ID COLON type_expression;

// Block
block : BRACES_BEGIN statement* BRACES_END;

// Expression
expression : ID
           | literal
           | expression arithmetic_operator expression
           | expression comparison_operator expression
           | expression logical_operator expression
           | grouped_expression;

grouped_expression : PARENTHESES_BEGIN expression PARENTHESES_END;

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