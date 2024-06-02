package cli_parse

import str "core:strings"
import mem "core:mem"

Flag :: struct {
	key: string,
	value: string,
}

parse_flag :: proc(arg: string, allocator := context.allocator) -> (flag: Flag, ok: bool){
	arg := arg
	arg = str.trim(arg, " \t\r")

	if len(arg) < 2 || arg[0] != '-' {
		return
	}

	comma_pos := -1
	for c, i in arg[1:] {
		if c == ':' {
			comma_pos = i
			break
		}
	}

	flag_data, err := str.clone(arg[1:], allocator = context.temp_allocator)
	if err != nil { return }

	key, val : string
	if comma_pos > -1 {
		key = str.clone(flag_data[:comma_pos])
		val = str.clone(flag_data[min(len(flag_data) - 1, comma_pos + 1):])
	}
	else {
		key = flag_data[:]
	}

	flag = Flag{
		key = key,
		value = val,
	}

	return flag, true
}

