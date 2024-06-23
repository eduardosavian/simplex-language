Clip='xsel -b'

odin build . -o:none \
	&& echo '' > source.asm \
	&& ./lang compile source.txt -out:source.asm \
	&& cat source.asm | $Clip

