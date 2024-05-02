public class Simplex {
    public enum Operation {
        PARSER("-parser"),
        TYPE_CHECKER("-type-checker");

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
            case PARSER:
                if (args.length < 3) {
                    System.err.println("Usage: java Main -parse <input_file> <print_type>");
                    System.exit(1);
                }

                String inputFilePath = args[1];
                String printType = args[2];

                Sintatic sintatic = new Sintatic();
                sintatic.execute(inputFilePath, printType);
                break;
            case TYPE_CHECKER:
                if (args.length < 2) {
                    System.err.println("Usage: java Main -generate-tree <input_file>");
                    System.exit(1);
                }

                String inputFilePath2 = args[1];

                Semantic semantic = new Semantic();
                semantic.execute(inputFilePath2);

                break;
            default:
                System.out.println("Invalid operation");
                break;
        }
    }
}
