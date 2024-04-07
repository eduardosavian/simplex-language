// Generated from /home/eduardo/Documents/Projects/simplex-language/simplex/SimplexParser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class SimplexParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, ECHO=2, IF=3, ELSE=4, ELIF=5, SWITCH=6, CASE=7, DEFAULT=8, CONTINUE=9, 
		FOR=10, BREAK=11, RETURN=12, DO=13, FUN=14, RIGHT_ARROW=15, TYPE=16, STRUCT=17, 
		LITERAL_HEX=18, LITERAL_BIN=19, LITERAL_INT=20, LITERAL_FLOAT=21, LITERAL_STRING=22, 
		LITERAL_RUNE=23, DOT=24, COMMA=25, COLON=26, EOS=27, SQUARE_OPEN=28, SQUARE_CLOSE=29, 
		CURLY_OPEN=30, CURLY_CLOSE=31, PAREN_OPEN=32, PAREN_CLOSE=33, CARET=34, 
		TRUE=35, FALSE=36, NIL=37, MINUS=38, PLUS=39, SLASH=40, STAR=41, MOD=42, 
		LT=43, GT=44, LT_EQ=45, GT_EQ=46, EQ_EQ=47, NOT_EQ=48, LOGIC_AND=49, LOGIC_OR=50, 
		LOGIC_NOT=51, LOGIC_XOR=52, OR=53, EQ=54, AND=55, XOR=56, SHIFT_LEFT=57, 
		SHIFT_RIGHT=58, SL_COMMENT=59, ML_COMMENT=60, ID=61, READ=62;
	public static final int
		RULE_program = 0, RULE_inlineStatement = 1, RULE_returnStatement = 2, 
		RULE_breakStatement = 3, RULE_continueStatement = 4, RULE_statement = 5, 
		RULE_varDeclaration = 6, RULE_identifierList = 7, RULE_varAssignment = 8, 
		RULE_sliceIndicator = 9, RULE_typeExpression = 10, RULE_funcDeclaration = 11, 
		RULE_field = 12, RULE_fieldList = 13, RULE_scope = 14, RULE_simpleFor = 15, 
		RULE_tripleFor = 16, RULE_doFor = 17, RULE_ifStatement = 18, RULE_exprStatement = 19, 
		RULE_expression = 20, RULE_logicDisjunction = 21, RULE_logicConjunction = 22, 
		RULE_comparison = 23, RULE_term = 24, RULE_factor = 25, RULE_bitShift = 26, 
		RULE_unary = 27, RULE_primary = 28, RULE_group = 29, RULE_functionCall = 30, 
		RULE_expressionList = 31, RULE_integer = 32, RULE_real = 33, RULE_indexing = 34, 
		RULE_echoStatement = 35, RULE_readStatement = 36;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "inlineStatement", "returnStatement", "breakStatement", "continueStatement", 
			"statement", "varDeclaration", "identifierList", "varAssignment", "sliceIndicator", 
			"typeExpression", "funcDeclaration", "field", "fieldList", "scope", "simpleFor", 
			"tripleFor", "doFor", "ifStatement", "exprStatement", "expression", "logicDisjunction", 
			"logicConjunction", "comparison", "term", "factor", "bitShift", "unary", 
			"primary", "group", "functionCall", "expressionList", "integer", "real", 
			"indexing", "echoStatement", "readStatement"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'ECHO'", "'if'", "'else'", "'elif'", "'switch'", "'case'", 
			"'default'", "'continue'", "'for'", "'break'", "'return'", "'do'", "'fun'", 
			"'->'", "'type'", "'struct'", null, null, null, null, null, null, "'.'", 
			"','", "':'", "';'", "'['", "']'", "'{'", "'}'", "'('", "')'", "'^'", 
			"'true'", "'false'", "'nil'", "'-'", "'+'", "'/'", "'*'", "'%'", "'<'", 
			"'>'", "'>='", "'<='", "'=='", "'!='", "'&&'", "'||'", "'!'", "'~~'", 
			"'|'", "'='", "'&'", "'~'", "'<<'", "'>>'", null, null, null, "'read'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "ECHO", "IF", "ELSE", "ELIF", "SWITCH", "CASE", "DEFAULT", 
			"CONTINUE", "FOR", "BREAK", "RETURN", "DO", "FUN", "RIGHT_ARROW", "TYPE", 
			"STRUCT", "LITERAL_HEX", "LITERAL_BIN", "LITERAL_INT", "LITERAL_FLOAT", 
			"LITERAL_STRING", "LITERAL_RUNE", "DOT", "COMMA", "COLON", "EOS", "SQUARE_OPEN", 
			"SQUARE_CLOSE", "CURLY_OPEN", "CURLY_CLOSE", "PAREN_OPEN", "PAREN_CLOSE", 
			"CARET", "TRUE", "FALSE", "NIL", "MINUS", "PLUS", "SLASH", "STAR", "MOD", 
			"LT", "GT", "LT_EQ", "GT_EQ", "EQ_EQ", "NOT_EQ", "LOGIC_AND", "LOGIC_OR", 
			"LOGIC_NOT", "LOGIC_XOR", "OR", "EQ", "AND", "XOR", "SHIFT_LEFT", "SHIFT_RIGHT", 
			"SL_COMMENT", "ML_COMMENT", "ID", "READ"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "SimplexParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SimplexParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6991839492029840908L) != 0)) {
				{
				{
				setState(74);
				statement();
				}
				}
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InlineStatementContext extends ParserRuleContext {
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public ExprStatementContext exprStatement() {
			return getRuleContext(ExprStatementContext.class,0);
		}
		public VarDeclarationContext varDeclaration() {
			return getRuleContext(VarDeclarationContext.class,0);
		}
		public VarAssignmentContext varAssignment() {
			return getRuleContext(VarAssignmentContext.class,0);
		}
		public InlineStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inlineStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterInlineStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitInlineStatement(this);
		}
	}

	public final InlineStatementContext inlineStatement() throws RecognitionException {
		InlineStatementContext _localctx = new InlineStatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_inlineStatement);
		try {
			setState(86);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(80);
				returnStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(81);
				breakStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(82);
				continueStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(83);
				exprStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(84);
				varDeclaration();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(85);
				varAssignment();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(SimplexParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitReturnStatement(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_returnStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(RETURN);
			setState(89);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BreakStatementContext extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(SimplexParser.BREAK, 0); }
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitBreakStatement(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(BREAK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContinueStatementContext extends ParserRuleContext {
		public TerminalNode CONTINUE() { return getToken(SimplexParser.CONTINUE, 0); }
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitContinueStatement(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(CONTINUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public InlineStatementContext inlineStatement() {
			return getRuleContext(InlineStatementContext.class,0);
		}
		public TerminalNode EOS() { return getToken(SimplexParser.EOS, 0); }
		public SimpleForContext simpleFor() {
			return getRuleContext(SimpleForContext.class,0);
		}
		public TripleForContext tripleFor() {
			return getRuleContext(TripleForContext.class,0);
		}
		public DoForContext doFor() {
			return getRuleContext(DoForContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public FuncDeclarationContext funcDeclaration() {
			return getRuleContext(FuncDeclarationContext.class,0);
		}
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public EchoStatementContext echoStatement() {
			return getRuleContext(EchoStatementContext.class,0);
		}
		public ReadStatementContext readStatement() {
			return getRuleContext(ReadStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_statement);
		try {
			setState(106);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				inlineStatement();
				setState(96);
				match(EOS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
				simpleFor();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(99);
				tripleFor();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(100);
				doFor();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(101);
				ifStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(102);
				funcDeclaration();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(103);
				scope();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(104);
				echoStatement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(105);
				readStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarDeclarationContext extends ParserRuleContext {
		public IdentifierListContext identifierList() {
			return getRuleContext(IdentifierListContext.class,0);
		}
		public TerminalNode COLON() { return getToken(SimplexParser.COLON, 0); }
		public TypeExpressionContext typeExpression() {
			return getRuleContext(TypeExpressionContext.class,0);
		}
		public TerminalNode EQ() { return getToken(SimplexParser.EQ, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public VarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitVarDeclaration(this);
		}
	}

	public final VarDeclarationContext varDeclaration() throws RecognitionException {
		VarDeclarationContext _localctx = new VarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_varDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			identifierList();
			setState(109);
			match(COLON);
			setState(110);
			typeExpression();
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EQ) {
				{
				setState(111);
				match(EQ);
				setState(112);
				expressionList();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierListContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(SimplexParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SimplexParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SimplexParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SimplexParser.COMMA, i);
		}
		public IdentifierListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterIdentifierList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitIdentifierList(this);
		}
	}

	public final IdentifierListContext identifierList() throws RecognitionException {
		IdentifierListContext _localctx = new IdentifierListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_identifierList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(ID);
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(116);
				match(COMMA);
				setState(117);
				match(ID);
				}
				}
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarAssignmentContext extends ParserRuleContext {
		public List<ExpressionListContext> expressionList() {
			return getRuleContexts(ExpressionListContext.class);
		}
		public ExpressionListContext expressionList(int i) {
			return getRuleContext(ExpressionListContext.class,i);
		}
		public TerminalNode EQ() { return getToken(SimplexParser.EQ, 0); }
		public VarAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterVarAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitVarAssignment(this);
		}
	}

	public final VarAssignmentContext varAssignment() throws RecognitionException {
		VarAssignmentContext _localctx = new VarAssignmentContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_varAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			expressionList();
			setState(124);
			match(EQ);
			setState(125);
			expressionList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SliceIndicatorContext extends ParserRuleContext {
		public TerminalNode SQUARE_OPEN() { return getToken(SimplexParser.SQUARE_OPEN, 0); }
		public TerminalNode SQUARE_CLOSE() { return getToken(SimplexParser.SQUARE_CLOSE, 0); }
		public SliceIndicatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sliceIndicator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterSliceIndicator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitSliceIndicator(this);
		}
	}

	public final SliceIndicatorContext sliceIndicator() throws RecognitionException {
		SliceIndicatorContext _localctx = new SliceIndicatorContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_sliceIndicator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(SQUARE_OPEN);
			setState(128);
			match(SQUARE_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeExpressionContext extends ParserRuleContext {
		public SliceIndicatorContext sliceIndicator() {
			return getRuleContext(SliceIndicatorContext.class,0);
		}
		public TypeExpressionContext typeExpression() {
			return getRuleContext(TypeExpressionContext.class,0);
		}
		public TerminalNode CARET() { return getToken(SimplexParser.CARET, 0); }
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public TypeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterTypeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitTypeExpression(this);
		}
	}

	public final TypeExpressionContext typeExpression() throws RecognitionException {
		TypeExpressionContext _localctx = new TypeExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_typeExpression);
		try {
			setState(136);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SQUARE_OPEN:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				sliceIndicator();
				setState(131);
				typeExpression();
				}
				break;
			case CARET:
				enterOuterAlt(_localctx, 2);
				{
				setState(133);
				match(CARET);
				setState(134);
				typeExpression();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(135);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncDeclarationContext extends ParserRuleContext {
		public TerminalNode FUN() { return getToken(SimplexParser.FUN, 0); }
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public TerminalNode PAREN_OPEN() { return getToken(SimplexParser.PAREN_OPEN, 0); }
		public FieldListContext fieldList() {
			return getRuleContext(FieldListContext.class,0);
		}
		public TerminalNode PAREN_CLOSE() { return getToken(SimplexParser.PAREN_CLOSE, 0); }
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public TerminalNode RIGHT_ARROW() { return getToken(SimplexParser.RIGHT_ARROW, 0); }
		public TypeExpressionContext typeExpression() {
			return getRuleContext(TypeExpressionContext.class,0);
		}
		public FuncDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterFuncDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitFuncDeclaration(this);
		}
	}

	public final FuncDeclarationContext funcDeclaration() throws RecognitionException {
		FuncDeclarationContext _localctx = new FuncDeclarationContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_funcDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(FUN);
			setState(139);
			match(ID);
			setState(140);
			match(PAREN_OPEN);
			setState(141);
			fieldList();
			setState(142);
			match(PAREN_CLOSE);
			{
			setState(143);
			match(RIGHT_ARROW);
			setState(144);
			typeExpression();
			}
			setState(146);
			scope();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public TerminalNode COLON() { return getToken(SimplexParser.COLON, 0); }
		public TypeExpressionContext typeExpression() {
			return getRuleContext(TypeExpressionContext.class,0);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitField(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(ID);
			setState(149);
			match(COLON);
			setState(150);
			typeExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldListContext extends ParserRuleContext {
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SimplexParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SimplexParser.COMMA, i);
		}
		public FieldListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterFieldList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitFieldList(this);
		}
	}

	public final FieldListContext fieldList() throws RecognitionException {
		FieldListContext _localctx = new FieldListContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_fieldList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			field();
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(153);
				match(COMMA);
				setState(154);
				field();
				}
				}
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ScopeContext extends ParserRuleContext {
		public TerminalNode CURLY_OPEN() { return getToken(SimplexParser.CURLY_OPEN, 0); }
		public TerminalNode CURLY_CLOSE() { return getToken(SimplexParser.CURLY_CLOSE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ScopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterScope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitScope(this);
		}
	}

	public final ScopeContext scope() throws RecognitionException {
		ScopeContext _localctx = new ScopeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_scope);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(CURLY_OPEN);
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6991839492029840908L) != 0)) {
				{
				{
				setState(161);
				statement();
				}
				}
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(167);
			match(CURLY_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SimpleForContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(SimplexParser.FOR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public SimpleForContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleFor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterSimpleFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitSimpleFor(this);
		}
	}

	public final SimpleForContext simpleFor() throws RecognitionException {
		SimpleForContext _localctx = new SimpleForContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_simpleFor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(FOR);
			setState(170);
			expression();
			setState(171);
			scope();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TripleForContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(SimplexParser.FOR, 0); }
		public List<InlineStatementContext> inlineStatement() {
			return getRuleContexts(InlineStatementContext.class);
		}
		public InlineStatementContext inlineStatement(int i) {
			return getRuleContext(InlineStatementContext.class,i);
		}
		public List<TerminalNode> EOS() { return getTokens(SimplexParser.EOS); }
		public TerminalNode EOS(int i) {
			return getToken(SimplexParser.EOS, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public TripleForContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tripleFor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterTripleFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitTripleFor(this);
		}
	}

	public final TripleForContext tripleFor() throws RecognitionException {
		TripleForContext _localctx = new TripleForContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_tripleFor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(FOR);
			setState(174);
			inlineStatement();
			setState(175);
			match(EOS);
			setState(176);
			expression();
			setState(177);
			match(EOS);
			setState(178);
			inlineStatement();
			setState(179);
			scope();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DoForContext extends ParserRuleContext {
		public TerminalNode DO() { return getToken(SimplexParser.DO, 0); }
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public TerminalNode FOR() { return getToken(SimplexParser.FOR, 0); }
		public InlineStatementContext inlineStatement() {
			return getRuleContext(InlineStatementContext.class,0);
		}
		public TerminalNode EOS() { return getToken(SimplexParser.EOS, 0); }
		public DoForContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doFor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterDoFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitDoFor(this);
		}
	}

	public final DoForContext doFor() throws RecognitionException {
		DoForContext _localctx = new DoForContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_doFor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			match(DO);
			setState(182);
			scope();
			setState(183);
			match(FOR);
			setState(184);
			inlineStatement();
			setState(185);
			match(EOS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(SimplexParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<ScopeContext> scope() {
			return getRuleContexts(ScopeContext.class);
		}
		public ScopeContext scope(int i) {
			return getRuleContext(ScopeContext.class,i);
		}
		public List<TerminalNode> ELSE() { return getTokens(SimplexParser.ELSE); }
		public TerminalNode ELSE(int i) {
			return getToken(SimplexParser.ELSE, i);
		}
		public List<IfStatementContext> ifStatement() {
			return getRuleContexts(IfStatementContext.class);
		}
		public IfStatementContext ifStatement(int i) {
			return getRuleContext(IfStatementContext.class,i);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitIfStatement(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_ifStatement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(IF);
			setState(188);
			expression();
			setState(189);
			scope();
			setState(194);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(190);
					match(ELSE);
					setState(191);
					ifStatement();
					}
					} 
				}
				setState(196);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			setState(199);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(197);
				match(ELSE);
				setState(198);
				scope();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterExprStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitExprStatement(this);
		}
	}

	public final ExprStatementContext exprStatement() throws RecognitionException {
		ExprStatementContext _localctx = new ExprStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_exprStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public LogicDisjunctionContext logicDisjunction() {
			return getRuleContext(LogicDisjunctionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			logicDisjunction();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogicDisjunctionContext extends ParserRuleContext {
		public List<LogicConjunctionContext> logicConjunction() {
			return getRuleContexts(LogicConjunctionContext.class);
		}
		public LogicConjunctionContext logicConjunction(int i) {
			return getRuleContext(LogicConjunctionContext.class,i);
		}
		public List<TerminalNode> LOGIC_OR() { return getTokens(SimplexParser.LOGIC_OR); }
		public TerminalNode LOGIC_OR(int i) {
			return getToken(SimplexParser.LOGIC_OR, i);
		}
		public List<TerminalNode> LOGIC_XOR() { return getTokens(SimplexParser.LOGIC_XOR); }
		public TerminalNode LOGIC_XOR(int i) {
			return getToken(SimplexParser.LOGIC_XOR, i);
		}
		public LogicDisjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicDisjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterLogicDisjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitLogicDisjunction(this);
		}
	}

	public final LogicDisjunctionContext logicDisjunction() throws RecognitionException {
		LogicDisjunctionContext _localctx = new LogicDisjunctionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_logicDisjunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			logicConjunction();
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LOGIC_OR || _la==LOGIC_XOR) {
				{
				{
				setState(206);
				_la = _input.LA(1);
				if ( !(_la==LOGIC_OR || _la==LOGIC_XOR) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(207);
				logicConjunction();
				}
				}
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogicConjunctionContext extends ParserRuleContext {
		public List<ComparisonContext> comparison() {
			return getRuleContexts(ComparisonContext.class);
		}
		public ComparisonContext comparison(int i) {
			return getRuleContext(ComparisonContext.class,i);
		}
		public List<TerminalNode> LOGIC_AND() { return getTokens(SimplexParser.LOGIC_AND); }
		public TerminalNode LOGIC_AND(int i) {
			return getToken(SimplexParser.LOGIC_AND, i);
		}
		public LogicConjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicConjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterLogicConjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitLogicConjunction(this);
		}
	}

	public final LogicConjunctionContext logicConjunction() throws RecognitionException {
		LogicConjunctionContext _localctx = new LogicConjunctionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_logicConjunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			comparison();
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LOGIC_AND) {
				{
				{
				{
				setState(214);
				match(LOGIC_AND);
				}
				setState(215);
				comparison();
				}
				}
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> EQ_EQ() { return getTokens(SimplexParser.EQ_EQ); }
		public TerminalNode EQ_EQ(int i) {
			return getToken(SimplexParser.EQ_EQ, i);
		}
		public List<TerminalNode> NOT_EQ() { return getTokens(SimplexParser.NOT_EQ); }
		public TerminalNode NOT_EQ(int i) {
			return getToken(SimplexParser.NOT_EQ, i);
		}
		public List<TerminalNode> GT() { return getTokens(SimplexParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(SimplexParser.GT, i);
		}
		public List<TerminalNode> GT_EQ() { return getTokens(SimplexParser.GT_EQ); }
		public TerminalNode GT_EQ(int i) {
			return getToken(SimplexParser.GT_EQ, i);
		}
		public List<TerminalNode> LT() { return getTokens(SimplexParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(SimplexParser.LT, i);
		}
		public List<TerminalNode> LT_EQ() { return getTokens(SimplexParser.LT_EQ); }
		public TerminalNode LT_EQ(int i) {
			return getToken(SimplexParser.LT_EQ, i);
		}
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitComparison(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_comparison);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			term();
			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 554153860399104L) != 0)) {
				{
				{
				setState(222);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 554153860399104L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(223);
				term();
				}
				}
				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(SimplexParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(SimplexParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(SimplexParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(SimplexParser.MINUS, i);
		}
		public List<TerminalNode> OR() { return getTokens(SimplexParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(SimplexParser.OR, i);
		}
		public List<TerminalNode> XOR() { return getTokens(SimplexParser.XOR); }
		public TerminalNode XOR(int i) {
			return getToken(SimplexParser.XOR, i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			factor();
			setState(234);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 81065617926389760L) != 0)) {
				{
				{
				setState(230);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 81065617926389760L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(231);
				factor();
				}
				}
				setState(236);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FactorContext extends ParserRuleContext {
		public List<BitShiftContext> bitShift() {
			return getRuleContexts(BitShiftContext.class);
		}
		public BitShiftContext bitShift(int i) {
			return getRuleContext(BitShiftContext.class,i);
		}
		public List<TerminalNode> STAR() { return getTokens(SimplexParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(SimplexParser.STAR, i);
		}
		public List<TerminalNode> SLASH() { return getTokens(SimplexParser.SLASH); }
		public TerminalNode SLASH(int i) {
			return getToken(SimplexParser.SLASH, i);
		}
		public List<TerminalNode> MOD() { return getTokens(SimplexParser.MOD); }
		public TerminalNode MOD(int i) {
			return getToken(SimplexParser.MOD, i);
		}
		public List<TerminalNode> AND() { return getTokens(SimplexParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(SimplexParser.AND, i);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitFactor(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			bitShift();
			setState(242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 36036493600358400L) != 0)) {
				{
				{
				setState(238);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 36036493600358400L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(239);
				bitShift();
				}
				}
				setState(244);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BitShiftContext extends ParserRuleContext {
		public List<UnaryContext> unary() {
			return getRuleContexts(UnaryContext.class);
		}
		public UnaryContext unary(int i) {
			return getRuleContext(UnaryContext.class,i);
		}
		public List<TerminalNode> SHIFT_LEFT() { return getTokens(SimplexParser.SHIFT_LEFT); }
		public TerminalNode SHIFT_LEFT(int i) {
			return getToken(SimplexParser.SHIFT_LEFT, i);
		}
		public List<TerminalNode> SHIFT_RIGHT() { return getTokens(SimplexParser.SHIFT_RIGHT); }
		public TerminalNode SHIFT_RIGHT(int i) {
			return getToken(SimplexParser.SHIFT_RIGHT, i);
		}
		public BitShiftContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitShift; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterBitShift(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitBitShift(this);
		}
	}

	public final BitShiftContext bitShift() throws RecognitionException {
		BitShiftContext _localctx = new BitShiftContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_bitShift);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			unary();
			setState(250);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SHIFT_LEFT || _la==SHIFT_RIGHT) {
				{
				{
				setState(246);
				_la = _input.LA(1);
				if ( !(_la==SHIFT_LEFT || _la==SHIFT_RIGHT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(247);
				unary();
				}
				}
				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnaryContext extends ParserRuleContext {
		public UnaryContext unary() {
			return getRuleContext(UnaryContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(SimplexParser.MINUS, 0); }
		public TerminalNode PLUS() { return getToken(SimplexParser.PLUS, 0); }
		public TerminalNode XOR() { return getToken(SimplexParser.XOR, 0); }
		public TerminalNode LOGIC_NOT() { return getToken(SimplexParser.LOGIC_NOT, 0); }
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public UnaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitUnary(this);
		}
	}

	public final UnaryContext unary() throws RecognitionException {
		UnaryContext _localctx = new UnaryContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_unary);
		int _la;
		try {
			setState(256);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MINUS:
			case PLUS:
			case LOGIC_NOT:
			case XOR:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(253);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 74310218485334016L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(254);
				unary();
				}
				}
				break;
			case LITERAL_HEX:
			case LITERAL_BIN:
			case LITERAL_INT:
			case LITERAL_FLOAT:
			case LITERAL_STRING:
			case LITERAL_RUNE:
			case PAREN_OPEN:
			case TRUE:
			case FALSE:
			case NIL:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(255);
				primary();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryContext extends ParserRuleContext {
		public IndexingContext indexing() {
			return getRuleContext(IndexingContext.class,0);
		}
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public RealContext real() {
			return getRuleContext(RealContext.class,0);
		}
		public TerminalNode LITERAL_STRING() { return getToken(SimplexParser.LITERAL_STRING, 0); }
		public TerminalNode LITERAL_RUNE() { return getToken(SimplexParser.LITERAL_RUNE, 0); }
		public TerminalNode TRUE() { return getToken(SimplexParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(SimplexParser.FALSE, 0); }
		public TerminalNode NIL() { return getToken(SimplexParser.NIL, 0); }
		public GroupContext group() {
			return getRuleContext(GroupContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitPrimary(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_primary);
		try {
			setState(269);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(258);
				indexing();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(259);
				match(ID);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(260);
				integer();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(261);
				real();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(262);
				match(LITERAL_STRING);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(263);
				match(LITERAL_RUNE);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(264);
				match(TRUE);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(265);
				match(FALSE);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(266);
				match(NIL);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(267);
				group();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(268);
				functionCall();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GroupContext extends ParserRuleContext {
		public TerminalNode PAREN_OPEN() { return getToken(SimplexParser.PAREN_OPEN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PAREN_CLOSE() { return getToken(SimplexParser.PAREN_CLOSE, 0); }
		public GroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_group; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitGroup(this);
		}
	}

	public final GroupContext group() throws RecognitionException {
		GroupContext _localctx = new GroupContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_group);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			match(PAREN_OPEN);
			setState(272);
			expression();
			setState(273);
			match(PAREN_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public TerminalNode PAREN_OPEN() { return getToken(SimplexParser.PAREN_OPEN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode PAREN_CLOSE() { return getToken(SimplexParser.PAREN_CLOSE, 0); }
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitFunctionCall(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_functionCall);
		try {
			setState(283);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(275);
				match(ID);
				setState(276);
				match(PAREN_OPEN);
				setState(277);
				expressionList();
				setState(278);
				match(PAREN_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(280);
				match(ID);
				setState(281);
				match(PAREN_OPEN);
				setState(282);
				match(PAREN_CLOSE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SimplexParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SimplexParser.COMMA, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitExpressionList(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			expression();
			setState(290);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(286);
				match(COMMA);
				setState(287);
				expression();
				}
				}
				setState(292);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IntegerContext extends ParserRuleContext {
		public TerminalNode LITERAL_HEX() { return getToken(SimplexParser.LITERAL_HEX, 0); }
		public TerminalNode LITERAL_BIN() { return getToken(SimplexParser.LITERAL_BIN, 0); }
		public TerminalNode LITERAL_INT() { return getToken(SimplexParser.LITERAL_INT, 0); }
		public IntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitInteger(this);
		}
	}

	public final IntegerContext integer() throws RecognitionException {
		IntegerContext _localctx = new IntegerContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_integer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1835008L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RealContext extends ParserRuleContext {
		public TerminalNode LITERAL_FLOAT() { return getToken(SimplexParser.LITERAL_FLOAT, 0); }
		public RealContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_real; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterReal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitReal(this);
		}
	}

	public final RealContext real() throws RecognitionException {
		RealContext _localctx = new RealContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_real);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			match(LITERAL_FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexingContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public TerminalNode SQUARE_OPEN() { return getToken(SimplexParser.SQUARE_OPEN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SQUARE_CLOSE() { return getToken(SimplexParser.SQUARE_CLOSE, 0); }
		public IndexingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexing; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterIndexing(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitIndexing(this);
		}
	}

	public final IndexingContext indexing() throws RecognitionException {
		IndexingContext _localctx = new IndexingContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_indexing);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			match(ID);
			setState(298);
			match(SQUARE_OPEN);
			setState(299);
			expression();
			setState(300);
			match(SQUARE_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EchoStatementContext extends ParserRuleContext {
		public TerminalNode ECHO() { return getToken(SimplexParser.ECHO, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode EOS() { return getToken(SimplexParser.EOS, 0); }
		public EchoStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_echoStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterEchoStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitEchoStatement(this);
		}
	}

	public final EchoStatementContext echoStatement() throws RecognitionException {
		EchoStatementContext _localctx = new EchoStatementContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_echoStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			match(ECHO);
			setState(303);
			expression();
			setState(304);
			match(EOS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReadStatementContext extends ParserRuleContext {
		public TerminalNode READ() { return getToken(SimplexParser.READ, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode EOS() { return getToken(SimplexParser.EOS, 0); }
		public ReadStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_readStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterReadStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitReadStatement(this);
		}
	}

	public final ReadStatementContext readStatement() throws RecognitionException {
		ReadStatementContext _localctx = new ReadStatementContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_readStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			match(READ);
			setState(307);
			expression();
			setState(308);
			match(EOS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001>\u0137\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0001\u0000\u0005\u0000L\b\u0000\n\u0000\f\u0000"+
		"O\t\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001W\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005k\b\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006r\b\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007w\b\u0007\n\u0007\f\u0007"+
		"z\t\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u0089\b\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\r\u0005\r\u009c\b\r\n\r\f\r\u009f\t\r\u0001\u000e\u0001"+
		"\u000e\u0005\u000e\u00a3\b\u000e\n\u000e\f\u000e\u00a6\t\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0005\u0012\u00c1\b\u0012\n\u0012\f\u0012\u00c4\t\u0012\u0001\u0012\u0001"+
		"\u0012\u0003\u0012\u00c8\b\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001"+
		"\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u00d1\b\u0015\n"+
		"\u0015\f\u0015\u00d4\t\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0005"+
		"\u0016\u00d9\b\u0016\n\u0016\f\u0016\u00dc\t\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0005\u0017\u00e1\b\u0017\n\u0017\f\u0017\u00e4\t\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u00e9\b\u0018\n\u0018\f\u0018"+
		"\u00ec\t\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0005\u0019\u00f1\b"+
		"\u0019\n\u0019\f\u0019\u00f4\t\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0005\u001a\u00f9\b\u001a\n\u001a\f\u001a\u00fc\t\u001a\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0003\u001b\u0101\b\u001b\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u010e\b\u001c\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u011c"+
		"\b\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0005\u001f\u0121\b\u001f"+
		"\n\u001f\f\u001f\u0124\t\u001f\u0001 \u0001 \u0001!\u0001!\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0001#\u0001#\u0001#\u0001#\u0001$\u0001$\u0001"+
		"$\u0001$\u0001$\u0000\u0000%\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFH\u0000"+
		"\u0007\u0002\u00002244\u0001\u0000+0\u0003\u0000&\'5588\u0002\u0000(*"+
		"77\u0001\u00009:\u0003\u0000&\'3388\u0001\u0000\u0012\u0014\u013a\u0000"+
		"M\u0001\u0000\u0000\u0000\u0002V\u0001\u0000\u0000\u0000\u0004X\u0001"+
		"\u0000\u0000\u0000\u0006[\u0001\u0000\u0000\u0000\b]\u0001\u0000\u0000"+
		"\u0000\nj\u0001\u0000\u0000\u0000\fl\u0001\u0000\u0000\u0000\u000es\u0001"+
		"\u0000\u0000\u0000\u0010{\u0001\u0000\u0000\u0000\u0012\u007f\u0001\u0000"+
		"\u0000\u0000\u0014\u0088\u0001\u0000\u0000\u0000\u0016\u008a\u0001\u0000"+
		"\u0000\u0000\u0018\u0094\u0001\u0000\u0000\u0000\u001a\u0098\u0001\u0000"+
		"\u0000\u0000\u001c\u00a0\u0001\u0000\u0000\u0000\u001e\u00a9\u0001\u0000"+
		"\u0000\u0000 \u00ad\u0001\u0000\u0000\u0000\"\u00b5\u0001\u0000\u0000"+
		"\u0000$\u00bb\u0001\u0000\u0000\u0000&\u00c9\u0001\u0000\u0000\u0000("+
		"\u00cb\u0001\u0000\u0000\u0000*\u00cd\u0001\u0000\u0000\u0000,\u00d5\u0001"+
		"\u0000\u0000\u0000.\u00dd\u0001\u0000\u0000\u00000\u00e5\u0001\u0000\u0000"+
		"\u00002\u00ed\u0001\u0000\u0000\u00004\u00f5\u0001\u0000\u0000\u00006"+
		"\u0100\u0001\u0000\u0000\u00008\u010d\u0001\u0000\u0000\u0000:\u010f\u0001"+
		"\u0000\u0000\u0000<\u011b\u0001\u0000\u0000\u0000>\u011d\u0001\u0000\u0000"+
		"\u0000@\u0125\u0001\u0000\u0000\u0000B\u0127\u0001\u0000\u0000\u0000D"+
		"\u0129\u0001\u0000\u0000\u0000F\u012e\u0001\u0000\u0000\u0000H\u0132\u0001"+
		"\u0000\u0000\u0000JL\u0003\n\u0005\u0000KJ\u0001\u0000\u0000\u0000LO\u0001"+
		"\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000"+
		"N\u0001\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000\u0000PW\u0003\u0004"+
		"\u0002\u0000QW\u0003\u0006\u0003\u0000RW\u0003\b\u0004\u0000SW\u0003&"+
		"\u0013\u0000TW\u0003\f\u0006\u0000UW\u0003\u0010\b\u0000VP\u0001\u0000"+
		"\u0000\u0000VQ\u0001\u0000\u0000\u0000VR\u0001\u0000\u0000\u0000VS\u0001"+
		"\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000VU\u0001\u0000\u0000\u0000"+
		"W\u0003\u0001\u0000\u0000\u0000XY\u0005\f\u0000\u0000YZ\u0003(\u0014\u0000"+
		"Z\u0005\u0001\u0000\u0000\u0000[\\\u0005\u000b\u0000\u0000\\\u0007\u0001"+
		"\u0000\u0000\u0000]^\u0005\t\u0000\u0000^\t\u0001\u0000\u0000\u0000_`"+
		"\u0003\u0002\u0001\u0000`a\u0005\u001b\u0000\u0000ak\u0001\u0000\u0000"+
		"\u0000bk\u0003\u001e\u000f\u0000ck\u0003 \u0010\u0000dk\u0003\"\u0011"+
		"\u0000ek\u0003$\u0012\u0000fk\u0003\u0016\u000b\u0000gk\u0003\u001c\u000e"+
		"\u0000hk\u0003F#\u0000ik\u0003H$\u0000j_\u0001\u0000\u0000\u0000jb\u0001"+
		"\u0000\u0000\u0000jc\u0001\u0000\u0000\u0000jd\u0001\u0000\u0000\u0000"+
		"je\u0001\u0000\u0000\u0000jf\u0001\u0000\u0000\u0000jg\u0001\u0000\u0000"+
		"\u0000jh\u0001\u0000\u0000\u0000ji\u0001\u0000\u0000\u0000k\u000b\u0001"+
		"\u0000\u0000\u0000lm\u0003\u000e\u0007\u0000mn\u0005\u001a\u0000\u0000"+
		"nq\u0003\u0014\n\u0000op\u00056\u0000\u0000pr\u0003>\u001f\u0000qo\u0001"+
		"\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000r\r\u0001\u0000\u0000\u0000"+
		"sx\u0005=\u0000\u0000tu\u0005\u0019\u0000\u0000uw\u0005=\u0000\u0000v"+
		"t\u0001\u0000\u0000\u0000wz\u0001\u0000\u0000\u0000xv\u0001\u0000\u0000"+
		"\u0000xy\u0001\u0000\u0000\u0000y\u000f\u0001\u0000\u0000\u0000zx\u0001"+
		"\u0000\u0000\u0000{|\u0003>\u001f\u0000|}\u00056\u0000\u0000}~\u0003>"+
		"\u001f\u0000~\u0011\u0001\u0000\u0000\u0000\u007f\u0080\u0005\u001c\u0000"+
		"\u0000\u0080\u0081\u0005\u001d\u0000\u0000\u0081\u0013\u0001\u0000\u0000"+
		"\u0000\u0082\u0083\u0003\u0012\t\u0000\u0083\u0084\u0003\u0014\n\u0000"+
		"\u0084\u0089\u0001\u0000\u0000\u0000\u0085\u0086\u0005\"\u0000\u0000\u0086"+
		"\u0089\u0003\u0014\n\u0000\u0087\u0089\u0005=\u0000\u0000\u0088\u0082"+
		"\u0001\u0000\u0000\u0000\u0088\u0085\u0001\u0000\u0000\u0000\u0088\u0087"+
		"\u0001\u0000\u0000\u0000\u0089\u0015\u0001\u0000\u0000\u0000\u008a\u008b"+
		"\u0005\u000e\u0000\u0000\u008b\u008c\u0005=\u0000\u0000\u008c\u008d\u0005"+
		" \u0000\u0000\u008d\u008e\u0003\u001a\r\u0000\u008e\u008f\u0005!\u0000"+
		"\u0000\u008f\u0090\u0005\u000f\u0000\u0000\u0090\u0091\u0003\u0014\n\u0000"+
		"\u0091\u0092\u0001\u0000\u0000\u0000\u0092\u0093\u0003\u001c\u000e\u0000"+
		"\u0093\u0017\u0001\u0000\u0000\u0000\u0094\u0095\u0005=\u0000\u0000\u0095"+
		"\u0096\u0005\u001a\u0000\u0000\u0096\u0097\u0003\u0014\n\u0000\u0097\u0019"+
		"\u0001\u0000\u0000\u0000\u0098\u009d\u0003\u0018\f\u0000\u0099\u009a\u0005"+
		"\u0019\u0000\u0000\u009a\u009c\u0003\u0018\f\u0000\u009b\u0099\u0001\u0000"+
		"\u0000\u0000\u009c\u009f\u0001\u0000\u0000\u0000\u009d\u009b\u0001\u0000"+
		"\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u001b\u0001\u0000"+
		"\u0000\u0000\u009f\u009d\u0001\u0000\u0000\u0000\u00a0\u00a4\u0005\u001e"+
		"\u0000\u0000\u00a1\u00a3\u0003\n\u0005\u0000\u00a2\u00a1\u0001\u0000\u0000"+
		"\u0000\u00a3\u00a6\u0001\u0000\u0000\u0000\u00a4\u00a2\u0001\u0000\u0000"+
		"\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a7\u0001\u0000\u0000"+
		"\u0000\u00a6\u00a4\u0001\u0000\u0000\u0000\u00a7\u00a8\u0005\u001f\u0000"+
		"\u0000\u00a8\u001d\u0001\u0000\u0000\u0000\u00a9\u00aa\u0005\n\u0000\u0000"+
		"\u00aa\u00ab\u0003(\u0014\u0000\u00ab\u00ac\u0003\u001c\u000e\u0000\u00ac"+
		"\u001f\u0001\u0000\u0000\u0000\u00ad\u00ae\u0005\n\u0000\u0000\u00ae\u00af"+
		"\u0003\u0002\u0001\u0000\u00af\u00b0\u0005\u001b\u0000\u0000\u00b0\u00b1"+
		"\u0003(\u0014\u0000\u00b1\u00b2\u0005\u001b\u0000\u0000\u00b2\u00b3\u0003"+
		"\u0002\u0001\u0000\u00b3\u00b4\u0003\u001c\u000e\u0000\u00b4!\u0001\u0000"+
		"\u0000\u0000\u00b5\u00b6\u0005\r\u0000\u0000\u00b6\u00b7\u0003\u001c\u000e"+
		"\u0000\u00b7\u00b8\u0005\n\u0000\u0000\u00b8\u00b9\u0003\u0002\u0001\u0000"+
		"\u00b9\u00ba\u0005\u001b\u0000\u0000\u00ba#\u0001\u0000\u0000\u0000\u00bb"+
		"\u00bc\u0005\u0003\u0000\u0000\u00bc\u00bd\u0003(\u0014\u0000\u00bd\u00c2"+
		"\u0003\u001c\u000e\u0000\u00be\u00bf\u0005\u0004\u0000\u0000\u00bf\u00c1"+
		"\u0003$\u0012\u0000\u00c0\u00be\u0001\u0000\u0000\u0000\u00c1\u00c4\u0001"+
		"\u0000\u0000\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001"+
		"\u0000\u0000\u0000\u00c3\u00c7\u0001\u0000\u0000\u0000\u00c4\u00c2\u0001"+
		"\u0000\u0000\u0000\u00c5\u00c6\u0005\u0004\u0000\u0000\u00c6\u00c8\u0003"+
		"\u001c\u000e\u0000\u00c7\u00c5\u0001\u0000\u0000\u0000\u00c7\u00c8\u0001"+
		"\u0000\u0000\u0000\u00c8%\u0001\u0000\u0000\u0000\u00c9\u00ca\u0003(\u0014"+
		"\u0000\u00ca\'\u0001\u0000\u0000\u0000\u00cb\u00cc\u0003*\u0015\u0000"+
		"\u00cc)\u0001\u0000\u0000\u0000\u00cd\u00d2\u0003,\u0016\u0000\u00ce\u00cf"+
		"\u0007\u0000\u0000\u0000\u00cf\u00d1\u0003,\u0016\u0000\u00d0\u00ce\u0001"+
		"\u0000\u0000\u0000\u00d1\u00d4\u0001\u0000\u0000\u0000\u00d2\u00d0\u0001"+
		"\u0000\u0000\u0000\u00d2\u00d3\u0001\u0000\u0000\u0000\u00d3+\u0001\u0000"+
		"\u0000\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d5\u00da\u0003.\u0017"+
		"\u0000\u00d6\u00d7\u00051\u0000\u0000\u00d7\u00d9\u0003.\u0017\u0000\u00d8"+
		"\u00d6\u0001\u0000\u0000\u0000\u00d9\u00dc\u0001\u0000\u0000\u0000\u00da"+
		"\u00d8\u0001\u0000\u0000\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db"+
		"-\u0001\u0000\u0000\u0000\u00dc\u00da\u0001\u0000\u0000\u0000\u00dd\u00e2"+
		"\u00030\u0018\u0000\u00de\u00df\u0007\u0001\u0000\u0000\u00df\u00e1\u0003"+
		"0\u0018\u0000\u00e0\u00de\u0001\u0000\u0000\u0000\u00e1\u00e4\u0001\u0000"+
		"\u0000\u0000\u00e2\u00e0\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000"+
		"\u0000\u0000\u00e3/\u0001\u0000\u0000\u0000\u00e4\u00e2\u0001\u0000\u0000"+
		"\u0000\u00e5\u00ea\u00032\u0019\u0000\u00e6\u00e7\u0007\u0002\u0000\u0000"+
		"\u00e7\u00e9\u00032\u0019\u0000\u00e8\u00e6\u0001\u0000\u0000\u0000\u00e9"+
		"\u00ec\u0001\u0000\u0000\u0000\u00ea\u00e8\u0001\u0000\u0000\u0000\u00ea"+
		"\u00eb\u0001\u0000\u0000\u0000\u00eb1\u0001\u0000\u0000\u0000\u00ec\u00ea"+
		"\u0001\u0000\u0000\u0000\u00ed\u00f2\u00034\u001a\u0000\u00ee\u00ef\u0007"+
		"\u0003\u0000\u0000\u00ef\u00f1\u00034\u001a\u0000\u00f0\u00ee\u0001\u0000"+
		"\u0000\u0000\u00f1\u00f4\u0001\u0000\u0000\u0000\u00f2\u00f0\u0001\u0000"+
		"\u0000\u0000\u00f2\u00f3\u0001\u0000\u0000\u0000\u00f33\u0001\u0000\u0000"+
		"\u0000\u00f4\u00f2\u0001\u0000\u0000\u0000\u00f5\u00fa\u00036\u001b\u0000"+
		"\u00f6\u00f7\u0007\u0004\u0000\u0000\u00f7\u00f9\u00036\u001b\u0000\u00f8"+
		"\u00f6\u0001\u0000\u0000\u0000\u00f9\u00fc\u0001\u0000\u0000\u0000\u00fa"+
		"\u00f8\u0001\u0000\u0000\u0000\u00fa\u00fb\u0001\u0000\u0000\u0000\u00fb"+
		"5\u0001\u0000\u0000\u0000\u00fc\u00fa\u0001\u0000\u0000\u0000\u00fd\u00fe"+
		"\u0007\u0005\u0000\u0000\u00fe\u0101\u00036\u001b\u0000\u00ff\u0101\u0003"+
		"8\u001c\u0000\u0100\u00fd\u0001\u0000\u0000\u0000\u0100\u00ff\u0001\u0000"+
		"\u0000\u0000\u01017\u0001\u0000\u0000\u0000\u0102\u010e\u0003D\"\u0000"+
		"\u0103\u010e\u0005=\u0000\u0000\u0104\u010e\u0003@ \u0000\u0105\u010e"+
		"\u0003B!\u0000\u0106\u010e\u0005\u0016\u0000\u0000\u0107\u010e\u0005\u0017"+
		"\u0000\u0000\u0108\u010e\u0005#\u0000\u0000\u0109\u010e\u0005$\u0000\u0000"+
		"\u010a\u010e\u0005%\u0000\u0000\u010b\u010e\u0003:\u001d\u0000\u010c\u010e"+
		"\u0003<\u001e\u0000\u010d\u0102\u0001\u0000\u0000\u0000\u010d\u0103\u0001"+
		"\u0000\u0000\u0000\u010d\u0104\u0001\u0000\u0000\u0000\u010d\u0105\u0001"+
		"\u0000\u0000\u0000\u010d\u0106\u0001\u0000\u0000\u0000\u010d\u0107\u0001"+
		"\u0000\u0000\u0000\u010d\u0108\u0001\u0000\u0000\u0000\u010d\u0109\u0001"+
		"\u0000\u0000\u0000\u010d\u010a\u0001\u0000\u0000\u0000\u010d\u010b\u0001"+
		"\u0000\u0000\u0000\u010d\u010c\u0001\u0000\u0000\u0000\u010e9\u0001\u0000"+
		"\u0000\u0000\u010f\u0110\u0005 \u0000\u0000\u0110\u0111\u0003(\u0014\u0000"+
		"\u0111\u0112\u0005!\u0000\u0000\u0112;\u0001\u0000\u0000\u0000\u0113\u0114"+
		"\u0005=\u0000\u0000\u0114\u0115\u0005 \u0000\u0000\u0115\u0116\u0003>"+
		"\u001f\u0000\u0116\u0117\u0005!\u0000\u0000\u0117\u011c\u0001\u0000\u0000"+
		"\u0000\u0118\u0119\u0005=\u0000\u0000\u0119\u011a\u0005 \u0000\u0000\u011a"+
		"\u011c\u0005!\u0000\u0000\u011b\u0113\u0001\u0000\u0000\u0000\u011b\u0118"+
		"\u0001\u0000\u0000\u0000\u011c=\u0001\u0000\u0000\u0000\u011d\u0122\u0003"+
		"(\u0014\u0000\u011e\u011f\u0005\u0019\u0000\u0000\u011f\u0121\u0003(\u0014"+
		"\u0000\u0120\u011e\u0001\u0000\u0000\u0000\u0121\u0124\u0001\u0000\u0000"+
		"\u0000\u0122\u0120\u0001\u0000\u0000\u0000\u0122\u0123\u0001\u0000\u0000"+
		"\u0000\u0123?\u0001\u0000\u0000\u0000\u0124\u0122\u0001\u0000\u0000\u0000"+
		"\u0125\u0126\u0007\u0006\u0000\u0000\u0126A\u0001\u0000\u0000\u0000\u0127"+
		"\u0128\u0005\u0015\u0000\u0000\u0128C\u0001\u0000\u0000\u0000\u0129\u012a"+
		"\u0005=\u0000\u0000\u012a\u012b\u0005\u001c\u0000\u0000\u012b\u012c\u0003"+
		"(\u0014\u0000\u012c\u012d\u0005\u001d\u0000\u0000\u012dE\u0001\u0000\u0000"+
		"\u0000\u012e\u012f\u0005\u0002\u0000\u0000\u012f\u0130\u0003(\u0014\u0000"+
		"\u0130\u0131\u0005\u001b\u0000\u0000\u0131G\u0001\u0000\u0000\u0000\u0132"+
		"\u0133\u0005>\u0000\u0000\u0133\u0134\u0003(\u0014\u0000\u0134\u0135\u0005"+
		"\u001b\u0000\u0000\u0135I\u0001\u0000\u0000\u0000\u0014MVjqx\u0088\u009d"+
		"\u00a4\u00c2\u00c7\u00d2\u00da\u00e2\u00ea\u00f2\u00fa\u0100\u010d\u011b"+
		"\u0122";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}