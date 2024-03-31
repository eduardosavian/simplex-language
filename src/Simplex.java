import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Simplex {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: java Main <input_file>");
            System.exit(1);
        }

        String inputFilePath = args[0];

        InputStream inputStream = new FileInputStream(inputFilePath);

        @SuppressWarnings("deprecation")
        ANTLRInputStream input = new ANTLRInputStream(inputStream);

        SimplexLexer lexer = new SimplexLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        SimplexParser parser = new SimplexParser(tokens);

        ParseTree tree = parser.program();

        System.out.println(tree.toStringTree(parser));
    }
}