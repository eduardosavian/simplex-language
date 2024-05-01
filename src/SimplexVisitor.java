import java.util.ArrayList;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class SimplexVisitor extends SimplexParserBaseVisitor<Integer> {
    ArrayList<Symbol> symbols = new ArrayList<Symbol>();

    @Override
    public Integer visitBitShift(SimplexParser.BitShiftContext ctx) {

        return super.visitBitShift(ctx);
    }

    @Override
    public Integer visitBreakStatement(SimplexParser.BreakStatementContext ctx) {

        return super.visitBreakStatement(ctx);
    }

    @Override
    public Integer visitComparison(SimplexParser.ComparisonContext ctx) {

        return super.visitComparison(ctx);
    }

    @Override
    public Integer visitContinueStatement(SimplexParser.ContinueStatementContext ctx) {

        return super.visitContinueStatement(ctx);
    }

    @Override
    public Integer visitDoFor(SimplexParser.DoForContext ctx) {

        return super.visitDoFor(ctx);
    }

    @Override
    public Integer visitExprStatement(SimplexParser.ExprStatementContext ctx) {

        return super.visitExprStatement(ctx);
    }

    @Override
    public Integer visitExpression(SimplexParser.ExpressionContext ctx) {

        return super.visitExpression(ctx);
    }

    @Override
    public Integer visitExpressionList(SimplexParser.ExpressionListContext ctx) {

        return super.visitExpressionList(ctx);
    }

    @Override
    public Integer visitFactor(SimplexParser.FactorContext ctx) {

        return super.visitFactor(ctx);
    }

    @Override
    public Integer visitField(SimplexParser.FieldContext ctx) {

        return super.visitField(ctx);
    }

    @Override
    public Integer visitFieldList(SimplexParser.FieldListContext ctx) {

        return super.visitFieldList(ctx);
    }

    @Override
    public Integer visitFuncDeclaration(SimplexParser.FuncDeclarationContext ctx) {

        return super.visitFuncDeclaration(ctx);
    }

    @Override
    public Integer visitFunctionCall(SimplexParser.FunctionCallContext ctx) {

        return super.visitFunctionCall(ctx);
    }

    @Override
    public Integer visitGroup(SimplexParser.GroupContext ctx) {

        return super.visitGroup(ctx);
    }

    @Override
    public Integer visitIdentifierList(SimplexParser.IdentifierListContext ctx) {

        return super.visitIdentifierList(ctx);
    }

    @Override
    public Integer visitIfStatement(SimplexParser.IfStatementContext ctx) {

        return super.visitIfStatement(ctx);
    }

    @Override
    public Integer visitIndexing(SimplexParser.IndexingContext ctx) {

        return super.visitIndexing(ctx);
    }

    @Override
    public Integer visitInlineStatement(SimplexParser.InlineStatementContext ctx) {

        return super.visitInlineStatement(ctx);
    }

    @Override
    public Integer visitInteger(SimplexParser.IntegerContext ctx) {

        return super.visitInteger(ctx);
    }

    @Override
    public Integer visitLogicConjunction(SimplexParser.LogicConjunctionContext ctx) {

        return super.visitLogicConjunction(ctx);
    }

    @Override
    public Integer visitLogicDisjunction(SimplexParser.LogicDisjunctionContext ctx) {

        return super.visitLogicDisjunction(ctx);
    }

    @Override
    public Integer visitPrimary(SimplexParser.PrimaryContext ctx) {

        return super.visitPrimary(ctx);
    }

    @Override
    public Integer visitProgram(SimplexParser.ProgramContext ctx) {

        return super.visitProgram(ctx);
    }

    @Override
    public Integer visitReal(SimplexParser.RealContext ctx) {

        return super.visitReal(ctx);
    }

    @Override
    public Integer visitReturnStatement(SimplexParser.ReturnStatementContext ctx) {

        return super.visitReturnStatement(ctx);
    }

    @Override
    public Integer visitScope(SimplexParser.ScopeContext ctx) {

        return super.visitScope(ctx);
    }

    @Override
    public Integer visitSimpleFor(SimplexParser.SimpleForContext ctx) {

        return super.visitSimpleFor(ctx);
    }

    @Override
    public Integer visitSliceIndicator(SimplexParser.SliceIndicatorContext ctx) {

        return super.visitSliceIndicator(ctx);
    }

    @Override
    public Integer visitStatement(SimplexParser.StatementContext ctx) {

        return super.visitStatement(ctx);
    }

    @Override
    public Integer visitTerm(SimplexParser.TermContext ctx) {

        return super.visitTerm(ctx);
    }

    @Override
    public Integer visitTripleFor(SimplexParser.TripleForContext ctx) {

        return super.visitTripleFor(ctx);
    }

    @Override
    public Integer visitTypeExpression(SimplexParser.TypeExpressionContext ctx) {

        return super.visitTypeExpression(ctx);
    }

    @Override
    public Integer visitUnary(SimplexParser.UnaryContext ctx) {

        return super.visitUnary(ctx);
    }

    @Override
    public Integer visitVarAssignment(SimplexParser.VarAssignmentContext ctx) {

        return super.visitVarAssignment(ctx);
    }

    @Override
    public Integer visitVarDeclaration(SimplexParser.VarDeclarationContext ctx) {
        String id = ctx.getChild(0).getText();
        String type = ctx.getChild(2).getText();

        System.out.println("id: " + id + " type: " + type);

        // name;
        // type;
        // initialized;
        // used;
        // scoped;
        // param;
        // array;
        // matrix;
        // ref;
        // func;

        return super.visitVarDeclaration(ctx);
    }

    @Override
    protected Integer aggregateResult(Integer aggregate, Integer nextResult) {

        return super.aggregateResult(aggregate, nextResult);
    }

    @Override
    protected Integer defaultResult() {

        return super.defaultResult();
    }

    @Override
    protected boolean shouldVisitNextChild(RuleNode node, Integer currentResult) {

        return super.shouldVisitNextChild(node, currentResult);
    }

    @Override
    public Integer visit(ParseTree tree) {

        return super.visit(tree);
    }

    @Override
    public Integer visitChildren(RuleNode arg0) {

        return super.visitChildren(arg0);
    }

    @Override
    public Integer visitErrorNode(ErrorNode node) {

        return super.visitErrorNode(node);
    }

    @Override
    public Integer visitTerminal(TerminalNode node) {

        return super.visitTerminal(node);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {

        return super.equals(obj);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void finalize() throws Throwable {

        super.finalize();
    }

    @Override
    public int hashCode() {

        return super.hashCode();
    }

    @Override
    public String toString() {

        return super.toString();
    }

    public String verifyScope() {
        return "";
    }

}
