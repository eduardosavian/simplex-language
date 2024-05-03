import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Semantic {
    public void execute(File file) throws IOException {
        typeChecker(file.getContent());
    }

    private void typeChecker(String fileContent) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());

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
