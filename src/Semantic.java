import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Semantic {
    public void execute(String inputFilePath) throws IOException {
        typeChecker(inputFilePath);
    }

    private void typeChecker(String inputFilePath) throws IOException {
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
}
