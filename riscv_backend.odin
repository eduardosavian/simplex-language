package lang

import "core:fmt"
import str "core:strings"

generate_text_section :: proc() -> string {
	unimplemented()
}

rv32_generate_data_section :: proc(table: StaticDataTable) -> string {
	sb := str.builder_make()
	for label, space in table {
		fmt.sbprintfln(&sb, "%v: .space %v", label, space)
	}
	resize(&sb.buf, len(sb.buf))
	return string(sb.buf[:])
}

