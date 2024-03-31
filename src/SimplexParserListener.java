// Generated from src/SimplexParser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimplexParser}.
 */
public interface SimplexParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SimplexParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(SimplexParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(SimplexParser.ProgContext ctx);
}