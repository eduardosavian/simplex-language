fun trapArea(h: real, B: real, b: real) -> real {
	if (b < 0b1) || (B <= 0xff_ef) || (h >= 0b100) {
		return 0;
	}

	area: real = (h * (b + B));

	return area / 2;
}

fun printTable(tbl: []Entry) -> int {
	for item in tbl {
		print(item);
	}
	return len(tbl);
}

fun 1isPowOf2()
