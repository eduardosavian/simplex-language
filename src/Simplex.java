public class Simplex {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: java Main <input_file> <print_type>");
            System.exit(1);
        }

        Utils utils = new Utils();

        String inputFilePath = args[0];
        String printType = args[1];

        switch (printType) {
            case "normal":
                utils.printNormal(inputFilePath);
                break;
            case "tree":
                utils.printTree(inputFilePath);
                break;
            default:
                System.err.println("Invalid print type. Supported types: normal, formatted");
                System.exit(1);
        }
    }
}
