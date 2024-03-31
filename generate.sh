#!/bin/sh

# ANTLR4.jar Relative path
antlr4_path="libs/antlr4.jar"

# ANTLR4.jar Full path
antlr4_full_path=$(realpath "$antlr4_path")

# Source path
src_path="src/"

# Language path, always will be Simplex
simplex_path="$src_path/Simplex.g4"

# Remove existing .java and class files in src/
find $src_path -type f \( -name "*.java" -and -not -name "Main.java" \) -delete
find $src_path -type f -name "*.class" -delete
find $src_path -type f -name "*.tokens" -delete
find $src_path -type f -name "*.interp" -delete

# Run the ANTLR 4 tool
java -cp "$antlr4_full_path:$CLASSPATH" org.antlr.v4.Tool "$simplex_path"