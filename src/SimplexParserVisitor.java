// Generated from /home/eduardo/Documents/Projects/simplex-language/simplex/SimplexParser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SimplexParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SimplexParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SimplexParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(SimplexParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#inlineStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInlineStatement(SimplexParser.InlineStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(SimplexParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(SimplexParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(SimplexParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(SimplexParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(SimplexParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#identifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierList(SimplexParser.IdentifierListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#varAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarAssignment(SimplexParser.VarAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#sliceIndicator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSliceIndicator(SimplexParser.SliceIndicatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#typeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeExpression(SimplexParser.TypeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#funcDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDeclaration(SimplexParser.FuncDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(SimplexParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#fieldList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldList(SimplexParser.FieldListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#scope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScope(SimplexParser.ScopeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#simpleFor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleFor(SimplexParser.SimpleForContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#tripleFor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTripleFor(SimplexParser.TripleForContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#doFor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoFor(SimplexParser.DoForContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(SimplexParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#exprStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStatement(SimplexParser.ExprStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(SimplexParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#logicDisjunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicDisjunction(SimplexParser.LogicDisjunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#logicConjunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicConjunction(SimplexParser.LogicConjunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(SimplexParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(SimplexParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(SimplexParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#bitShift}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitShift(SimplexParser.BitShiftContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(SimplexParser.UnaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(SimplexParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroup(SimplexParser.GroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(SimplexParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(SimplexParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(SimplexParser.IntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#real}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReal(SimplexParser.RealContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplexParser#indexing}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexing(SimplexParser.IndexingContext ctx);
}