parser grammar SimplexParser;

options { tokenVocab=SimplexLexer; }

// Entry point for a Simplex program
prog : ID;

// Other grammar rules can be defined here as per the Simplex language syntax.
