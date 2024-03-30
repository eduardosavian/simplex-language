#!/bin/sh

# ANTLR4.jar Relative path
antlr4_path="tools/antlr4.jar"

# ANTLR4.jar Full path
antlr4_full_path=$(realpath "$antlr4_path")

# Language name, always will be Simplex
simplex_path="Simplex"

echo antlr4_full_path

# Run Test
#java org.antlr.v4.gui.TestRig "$simplex_path" $*
