import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.gui.TestRig;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

/**
 * Print
 */
public class Utils {
    public void printNormal(String inputFilePath) throws IOException {
        InputStream inputStream = new FileInputStream(inputFilePath);

        @SuppressWarnings("deprecation")
        ANTLRInputStream input = new ANTLRInputStream(inputStream);

        SimplexLexer lexer = new SimplexLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        SimplexParser parser = new SimplexParser(tokens);

        ParseTree tree = parser.program();

        System.out.println(tree.toStringTree(parser));
    }

    public void printTree(String inputFilePath) {
        try {
            TestRig.main(new String[]{"Simplex", "program", "-gui", "-tree", inputFilePath});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

