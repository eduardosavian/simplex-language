# The Simplex Language or Slang

Made by Eduardo Savian, Pablo Marques e Marcos Fehlauer

## Setup on Unix

### Go to the script directory

```bash
cd scripts/
```

### Give permitions to the bash scripts

```bash
chmod +x scripts/generate.sh scripts/compile.sh scripts/run.sh
```

### Generate ANTLR files

```bash
./generate.sh
```

### Back to root project directory

```bash
cd ..
```

## Compile and run

### Scripts

#### Go to the script directory

```bash
cd scripts/
```

#### Compile

```bash
./compile.sh
```
#### Run the ANTLR

```bash
./run.sh ../tests/<input_file>.txt 
```

### Ant

#### Ant build

```bash
ant build
```
#### Ant run

```bash
ant -Darg="tests/<input_file>.txt" run
```

## Reference

- [ANTLR](https://www.antlr.org/index.html)

- [ANTLR Donwloads](https://www.antlr.org/download.html)

- [ANTLR Tutorial](https://github.com/antlr/antlr4/blob/master/doc/index.md)

- [ANTLR Lab](http://lab.antlr.org/)
