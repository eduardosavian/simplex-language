# Simple Language

## Setup on Unix

C

```bash
chmod +x antlr4.sh grun.sh
```

```bash
./antlr4.sh src/Simplex.g4
```

```bash
javac src/*.java
```

```bash
cd src/
```

```bash
./grun Simplex r -tokens
``.sh`

```bash
./antlr4.sh src/Simplex.g4 && javac src/*.java && cd src/ && ./grun.sh Simplex r -tokens && cd ..
```

## Reference

- https://www.antlr.org/index.html

- https://www.antlr.org/download.html

- https://github.com/antlr/antlr4/blob/master/doc/index.md