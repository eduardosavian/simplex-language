import java.io.IOException;

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
        String runError = "Usage: java -jar simplex.jar <file_path> <operation_flag>";
        if (args.length < 2) {
            System.err.println(runError);
            System.exit(1);
        }

        Operation operationFlag = null;
        for (Operation operation : Operation.values()) {
            if (operation.getFlag().equals(args[1])) {
                operationFlag = operation;
                Menu(args, operationFlag, runError);
                return;
            } 
        }

        System.out.println("Invalid operation");
        System.out.println("Valid operations are: -parser, -type-checker");

    }

    private static void Menu(String[] args, Operation operationFlag, String runError) throws IOException {
        String inputFilePath = args[0];
        File file = new File(inputFilePath);

        switch (operationFlag) {
            case PARSER:
                if (args.length < 3) {
                    runError += " <print_type>";
                    System.err.println(runError);
                    System.exit(1);
                }

                String printFlag = args[2];

                Sintatic sintatic = new Sintatic();
                sintatic.execute(file, printFlag);
                break;
            case TYPE_CHECKER:
                Semantic semantic = new Semantic();
                semantic.execute(file);

                break;
            default:
                System.out.println("Invalid operation");
                System.out.println("Valid operations are: -parser, -type-checker");
                break;
        }
    }
}
