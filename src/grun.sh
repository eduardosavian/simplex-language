#!/bin/sh

# Prefix the first argument with "src/"
first_arg="$1"

java org.antlr.v4.gui.TestRig "$first_arg" "${@:2}"