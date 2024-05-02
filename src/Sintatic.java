import java.lang.reflect.Method;


public class Sintatic {
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
        for (PrintFlag flag : Sintatic.PrintFlag.values()) {
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
            
            
            // String[] argArray = args.split("\\s+");

            // String[] fullArgs = new String[argArray.length + 3];
            // fullArgs[0] = "Simplex";
            // fullArgs[1] = "program";
            // fullArgs[2] = inputFilePath;
            // System.arraycopy(argArray, 0, fullArgs, 3, argArray.length);

            // for (String arg : fullArgs) {
            //     System.out.println(arg);
            // }

            // Class<?> testRigClass = Class.forName("org.antlr.v4.gui.TestRig");
            // Method mainMethod = testRigClass.getMethod("main", String[].class);
            // mainMethod.invoke(null, new Object[] { fullArgs });
        } catch (Exception e) {
            System.out.println("");
        }
    }
}