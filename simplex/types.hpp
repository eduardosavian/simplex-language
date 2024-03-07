#ifndef _types_hpp_include_

#include <cstddef>
#include <cstdint>

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

#define _types_hpp_include_
#endif /* Include guard */
