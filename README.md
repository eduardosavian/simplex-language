# Simple Language

## Setup on Unix

### Change directory to src/

```bash
cd src/
```

### Give permitions to the bash scripts

```bash
chmod +x antlr4.sh grun.sh
```

### Generate Parser

```bash
./antlr4.sh Simplex.g4
```

### Compiler the Simplex .java files

```bash
javac *.java
```

### Run the ANTLR Test

```bash
./grun Simplex r -tokens
```

### All the commands

```bash
cd src/ && ./antlr4.sh Simplex.g4 && javac *.java && ./grun.sh Simplex r -tokens
```

## Reference

- [https://www.antlr.org/index.html](ANTLR)

- [https://www.antlr.org/download.html] (ANTLR Dowloads)

- [https://github.com/antlr/antlr4/blob/master/doc/index.md] (ANTLR Tutorial)
