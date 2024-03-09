CXX ?= clang++
CFLAGS := -std=c++20 -O1 -g -pipe -fno-strict-aliasing -Wall -Wextra -fsanitize=address
LDFLAGS := -fuse-ld=mold

SOURCES := file.cpp lexer.cpp
HEADERS := common.hpp file.hpp lexer.hpp utils.hpp
TESTS := tests/lexer.test.cpp

.PHONY: clean run

run: bin/simpcc
	@ ./bin/simpcc

bin/simpcc: main.cpp bin/parser.o bin/lexer.o bin/file.o $(TESTS)
	$(CXX) $(CFLAGS) \
		main.cpp bin/parser.o bin/lexer.o bin/file.o $(LDFLAGS) \
		-o bin/simpcc

bin/file.o: file.cpp $(HEADERS)
	$(CXX) $(CFLAGS) -c file.cpp -o bin/file.o

bin/lexer.o: lexer.cpp $(HEADERS)
	$(CXX) $(CFLAGS) -c lexer.cpp -o bin/lexer.o

bin/parser.o: parser.cpp $(HEADERS)
	$(CXX) $(CFLAGS) -c parser.cpp -o bin/parser.o


clean:
	rm -f bin/*
