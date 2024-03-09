CXX ?= clang++

.PHONY: all clean

run:
	@ CXX=$(CXX) make -C simplex -j$$(nproc)

clean:
	@ CXX=$(CXX) make -C simplex clean
