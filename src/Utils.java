import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Utils {
    public void printTree(String inputFilePath) throws IOException {
        InputStream inputStream = new FileInputStream(inputFilePath);

        @SuppressWarnings("deprecation")
        ANTLRInputStream input = new ANTLRInputStream(inputStream);

        SimplexLexer lexer = new SimplexLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        SimplexParser parser = new SimplexParser(tokens);

        ParseTree tree = parser.program();

        System.out.println(tree.toStringTree(parser));
    }

    public void semantic(String inputFilePath) throws IOException {
        InputStream inputStream = new FileInputStream(inputFilePath);

        @SuppressWarnings("deprecation")
        ANTLRInputStream input = new ANTLRInputStream(inputStream);

        SimplexLexer lexer = new SimplexLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        SimplexParser parser = new SimplexParser(tokens);

        ParseTree tree = parser.program();

        SimplexVisitor simplex = new SimplexVisitor();

        simplex.visit(tree);
    }

    public void antlrCommand(String grammarName, String startRuleName, String inputFilePath, String args) {
        try {
            String[] argArray = args.split("\\s+");

            String[] fullArgs = new String[argArray.length + 3];
            fullArgs[0] = grammarName;
            fullArgs[1] = startRuleName;
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