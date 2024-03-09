CXX ?= clang++

.PHONY: all clean

all:
	@ CXX=$(CXX) make -C simplex -j$$($$(nproc) * 2)

clean:
	@ CXX=$(CXX) make -C simplex clean
