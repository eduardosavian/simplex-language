#!/bin/sh

# ANTLR4.jar Relative path
antlr4_path="../tools/antlr4.jar"

# ANTLR4.jar Full path
antlr4_full_path=$(realpath "$antlr4_path")

# Package directory
package_dir="../src/"

# Main class name
main_class="Main"

# Run Main.class
java -cp "$antlr4_full_path:$package_dir" "$main_class"
