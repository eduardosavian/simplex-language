public class Simplex {
    public enum Operation {
        TOKENIZE("-tokenize"),
        PARSE("-parse"),
        GENERATE_TREE("-generate-tree");

        private final String flag;

        Operation(String flag) {
            this.flag = flag;
        }

        public String getFlag() {
            return flag;
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Usage: java Main <operation>");
            System.exit(1);
        }

        Operation operationFlag = null;
        for (Operation operation : Operation.values()) {
            if (operation.getFlag().equals(args[0])) {
                operationFlag = operation;
                break;
            }
        }
        
        switch (operationFlag) {
            case TOKENIZE:
                
                break;
            case PARSE:
                // Logic for parsing
                //verify
                if (args.length < 3) {
                    System.err.println("Usage: java Main -parse <input_file> <print_type>");
                    System.exit(1);
                }

                Utils parser = new Utils();

                String inputFilePath = args[1];
                String printType = args[2];

                switch (printType) {
                    case "-tree":
                        parser.antlrCommand("Simplex", "program", inputFilePath, printType);
                        break;
                    case "-gui":
                        parser.antlrCommand("Simplex", "program", inputFilePath, printType);
                        break;
                    case "-tokens":
                        parser.antlrCommand("Simplex", "program", inputFilePath, printType);
                        break;
                    case "-trace":
                        parser.antlrCommand("Simplex", "program", inputFilePath, printType);
                        break;
                    case "-diagnostics":
                        parser.antlrCommand("Simplex", "program", inputFilePath, printType);
                        break;
                    case "-ps":
                        parser.antlrCommand("Simplex", "program", inputFilePath, printType);
                        break;
                    case "-runTree":
                        parser.printTree(inputFilePath);
                        break;
                    case "-runWalk":
                        parser.printWalk(inputFilePath);
                        break;
                    default:
                        System.out.println("Invalid print type");
                        break;
                }
                break;
            case GENERATE_TREE:
                // Logic for generating syntax tree
                break;
        }

    }
}
