public class Simplex {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.out.println("Number of arguments: " + args.length);
            for (String arg : args) {
                System.out.println(arg);
            }

            System.err.println("Usage: java Main <input_file> <print_type> <antlr_args>");
            System.exit(1);
        }

        Utils utils = new Utils();

        String inputFilePath = "tests/" + args[0];
        String printType = args[1];
        String antlrArges = args[2];

        switch (printType) {
            case "normal":
                utils.printNormal(inputFilePath);
                break;
            case "tree":
                utils.printTreeGUI(inputFilePath);
                break;
            case "generic":
                utils.printGeneric("Simplex", "Program", inputFilePath, antlrArges);
                break;
            default:
                System.err.println("Invalid print type. Supported types: normal, formatted");
                System.exit(1);
        }
    }
}
