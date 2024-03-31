public class Simplex {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: java Main <input_file>");
            System.exit(1);
        }

        Utils utils = new Utils();

        String inputFilePath = args[0];

        utils.printNormal(inputFilePath);
    }
}