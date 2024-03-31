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
	 * Enter a parse tree produced by {@link SimplexParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void enterVariable_declaration(SimplexParser.Variable_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void exitVariable_declaration(SimplexParser.Variable_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void enterFunction_declaration(SimplexParser.Function_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void exitFunction_declaration(SimplexParser.Function_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#parameter_list}.
	 * @param ctx the parse tree
	 */
	void enterParameter_list(SimplexParser.Parameter_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#parameter_list}.
	 * @param ctx the parse tree
	 */
	void exitParameter_list(SimplexParser.Parameter_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(SimplexParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(SimplexParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(SimplexParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(SimplexParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_statement(SimplexParser.If_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_statement(SimplexParser.If_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#if_else_statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_else_statement(SimplexParser.If_else_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#if_else_statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_else_statement(SimplexParser.If_else_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#switch_cases}.
	 * @param ctx the parse tree
	 */
	void enterSwitch_cases(SimplexParser.Switch_casesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#switch_cases}.
	 * @param ctx the parse tree
	 */
	void exitSwitch_cases(SimplexParser.Switch_casesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#switch_case}.
	 * @param ctx the parse tree
	 */
	void enterSwitch_case(SimplexParser.Switch_caseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#switch_case}.
	 * @param ctx the parse tree
	 */
	void exitSwitch_case(SimplexParser.Switch_caseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#loop_statement}.
	 * @param ctx the parse tree
	 */
	void enterLoop_statement(SimplexParser.Loop_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#loop_statement}.
	 * @param ctx the parse tree
	 */
	void exitLoop_statement(SimplexParser.Loop_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#while_loop}.
	 * @param ctx the parse tree
	 */
	void enterWhile_loop(SimplexParser.While_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#while_loop}.
	 * @param ctx the parse tree
	 */
	void exitWhile_loop(SimplexParser.While_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void enterFor_loop(SimplexParser.For_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void exitFor_loop(SimplexParser.For_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#for_initializer}.
	 * @param ctx the parse tree
	 */
	void enterFor_initializer(SimplexParser.For_initializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#for_initializer}.
	 * @param ctx the parse tree
	 */
	void exitFor_initializer(SimplexParser.For_initializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#for_update}.
	 * @param ctx the parse tree
	 */
	void enterFor_update(SimplexParser.For_updateContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#for_update}.
	 * @param ctx the parse tree
	 */
	void exitFor_update(SimplexParser.For_updateContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#do_while_loop}.
	 * @param ctx the parse tree
	 */
	void enterDo_while_loop(SimplexParser.Do_while_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#do_while_loop}.
	 * @param ctx the parse tree
	 */
	void exitDo_while_loop(SimplexParser.Do_while_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#assignment_statement}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_statement(SimplexParser.Assignment_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#assignment_statement}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_statement(SimplexParser.Assignment_statementContext ctx);
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
	 * Enter a parse tree produced by {@link SimplexParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(SimplexParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(SimplexParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(SimplexParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(SimplexParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#arithmetic_operator}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic_operator(SimplexParser.Arithmetic_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#arithmetic_operator}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic_operator(SimplexParser.Arithmetic_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#comparison_operator}.
	 * @param ctx the parse tree
	 */
	void enterComparison_operator(SimplexParser.Comparison_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#comparison_operator}.
	 * @param ctx the parse tree
	 */
	void exitComparison_operator(SimplexParser.Comparison_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#logical_operator}.
	 * @param ctx the parse tree
	 */
	void enterLogical_operator(SimplexParser.Logical_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#logical_operator}.
	 * @param ctx the parse tree
	 */
	void exitLogical_operator(SimplexParser.Logical_operatorContext ctx);
}