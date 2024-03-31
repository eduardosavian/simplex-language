// import ANTLR's runtime libraries
import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: java Main <input_file>");
            System.exit(1);
        }

        String inputFilePath = args[0];
        // create a CharStream that reads from standard input
        InputStream inputStream = new FileInputStream(inputFilePath);
        // create a lexer that feeds off of input CharStream
        ANTLRInputStream input = new ANTLRInputStream(inputStream);
        SimplexLexer lexer = new SimplexLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a buffer of tokens pulled from the lexer
        SimplexParser parser = new SimplexParser(tokens);

        ParseTree tree = parser.init();

        System.out.println(tree.toStringTree(parser));
    }
}