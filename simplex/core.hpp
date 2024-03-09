#ifndef _core_hpp_include_
#define _core_hpp_include_

// Core: A simple single header library to enhance C++ /////////////////////////
// See end of file for License information.
//
// This is a single header full of utilities that I consider to be either
// essential or extremely important for a programming language like C++.
//
// Main features include:
// - No usage of `virtual`.
// - No exceptions, errors are just values.
// - No implict memory allocations.
// - Defer.
// - Slice-centric design to prevent bounds checking problems.
// - Custom Allocator support without "polymorphic resource" madness.
// - Vector arithmetic built in.
// - Track calls with the `Caller_Location` macro.
// - DynamicArray, Stack, Queue, Map (TODO) that use the Allocator interface
//   to get resources.
// - Memory Arena, convenience functions such as make() and make_slice().
// - Environment constexpr variables and macros for conditional compilation.
// - Still be able to take advantage of newer C++20 features.
// - TODO: String type that is UTF-8 centric.
//
// Requires C++20 or above, you can make this work with C++17 if you get rid of
// the Caller_Location feature.

// Primitive Types: Built-ins //////////////////////////////////////////////////
#ifndef _types_hpp_include_
#define _types_hpp_include_

#include <cstddef>
#include <cstdint>

namespace x {
using i8  = int8_t;
using i16 = int16_t;
using i32 = int32_t;
using i64 = int64_t;
using u8  = uint8_t;
using u16 = uint16_t;
using u32 = uint32_t;
using u64 = uint64_t;

using usize = size_t;
using isize = ptrdiff_t;

using uintptr = uintptr_t;

using byte = unsigned char;
using rune = u32;

// Some machines don't have access to float 16
#ifndef DISABLE_FLOAT16
using f16 = _Float16;
#endif
using f32 = float;
using f64 = double;

#ifndef __STDC_NO_COMPLEX_
using complex64  = _Complex float;
using complex128 = _Complex double;
#endif

// Make sure this isn't some weird platform
static_assert(sizeof(usize) == sizeof(isize), "Mismatched isize and usize");
static_assert(sizeof(f16) == 2, "Wrong size for f16");
static_assert(sizeof(f32) == 4, "Wrong size for f32");
static_assert(sizeof(f64) == 8, "Wrong size for f64");
static_assert(sizeof(complex64) == 8, "Wrong size for complex64");
static_assert(sizeof(complex128) == 16, "Wrong size for complex128");
}

#ifdef USE_CORE_BUILTIN_TYPES
using x::i8;
using x::i16;
using x::i32;
using x::i64;
using x::u8;
using x::u16;
using x::u32;
using x::u64;
using x::usize;
using x::isize;
using x::uintptr;
using x::byte;
using x::rune;
#ifndef DISABLE_FLOAT16
using x::f16;
#endif
using x::f32;
using x::f64;
#ifndef __STDC_NO_COMPLEX_
using x::complex64;
using x::complex128;
#endif
#endif

#endif /* Include guard */
// Environment and Standard Includes ///////////////////////////////////////////
#ifndef _environment_hpp_include_
#define _environment_hpp_include_

#include <source_location>
#include <bit>

namespace x {
using std::bit_cast;
using SourceLocation = std::source_location;

// NOTE: Do **NOT** change the order of the ifdefs
#if defined(__clang__)
	#define COMPILER_VENDOR_CLANG 1
#elif defined(__GNUC__) && !defined(__clang__)
	#define COMPILER_VENDOR_GCC 1
#elif defined(_MSVC_VER)
	#define COMPILER_VENDOR_MSVC 1
#else
	#define COMPILER_VENDOR_UNKNOWN 1
#endif

#if COMPILER_VENDOR_CLANG || COMPILER_VENDOR_GCC
#define USE_BUILTIN_MEM_FUNCTIONS 1
#else
#include <cstring>
#endif

namespace env {
enum struct CompilerVendor : u8 {
	Unknown = 0,
	Clang, GCC, MSVC,
};

constexpr auto compiler_vendor =
#if COMPILER_VENDOR_CLANG
	CompilerVendor::Clang
#elif COMPILER_VENDOR_GCC
	CompilerVendor::GCC
#elif COMPILER_VENDOR_MSVC
	CompilerVendor::MSVC
#else
	#warning "Unknown Compiler Vendor: This may result in degraded performance."
	CompilerVendor::Unknown
#endif
;

constexpr char const* const compiler_vendor_name =
	compiler_vendor == CompilerVendor::Clang ? "clang"
	: compiler_vendor == CompilerVendor::GCC ? "gcc"
	: compiler_vendor == CompilerVendor::MSVC ? "msvc"
	: "<unknown>"
;

constexpr bool use_builtin_mem_functions =
	bool(USE_BUILTIN_MEM_FUNCTIONS)
;

constexpr bool assert_enabled =
#ifdef DISABLE_ASSERT
	false
#else
	true
#endif
;

constexpr bool debug_build =
#if defined(NDEBUG) || defined(RELEASE_BUILD)
	false
#endif
	true
;

constexpr bool bounds_checking_enabled =
#ifdef DISABLE_BOUNDS_CHECKING
	false
#else
	true
#endif
;
}

// To use as a default parameter to functions that represent the
// caller's location
#define Caller_Location \
	[[maybe_unused]] \
	x::SourceLocation const& caller_location = x::SourceLocation::current()

}

#endif /* Include guard */
// General Utility /////////////////////////////////////////////////////////////
#ifndef _utility_hpp_include_
#define _utility_hpp_include_
namespace x {
// Absolute value
template<typename T>
constexpr
T abs(T const& x){
	if(x < 0){
		return -x;
	}
	return x;
}

// Maximum
template<typename T>
constexpr
T max(T const& a, T const& b){
	if(b > a){ return b; }
	return a;
}

// Minimum
template<typename T>
constexpr
T min(T const& a, T const& b){
	if(b < a){ return b; }
	return a;
}

// Maximum
template<typename T, typename ...Rest>
constexpr
T max(T const& a, T const& b, Rest&& ...rest){
	if(b > a){
		return max(b, rest...);
	}
	return max(a, rest...);
}

// Minimum
template<typename T, typename ...Rest>
constexpr
T min(T const& a, T const& b, Rest&& ...rest){
	if(b < a){
		return min(b, rest...);
	}
	return min(a, rest...);
}

// Make x fit the rage mini..maxi (inclusive)
template<typename T>
constexpr
T clamp(T const& mini, T const& x, T const& maxi){
	return min(max(x, mini), maxi);
}

namespace typing {
template<typename T>
struct RemoveReferenceType {typedef T Type; };
template<typename T>
struct RemoveReferenceType<T&> {typedef T Type; };
template<typename T>
struct RemoveReferenceType<T&&> {typedef T Type; };

template<typename T, T v>
struct IntegralConstant {
	static constexpr T value = v;
	typedef T ValueType;
	constexpr operator ValueType() { return value; }
};

using TrueType  = IntegralConstant<bool, true>;
using FalseType = IntegralConstant<bool, false>;

template<typename A, typename B>
struct SameType : FalseType {};

template<typename T>
struct SameType<T, T> : TrueType {};

template<typename A, typename B>
constexpr auto same_as = SameType<A, B>::value;

template<typename T>
struct IsLValueReference : FalseType {};
template<typename T>
struct IsLValueReference<T&> : TrueType {};
template<typename T>
struct IsLValueReference<T&&> : FalseType {};

template<typename T>
struct IsRValueReference : FalseType {};
template<typename T>
struct IsRValueReference<T&> : FalseType {};
template<typename T>
struct IsRValueReference<T&&> : TrueType {};

template<typename T>
using RemoveReference = typename RemoveReferenceType<T>::Type;

template<typename T>
constexpr bool is_lvalue_ref = IsLValueReference<T>::value;

template<typename T>
constexpr bool is_rvalue_ref = IsRValueReference<T>::value;
}

// Cast x to rvalue reference
template<typename T>
constexpr
typing::RemoveReference<T>&& move(T&& x) noexcept {
	using Rv = typing::RemoveReference<T>&&;
	return static_cast<Rv>(x);
}

// Contitionally moves x, if and only if, x is an rvalue reference.
// Requires passing template type explicitly. This is used to implement
// "perfect forwarding"
template<typename T>
constexpr
T&& forward(typing::RemoveReference<T>& x) noexcept {
	return static_cast<T&&>(x);
}

// Contitionally moves x, if and only if, x is an rvalue reference.
// Requires passing template type explicitly. This is used to implement
// "perfect forwarding"
template<typename T>
constexpr
T&& forward(typing::RemoveReference<T>&& x) noexcept {
	static_assert(
		!typing::is_lvalue_ref<T>,
		"Cannot use forward() to convert an rvalue to an lvalue"
	);
	return static_cast<T&&>(x);
}

// Swap values of a and b
template<typename T>
constexpr
void swap(T& a, T& b) noexcept {
	T t = x::move(b);
	b   = x::move(a);
	a   = x::move(t);
}

// Replaces x with val and returns the old value of x
template<typename T, typename U = T>
T exchange(T& x, U&& val) noexcept {
	T t = x::move(x);
	x   = x::forward<U>(val);
	return t;
}

template<typename A, typename B = A>
struct Pair {
	A a;
	B b;
};

// Check if an error enum is zero.
// If it is a boolean, check if it's true (ok)
template<typename EnumType>
inline
bool error_ok(EnumType e){
	return (isize)(e) == 0;
}

template<>
inline
bool error_ok<bool>(bool e){
	return e;
}

#define Or_Return(ERR_) if(!error_ok((ERR_))){ return ERR_; }

}

#endif /* Include guard */
// Defer ///////////////////////////////////////////////////////////////////////
#ifndef _defer_hpp_include_
#define _defer_hpp_include_
namespace x::defer_impl {
	template <typename Fn>
	struct DeferredCall {
		Fn f;

		DeferredCall(Fn&& f)
			: f(x::move(f)) {}

		~DeferredCall(){
			f();
		}
	};
}

#define DEFER_GLUE_0(X_, Y_) X_##Y_
#define DEFER_GLUE_1(X_, Y_) DEFER_GLUE_0(X_, Y_)
#define DEFER_VAR_NAME(NAME_) DEFER_GLUE_1(NAME_, __COUNTER__)
#define Defer(BLK_) \
	x::defer_impl::DeferredCall DEFER_VAR_NAME(_deferred_call_) = [&](){ \
		do { \
			BLK_ \
			; \
		} while(0); \
	}
#endif /* Include guard */
// Assertions and Bounds checking //////////////////////////////////////////////
#ifndef _assert_hpp_include_
#define _assert_hpp_include_

#include <cstdio>
#include <cstdlib>

namespace x {
namespace impl {
[[maybe_unused]] static
void report_location(SourceLocation const& loc){
	std::fprintf(stderr, "%s:%d ", loc.file_name(), loc.line());
}

[[maybe_unused]] static
void report_location_and_function(SourceLocation const& loc){
	std::fprintf(stderr, "%s:%d %s ",
		loc.file_name(),
		loc.line(),
		loc.function_name());
}
}

// Causes a fatal error that cannot be recovered from
[[maybe_unused]] static
void panic(char const* const msg){
	do {
		std::fprintf(stderr, "Panic: %s\n", msg);
		std::abort();
	} while(1);
}

// Checks a predicate and panics if it is false
[[maybe_unused]] static
void panic_assert(
	bool predicate,
	const char* msg = "",
	Caller_Location
){
	if constexpr(env::assert_enabled){
		[[unlikely]]
		if(!predicate){
			impl::report_location(caller_location);
			std::fprintf(stderr, "%s ", msg);
			panic("Assertion failure");
		}
	}
}

// Checks a predicate and panics if it is false, enabled only in debug builds
[[maybe_unused]] static
void debug_assert(
	bool predicate,
	const char* msg = "",
	Caller_Location
){
	if constexpr(env::assert_enabled && !env::debug_build){
		[[unlikely]]
		if(!predicate){
			impl::report_location(caller_location);
			std::fprintf(stderr, "%s ", msg);
			panic("Assertion failure");
		}
	}
}

// Same as assert_expr, but used specifically for bounds checking
[[maybe_unused]] static
void bounds_check(
	bool predicate,
	Caller_Location
){
	if constexpr(env::bounds_checking_enabled){
		[[unlikely]]
		if(!predicate){
			impl::report_location_and_function(caller_location);
			panic("Bounds checking error.");
		}
	}
}

[[maybe_unused]] static
void unreachable(Caller_Location){
	if constexpr(env::assert_enabled){
		impl::report_location(caller_location);
		panic("Unreachable code");
	}
}

[[maybe_unused]] static
void unimplemented(Caller_Location){
	if constexpr(env::assert_enabled){
		impl::report_location_and_function(caller_location);
		panic("Unimplemented code");
	}
}

#ifndef DISABLE_ASSERT
#define Assert(PRED_) x::assert_expr(PRED_, #PRED_)
#else
#define Assert(PRED_, MSG_)
#endif
}
#endif /* Include guard */
// Primitive Types: Slice //////////////////////////////////////////////////////
#ifndef _slice_hpp_include_
#define _slice_hpp_include_
namespace x {
// A Slice is simply a pointer to contigous memory and a length, this is a much
// safer and useful construct than C's usual pointer decay.
template<typename T>
struct slice {
	constexpr
	usize size() const {
		return length;
	}

	constexpr
	T* raw_data() const {
		return &data[0];
	}

	constexpr
	T& operator[](usize idx){
		bounds_check(idx < length);
		return data[idx];
	}

	constexpr
	T const& operator[](usize idx) const {
		bounds_check(idx < length);
		return data[idx];
	}

	constexpr
	bool empty() const {
		return (data == nullptr) || (length == 0);
	}

	// Implicit comparison is not allowed.
	bool operator==(slice<T> const&) = delete;

	slice(T* ptr, usize length)
		: data{ptr}, length{length} {}

	slice()
		: data{nullptr}, length{0} {}

	slice(slice const& s)
		: data{s.data}, length{s.length} {}

	void operator=(slice const& s){
		data = s.data;
		length = s.length;
	}

	slice(slice&& s){
		data   = x::exchange(s.data, nullptr);
		length = x::exchange(s.length, 0);
	}

	void operator=(slice&& s){
		data   = x::exchange(s.data, nullptr);
		length = x::exchange(s.length, 0);
	}

	slice<T> sub_slice(usize start, usize end) const {
		bounds_check(end <= length);
		bounds_check(start <= length);
		if(start >= end){ return slice<T>(); }

		return slice<T>(&data[start], end - start);
	}

	struct Iterator {
		constexpr
		Iterator(T* data)
			: ptr_val(data) {}
		T& operator*() const { return *ptr_val; }
		T* operator->() const { return ptr_val; }
		constexpr
		auto& operator++(){ ptr_val += 1; return *this; }
		constexpr
		bool operator==(Iterator const& it){ return ptr_val == it.ptr_val; }
		constexpr
		bool operator!=(Iterator const& it){ return ptr_val != it.ptr_val; }
	private:
		T* ptr_val;
	};

	struct ConstIterator {
		constexpr
		ConstIterator(T const* data)
			: ptr_val(data) {}
		T const& operator*() const { return *ptr_val; }
		T* operator->() const { return ptr_val; }
		constexpr
		auto& operator++(){ ptr_val += 1; return *this; }
		constexpr
		bool operator==(ConstIterator const& it){ return ptr_val == it.ptr_val; }
		constexpr
		bool operator!=(ConstIterator const& it){ return ptr_val != it.ptr_val; }

	private:
		T const* ptr_val;
	};
	constexpr
	auto begin(){
		return Iterator(data);
	};

	constexpr
	auto end(){
		return Iterator(&data[length]);
	};

	constexpr
	auto begin() const {
		return ConstIterator(data);
	};

	constexpr
	auto end() const {
		return ConstIterator(&data[length]);
	};

	T* data;
	usize length;
};

template<typename T>
constexpr
bool slice_equal(slice<T> const& l, slice<T> const& r){
	if(l.size() != r.size()){
		return false;
	}

	auto ld = l.raw_data();
	auto rd = r.raw_data();
	for(usize i = 0; i < l.size(); i += 1){
		if(ld[i] != rd[i]){
			return false;
		}
	}
	return true;
}
}

#endif /* Include guard */
// Primitive Types: Array //////////////////////////////////////////////////////
#ifndef _array_hpp_include_
#define _array_hpp_include_
namespace x {
// An array is a fixed sized homogenous collection of values, usually used for
// math. Element wise operations are implemented for this struct to allow for
// vector arithmetic.
template<typename T, usize N>
struct array {
	constexpr
	usize size() const {
		return N;
	}

	constexpr
	T& operator[](usize idx){
		return data[idx];
	}

	constexpr
	T const& operator[](usize idx) const {
		return data[idx];
	}

	struct Iterator {
		Iterator(T* data)
			: ptr_val(data) {}
		T& operator*() const { return *ptr_val; }
		T* operator->() const { return ptr_val; }
		constexpr
		auto& operator++(){ ptr_val += 1; return *this; }
		constexpr
		bool operator==(Iterator const& it){ return ptr_val == it.ptr_val; }
		constexpr
		bool operator!=(Iterator const& it){ return ptr_val != it.ptr_val; }
	private:
		T* ptr_val;
	};

	struct ConstIterator {
		ConstIterator(T const* data)
			: ptr_val(data) {}
		T const& operator*() const { return *ptr_val; }
		T* operator->() const { return ptr_val; }
		constexpr
		auto& operator++(){ ptr_val += 1; return *this; }
		constexpr
		bool operator==(ConstIterator const& it){ return ptr_val == it.ptr_val; }
		constexpr
		bool operator!=(ConstIterator const& it){ return ptr_val != it.ptr_val; }

	private:
		T const* ptr_val;
	};

	constexpr
	auto begin(){
		return Iterator(&data[0]);
	}

	constexpr
	auto end(){
		return Iterator(&data[N - 1]);
	}

	constexpr
	auto begin() const {
		return ConstIterator(&data[0]);
	}

	constexpr
	auto end() const {
		return ConstIterator(&data[N - 1]);
	}

	T data[N];
};

template<typename T, usize N, usize I, typename U>
constexpr
void fill_arr_with_param_pack(array<T, N>& v, U&& elem){
	static_assert(I < N, "Out of bounds");
	v[I] = static_cast<T>(elem);
}

template<typename T, usize N, usize I = 0, typename U,typename... Args>
constexpr
void fill_arr_with_param_pack(array<T, N>& v, U&& elem, Args&& ...indices){
	static_assert(I < N, "Out of bounds");
	v[I] = static_cast<T>(elem);
	fill_arr_with_param_pack<T, N, I+1>(v, x::forward<Args>(indices)...);
}

template<typename T, usize N, typename U>
constexpr
auto swizzle(const array<T, N>& v, U&& idx){
	array<T, 1> res;
	res[0] = v[idx];
	return res;
}

template<typename T, usize N, typename... Index>
constexpr
auto swizzle(const array<T, N>& v, Index&& ...indices){
	constexpr usize L  = sizeof...(indices);
	array<usize, L> idxv;
	array<T, L> res;

	fill_arr_with_param_pack(idxv, x::forward<Index>(indices)...);
	for(usize i = 0; i < L; i += 1){
		res[i] = v[idxv[i]];
	}

	return res;
}

// Note: All operator overloads were auto generated.
template<typename T, usize N>
constexpr static inline
array<T, N> operator+(array<T, N> const& a, array<T, N> const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] + b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator-(array<T, N> const& a, array<T, N> const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] - b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator*(array<T, N> const& a, array<T, N> const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] * b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator/(array<T, N> const& a, array<T, N> const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] / b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator%(array<T, N> const& a, array<T, N> const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] % b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator&(array<T, N> const& a, array<T, N> const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] & b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator|(array<T, N> const& a, array<T, N> const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] | b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator^(array<T, N> const& a, array<T, N> const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] ^ b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator<<(array<T, N> const& a, array<T, N> const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] << b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator>>(array<T, N> const& a, array<T, N> const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] >> b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator+(array<T, N> const& a, T const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] + b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator-(array<T, N> const& a, T const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] - b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator*(array<T, N> const& a, T const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] * b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator/(array<T, N> const& a, T const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] / b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator%(array<T, N> const& a, T const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] % b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator&(array<T, N> const& a, T const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] & b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator|(array<T, N> const& a, T const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] | b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator^(array<T, N> const& a, T const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] ^ b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator<<(array<T, N> const& a, T const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] << b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator>>(array<T, N> const& a, T const& b){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] >> b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<bool, N> operator==(array<T, N> const& a, array<T, N> const& b){
	array<bool, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] == b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<bool, N> operator!=(array<T, N> const& a, array<T, N> const& b){
	array<bool, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] != b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<bool, N> operator>(array<T, N> const& a, array<T, N> const& b){
	array<bool, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] > b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<bool, N> operator<(array<T, N> const& a, array<T, N> const& b){
	array<bool, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] < b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<bool, N> operator>=(array<T, N> const& a, array<T, N> const& b){
	array<bool, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] >= b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<bool, N> operator<=(array<T, N> const& a, array<T, N> const& b){
	array<bool, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] <= b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<bool, N> operator==(array<T, N> const& a, T const& b){
	array<bool, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] == b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<bool, N> operator!=(array<T, N> const& a, T const& b){
	array<bool, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] != b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<bool, N> operator>(array<T, N> const& a, T const& b){
	array<bool, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] > b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<bool, N> operator<(array<T, N> const& a, T const& b){
	array<bool, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] < b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<bool, N> operator>=(array<T, N> const& a, T const& b){
	array<bool, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] >= b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<bool, N> operator<=(array<T, N> const& a, T const& b){
	array<bool, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = a[i] <= b; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator-(array<T, N> const& a){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = - a[i]; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator~(array<T, N> const& a){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = ~ a[i]; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator!(array<T, N> const& a){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = ! a[i]; }
	return res;
}
template<typename T, usize N>
constexpr static inline
array<T, N> operator+(array<T, N> const& a){
	array<T, N> res;
	for(usize i = 0; i < N; i += 1){ res[i] = + a[i]; }
	return res;
}
}
#endif /* Include guard */
// Memory //////////////////////////////////////////////////////////////////////
#ifndef _memory_hpp_include_
#define _memory_hpp_include_
namespace x {
// Set nbytes of ptr to val
[[maybe_unused]] static
void* mem_set(void* ptr, u8 val, usize nbytes){
	#if USE_BUILTIN_MEM_FUNCTIONS
	__builtin_memset(ptr, val, nbytes);
	#else
	memset(ptr, val, nbytes);
	#endif
	return ptr;
}

template<typename T>
constexpr
bool valid_alignment(T n){
	return (n > 0) && ((n & (n - 1)) == 0);
}

// Copy nbytes from source to dest
[[maybe_unused]] static
void* mem_copy(void* dest, const void* source, usize nbytes){
	#if USE_BUILTIN_MEM_FUNCTIONS
	__builtin_memcpy(dest, source, nbytes);
	#else
	memcpy(dest, source, nbytes);
	#endif
	return dest;
}

// Copies slice using mem_copy, will not run copy/move assignment
template<typename T>
slice<T> slice_raw_copy(slice<T> dest, slice<T> source, usize n){
	bounds_check((n <= dest.size()) && (n <= source.size()));
	mem_copy(dest.raw_data(), source.raw_data(), n * sizeof(T));
	return dest;
}

// Copies slice regularly, will run copy assignment
template<typename T>
slice<T> slice_copy(slice<T> dest, slice<T> source, usize n){
	bounds_check((n <= dest.size()) && (n <= source.size()));
	auto d = dest.raw_data();
	auto s = source.raw_data();

	for(usize i = 0; i < n; i += 1){
		d[i] = s[i];
	}
	return dest;
}

// Moves n elements of source to dest individually
template<typename T>
slice<T> slice_move(slice<T> dest, slice<T> source, usize n){
	bounds_check((n <= dest.size()) && (n <= source.size()));
	auto d = dest.raw_data();
	auto s = source.raw_data();

	for(usize i = 0; i < n; i += 1){
		d[i] = x::move(s[i]);
	}

	return dest;
}

// Align an integer number to a particular alignment
template<typename T>
constexpr
T align_forward(T value, T align){
	T mod = value % align;

	if(mod > 0){
		value += (align - mod);
	}

	return value;
}
// Memory Allocator Interface
struct Allocator {
	enum struct Op : u8 {
		Alloc,   // Allocate a zero-initialized block of memory
		Resize,  // Try to resize allocation in place
		Free,    // Deallocate a block of memory owned by allocator
		FreeAll, // Deallocate all blocks owned by allocator
	};

	enum struct Error : u8 {
		None = 0,
		OutOfMemory,
		NotOwnedPointer,
		AlignmentError,
		CannotResize,
		CannotFree,
		UnsupportedOperation,
		CustomError, // Cast to implementation's error type to get value.
	};

	using Proc = Pair<void*, Error> (*) (
		void* impl,
		Op operation,
		usize new_size,
		usize align,
		void* old_ptr,
		usize old_size,
		SourceLocation const& caller_location
	);

	constexpr
	auto procedure_ptr() const {
		return proc_;
	}

	constexpr
	auto impl_data() const {
		return impl_data_;
	}

	// Raw allocator procedure
	Pair<void*, Error> procedure(
		Op operation,
		usize new_size,
		usize align,
		void* old_ptr,
		usize old_size,
		Caller_Location
	){
		return proc_(
			impl_data_,
			operation,
			new_size,
			align,
			old_ptr,
			old_size,
			caller_location
		);
	}

	auto alloc(usize size, usize align, Caller_Location){
		return procedure(Op::Alloc, size, align, nullptr, 0, caller_location);
	}

	auto resize(
		void* old_ptr,
		usize new_size,
		usize old_size,
		Caller_Location
	){
		return procedure(
			Op::Resize,
			new_size,
			0,
			old_ptr,
			old_size,
			caller_location);
	}

	auto free(void* ptr, Caller_Location){
		auto [_, err] = procedure(Op::Free, 0, 0, ptr, 0, caller_location);
		return err;
	}

	bool free_all(Caller_Location){
		auto [_, err] = procedure(
			Op::FreeAll,
			0,
			0,
			nullptr,
			0,
			caller_location);
		return error_ok(err);
	}

	constexpr
	Allocator(){}

	constexpr
	Allocator(void* impl, Proc proc) : impl_data_{impl}, proc_{proc} {}

private:
	void* impl_data_ = nullptr;
	Proc proc_ = nullptr;
};

// Allocate a particular type and run its constructor with provided
// arguments (forced error check)
template<typename T, typename ...Args>
[[nodiscard]]
Pair<T*, Allocator::Error> make_checked(
	Allocator allocator,
	Args&& ...args,
	Caller_Location
){
	auto [raw, err] = allocator.alloc(sizeof(T), alignof(T), caller_location);
	auto ptr = static_cast<T*>(raw);

	if(error_ok(err)){
		new (ptr) T(x::forward<Args>(args)...);
	}
	else {
		ptr = nullptr;
	}

	return {ptr, err};
}

// Allocate a particular type and run its constructor with provided
// arguments
template<typename T, typename ...Args>
[[nodiscard]]
T* make(Allocator allocator, Args&& ...args, Caller_Location){
	auto [ptr, _] = make_checked<T>(
		allocator,
		x::forward<Args>(args)...,
		caller_location);
	return ptr;
}

// Allocator a slice of a particular type and run constructor on each element
// with provided arguments (forced error checking)
template<typename T, typename ...Args>
[[nodiscard]]
Pair<slice<T>, Allocator::Error> make_slice_checked(
	Allocator allocator,
	usize count,
	Args&&... args,
	Caller_Location
){
	[[unlikely]] if(count == 0){
		return {slice<T>(), Allocator::Error::None};
	}

	auto [raw, err] = allocator.alloc(
		sizeof(T) * count,
		alignof(T),
		caller_location);
	T* ptr = static_cast<T*>(raw);

	if(error_ok(err)){
		for(usize i = 0; i < count; i += 1){
			new (&ptr[i]) T(args...);
		}
	}
	else {
		ptr = nullptr;
		count = 0;
	}

	auto sl = slice<T>(ptr, count);
	return {sl, err};
}

// Allocator a slice of a particular type and run constructor on each element
// with provided arguments
template<typename T, typename ...Args>
[[nodiscard]]
slice<T> make_slice(
	Allocator allocator,
	usize count,
	Args&& ...args,
	Caller_Location
){
	auto [ptr, _] = make_slice_checked<T>(
		allocator,
		count,
		x::forward<Args>(args)...,
		caller_location
	);
	return ptr;
}

// Allocate a particular type, don't run constructor.
template<typename T>
[[nodiscard]]
Pair<T*, Allocator::Error> make_raw(Allocator allocator, Caller_Location){
	auto [raw, err] = allocator.alloc(sizeof(T), alignof(T), caller_location);
	T* ptr = static_cast<T*>(raw);
	return {ptr, err};
}

template<typename T>
[[nodiscard]]
Pair<slice<T>, Allocator::Error> make_slice_raw(
	Allocator allocator,
	usize count,
	Caller_Location
){
	[[unlikely]] if(count == 0){
		return {slice<T>(), Allocator::Error::None};
	}

	auto [raw, err] = allocator.alloc(
		sizeof(T) * count,
		alignof(T),
		caller_location);
	T* ptr = static_cast<T*>(raw);

	if(!error_ok(err)){
		ptr = nullptr;
		count = 0;
	}

	auto sl = slice<T>(ptr, count);
	return {sl, err};
}

// Run object's destructor and call allocator.free() on it.
template<typename T>
void destroy(Allocator allocator, T* obj){
	if(obj != nullptr){
		obj->~T();
		allocator.free(obj);
	}
}

// Run each item's destructor and call allocator.free() on it.
template<typename T>
void destroy(Allocator allocator, slice<T> slice){
	bool ok = !slice.empty();
	if(ok){
		T* raw = slice.raw_data();
		auto n = slice.size();
		for(usize i = 0; i < n; i += 1){
			raw[i].~T();
		}
		allocator.free(raw);
	}
}

#undef USE_BUILTIN_MEM_FUNCTIONS
}

#endif /* Include guard */
// Dynamic Array ///////////////////////////////////////////////////////////////
#ifndef _dynamic_array_hpp_include_
#define _dynamic_array_hpp_include_

#include <new>

namespace x {
template<typename T>
struct DynamicArray {
	static constexpr usize default_initial_capacity = 16;

	constexpr
	T& operator[](usize idx) {
		bounds_check(idx < length);
		return data.raw_data()[idx];
	}

	constexpr
	T const& operator[](usize idx) const {
		bounds_check(idx < length);
		return data.raw_data()[idx];
	}

	constexpr
	T* raw_data() const {
		return data.raw_data();
	}

	constexpr
	Allocator allocator() & {
		return backing_allocator;
	}

	constexpr
	usize size() const {
		return length;
	}

	constexpr
	usize capacity() const {
		return data.size();
	}

	constexpr
	bool empty() const {
		return (data.empty()) || (length == 0);
	}

	// Clear all elements, but retain capacity, this destroys all objects
	// in the array.
	constexpr
	void clear(){
		auto s = data.sub_slice(0, length);
		for(auto& e : s){
			e.~T();
		}
		length = 0;
	}

	// Append item to end of array, returns updated length
	constexpr
	usize append(T const& e){
		if((length + 1) >= data.size()){
			auto err = resize_capacity((data.size() + 1) * 2);
			if(!error_ok(err)){ return length; }
		}
		new (&data[length]) T(e);
		length += 1;
		return length;
	}

	// Append item to end of array, returns updated length
	constexpr
	usize append(T&& e){
		if((length + 1) >= data.size()){
			auto err = resize_capacity((data.size() + 1) * 2);
			debug_assert(error_ok(err), "Failed to append");
			if(!error_ok(err)){ return length; }
		}
		new (&data[length]) T(x::move(e));
		length += 1;
		return length;
	}

	// Remove last item
	constexpr
	void pop(){
		if(length < 1){ return; }
		length -= 1;
		data[length].~T();
	}
	// Remove element at idx by swapping it with last element and deleting
	// it, this operation does not preserve the order of the array
	constexpr
	void remove_unordered(usize idx){
		bounds_check(idx < length);
		x::swap(data[idx], data[length - 1]);
		pop();
	}

	// Insert element at index, appends it and swaps last element with index,
	// this operation does not preserve the order of the array
	template<typename U>
	constexpr
	usize insert_unordered(U&& e, usize idx){
		bounds_check(idx < (length + 1));
		auto old = length;
		append(x::forward<U>(e));

		// Failed to append
		[[unlikely]] if (length == old){ return old; }

		swap(data[idx], data[length - 1]);
		return length;
	}

	template<typename U>
	constexpr
	usize insert_ordered(U&& e, usize idx){
		bounds_check(idx <= data.size());

		auto old = length;
		append(x::forward<U>(e));

		// Failed to append
		[[unlikely]] if (length == old){ return old; }

		auto raw = data.raw_data();
		for(usize i = idx; i < length - 1; i += 1){
			x::swap(raw[i], raw[length - 1]);
		}

		return length;
	}
	// Remove element at idx by shifiting later elements and deleting
	// it, this operation preserves the order of the array
	constexpr
	void remove_ordered(usize idx){
		bounds_check(idx < length);
		if(idx == (length - 1)){
			pop();
			return;
		}

		auto raw = data.raw_data();
		for(usize i = idx; i < (length - 1); i += 1){
			x::swap(raw[i], raw[i+1]);
		}

		pop();
	}

	constexpr
	Allocator::Error resize_capacity(usize new_size){
		if(new_size == 0){
			clear();
			backing_allocator.free(data.raw_data());
			data = slice<T>{};
			return Allocator::Error::None;
		}

		// TODO: Use Allocator.resize() when possible
		auto [new_data, err] = make_slice_raw<T>(backing_allocator, new_size);
		Or_Return(err);

		if(new_size >= length){
			slice_raw_copy(new_data, data, length);
			destroy(backing_allocator, data);
			data = new_data;
		}
		else {
			// Remove excess elements
			auto diff = length - new_size;
			auto old_data = data.raw_data();
			for(usize i = 0; i < diff; i += 1){
				old_data[length - (i+1)].~T();
			}
			length = new_size;

			slice_raw_copy(new_data, data, new_size);
			destroy(backing_allocator, data);
			data = new_data;
		}

		return err;
	}

	constexpr
	DynamicArray<T> clone(Allocator allocator){
		auto arr = DynamicArray<T>(allocator);
		auto err = arr.resize_capacity(length + 1);
		if(!error_ok(err)){
			return arr;
		}
		slice_copy(arr.data, data, length);
		return arr;
	}

	constexpr
	DynamicArray(Allocator allocator, usize cap = default_initial_capacity)
		: data{slice<T>()}, length{0}, backing_allocator{allocator}
	{
		resize_capacity(cap);
	}

	// Move storage slice out of DynamicArray, resetting its capacity
	[[nodiscard]]
	slice<T> extract_data(){
		auto s = x::exchange(data, slice<T>());
		length = 0;
		return s;
	}

	DynamicArray(DynamicArray const&) = delete;
	void operator=(DynamicArray const&) = delete;

	DynamicArray(DynamicArray&& arr){
		data      = x::exchange(arr.data, slice<T>{});
		length    = x::exchange(arr.length, 0);
		backing_allocator = arr.backing_allocator;
	}

	void operator=(DynamicArray&& arr){
		destroy(backing_allocator, data);
		data      = x::exchange(arr.data, slice<T>{});
		length    = x::exchange(arr.length, 0);
		backing_allocator = arr.backing_allocator;
	}

	~DynamicArray(){
		clear();
		backing_allocator.free(data.raw_data());

		data = slice<T>{};
		length = 0;
	}

	constexpr
	auto begin(){
		return data.sub_slice(0, length).begin();
	}

	constexpr
	auto end(){
		return data.sub_slice(0, length).end();
	}

	constexpr
	auto begin() const {
		const auto s = data.sub_slice(0, length);
		return s.begin();
	}

	constexpr
	auto end() const {
		const auto s = data.sub_slice(0, length);
		return s.end();
	}

	slice<T> data;
	usize length;
	Allocator backing_allocator;
};
}

#endif /* Include guard */
// Bump Allocator //////////////////////////////////////////////////////////////
#ifndef _bump_allocator_hpp_include_
#define _bump_allocator_hpp_include_
namespace x {

// A bump allocator is a simple allocator that manages a contigous region
// of memory, it cannot free individual allocations, but it can release
// all of its memory at once.
struct BumpAllocator {
	using Error = Allocator::Error;

	constexpr
	usize available_space() const {
		return data_.size() - usize(offset_);
	}

	constexpr
	usize size() const {
		return data_.size();
	}

	bool owns_ptr(void const * ptr) const {
		auto p = uintptr(ptr);
		auto base = uintptr(data_.raw_data());
		auto limit = base + size();
		return (p >= base) && (p < limit);
	}

	Pair<void*, Error> alloc(usize size, usize align){
		auto [ptr, err] = alloc_undef(size, align);
		if(error_ok(err)){
			mem_set(ptr, 0, size);
		}

		return {ptr, err};
	}

	Pair<void*, Error> alloc_undef(usize size, usize align){
		panic_assert(valid_alignment(align), "Invalid memory aligment");
		auto err = Error::None;
		uintptr base = uintptr(data_.raw_data());
		uintptr start = base + offset_;
		uintptr pad = align_forward(start, align) - start;
		uintptr required = offset_ + pad + size;

		// >= ?
		if(required > available_space()){
			err = Error::OutOfMemory;
			return {nullptr, err};
		}

		auto allocation = (void*)(start + pad);

		offset_ += pad + size;
		last_allocation_ = allocation;
		last_size_ = size;

		return {allocation, err};
	}

	Error free(void* ptr){
		if(ptr != last_allocation_){
			return Error::CannotFree;
		}
		auto last = uintptr(last_allocation_);
		auto base = uintptr(data_.raw_data());

		offset_ = last - base;
		last_allocation_ = nullptr;
		last_size_ = 0;
		return Error::None;
	}

	Pair<void*, Error> resize(void* ptr, usize new_size){
		if(ptr != last_allocation_){
			return {nullptr, Error::CannotResize};
		}

		auto base     = uintptr(data_.raw_data());
		auto last     = uintptr(last_allocation_);
		auto required = (last - base) + new_size;

		auto last_offset = base - last;
		if(required > (data_.size() - last_offset)){
			return {nullptr, Error::OutOfMemory};
		}

		// Fill extra memory with 0's
		if(new_size > last_size_){
			auto diff = new_size - last_size_;
			void* start = (void*)(last + last_size_);
			mem_set(start, 0, diff);
		}

		offset_ = last - base + new_size;
		last_size_ = new_size;

		return {ptr, Error::None};
	}

	void reset(){
		offset_ = 0;
		last_allocation_ = nullptr;
		last_size_ = 0;
	}

	// Change allocator's storage slice.
	void set_storage(slice<u8> data){
		reset();
		data_ = data;
	}

	// Expose BumpAllocator as an Allocator
	Allocator allocator() &;

	BumpAllocator(){}

	BumpAllocator(BumpAllocator const& bp) = default;

	BumpAllocator(slice<u8> data)
		: data_(data) {}

	BumpAllocator(BumpAllocator&& bp)
		: data_(x::move(bp.data_))
	{
		offset_          = x::exchange(bp.offset_, 0);
		last_allocation_ = x::exchange(bp.last_allocation_, nullptr);
		last_size_       = x::exchange(bp.last_size_, 0);
	}

	void operator=(BumpAllocator && bp){
		data_            = x::exchange(bp.data_, slice<u8>{});
		offset_          = x::exchange(bp.offset_, 0);
		last_allocation_ = x::exchange(bp.last_allocation_, nullptr);
		last_size_       = x::exchange(bp.last_size_, 0);
	}

	slice<u8> data_ = {};
	uintptr offset_ = 0;
	void* last_allocation_ = nullptr;
	usize last_size_ = 0;
};

[[maybe_unused]] static
Pair<void*, Allocator::Error> _bump_allocator_proc (
	void* impl,
	Allocator::Op operation,
	usize new_size,
	usize align,
	void* old_ptr,
	usize,
	Caller_Location
){
	using Op = Allocator::Op;

	auto bump_alloc = static_cast<BumpAllocator*>(impl);
	auto err = Allocator::Error::None;

	switch (operation) {
		case Op::Alloc: {
			auto [ptr, err] = bump_alloc->alloc(new_size, align);
			return {ptr, err};
		} break;

		case Op::Resize: {
			auto [ptr, err] = bump_alloc->resize(old_ptr, new_size);
			return {ptr, err};
		} break;

		case Op::Free: {
			err = bump_alloc->free(old_ptr);
		} break;

		case Op::FreeAll: {
			bump_alloc->reset();
		} break;

		default: {
			err = Allocator::Error::UnsupportedOperation;
			unreachable();
		} break;
	}

	return {nullptr, err};
}

inline
Allocator BumpAllocator::allocator() & {
	return Allocator((void*)(this), _bump_allocator_proc);
}
}
#endif /* Include guard */
// Standard Heap Allocator /////////////////////////////////////////////////////
#ifndef _std_heap_allocator_hpp_include_
#define _std_heap_allocator_hpp_include_
namespace x {
[[maybe_unused]] static
Pair<void*, Allocator::Error> _std_heap_alloc_proc(
	void*,
	Allocator::Op op,
	usize size,
	usize align,
	void* ptr,
	usize,
	Caller_Location
){
	using Op = Allocator::Op;

	auto err = Allocator::Error::None;

	// Note: Allocating with a particular alignment in C++ is very annoying, so we over-allocate a bit to make sure
	switch(op){
		case Op::Alloc: {
			void* raw      = new u8[align_forward(size, align)];
			auto unaligned = uintptr(raw);
			auto aligned   = align_forward(unaligned, uintptr(align));
			if(raw == nullptr){
				err = Allocator::Error::OutOfMemory;
			}
			// if(aligned != unaligned){
			// 	err = Allocator::Error::AlignmentError;
			// 	delete[] (u8*)raw;
			// 	raw = nullptr;
			// }
			return {(void*)(aligned), err};
		} break;

		case Op::Free: {
			delete[] (u8*)ptr;
			return {nullptr, err};
		} break;

		case Op::Resize: {
			err = Allocator::Error::UnsupportedOperation;
			return {nullptr, err};
		} break;

		case Op::FreeAll: {
			err = Allocator::Error::UnsupportedOperation;
			return {nullptr, err};
		} break;

		default: {
			err = Allocator::Error::UnsupportedOperation;
			unreachable();
		} break;
	}

	return {nullptr, err};
}

[[maybe_unused]] static
Allocator std_heap_allocator(){
	return Allocator(nullptr, _std_heap_alloc_proc);
}
}
#endif /* Include guard */
// Arena Allocator /////////////////////////////////////////////////////////////
#ifndef _arena_allocator_hpp_include_
#define _arena_allocator_hpp_include_
#define LOG(FMT_, ...) \
std::printf("%s:%d " FMT_ "\n", caller_location.file_name(), caller_location.line(), __VA_ARGS__)

namespace x {
// An arena is a series of BumpAllocator's, it uses another allocator for
// requesting extra memory (usually the heap allocator)
struct Arena {
	using Error = Allocator::Error;

	enum struct ResetMode {
		RetainCapacity,
		DeallocAll,
	};

	Allocator backing_alloc = {};
	DynamicArray<BumpAllocator> mem_pools;

	Pair<void*, Error> alloc(usize size, usize align, Caller_Location){
		panic_assert(valid_alignment(align), "Bad alignment", caller_location);
		if(size == 0){ return {0, Error::None }; }

		for(auto& pool : mem_pools){
			auto [ptr, err] = pool.alloc(size, align);
			if(error_ok(err)){
				return {ptr, Error::None};
			}
		}

		// No pool available, create new one
		auto err = create_new_pool(size, align);
		if(!error_ok(err)){
			return {nullptr, err};
		}

		return mem_pools[mem_pools.size() - 1].alloc(size, align);
	}

	Pair<void*, Error> resize(void* ptr, usize new_size, Caller_Location){
		auto i = find_ptr_pool(ptr);
		if(i < 0){
			return {nullptr, Error::NotOwnedPointer};
		}
		return mem_pools[i].resize(ptr, new_size);
	}

	isize find_ptr_pool(void* ptr) const {
		isize i = 0;
		for(auto const& pool : mem_pools){
			if(pool.owns_ptr(ptr)){
				return i;
			}
			i += 1;
		}
		return -1;
	}

	void reset(ResetMode policy = ResetMode::RetainCapacity){
		switch (policy) {
			case ResetMode::RetainCapacity: {
				for(auto& pool : mem_pools){
					pool.reset();
				}
			} break;

			case ResetMode::DeallocAll: {
				for(auto& pool : mem_pools){
					auto storage = x::move(pool.data_);
					backing_alloc.free(storage.raw_data());
				}
				mem_pools.clear();
			} break;
		}

	}

	Error create_new_pool(usize size, usize align, Caller_Location) {
		// Make damn sure there's enough size.
		auto size_aligned      = align_forward(size, align);
		auto min_pool_aligned  = align_forward(default_min_pool_size, align);
		auto real_size         = max(size_aligned, min_pool_aligned);

		auto [data, err] = make_slice_checked<u8>(backing_alloc, real_size);

		if(!error_ok(err)){
			return err;
		}

		auto bp = BumpAllocator(data);
		mem_pools.append(bp);

		return Error::None;
	}

	static constexpr usize default_min_pool_size = 128;

	Allocator allocator() &;

	Arena(Allocator backing)
		: backing_alloc{backing},
		mem_pools(backing) {}

	~Arena(){
		for(auto& pool : mem_pools){
			destroy(backing_alloc, pool.data_);
		}
	}
};

[[maybe_unused]] static
Pair<void*, Allocator::Error> _arena_allocator_proc (
	void* impl,
	Allocator::Op operation,
	usize new_size,
	usize align,
	void* old_ptr,
	usize,
	Caller_Location
){
	using Op = Allocator::Op;
	auto arena = static_cast<Arena*>(impl);
	auto err = Arena::Error::None;

	switch (operation) {
		case Op::Alloc: {
			return arena->alloc(new_size, align, caller_location);
		} break;

		case Op::Resize: {
			return arena->resize(old_ptr, new_size, caller_location);
		} break;

		case Op::Free: {
			err = Arena::Error::UnsupportedOperation;
		} break;

		case Op::FreeAll: {
			arena->reset();
		} break;

		default:{
			err = Arena::Error::UnsupportedOperation;
			unreachable();
		} break;
	}

	return {nullptr, err};
}

inline Allocator Arena::allocator() & {
	return Allocator(this, _arena_allocator_proc);
}
}
#endif /* Include guard */
// Tracking Allocator //////////////////////////////////////////////////////////
#ifndef _tracking_allocator_hpp_include_
#define _tracking_allocator_hpp_include_
namespace x {
struct TrackingAllocator {
	using Error = Allocator::Error;

	struct Allocation {
		void* ptr;
		usize size;
		SourceLocation where;
	};

	DynamicArray<Allocation> allocations;
	Allocator backing_alloc;
	SourceLocation created_at;

	TrackingAllocator(Allocator alloc, Caller_Location)
		: allocations(alloc),
		backing_alloc(alloc),
		created_at(caller_location)
	{
			debug_assert(
				alloc.procedure_ptr() != nullptr,
				"Null procedure is not allowed.");
	}

	Allocator allocator() &;

	bool report(){
		std::printf("Testing allocator report (created at %s:%d):\n",
			  created_at.file_name(),
			  created_at.line());

		if(allocations.size() > 0){
			std::printf("Leaked allocations:\n");
			for(auto const& a : allocations){
				std::printf("  %p: %zuB from %s:%d\n",
					a.ptr,
					a.size,
					a.where.file_name(),
					a.where.line());
			}
			return false;
		}
		else {
			std::printf("  No leaks found.\n");
			return true;
		}
	}

	Pair<void*, Error> alloc(usize new_size, usize align, Caller_Location){
		auto [ptr, err] = backing_alloc.alloc(
			new_size,
			align,
			caller_location);

		if(!error_ok(err)){
			ptr = nullptr;
		}
		else {
			allocations.append(Allocation{
				.ptr = ptr,
				.size = new_size,
				.where = caller_location,
			});
		}
		return {ptr, err};
	}

	Pair<void*, Error> resize(
		usize new_size,
		void* old_ptr,
		usize old_size,
		Caller_Location
	){
		auto [ptr, err] = backing_alloc.resize(old_ptr, new_size, old_size);
		if(!error_ok(err)){
			return {nullptr, err};
		}
		else {
			for(auto& alloc : allocations){
				if(alloc.ptr == old_ptr){
					alloc.size = new_size;
					break;
				}
			}
		}
		return {ptr, err};
	}

	Error free(void* old_ptr, Caller_Location){
		auto err = backing_alloc.free(old_ptr, caller_location);
		// Even if the operation is not supported or couldn't be
		// completed, the allocation is still marked as freed, this is
		// mostly for consistency with arena-style allocators.
		bool ok = (err == Error::None) ||
			(err == Error::UnsupportedOperation) ||
			(err == Error::CannotFree);

		if(ok) {
			for(usize i = 0; i < allocations.size(); i += 1){
				auto const& alloc = allocations[i];
				if(alloc.ptr == old_ptr){
					allocations.remove_unordered(i);
					return err;
				}
			}
		}
		return err;
	}

	Error free_all(Caller_Location){
		auto ok = backing_alloc.free_all(caller_location);
		if(error_ok(ok)){
			allocations.clear();
		}
		return ok ? Error::None : Error::UnsupportedOperation;
	}
	~TrackingAllocator(){}
};

[[maybe_unused]] static
Pair<void*, Allocator::Error> _tracking_allocator_proc(
	void* impl,
	Allocator::Op op,
	usize new_size,
	usize align,
	void* old_ptr,
	usize old_size,
	Caller_Location
){
	using Op = Allocator::Op;
	using Error = Allocator::Error;

	auto talloc = bit_cast<TrackingAllocator*>(impl);

	switch (op) {
		case Op::Alloc: {
			return talloc->alloc(new_size, align, caller_location);
		} break;

		case Op::Resize: {
			return talloc->resize(
				new_size,
				old_ptr,
				old_size,
				caller_location);
		} break;

		case Op::Free: {
			return {nullptr, talloc->free(old_ptr, caller_location) };
		};

		case Op::FreeAll: {
			return {nullptr, talloc->free_all(caller_location)};
		} break;
	}

	return {nullptr, Error::UnsupportedOperation};
}

inline Allocator TrackingAllocator::allocator() & {
	return Allocator(this, _tracking_allocator_proc);
}
}
#endif /* Include guard */
/*
Copyright marcs-feh 2024

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the “Software”), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
of the Software, and to permit persons to whom the Software is furnished to do
so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

*/

#endif /* Include guard */

