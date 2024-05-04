import java.lang.reflect.Method;


public class Syntactic {
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

    public void execute(File file, String printFlag) {
        PrintFlag PrintFlag = null;
        for (PrintFlag flag : Syntactic.PrintFlag.values()) {
            if (flag.getFlag().equals(printFlag)) {
                PrintFlag = flag;
                antlrCommand(file.getPath(), PrintFlag);
                return;
            }
        }
        System.out.println("Invalid print type");
        System.out.println("Valid print types are: -gui, -tree, -tokens");
    }

    private void antlrCommand(String inputFilePath, PrintFlag printType) {
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
}