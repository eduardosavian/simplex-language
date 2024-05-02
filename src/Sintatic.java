import java.lang.reflect.Method;

public class Sintatic {
    public enum Operation {
        GUI("-gui"),
        TREE("-tree"),
        TOKENS("-tokens");

        private final String flag;

        Operation(String flag) {
            this.flag = flag;
        }

        public String getFlag() {
            return flag;
        }
    }

    public void execute(String inputFilePath, String printType) {
        Operation operationFlag = null;
        for (Operation operation : Operation.values()) {
            if (operation.getFlag().equals(printType)) {
                operationFlag = operation;
                break;
            }
        }

        switch (operationFlag) {
            case TREE:
                antlrCommand(inputFilePath, printType);
                break;
            case GUI:
                antlrCommand(inputFilePath, printType);
                break;
            case TOKENS:
                antlrCommand(inputFilePath, printType);
                break;
            default:
                System.out.println("Invalid print type");
                break;
        }
    }

    private void antlrCommand(String inputFilePath, String args) {
        try {
            String[] argArray = args.split("\\s+");

            String[] fullArgs = new String[argArray.length + 3];
            fullArgs[0] = "Simplex";
            fullArgs[1] = "program";
            fullArgs[2] = inputFilePath;
            System.arraycopy(argArray, 0, fullArgs, 3, argArray.length);

            for (String arg : fullArgs) {
                System.out.println(arg);
            }

            Class<?> testRigClass = Class.forName("org.antlr.v4.gui.TestRig");
            Method mainMethod = testRigClass.getMethod("main", String[].class);
            mainMethod.invoke(null, new Object[] { fullArgs });
        } catch (Exception e) {
            System.out.println("");
        }
    }
}