#ifndef _common_hpp_include_
#define _common_hpp_include_

#include <utility>
#include <source_location>
#include <vector>
#include <array>
#include <string>

#include "types.hpp"

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

#define Caller_Location \
	std::source_location caller_location = std::source_location::current()

static void panic(const char* msg, Caller_Location){
	std::fprintf(stderr, "%s:%d Panic: %s\n", caller_location.file_name(), caller_location.line(), msg);
	std::abort();
}

namespace defer_impl {
	template <typename Fn>
	struct DeferredCall {
		Fn f;

		DeferredCall(Fn&& f)
			: f(std::move(f)) {}

		~DeferredCall(){
			f();
		}
	};
}

#define DEFER_GLUE_0(X_, Y_) X_##Y_
#define DEFER_GLUE_1(X_, Y_) DEFER_GLUE_0(X_, Y_)
#define DEFER_VAR_NAME(NAME_) DEFER_GLUE_1(NAME_, __COUNTER__)
#define Defer(BLK_) \
	defer_impl::DeferredCall DEFER_VAR_NAME(_deferred_call_) = [&](){ \
		do { \
			BLK_ \
			; \
		} while(0); \
	}

#endif /* Include guard */
