// Generated from /home/eduardo/Documents/Projects/simplex-language/simplex/SimplexParser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimplexParser}.
 */
public interface SimplexParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SimplexParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(SimplexParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(SimplexParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#inlineStatement}.
	 * @param ctx the parse tree
	 */
	void enterInlineStatement(SimplexParser.InlineStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#inlineStatement}.
	 * @param ctx the parse tree
	 */
	void exitInlineStatement(SimplexParser.InlineStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(SimplexParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(SimplexParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(SimplexParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(SimplexParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(SimplexParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(SimplexParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(SimplexParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(SimplexParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(SimplexParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(SimplexParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierList(SimplexParser.IdentifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierList(SimplexParser.IdentifierListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#varAssignment}.
	 * @param ctx the parse tree
	 */
	void enterVarAssignment(SimplexParser.VarAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#varAssignment}.
	 * @param ctx the parse tree
	 */
	void exitVarAssignment(SimplexParser.VarAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#sliceIndicator}.
	 * @param ctx the parse tree
	 */
	void enterSliceIndicator(SimplexParser.SliceIndicatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#sliceIndicator}.
	 * @param ctx the parse tree
	 */
	void exitSliceIndicator(SimplexParser.SliceIndicatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#typeExpression}.
	 * @param ctx the parse tree
	 */
	void enterTypeExpression(SimplexParser.TypeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#typeExpression}.
	 * @param ctx the parse tree
	 */
	void exitTypeExpression(SimplexParser.TypeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#funcDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFuncDeclaration(SimplexParser.FuncDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#funcDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFuncDeclaration(SimplexParser.FuncDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(SimplexParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(SimplexParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#fieldList}.
	 * @param ctx the parse tree
	 */
	void enterFieldList(SimplexParser.FieldListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#fieldList}.
	 * @param ctx the parse tree
	 */
	void exitFieldList(SimplexParser.FieldListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#scope}.
	 * @param ctx the parse tree
	 */
	void enterScope(SimplexParser.ScopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#scope}.
	 * @param ctx the parse tree
	 */
	void exitScope(SimplexParser.ScopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#simpleFor}.
	 * @param ctx the parse tree
	 */
	void enterSimpleFor(SimplexParser.SimpleForContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#simpleFor}.
	 * @param ctx the parse tree
	 */
	void exitSimpleFor(SimplexParser.SimpleForContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#tripleFor}.
	 * @param ctx the parse tree
	 */
	void enterTripleFor(SimplexParser.TripleForContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#tripleFor}.
	 * @param ctx the parse tree
	 */
	void exitTripleFor(SimplexParser.TripleForContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#doFor}.
	 * @param ctx the parse tree
	 */
	void enterDoFor(SimplexParser.DoForContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#doFor}.
	 * @param ctx the parse tree
	 */
	void exitDoFor(SimplexParser.DoForContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(SimplexParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(SimplexParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#exprStatement}.
	 * @param ctx the parse tree
	 */
	void enterExprStatement(SimplexParser.ExprStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#exprStatement}.
	 * @param ctx the parse tree
	 */
	void exitExprStatement(SimplexParser.ExprStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(SimplexParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(SimplexParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#logicDisjunction}.
	 * @param ctx the parse tree
	 */
	void enterLogicDisjunction(SimplexParser.LogicDisjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#logicDisjunction}.
	 * @param ctx the parse tree
	 */
	void exitLogicDisjunction(SimplexParser.LogicDisjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#logicConjunction}.
	 * @param ctx the parse tree
	 */
	void enterLogicConjunction(SimplexParser.LogicConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#logicConjunction}.
	 * @param ctx the parse tree
	 */
	void exitLogicConjunction(SimplexParser.LogicConjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(SimplexParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(SimplexParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(SimplexParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(SimplexParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(SimplexParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(SimplexParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#bitShift}.
	 * @param ctx the parse tree
	 */
	void enterBitShift(SimplexParser.BitShiftContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#bitShift}.
	 * @param ctx the parse tree
	 */
	void exitBitShift(SimplexParser.BitShiftContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(SimplexParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(SimplexParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(SimplexParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(SimplexParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#group}.
	 * @param ctx the parse tree
	 */
	void enterGroup(SimplexParser.GroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#group}.
	 * @param ctx the parse tree
	 */
	void exitGroup(SimplexParser.GroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(SimplexParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(SimplexParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(SimplexParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(SimplexParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(SimplexParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(SimplexParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#real}.
	 * @param ctx the parse tree
	 */
	void enterReal(SimplexParser.RealContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#real}.
	 * @param ctx the parse tree
	 */
	void exitReal(SimplexParser.RealContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#indexing}.
	 * @param ctx the parse tree
	 */
	void enterIndexing(SimplexParser.IndexingContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#indexing}.
	 * @param ctx the parse tree
	 */
	void exitIndexing(SimplexParser.IndexingContext ctx);
}