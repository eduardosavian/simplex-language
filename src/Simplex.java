import java.io.*;

public class Simplex {
    public enum Operation {
        PARSER("-parser"),
        TYPE_CHECKER("-generate-tree");

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

                handleParseOperation(inputFilePath, printType);
                break;
            case TYPE_CHECKER:
                // Logic for generate tree operation
                break;
            default:
                System.out.println("Invalid operation");
                break;
        }
    }

    private static void handleParseOperation(String inputFilePath, String printType) {
        Utils parser = new Utils();

        try {
            StringBuilder fileContents = readFile(inputFilePath);

            // Ensure file contents are wrapped with curly braces if needed
            wrapWithCurlyBracesIfNeeded(fileContents);

            // Write modified file contents back to the same file
            writeFile(inputFilePath, fileContents.toString());

            // Perform parsing based on printType
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
                case "-semantic":
                    parser.semantic(inputFilePath);
                    break;
                default:
                    System.out.println("Invalid print type");
                    break;
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static StringBuilder readFile(String filePath) throws IOException {
        StringBuilder fileContents = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContents.append(line).append("\n");
            }
        }

        return fileContents;
    }

    private static void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    private static void wrapWithCurlyBracesIfNeeded(StringBuilder fileContents) {
        String trimmedContents = fileContents.toString().trim();
        if (!trimmedContents.startsWith("{")) {
            fileContents.insert(0, "{\n");
        }
        if (!trimmedContents.endsWith("}")) {
            fileContents.append("\n}");
        }
    }
}
