# Simple Language

## Setup on Unix

### Change directory to src/

```bash
cd src/
```

### Give permitions to the bash scripts

```bash
chmod +x antlr.sh grun.sh
```

### Generate Parser

```bash
./antlr.sh Simplex.g4
```

### Compiler the Simplex .java files

```bash
javac *.java
```

### Run the ANTLR Test

```bash
./grun Simplex r -tokens
```

### Back to the root directory

```bash
cd ..
```

### All the commands

```bash
./antlr.sh Simplex.g4 && javac *.java && ./grun.sh Simplex r -tokens
```

## Reference

- [ANTLR](https://www.antlr.org/index.html)

- [ANTLR Donwloads](https://www.antlr.org/download.html)

- [ANTLR Tutorial](https://github.com/antlr/antlr4/blob/master/doc/index.md)

- [ANTLR Lab](http://lab.antlr.org/)