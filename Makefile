CXX ?= clang++

.PHONY: all clean

run:
	@ CXX=$(CXX) make -C simplex -j$$(nproc)
	@ ./simplex/bin/simpcc source.txt

clean:
	@ CXX=$(CXX) make -C simplex clean
