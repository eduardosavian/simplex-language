// Generated from /home/eduardo/Documents/Projects/simplex-language/src/SimplexParser.g4 by ANTLR 4.13.1
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
		WS=1, INT=2, LONG=3, FLOAT=4, DOUBLE=5, CHAR=6, IF=7, ELSE=8, SWITCH=9, 
		CASE=10, WHILE=11, FOR=12, BREAK=13, RETURN=14, DO=15, PROC=16, TYPE=17, 
		STRUCT=18, STRING=19, LITERAL_HEX=20, LITERAL_BIN=21, LITERAL_INT=22, 
		LITERAL_FLOAT=23, LITERAL_STRING=24, LITERAL_CHAR=25, DOT=26, COMMA=27, 
		COLON=28, SEMICOLON=29, BRACKET_BEGIN=30, BRACKET_END=31, BRACES_BEGIN=32, 
		BRACES_END=33, PARENTHESES_BEGIN=34, PARENTHESES_END=35, MINUS=36, PLUS=37, 
		DIV=38, MUL=39, MOD=40, LT=41, GT=42, LT_EQ=43, GT_EQ=44, EQ=45, DIFF=46, 
		ASSIGN=47, AND=48, OR=49, NOT=50, BIT_SHL=51, BIT_SHR=52, BIT_AND=53, 
		BIT_OR=54, BIT_XOR=55, BIT_NOT=56, SL_COMMENT=57, ML_COMMENT=58, ID=59, 
		READ=60, WRITE=61;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_variable_declaration = 2, RULE_function_declaration = 3, 
		RULE_parameter_list = 4, RULE_parameter = 5, RULE_block = 6, RULE_if_statement = 7, 
		RULE_if_else_statement = 8, RULE_loop_statement = 9, RULE_while_loop = 10, 
		RULE_for_loop = 11, RULE_for_initializer = 12, RULE_for_update = 13, RULE_do_while_loop = 14, 
		RULE_assignment_statement = 15, RULE_input_statement = 16, RULE_output_statement = 17, 
		RULE_expression = 18, RULE_literal = 19, RULE_type = 20, RULE_arithmetic_operator = 21, 
		RULE_comparison_operator = 22, RULE_logical_operator = 23;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "variable_declaration", "function_declaration", 
			"parameter_list", "parameter", "block", "if_statement", "if_else_statement", 
			"loop_statement", "while_loop", "for_loop", "for_initializer", "for_update", 
			"do_while_loop", "assignment_statement", "input_statement", "output_statement", 
			"expression", "literal", "type", "arithmetic_operator", "comparison_operator", 
			"logical_operator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'int'", "'long'", "'float'", "'double'", "'char'", "'if'", 
			"'else'", "'switch'", "'case'", "'while'", "'for'", "'break'", "'return'", 
			"'do'", "'proc'", "'type'", "'struct'", "'string'", null, null, null, 
			null, "'\".*?\"'", null, "'.'", "','", "':'", "';'", "'['", "']'", "'{'", 
			"'}'", "'('", "')'", "'-'", "'+'", "'/'", "'*'", "'%'", "'<'", "'>'", 
			"'>='", "'<='", "'=='", "'!='", "'='", "'&&'", "'||'", "'!'", "'<<'", 
			"'>>'", "'&'", "'|'", "'^'", "'~'", null, null, null, "'read'", "'write'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "INT", "LONG", "FLOAT", "DOUBLE", "CHAR", "IF", "ELSE", "SWITCH", 
			"CASE", "WHILE", "FOR", "BREAK", "RETURN", "DO", "PROC", "TYPE", "STRUCT", 
			"STRING", "LITERAL_HEX", "LITERAL_BIN", "LITERAL_INT", "LITERAL_FLOAT", 
			"LITERAL_STRING", "LITERAL_CHAR", "DOT", "COMMA", "COLON", "SEMICOLON", 
			"BRACKET_BEGIN", "BRACKET_END", "BRACES_BEGIN", "BRACES_END", "PARENTHESES_BEGIN", 
			"PARENTHESES_END", "MINUS", "PLUS", "DIV", "MUL", "MOD", "LT", "GT", 
			"LT_EQ", "GT_EQ", "EQ", "DIFF", "ASSIGN", "AND", "OR", "NOT", "BIT_SHL", 
			"BIT_SHR", "BIT_AND", "BIT_OR", "BIT_XOR", "BIT_NOT", "SL_COMMENT", "ML_COMMENT", 
			"ID", "READ", "WRITE"
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
			setState(49); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(48);
				statement();
				}
				}
				setState(51); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 4035225270419052672L) != 0) );
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
		public Variable_declarationContext variable_declaration() {
			return getRuleContext(Variable_declarationContext.class,0);
		}
		public Function_declarationContext function_declaration() {
			return getRuleContext(Function_declarationContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public If_statementContext if_statement() {
			return getRuleContext(If_statementContext.class,0);
		}
		public If_else_statementContext if_else_statement() {
			return getRuleContext(If_else_statementContext.class,0);
		}
		public Loop_statementContext loop_statement() {
			return getRuleContext(Loop_statementContext.class,0);
		}
		public Assignment_statementContext assignment_statement() {
			return getRuleContext(Assignment_statementContext.class,0);
		}
		public Input_statementContext input_statement() {
			return getRuleContext(Input_statementContext.class,0);
		}
		public Output_statementContext output_statement() {
			return getRuleContext(Output_statementContext.class,0);
		}
		public TerminalNode RETURN() { return getToken(SimplexParser.RETURN, 0); }
		public TerminalNode SEMICOLON() { return getToken(SimplexParser.SEMICOLON, 0); }
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
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(64);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				variable_declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(54);
				function_declaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(55);
				block();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(56);
				if_statement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(57);
				if_else_statement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(58);
				loop_statement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(59);
				assignment_statement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(60);
				input_statement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(61);
				output_statement();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(62);
				match(RETURN);
				setState(63);
				match(SEMICOLON);
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
	public static class Variable_declarationContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public TerminalNode COLON() { return getToken(SimplexParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(SimplexParser.SEMICOLON, 0); }
		public TerminalNode ASSIGN() { return getToken(SimplexParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Variable_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterVariable_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitVariable_declaration(this);
		}
	}

	public final Variable_declarationContext variable_declaration() throws RecognitionException {
		Variable_declarationContext _localctx = new Variable_declarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_variable_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(ID);
			setState(67);
			match(COLON);
			setState(68);
			type();
			{
			setState(69);
			match(ASSIGN);
			setState(70);
			expression(0);
			}
			setState(72);
			match(SEMICOLON);
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
	public static class Function_declarationContext extends ParserRuleContext {
		public TerminalNode PROC() { return getToken(SimplexParser.PROC, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public TerminalNode PARENTHESES_BEGIN() { return getToken(SimplexParser.PARENTHESES_BEGIN, 0); }
		public TerminalNode PARENTHESES_END() { return getToken(SimplexParser.PARENTHESES_END, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Parameter_listContext parameter_list() {
			return getRuleContext(Parameter_listContext.class,0);
		}
		public Function_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterFunction_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitFunction_declaration(this);
		}
	}

	public final Function_declarationContext function_declaration() throws RecognitionException {
		Function_declarationContext _localctx = new Function_declarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_function_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(PROC);
			setState(75);
			type();
			setState(76);
			match(ID);
			setState(77);
			match(PARENTHESES_BEGIN);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 124L) != 0)) {
				{
				setState(78);
				parameter_list();
				}
			}

			setState(81);
			match(PARENTHESES_END);
			setState(82);
			block();
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
	public static class Parameter_listContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SimplexParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SimplexParser.COMMA, i);
		}
		public Parameter_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterParameter_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitParameter_list(this);
		}
	}

	public final Parameter_listContext parameter_list() throws RecognitionException {
		Parameter_listContext _localctx = new Parameter_listContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_parameter_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			parameter();
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(85);
				match(COMMA);
				setState(86);
				parameter();
				}
				}
				setState(91);
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
	public static class ParameterContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitParameter(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			type();
			setState(93);
			match(ID);
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
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode BRACES_BEGIN() { return getToken(SimplexParser.BRACES_BEGIN, 0); }
		public TerminalNode BRACES_END() { return getToken(SimplexParser.BRACES_END, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(BRACES_BEGIN);
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4035225270419052672L) != 0)) {
				{
				{
				setState(96);
				statement();
				}
				}
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(102);
			match(BRACES_END);
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
	public static class If_statementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(SimplexParser.IF, 0); }
		public TerminalNode PARENTHESES_BEGIN() { return getToken(SimplexParser.PARENTHESES_BEGIN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PARENTHESES_END() { return getToken(SimplexParser.PARENTHESES_END, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public If_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterIf_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitIf_statement(this);
		}
	}

	public final If_statementContext if_statement() throws RecognitionException {
		If_statementContext _localctx = new If_statementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_if_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(IF);
			setState(105);
			match(PARENTHESES_BEGIN);
			setState(106);
			expression(0);
			setState(107);
			match(PARENTHESES_END);
			setState(108);
			block();
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
	public static class If_else_statementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(SimplexParser.IF, 0); }
		public TerminalNode PARENTHESES_BEGIN() { return getToken(SimplexParser.PARENTHESES_BEGIN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PARENTHESES_END() { return getToken(SimplexParser.PARENTHESES_END, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(SimplexParser.ELSE, 0); }
		public If_else_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_else_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterIf_else_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitIf_else_statement(this);
		}
	}

	public final If_else_statementContext if_else_statement() throws RecognitionException {
		If_else_statementContext _localctx = new If_else_statementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_if_else_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(IF);
			setState(111);
			match(PARENTHESES_BEGIN);
			setState(112);
			expression(0);
			setState(113);
			match(PARENTHESES_END);
			setState(114);
			block();
			setState(115);
			match(ELSE);
			setState(116);
			block();
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
	public static class Loop_statementContext extends ParserRuleContext {
		public While_loopContext while_loop() {
			return getRuleContext(While_loopContext.class,0);
		}
		public For_loopContext for_loop() {
			return getRuleContext(For_loopContext.class,0);
		}
		public Do_while_loopContext do_while_loop() {
			return getRuleContext(Do_while_loopContext.class,0);
		}
		public Loop_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterLoop_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitLoop_statement(this);
		}
	}

	public final Loop_statementContext loop_statement() throws RecognitionException {
		Loop_statementContext _localctx = new Loop_statementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_loop_statement);
		try {
			setState(121);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WHILE:
				enterOuterAlt(_localctx, 1);
				{
				setState(118);
				while_loop();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(119);
				for_loop();
				}
				break;
			case DO:
				enterOuterAlt(_localctx, 3);
				{
				setState(120);
				do_while_loop();
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
	public static class While_loopContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(SimplexParser.WHILE, 0); }
		public TerminalNode PARENTHESES_BEGIN() { return getToken(SimplexParser.PARENTHESES_BEGIN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PARENTHESES_END() { return getToken(SimplexParser.PARENTHESES_END, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public While_loopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterWhile_loop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitWhile_loop(this);
		}
	}

	public final While_loopContext while_loop() throws RecognitionException {
		While_loopContext _localctx = new While_loopContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_while_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(WHILE);
			setState(124);
			match(PARENTHESES_BEGIN);
			setState(125);
			expression(0);
			setState(126);
			match(PARENTHESES_END);
			setState(127);
			block();
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
	public static class For_loopContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(SimplexParser.FOR, 0); }
		public TerminalNode PARENTHESES_BEGIN() { return getToken(SimplexParser.PARENTHESES_BEGIN, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(SimplexParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(SimplexParser.SEMICOLON, i);
		}
		public TerminalNode PARENTHESES_END() { return getToken(SimplexParser.PARENTHESES_END, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public For_initializerContext for_initializer() {
			return getRuleContext(For_initializerContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public For_updateContext for_update() {
			return getRuleContext(For_updateContext.class,0);
		}
		public For_loopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterFor_loop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitFor_loop(this);
		}
	}

	public final For_loopContext for_loop() throws RecognitionException {
		For_loopContext _localctx = new For_loopContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_for_loop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			match(FOR);
			setState(130);
			match(PARENTHESES_BEGIN);
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(131);
				for_initializer();
				}
			}

			setState(134);
			match(SEMICOLON);
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 576460769549352960L) != 0)) {
				{
				setState(135);
				expression(0);
				}
			}

			setState(138);
			match(SEMICOLON);
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(139);
				for_update();
				}
			}

			setState(142);
			match(PARENTHESES_END);
			setState(143);
			block();
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
	public static class For_initializerContext extends ParserRuleContext {
		public Variable_declarationContext variable_declaration() {
			return getRuleContext(Variable_declarationContext.class,0);
		}
		public Assignment_statementContext assignment_statement() {
			return getRuleContext(Assignment_statementContext.class,0);
		}
		public For_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterFor_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitFor_initializer(this);
		}
	}

	public final For_initializerContext for_initializer() throws RecognitionException {
		For_initializerContext _localctx = new For_initializerContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_for_initializer);
		try {
			setState(147);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(145);
				variable_declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(146);
				assignment_statement();
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
	public static class For_updateContext extends ParserRuleContext {
		public Assignment_statementContext assignment_statement() {
			return getRuleContext(Assignment_statementContext.class,0);
		}
		public For_updateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_update; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterFor_update(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitFor_update(this);
		}
	}

	public final For_updateContext for_update() throws RecognitionException {
		For_updateContext _localctx = new For_updateContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_for_update);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			assignment_statement();
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
	public static class Do_while_loopContext extends ParserRuleContext {
		public TerminalNode DO() { return getToken(SimplexParser.DO, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(SimplexParser.WHILE, 0); }
		public TerminalNode PARENTHESES_BEGIN() { return getToken(SimplexParser.PARENTHESES_BEGIN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PARENTHESES_END() { return getToken(SimplexParser.PARENTHESES_END, 0); }
		public TerminalNode SEMICOLON() { return getToken(SimplexParser.SEMICOLON, 0); }
		public Do_while_loopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_do_while_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterDo_while_loop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitDo_while_loop(this);
		}
	}

	public final Do_while_loopContext do_while_loop() throws RecognitionException {
		Do_while_loopContext _localctx = new Do_while_loopContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_do_while_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(DO);
			setState(152);
			block();
			setState(153);
			match(WHILE);
			setState(154);
			match(PARENTHESES_BEGIN);
			setState(155);
			expression(0);
			setState(156);
			match(PARENTHESES_END);
			setState(157);
			match(SEMICOLON);
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
	public static class Assignment_statementContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(SimplexParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(SimplexParser.SEMICOLON, 0); }
		public Assignment_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterAssignment_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitAssignment_statement(this);
		}
	}

	public final Assignment_statementContext assignment_statement() throws RecognitionException {
		Assignment_statementContext _localctx = new Assignment_statementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_assignment_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(ID);
			setState(160);
			match(ASSIGN);
			setState(161);
			expression(0);
			setState(162);
			match(SEMICOLON);
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
	public static class Input_statementContext extends ParserRuleContext {
		public TerminalNode READ() { return getToken(SimplexParser.READ, 0); }
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public TerminalNode SEMICOLON() { return getToken(SimplexParser.SEMICOLON, 0); }
		public Input_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_input_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterInput_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitInput_statement(this);
		}
	}

	public final Input_statementContext input_statement() throws RecognitionException {
		Input_statementContext _localctx = new Input_statementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_input_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(READ);
			setState(165);
			match(ID);
			setState(166);
			match(SEMICOLON);
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
	public static class Output_statementContext extends ParserRuleContext {
		public TerminalNode WRITE() { return getToken(SimplexParser.WRITE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(SimplexParser.SEMICOLON, 0); }
		public Output_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_output_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterOutput_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitOutput_statement(this);
		}
	}

	public final Output_statementContext output_statement() throws RecognitionException {
		Output_statementContext _localctx = new Output_statementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_output_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(WRITE);
			setState(169);
			expression(0);
			setState(170);
			match(SEMICOLON);
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
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode PARENTHESES_BEGIN() { return getToken(SimplexParser.PARENTHESES_BEGIN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode PARENTHESES_END() { return getToken(SimplexParser.PARENTHESES_END, 0); }
		public Arithmetic_operatorContext arithmetic_operator() {
			return getRuleContext(Arithmetic_operatorContext.class,0);
		}
		public Comparison_operatorContext comparison_operator() {
			return getRuleContext(Comparison_operatorContext.class,0);
		}
		public Logical_operatorContext logical_operator() {
			return getRuleContext(Logical_operatorContext.class,0);
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
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(173);
				match(ID);
				}
				break;
			case LITERAL_HEX:
			case LITERAL_BIN:
			case LITERAL_INT:
			case LITERAL_FLOAT:
			case LITERAL_STRING:
			case LITERAL_CHAR:
				{
				setState(174);
				literal();
				}
				break;
			case PARENTHESES_BEGIN:
				{
				setState(175);
				match(PARENTHESES_BEGIN);
				setState(176);
				expression(0);
				setState(177);
				match(PARENTHESES_END);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(195);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(193);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(181);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(182);
						arithmetic_operator();
						setState(183);
						expression(5);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(185);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(186);
						comparison_operator();
						setState(187);
						expression(4);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(189);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(190);
						logical_operator();
						setState(191);
						expression(3);
						}
						break;
					}
					} 
				}
				setState(197);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode LITERAL_HEX() { return getToken(SimplexParser.LITERAL_HEX, 0); }
		public TerminalNode LITERAL_BIN() { return getToken(SimplexParser.LITERAL_BIN, 0); }
		public TerminalNode LITERAL_INT() { return getToken(SimplexParser.LITERAL_INT, 0); }
		public TerminalNode LITERAL_FLOAT() { return getToken(SimplexParser.LITERAL_FLOAT, 0); }
		public TerminalNode LITERAL_STRING() { return getToken(SimplexParser.LITERAL_STRING, 0); }
		public TerminalNode LITERAL_CHAR() { return getToken(SimplexParser.LITERAL_CHAR, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 66060288L) != 0)) ) {
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
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(SimplexParser.INT, 0); }
		public TerminalNode LONG() { return getToken(SimplexParser.LONG, 0); }
		public TerminalNode FLOAT() { return getToken(SimplexParser.FLOAT, 0); }
		public TerminalNode DOUBLE() { return getToken(SimplexParser.DOUBLE, 0); }
		public TerminalNode CHAR() { return getToken(SimplexParser.CHAR, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 124L) != 0)) ) {
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
	public static class Arithmetic_operatorContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(SimplexParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(SimplexParser.MINUS, 0); }
		public TerminalNode MUL() { return getToken(SimplexParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(SimplexParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(SimplexParser.MOD, 0); }
		public Arithmetic_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterArithmetic_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitArithmetic_operator(this);
		}
	}

	public final Arithmetic_operatorContext arithmetic_operator() throws RecognitionException {
		Arithmetic_operatorContext _localctx = new Arithmetic_operatorContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_arithmetic_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2130303778816L) != 0)) ) {
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
	public static class Comparison_operatorContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(SimplexParser.LT, 0); }
		public TerminalNode GT() { return getToken(SimplexParser.GT, 0); }
		public TerminalNode LT_EQ() { return getToken(SimplexParser.LT_EQ, 0); }
		public TerminalNode GT_EQ() { return getToken(SimplexParser.GT_EQ, 0); }
		public TerminalNode EQ() { return getToken(SimplexParser.EQ, 0); }
		public TerminalNode DIFF() { return getToken(SimplexParser.DIFF, 0); }
		public Comparison_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterComparison_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitComparison_operator(this);
		}
	}

	public final Comparison_operatorContext comparison_operator() throws RecognitionException {
		Comparison_operatorContext _localctx = new Comparison_operatorContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_comparison_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 138538465099776L) != 0)) ) {
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
	public static class Logical_operatorContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(SimplexParser.AND, 0); }
		public TerminalNode OR() { return getToken(SimplexParser.OR, 0); }
		public TerminalNode NOT() { return getToken(SimplexParser.NOT, 0); }
		public Logical_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logical_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterLogical_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitLogical_operator(this);
		}
	}

	public final Logical_operatorContext logical_operator() throws RecognitionException {
		Logical_operatorContext _localctx = new Logical_operatorContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_logical_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1970324836974592L) != 0)) ) {
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 18:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001=\u00d1\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0001\u0000\u0004\u0000"+
		"2\b\u0000\u000b\u0000\f\u00003\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001A\b\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003P\b"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0005\u0004X\b\u0004\n\u0004\f\u0004[\t\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0005\u0006b\b\u0006\n\u0006"+
		"\f\u0006e\t\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0003\tz\b"+
		"\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u0085\b\u000b\u0001\u000b\u0001\u000b\u0003\u000b"+
		"\u0089\b\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u008d\b\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0003\f\u0094\b\f\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012"+
		"\u00b4\b\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0005\u0012\u00c2\b\u0012\n\u0012\f\u0012\u00c5\t\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001"+
		"\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0000\u0001$\u0018"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(*,.\u0000\u0005\u0001\u0000\u0014\u0019\u0001\u0000"+
		"\u0002\u0006\u0001\u0000$(\u0001\u0000).\u0001\u000002\u00d0\u00001\u0001"+
		"\u0000\u0000\u0000\u0002@\u0001\u0000\u0000\u0000\u0004B\u0001\u0000\u0000"+
		"\u0000\u0006J\u0001\u0000\u0000\u0000\bT\u0001\u0000\u0000\u0000\n\\\u0001"+
		"\u0000\u0000\u0000\f_\u0001\u0000\u0000\u0000\u000eh\u0001\u0000\u0000"+
		"\u0000\u0010n\u0001\u0000\u0000\u0000\u0012y\u0001\u0000\u0000\u0000\u0014"+
		"{\u0001\u0000\u0000\u0000\u0016\u0081\u0001\u0000\u0000\u0000\u0018\u0093"+
		"\u0001\u0000\u0000\u0000\u001a\u0095\u0001\u0000\u0000\u0000\u001c\u0097"+
		"\u0001\u0000\u0000\u0000\u001e\u009f\u0001\u0000\u0000\u0000 \u00a4\u0001"+
		"\u0000\u0000\u0000\"\u00a8\u0001\u0000\u0000\u0000$\u00b3\u0001\u0000"+
		"\u0000\u0000&\u00c6\u0001\u0000\u0000\u0000(\u00c8\u0001\u0000\u0000\u0000"+
		"*\u00ca\u0001\u0000\u0000\u0000,\u00cc\u0001\u0000\u0000\u0000.\u00ce"+
		"\u0001\u0000\u0000\u000002\u0003\u0002\u0001\u000010\u0001\u0000\u0000"+
		"\u000023\u0001\u0000\u0000\u000031\u0001\u0000\u0000\u000034\u0001\u0000"+
		"\u0000\u00004\u0001\u0001\u0000\u0000\u00005A\u0003\u0004\u0002\u0000"+
		"6A\u0003\u0006\u0003\u00007A\u0003\f\u0006\u00008A\u0003\u000e\u0007\u0000"+
		"9A\u0003\u0010\b\u0000:A\u0003\u0012\t\u0000;A\u0003\u001e\u000f\u0000"+
		"<A\u0003 \u0010\u0000=A\u0003\"\u0011\u0000>?\u0005\u000e\u0000\u0000"+
		"?A\u0005\u001d\u0000\u0000@5\u0001\u0000\u0000\u0000@6\u0001\u0000\u0000"+
		"\u0000@7\u0001\u0000\u0000\u0000@8\u0001\u0000\u0000\u0000@9\u0001\u0000"+
		"\u0000\u0000@:\u0001\u0000\u0000\u0000@;\u0001\u0000\u0000\u0000@<\u0001"+
		"\u0000\u0000\u0000@=\u0001\u0000\u0000\u0000@>\u0001\u0000\u0000\u0000"+
		"A\u0003\u0001\u0000\u0000\u0000BC\u0005;\u0000\u0000CD\u0005\u001c\u0000"+
		"\u0000DE\u0003(\u0014\u0000EF\u0005/\u0000\u0000FG\u0003$\u0012\u0000"+
		"GH\u0001\u0000\u0000\u0000HI\u0005\u001d\u0000\u0000I\u0005\u0001\u0000"+
		"\u0000\u0000JK\u0005\u0010\u0000\u0000KL\u0003(\u0014\u0000LM\u0005;\u0000"+
		"\u0000MO\u0005\"\u0000\u0000NP\u0003\b\u0004\u0000ON\u0001\u0000\u0000"+
		"\u0000OP\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000QR\u0005#\u0000"+
		"\u0000RS\u0003\f\u0006\u0000S\u0007\u0001\u0000\u0000\u0000TY\u0003\n"+
		"\u0005\u0000UV\u0005\u001b\u0000\u0000VX\u0003\n\u0005\u0000WU\u0001\u0000"+
		"\u0000\u0000X[\u0001\u0000\u0000\u0000YW\u0001\u0000\u0000\u0000YZ\u0001"+
		"\u0000\u0000\u0000Z\t\u0001\u0000\u0000\u0000[Y\u0001\u0000\u0000\u0000"+
		"\\]\u0003(\u0014\u0000]^\u0005;\u0000\u0000^\u000b\u0001\u0000\u0000\u0000"+
		"_c\u0005 \u0000\u0000`b\u0003\u0002\u0001\u0000a`\u0001\u0000\u0000\u0000"+
		"be\u0001\u0000\u0000\u0000ca\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000"+
		"\u0000df\u0001\u0000\u0000\u0000ec\u0001\u0000\u0000\u0000fg\u0005!\u0000"+
		"\u0000g\r\u0001\u0000\u0000\u0000hi\u0005\u0007\u0000\u0000ij\u0005\""+
		"\u0000\u0000jk\u0003$\u0012\u0000kl\u0005#\u0000\u0000lm\u0003\f\u0006"+
		"\u0000m\u000f\u0001\u0000\u0000\u0000no\u0005\u0007\u0000\u0000op\u0005"+
		"\"\u0000\u0000pq\u0003$\u0012\u0000qr\u0005#\u0000\u0000rs\u0003\f\u0006"+
		"\u0000st\u0005\b\u0000\u0000tu\u0003\f\u0006\u0000u\u0011\u0001\u0000"+
		"\u0000\u0000vz\u0003\u0014\n\u0000wz\u0003\u0016\u000b\u0000xz\u0003\u001c"+
		"\u000e\u0000yv\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000yx\u0001"+
		"\u0000\u0000\u0000z\u0013\u0001\u0000\u0000\u0000{|\u0005\u000b\u0000"+
		"\u0000|}\u0005\"\u0000\u0000}~\u0003$\u0012\u0000~\u007f\u0005#\u0000"+
		"\u0000\u007f\u0080\u0003\f\u0006\u0000\u0080\u0015\u0001\u0000\u0000\u0000"+
		"\u0081\u0082\u0005\f\u0000\u0000\u0082\u0084\u0005\"\u0000\u0000\u0083"+
		"\u0085\u0003\u0018\f\u0000\u0084\u0083\u0001\u0000\u0000\u0000\u0084\u0085"+
		"\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000\u0086\u0088"+
		"\u0005\u001d\u0000\u0000\u0087\u0089\u0003$\u0012\u0000\u0088\u0087\u0001"+
		"\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000\u0089\u008a\u0001"+
		"\u0000\u0000\u0000\u008a\u008c\u0005\u001d\u0000\u0000\u008b\u008d\u0003"+
		"\u001a\r\u0000\u008c\u008b\u0001\u0000\u0000\u0000\u008c\u008d\u0001\u0000"+
		"\u0000\u0000\u008d\u008e\u0001\u0000\u0000\u0000\u008e\u008f\u0005#\u0000"+
		"\u0000\u008f\u0090\u0003\f\u0006\u0000\u0090\u0017\u0001\u0000\u0000\u0000"+
		"\u0091\u0094\u0003\u0004\u0002\u0000\u0092\u0094\u0003\u001e\u000f\u0000"+
		"\u0093\u0091\u0001\u0000\u0000\u0000\u0093\u0092\u0001\u0000\u0000\u0000"+
		"\u0094\u0019\u0001\u0000\u0000\u0000\u0095\u0096\u0003\u001e\u000f\u0000"+
		"\u0096\u001b\u0001\u0000\u0000\u0000\u0097\u0098\u0005\u000f\u0000\u0000"+
		"\u0098\u0099\u0003\f\u0006\u0000\u0099\u009a\u0005\u000b\u0000\u0000\u009a"+
		"\u009b\u0005\"\u0000\u0000\u009b\u009c\u0003$\u0012\u0000\u009c\u009d"+
		"\u0005#\u0000\u0000\u009d\u009e\u0005\u001d\u0000\u0000\u009e\u001d\u0001"+
		"\u0000\u0000\u0000\u009f\u00a0\u0005;\u0000\u0000\u00a0\u00a1\u0005/\u0000"+
		"\u0000\u00a1\u00a2\u0003$\u0012\u0000\u00a2\u00a3\u0005\u001d\u0000\u0000"+
		"\u00a3\u001f\u0001\u0000\u0000\u0000\u00a4\u00a5\u0005<\u0000\u0000\u00a5"+
		"\u00a6\u0005;\u0000\u0000\u00a6\u00a7\u0005\u001d\u0000\u0000\u00a7!\u0001"+
		"\u0000\u0000\u0000\u00a8\u00a9\u0005=\u0000\u0000\u00a9\u00aa\u0003$\u0012"+
		"\u0000\u00aa\u00ab\u0005\u001d\u0000\u0000\u00ab#\u0001\u0000\u0000\u0000"+
		"\u00ac\u00ad\u0006\u0012\uffff\uffff\u0000\u00ad\u00b4\u0005;\u0000\u0000"+
		"\u00ae\u00b4\u0003&\u0013\u0000\u00af\u00b0\u0005\"\u0000\u0000\u00b0"+
		"\u00b1\u0003$\u0012\u0000\u00b1\u00b2\u0005#\u0000\u0000\u00b2\u00b4\u0001"+
		"\u0000\u0000\u0000\u00b3\u00ac\u0001\u0000\u0000\u0000\u00b3\u00ae\u0001"+
		"\u0000\u0000\u0000\u00b3\u00af\u0001\u0000\u0000\u0000\u00b4\u00c3\u0001"+
		"\u0000\u0000\u0000\u00b5\u00b6\n\u0004\u0000\u0000\u00b6\u00b7\u0003*"+
		"\u0015\u0000\u00b7\u00b8\u0003$\u0012\u0005\u00b8\u00c2\u0001\u0000\u0000"+
		"\u0000\u00b9\u00ba\n\u0003\u0000\u0000\u00ba\u00bb\u0003,\u0016\u0000"+
		"\u00bb\u00bc\u0003$\u0012\u0004\u00bc\u00c2\u0001\u0000\u0000\u0000\u00bd"+
		"\u00be\n\u0002\u0000\u0000\u00be\u00bf\u0003.\u0017\u0000\u00bf\u00c0"+
		"\u0003$\u0012\u0003\u00c0\u00c2\u0001\u0000\u0000\u0000\u00c1\u00b5\u0001"+
		"\u0000\u0000\u0000\u00c1\u00b9\u0001\u0000\u0000\u0000\u00c1\u00bd\u0001"+
		"\u0000\u0000\u0000\u00c2\u00c5\u0001\u0000\u0000\u0000\u00c3\u00c1\u0001"+
		"\u0000\u0000\u0000\u00c3\u00c4\u0001\u0000\u0000\u0000\u00c4%\u0001\u0000"+
		"\u0000\u0000\u00c5\u00c3\u0001\u0000\u0000\u0000\u00c6\u00c7\u0007\u0000"+
		"\u0000\u0000\u00c7\'\u0001\u0000\u0000\u0000\u00c8\u00c9\u0007\u0001\u0000"+
		"\u0000\u00c9)\u0001\u0000\u0000\u0000\u00ca\u00cb\u0007\u0002\u0000\u0000"+
		"\u00cb+\u0001\u0000\u0000\u0000\u00cc\u00cd\u0007\u0003\u0000\u0000\u00cd"+
		"-\u0001\u0000\u0000\u0000\u00ce\u00cf\u0007\u0004\u0000\u0000\u00cf/\u0001"+
		"\u0000\u0000\u0000\r3@OYcy\u0084\u0088\u008c\u0093\u00b3\u00c1\u00c3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}