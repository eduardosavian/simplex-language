#include "file.hpp"
#include <cstdio>
#include <string>

Pair<String, bool> read_file(const char* path){
	auto file = std::fopen(path, "rb");
	if(file == nullptr){
		return {"", false};
	}
	Defer(std::fclose(file));

	std::fseek(file, 0, SEEK_END);
	auto size = std::ftell(file);
	std::fseek(file, 0, SEEK_SET);

	auto data = new char[size];
	std::fread(&data[0], 1, size, file);

	auto str = std::basic_string_view<char>(data, size);
	return {str, true};
}

