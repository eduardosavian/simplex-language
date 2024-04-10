fun trapArea(h: real, B: real, b: real) -> real {
	if (b < 0b1) || (B < 0x1) || (h < 0o1) {
		return 0;
	}

	area: real = (h * (b + B));

	return area / 2;
}

