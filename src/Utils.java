import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.lang.reflect.Method;
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

    public void printGeneric(String grammarName, String startRuleName, String inputFilePath, String args) {
        try {
            // Split the args string into individual arguments
            String[] argArray = args.split("\\s+");
            
            String[] fullArgs = new String[argArray.length + 3];
            fullArgs[0] = grammarName;
            fullArgs[1] = startRuleName;
            fullArgs[2] = inputFilePath;
            System.arraycopy(argArray, 0, fullArgs, 3, argArray.length);
            
            Class<?> testRigClass = Class.forName("org.antlr.v4.gui.TestRig");
            Method mainMethod = testRigClass.getMethod("main", String[].class);
            mainMethod.invoke(null, new Object[]{fullArgs});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void printTree(String inputFilePath) {
        try {
            Class<?> testRigClass = Class.forName("org.antlr.v4.gui.TestRig");
            Method mainMethod = testRigClass.getMethod("main", String[].class);
            mainMethod.invoke(null, new Object[]{new String[]{"Simplex", "program", "-gui", inputFilePath}});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

