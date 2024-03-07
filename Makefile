CXX ?= clang++

.PHONY: all clean

all:
	@ CXX=$(CXX) make -C simplex -j$$(nproc)

clean:
	@ CXX=$(CXX) make -C simplex clean
