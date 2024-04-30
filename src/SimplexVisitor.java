import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class SimplexVisitor extends SimplexParserBaseVisitor<Integer>{
    Map<String, Integer> memory = new HashMap<String, Integer>();

    @Override
    public Integer visitBitShift(SimplexParser.BitShiftContext ctx) {
        // TODO Auto-generated method stub
        return super.visitBitShift(ctx);
    }

    @Override
    public Integer visitBreakStatement(SimplexParser.BreakStatementContext ctx) {
        // TODO Auto-generated method stub
        return super.visitBreakStatement(ctx);
    }

    @Override
    public Integer visitComparison(SimplexParser.ComparisonContext ctx) {
        // TODO Auto-generated method stub
        return super.visitComparison(ctx);
    }

    @Override
    public Integer visitContinueStatement(SimplexParser.ContinueStatementContext ctx) {
        // TODO Auto-generated method stub
        return super.visitContinueStatement(ctx);
    }

    @Override
    public Integer visitDoFor(SimplexParser.DoForContext ctx) {
        // TODO Auto-generated method stub
        return super.visitDoFor(ctx);
    }

    @Override
    public Integer visitExprStatement(SimplexParser.ExprStatementContext ctx) {
        // TODO Auto-generated method stub
        return super.visitExprStatement(ctx);
    }

    @Override
    public Integer visitExpression(SimplexParser.ExpressionContext ctx) {
        // TODO Auto-generated method stub
        return super.visitExpression(ctx);
    }

    @Override
    public Integer visitExpressionList(SimplexParser.ExpressionListContext ctx) {
        // TODO Auto-generated method stub
        return super.visitExpressionList(ctx);
    }

    @Override
    public Integer visitFactor(SimplexParser.FactorContext ctx) {
        // TODO Auto-generated method stub
        return super.visitFactor(ctx);
    }

    @Override
    public Integer visitField(SimplexParser.FieldContext ctx) {
        // TODO Auto-generated method stub
        return super.visitField(ctx);
    }

    @Override
    public Integer visitFieldList(SimplexParser.FieldListContext ctx) {
        // TODO Auto-generated method stub
        return super.visitFieldList(ctx);
    }

    @Override
    public Integer visitFuncDeclaration(SimplexParser.FuncDeclarationContext ctx) {
        // TODO Auto-generated method stub
        return super.visitFuncDeclaration(ctx);
    }

    @Override
    public Integer visitFunctionCall(SimplexParser.FunctionCallContext ctx) {
        // TODO Auto-generated method stub
        return super.visitFunctionCall(ctx);
    }

    @Override
    public Integer visitGroup(SimplexParser.GroupContext ctx) {
        // TODO Auto-generated method stub
        return super.visitGroup(ctx);
    }

    @Override
    public Integer visitIdentifierList(SimplexParser.IdentifierListContext ctx) {
        // TODO Auto-generated method stub
        return super.visitIdentifierList(ctx);
    }

    @Override
    public Integer visitIfStatement(SimplexParser.IfStatementContext ctx) {
        // TODO Auto-generated method stub
        return super.visitIfStatement(ctx);
    }

    @Override
    public Integer visitIndexing(SimplexParser.IndexingContext ctx) {
        // TODO Auto-generated method stub
        return super.visitIndexing(ctx);
    }

    @Override
    public Integer visitInlineStatement(SimplexParser.InlineStatementContext ctx) {
        // TODO Auto-generated method stub
        return super.visitInlineStatement(ctx);
    }

    @Override
    public Integer visitInteger(SimplexParser.IntegerContext ctx) {
        // TODO Auto-generated method stub
        return super.visitInteger(ctx);
    }

    @Override
    public Integer visitLogicConjunction(SimplexParser.LogicConjunctionContext ctx) {
        // TODO Auto-generated method stub
        return super.visitLogicConjunction(ctx);
    }

    @Override
    public Integer visitLogicDisjunction(SimplexParser.LogicDisjunctionContext ctx) {
        // TODO Auto-generated method stub
        return super.visitLogicDisjunction(ctx);
    }

    @Override
    public Integer visitPrimary(SimplexParser.PrimaryContext ctx) {
        // TODO Auto-generated method stub
        return super.visitPrimary(ctx);
    }

    @Override
    public Integer visitProgram(SimplexParser.ProgramContext ctx) {
        // TODO Auto-generated method stub
        return super.visitProgram(ctx);
    }

    @Override
    public Integer visitReal(SimplexParser.RealContext ctx) {
        // TODO Auto-generated method stub
        return super.visitReal(ctx);
    }

    @Override
    public Integer visitReturnStatement(SimplexParser.ReturnStatementContext ctx) {
        // TODO Auto-generated method stub
        return super.visitReturnStatement(ctx);
    }

    @Override
    public Integer visitScope(SimplexParser.ScopeContext ctx) {
        // TODO Auto-generated method stub
        return super.visitScope(ctx);
    }

    @Override
    public Integer visitSimpleFor(SimplexParser.SimpleForContext ctx) {
        // TODO Auto-generated method stub
        return super.visitSimpleFor(ctx);
    }

    @Override
    public Integer visitSliceIndicator(SimplexParser.SliceIndicatorContext ctx) {
        // TODO Auto-generated method stub
        return super.visitSliceIndicator(ctx);
    }

    @Override
    public Integer visitStatement(SimplexParser.StatementContext ctx) {
        // TODO Auto-generated method stub
        return super.visitStatement(ctx);
    }

    @Override
    public Integer visitTerm(SimplexParser.TermContext ctx) {
        // TODO Auto-generated method stub
        return super.visitTerm(ctx);
    }

    @Override
    public Integer visitTripleFor(SimplexParser.TripleForContext ctx) {
        // TODO Auto-generated method stub
        return super.visitTripleFor(ctx);
    }

    @Override
    public Integer visitTypeExpression(SimplexParser.TypeExpressionContext ctx) {
        // TODO Auto-generated method stub
        return super.visitTypeExpression(ctx);
    }

    @Override
    public Integer visitUnary(SimplexParser.UnaryContext ctx) {
        // TODO Auto-generated method stub
        return super.visitUnary(ctx);
    }

    @Override
    public Integer visitVarAssignment(SimplexParser.VarAssignmentContext ctx) {
        // TODO Auto-generated method stub
        return super.visitVarAssignment(ctx);
    }

    @Override
    public Integer visitVarDeclaration(SimplexParser.VarDeclarationContext ctx) {
        System.out.println(ctx.children.get(0).getText() + " = " + ctx.children.get(2).getText() + ";");
        // TODO Auto-generated method stub
        return super.visitVarDeclaration(ctx);
    }

    @Override
    protected Integer aggregateResult(Integer aggregate, Integer nextResult) {
        // TODO Auto-generated method stub
        return super.aggregateResult(aggregate, nextResult);
    }

    @Override
    protected Integer defaultResult() {
        // TODO Auto-generated method stub
        return super.defaultResult();
    }

    @Override
    protected boolean shouldVisitNextChild(RuleNode node, Integer currentResult) {
        // TODO Auto-generated method stub
        return super.shouldVisitNextChild(node, currentResult);
    }

    @Override
    public Integer visit(ParseTree tree) {
        // TODO Auto-generated method stub
        return super.visit(tree);
    }

    @Override
    public Integer visitChildren(RuleNode arg0) {
        // TODO Auto-generated method stub
        return super.visitChildren(arg0);
    }

    @Override
    public Integer visitErrorNode(ErrorNode node) {
        // TODO Auto-generated method stub
        return super.visitErrorNode(node);
    }

    @Override
    public Integer visitTerminal(TerminalNode node) {
        // TODO Auto-generated method stub
        return super.visitTerminal(node);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
    
}
