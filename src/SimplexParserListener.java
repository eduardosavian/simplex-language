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
	 * Enter a parse tree produced by {@link SimplexParser#top_level}.
	 * @param ctx the parse tree
	 */
	void enterTop_level(SimplexParser.Top_levelContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#top_level}.
	 * @param ctx the parse tree
	 */
	void exitTop_level(SimplexParser.Top_levelContext ctx);
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
	 * Enter a parse tree produced by {@link SimplexParser#inline_statement}.
	 * @param ctx the parse tree
	 */
	void enterInline_statement(SimplexParser.Inline_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#inline_statement}.
	 * @param ctx the parse tree
	 */
	void exitInline_statement(SimplexParser.Inline_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#type_definition}.
	 * @param ctx the parse tree
	 */
	void enterType_definition(SimplexParser.Type_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#type_definition}.
	 * @param ctx the parse tree
	 */
	void exitType_definition(SimplexParser.Type_definitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#type_expression}.
	 * @param ctx the parse tree
	 */
	void enterType_expression(SimplexParser.Type_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#type_expression}.
	 * @param ctx the parse tree
	 */
	void exitType_expression(SimplexParser.Type_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#type_prefix}.
	 * @param ctx the parse tree
	 */
	void enterType_prefix(SimplexParser.Type_prefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#type_prefix}.
	 * @param ctx the parse tree
	 */
	void exitType_prefix(SimplexParser.Type_prefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#slice_prefix}.
	 * @param ctx the parse tree
	 */
	void enterSlice_prefix(SimplexParser.Slice_prefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#slice_prefix}.
	 * @param ctx the parse tree
	 */
	void exitSlice_prefix(SimplexParser.Slice_prefixContext ctx);
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
	 * Enter a parse tree produced by {@link SimplexParser#variable_definition}.
	 * @param ctx the parse tree
	 */
	void enterVariable_definition(SimplexParser.Variable_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#variable_definition}.
	 * @param ctx the parse tree
	 */
	void exitVariable_definition(SimplexParser.Variable_definitionContext ctx);
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
	 * Enter a parse tree produced by {@link SimplexParser#struct_definition}.
	 * @param ctx the parse tree
	 */
	void enterStruct_definition(SimplexParser.Struct_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#struct_definition}.
	 * @param ctx the parse tree
	 */
	void exitStruct_definition(SimplexParser.Struct_definitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#record_list}.
	 * @param ctx the parse tree
	 */
	void enterRecord_list(SimplexParser.Record_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#record_list}.
	 * @param ctx the parse tree
	 */
	void exitRecord_list(SimplexParser.Record_listContext ctx);
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
	 * Enter a parse tree produced by {@link SimplexParser#function_definition}.
	 * @param ctx the parse tree
	 */
	void enterFunction_definition(SimplexParser.Function_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#function_definition}.
	 * @param ctx the parse tree
	 */
	void exitFunction_definition(SimplexParser.Function_definitionContext ctx);
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
	 * Enter a parse tree produced by {@link SimplexParser#if}.
	 * @param ctx the parse tree
	 */
	void enterIf(SimplexParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#if}.
	 * @param ctx the parse tree
	 */
	void exitIf(SimplexParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#else}.
	 * @param ctx the parse tree
	 */
	void enterElse(SimplexParser.ElseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#else}.
	 * @param ctx the parse tree
	 */
	void exitElse(SimplexParser.ElseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#elif}.
	 * @param ctx the parse tree
	 */
	void enterElif(SimplexParser.ElifContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#elif}.
	 * @param ctx the parse tree
	 */
	void exitElif(SimplexParser.ElifContext ctx);
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
	 * Enter a parse tree produced by {@link SimplexParser#expression_list}.
	 * @param ctx the parse tree
	 */
	void enterExpression_list(SimplexParser.Expression_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#expression_list}.
	 * @param ctx the parse tree
	 */
	void exitExpression_list(SimplexParser.Expression_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#function_call}.
	 * @param ctx the parse tree
	 */
	void enterFunction_call(SimplexParser.Function_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#function_call}.
	 * @param ctx the parse tree
	 */
	void exitFunction_call(SimplexParser.Function_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplexParser#grouped_expression}.
	 * @param ctx the parse tree
	 */
	void enterGrouped_expression(SimplexParser.Grouped_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplexParser#grouped_expression}.
	 * @param ctx the parse tree
	 */
	void exitGrouped_expression(SimplexParser.Grouped_expressionContext ctx);
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