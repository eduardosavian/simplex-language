CXX ?= clang++
CFLAGS := -std=c++20 -O1 -g -pipe -fno-strict-aliasing -Wall -Wextra
LDFLAGS := -fuse-ld=mold

SOURCES := file.cpp lexer.cpp
HEADERS := common.hpp file.hpp lexer.hpp types.hpp utils.hpp

.PHONY: clean dir-setup

bin/simpcc: bin/main.o bin/parser.o bin/lexer.o bin/file.o
	$(CXX) $(CFLAGS) \
		bin/main.o bin/parser.o bin/lexer.o bin/file.o $(LDFLAGS) \
		-o bin/simpcc

bin/file.o: file.cpp $(HEADERS)
	$(CXX) $(CFLAGS) -c file.cpp -o bin/file.o

bin/lexer.o: lexer.cpp $(HEADERS)
	$(CXX) $(CFLAGS) -c lexer.cpp -o bin/lexer.o

bin/parser.o: parser.cpp $(HEADERS)
	$(CXX) $(CFLAGS) -c parser.cpp -o bin/parser.o

bin/main.o:
	$(CXX) $(CFLAGS) -c main.cpp -o bin/main.o

clean:
	rm -f bin/*
