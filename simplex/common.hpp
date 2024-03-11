#ifndef _common_hpp_include_
#define _common_hpp_include_

#include <vector>
#include <array>
#include <string>

#define USE_CORE_BUILTIN_TYPES
#include "core.hpp"

using x::panic, x::min, x::max;

template<typename T>
using Vector = std::vector<T>;

template<typename T, usize N>
using Array = std::array<T, N>;

using String = std::string_view;

template <typename A, typename B>
struct Pair {
	A a{};
	B b{};
};

#endif /* Include guard */
