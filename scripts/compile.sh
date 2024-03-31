#!/bin/sh

# ANTLR4.jar Relative path
antlr4_path="../tools/antlr4.jar"

# ANTLR4.jar Full path
antlr4_full_path=$(realpath "$antlr4_path")

# Package name (if applicable)
package="../src/"

# Main name (if applicable)
main="Main.java"

# Language name, always will be Simplex
language="Simplex"

# Directory to store compiled classes
build_dir="../build"

# Create build directory if it doesn't exist
mkdir -p "$build_dir"

# Compile Java files into build directory
javac -cp "$antlr4_full_path:$CLASSPATH" -d "$build_dir" "${package}${language}"*.java "$package$main" -Xlint
