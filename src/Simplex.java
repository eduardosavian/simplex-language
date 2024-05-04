import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Simplex {
    public enum OperationFlag {
        PARSER("-parser"),
        TYPE_CHECKER("-type-checker");

        private final String flag;

        OperationFlag(String flag) {
            this.flag = flag;
        }

        public String getFlag() {
            return flag;
        }
    }

    public enum PrintFlag {
        GUI("-gui"),
        TREE("-tree"),
        TOKENS("-tokens");

        private final String flag;

        PrintFlag(String flag) {
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

        OperationFlag operationFlag = null;
        for (OperationFlag operation : OperationFlag.values()) {
            if (operation.getFlag().equals(args[1])) {
                operationFlag = operation;
                Menu(args, operationFlag, runError);
                return;
            }
        }

        System.out.println("Invalid operation");
        System.out.println("Valid operations are: -parser, -type-checker");
    }

    private static void Menu(String[] args, OperationFlag operationFlag, String runError) throws IOException {
        String inputFilePath = args[0];

        switch (operationFlag) {
            case PARSER:
                if (args.length < 3) {
                    runError += " <print_type>";
                    System.err.println(runError);
                    System.exit(1);
                }

                String printFlag = args[2];

                parser(inputFilePath, printFlag);
                break;
            case TYPE_CHECKER:
                String fileContent = readFile(inputFilePath);
                typeChecker(fileContent);

                break;
            default:
                System.out.println("Invalid operation");
                System.out.println("Valid operations are: -parser, -type-checker");
                break;
        }
    }

    private static void parser(String filePath, String printFlag) {
        PrintFlag PrintFlag = null;
        for (PrintFlag flag : Simplex.PrintFlag.values()) {
            if (flag.getFlag().equals(printFlag)) {
                PrintFlag = flag;
                antlrCommand(filePath, PrintFlag);
                return;
            }
        }
        System.out.println("Invalid print type");
        System.out.println("Valid print types are: -gui, -tree, -tokens");
    }

    private static void antlrCommand(String inputFilePath, PrintFlag printType) {
        try {
            String[] fullArgs = new String[4];
            fullArgs[0] = "Simplex";
            fullArgs[1] = "program";
            fullArgs[2] = inputFilePath;
            fullArgs[3] = printType.getFlag();

            Class<?> testRigClass = Class.forName("org.antlr.v4.gui.TestRig");
            Method mainMethod = testRigClass.getMethod("main", String[].class);
            mainMethod.invoke(null, new Object[] { fullArgs });
        } catch (Exception e) {
            System.out.println("");
        }
    }

    private static void typeChecker(String fileContent) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());

        @SuppressWarnings("deprecation")
        ANTLRInputStream input = new ANTLRInputStream(inputStream);

        SimplexLexer lexer = new SimplexLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        SimplexParser parser = new SimplexParser(tokens);

        ParseTree tree = parser.program();

        SimplexVisitor simplex = new SimplexVisitor();

        simplex.visit(tree);
    }

    private static String readFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder stringBuilder = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                line = reader.readLine();
            }

            reader.close();

            stringBuilder.insert(0, "{\n");
            stringBuilder.append("\n}");

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
