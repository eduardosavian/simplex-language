#!/bin/sh

# ANTLR4.jar Relative path
antlr4_path="/tools/antlr4.jar"

# ANTLR4.jar Full path
antlr4_full_path=$(realpath "$antlr4_path")

# Package name
package="src"

# Language name, always will be Simplex
language="Simplex"

# Compile Java files
javac -cp "$antlr4_full_path:$CLASSPATH" $language*.java Main.java #-Xlint
