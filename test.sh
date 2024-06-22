odin build . -o:none \
	&& rm source.asm \
	&& ./lang compile source.txt -out:source.asm \
	&& cat source.asm | wl-copy
