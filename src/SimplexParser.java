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
		WS=1, IF=2, ELSE=3, ELIF=4, SWITCH=5, CASE=6, DEFAULT=7, WHILE=8, FOR=9, 
		BREAK=10, RETURN=11, DO=12, FUNCTION=13, ARROW=14, TYPE=15, STRUCT=16, 
		LITERAL_HEX=17, LITERAL_BIN=18, LITERAL_INT=19, LITERAL_FLOAT=20, LITERAL_STRING=21, 
		LITERAL_CHAR=22, DOT=23, COMMA=24, COLON=25, SEMICOLON=26, BRACKET_BEGIN=27, 
		BRACKET_END=28, BRACES_BEGIN=29, BRACES_END=30, PARENTHESES_BEGIN=31, 
		PARENTHESES_END=32, CARET=33, MINUS=34, PLUS=35, DIV=36, MUL=37, MOD=38, 
		LT=39, GT=40, LT_EQ=41, GT_EQ=42, EQ=43, DIFF=44, ASSIGN=45, AND=46, OR=47, 
		NOT=48, BIT_SHL=49, BIT_SHR=50, BIT_AND=51, BIT_OR=52, BIT_NOT=53, SL_COMMENT=54, 
		ML_COMMENT=55, ID=56;
	public static final int
		RULE_program = 0, RULE_top_level = 1, RULE_statement = 2, RULE_inline_statement = 3, 
		RULE_type_definition = 4, RULE_type_expression = 5, RULE_type_prefix = 6, 
		RULE_slice_prefix = 7, RULE_variable_declaration = 8, RULE_variable_definition = 9, 
		RULE_assignment_statement = 10, RULE_struct_definition = 11, RULE_record_list = 12, 
		RULE_function_declaration = 13, RULE_function_definition = 14, RULE_parameter = 15, 
		RULE_scope = 16, RULE_if_statement = 17, RULE_if = 18, RULE_else = 19, 
		RULE_elif = 20, RULE_expression = 21, RULE_expression_list = 22, RULE_function_call = 23, 
		RULE_grouped_expression = 24, RULE_literal = 25, RULE_arithmetic_operator = 26, 
		RULE_comparison_operator = 27, RULE_logical_operator = 28;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "top_level", "statement", "inline_statement", "type_definition", 
			"type_expression", "type_prefix", "slice_prefix", "variable_declaration", 
			"variable_definition", "assignment_statement", "struct_definition", "record_list", 
			"function_declaration", "function_definition", "parameter", "scope", 
			"if_statement", "if", "else", "elif", "expression", "expression_list", 
			"function_call", "grouped_expression", "literal", "arithmetic_operator", 
			"comparison_operator", "logical_operator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'if'", "'else'", "'elif'", "'switch'", "'case'", "'default'", 
			"'while'", "'for'", "'break'", "'return'", "'do'", "'func'", "'->'", 
			"'type'", "'struct'", null, null, null, null, "'\".*?\"'", null, "'.'", 
			"','", "':'", "';'", "'['", "']'", "'{'", "'}'", "'('", "')'", "'^'", 
			"'-'", "'+'", "'/'", "'*'", "'%'", "'<'", "'>'", "'>='", "'<='", "'=='", 
			"'!='", "'='", "'&&'", "'||'", "'!'", "'<<'", "'>>'", "'&'", "'|'", "'~'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "IF", "ELSE", "ELIF", "SWITCH", "CASE", "DEFAULT", "WHILE", 
			"FOR", "BREAK", "RETURN", "DO", "FUNCTION", "ARROW", "TYPE", "STRUCT", 
			"LITERAL_HEX", "LITERAL_BIN", "LITERAL_INT", "LITERAL_FLOAT", "LITERAL_STRING", 
			"LITERAL_CHAR", "DOT", "COMMA", "COLON", "SEMICOLON", "BRACKET_BEGIN", 
			"BRACKET_END", "BRACES_BEGIN", "BRACES_END", "PARENTHESES_BEGIN", "PARENTHESES_END", 
			"CARET", "MINUS", "PLUS", "DIV", "MUL", "MOD", "LT", "GT", "LT_EQ", "GT_EQ", 
			"EQ", "DIFF", "ASSIGN", "AND", "OR", "NOT", "BIT_SHL", "BIT_SHR", "BIT_AND", 
			"BIT_OR", "BIT_NOT", "SL_COMMENT", "ML_COMMENT", "ID"
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
		public List<Top_levelContext> top_level() {
			return getRuleContexts(Top_levelContext.class);
		}
		public Top_levelContext top_level(int i) {
			return getRuleContext(Top_levelContext.class,i);
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
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 72057594037968896L) != 0)) {
				{
				{
				setState(58);
				top_level();
				}
				}
				setState(63);
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
	public static class Top_levelContext extends ParserRuleContext {
		public Function_definitionContext function_definition() {
			return getRuleContext(Function_definitionContext.class,0);
		}
		public Variable_declarationContext variable_declaration() {
			return getRuleContext(Variable_declarationContext.class,0);
		}
		public Type_definitionContext type_definition() {
			return getRuleContext(Type_definitionContext.class,0);
		}
		public Top_levelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_top_level; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterTop_level(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitTop_level(this);
		}
	}

	public final Top_levelContext top_level() throws RecognitionException {
		Top_levelContext _localctx = new Top_levelContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_top_level);
		try {
			setState(67);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FUNCTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				function_definition();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				variable_declaration();
				}
				break;
			case TYPE:
				enterOuterAlt(_localctx, 3);
				{
				setState(66);
				type_definition();
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
	public static class StatementContext extends ParserRuleContext {
		public Inline_statementContext inline_statement() {
			return getRuleContext(Inline_statementContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(SimplexParser.SEMICOLON, 0); }
		public If_statementContext if_statement() {
			return getRuleContext(If_statementContext.class,0);
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
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(73);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BREAK:
			case RETURN:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				inline_statement();
				setState(70);
				match(SEMICOLON);
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				if_statement();
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
	public static class Inline_statementContext extends ParserRuleContext {
		public Variable_declarationContext variable_declaration() {
			return getRuleContext(Variable_declarationContext.class,0);
		}
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public Assignment_statementContext assignment_statement() {
			return getRuleContext(Assignment_statementContext.class,0);
		}
		public TerminalNode BREAK() { return getToken(SimplexParser.BREAK, 0); }
		public TerminalNode RETURN() { return getToken(SimplexParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Inline_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inline_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterInline_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitInline_statement(this);
		}
	}

	public final Inline_statementContext inline_statement() throws RecognitionException {
		Inline_statementContext _localctx = new Inline_statementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_inline_statement);
		int _la;
		try {
			setState(83);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				variable_declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(76);
				function_call();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(77);
				assignment_statement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(78);
				match(BREAK);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(79);
				match(RETURN);
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 72057596193669120L) != 0)) {
					{
					setState(80);
					expression(0);
					}
				}

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
	public static class Type_definitionContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(SimplexParser.TYPE, 0); }
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public Type_expressionContext type_expression() {
			return getRuleContext(Type_expressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(SimplexParser.SEMICOLON, 0); }
		public Struct_definitionContext struct_definition() {
			return getRuleContext(Struct_definitionContext.class,0);
		}
		public Type_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterType_definition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitType_definition(this);
		}
	}

	public final Type_definitionContext type_definition() throws RecognitionException {
		Type_definitionContext _localctx = new Type_definitionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_type_definition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(TYPE);
			setState(86);
			match(ID);
			setState(91);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BRACKET_BEGIN:
			case CARET:
			case ID:
				{
				setState(87);
				type_expression();
				setState(88);
				match(SEMICOLON);
				}
				break;
			case STRUCT:
				{
				setState(90);
				struct_definition();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class Type_expressionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public Type_prefixContext type_prefix() {
			return getRuleContext(Type_prefixContext.class,0);
		}
		public Type_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterType_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitType_expression(this);
		}
	}

	public final Type_expressionContext type_expression() throws RecognitionException {
		Type_expressionContext _localctx = new Type_expressionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type_expression);
		try {
			setState(97);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(93);
				match(ID);
				}
				break;
			case BRACKET_BEGIN:
			case CARET:
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				type_prefix();
				setState(95);
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
	public static class Type_prefixContext extends ParserRuleContext {
		public Slice_prefixContext slice_prefix() {
			return getRuleContext(Slice_prefixContext.class,0);
		}
		public List<Type_prefixContext> type_prefix() {
			return getRuleContexts(Type_prefixContext.class);
		}
		public Type_prefixContext type_prefix(int i) {
			return getRuleContext(Type_prefixContext.class,i);
		}
		public TerminalNode CARET() { return getToken(SimplexParser.CARET, 0); }
		public Type_prefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterType_prefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitType_prefix(this);
		}
	}

	public final Type_prefixContext type_prefix() throws RecognitionException {
		Type_prefixContext _localctx = new Type_prefixContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_type_prefix);
		try {
			int _alt;
			setState(113);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BRACKET_BEGIN:
				enterOuterAlt(_localctx, 1);
				{
				setState(99);
				slice_prefix();
				setState(103);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(100);
						type_prefix();
						}
						} 
					}
					setState(105);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
				}
				}
				break;
			case CARET:
				enterOuterAlt(_localctx, 2);
				{
				setState(106);
				match(CARET);
				setState(110);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(107);
						type_prefix();
						}
						} 
					}
					setState(112);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				}
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
	public static class Slice_prefixContext extends ParserRuleContext {
		public TerminalNode BRACKET_BEGIN() { return getToken(SimplexParser.BRACKET_BEGIN, 0); }
		public TerminalNode BRACKET_END() { return getToken(SimplexParser.BRACKET_END, 0); }
		public Slice_prefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_slice_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterSlice_prefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitSlice_prefix(this);
		}
	}

	public final Slice_prefixContext slice_prefix() throws RecognitionException {
		Slice_prefixContext _localctx = new Slice_prefixContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_slice_prefix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(BRACKET_BEGIN);
			setState(116);
			match(BRACKET_END);
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
		public Type_expressionContext type_expression() {
			return getRuleContext(Type_expressionContext.class,0);
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
		enterRule(_localctx, 16, RULE_variable_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(ID);
			setState(119);
			match(COLON);
			setState(120);
			type_expression();
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
	public static class Variable_definitionContext extends ParserRuleContext {
		public Variable_declarationContext variable_declaration() {
			return getRuleContext(Variable_declarationContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(SimplexParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Variable_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterVariable_definition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitVariable_definition(this);
		}
	}

	public final Variable_definitionContext variable_definition() throws RecognitionException {
		Variable_definitionContext _localctx = new Variable_definitionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_variable_definition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			variable_declaration();
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(123);
				match(ASSIGN);
				setState(124);
				expression(0);
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
	public static class Assignment_statementContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(SimplexParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
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
		enterRule(_localctx, 20, RULE_assignment_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(ID);
			setState(128);
			match(ASSIGN);
			setState(129);
			expression(0);
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
	public static class Struct_definitionContext extends ParserRuleContext {
		public TerminalNode STRUCT() { return getToken(SimplexParser.STRUCT, 0); }
		public TerminalNode BRACES_BEGIN() { return getToken(SimplexParser.BRACES_BEGIN, 0); }
		public TerminalNode BRACES_END() { return getToken(SimplexParser.BRACES_END, 0); }
		public Record_listContext record_list() {
			return getRuleContext(Record_listContext.class,0);
		}
		public Struct_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_struct_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterStruct_definition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitStruct_definition(this);
		}
	}

	public final Struct_definitionContext struct_definition() throws RecognitionException {
		Struct_definitionContext _localctx = new Struct_definitionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_struct_definition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(STRUCT);
			setState(132);
			match(BRACES_BEGIN);
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(133);
				record_list();
				}
			}

			setState(136);
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
	public static class Record_listContext extends ParserRuleContext {
		public List<Variable_declarationContext> variable_declaration() {
			return getRuleContexts(Variable_declarationContext.class);
		}
		public Variable_declarationContext variable_declaration(int i) {
			return getRuleContext(Variable_declarationContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SimplexParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SimplexParser.COMMA, i);
		}
		public Record_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_record_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterRecord_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitRecord_list(this);
		}
	}

	public final Record_listContext record_list() throws RecognitionException {
		Record_listContext _localctx = new Record_listContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_record_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			variable_declaration();
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(139);
				match(COMMA);
				setState(140);
				variable_declaration();
				}
				}
				setState(145);
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
	public static class Function_declarationContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(SimplexParser.FUNCTION, 0); }
		public List<TerminalNode> ID() { return getTokens(SimplexParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SimplexParser.ID, i);
		}
		public TerminalNode PARENTHESES_BEGIN() { return getToken(SimplexParser.PARENTHESES_BEGIN, 0); }
		public Record_listContext record_list() {
			return getRuleContext(Record_listContext.class,0);
		}
		public TerminalNode PARENTHESES_END() { return getToken(SimplexParser.PARENTHESES_END, 0); }
		public TerminalNode ARROW() { return getToken(SimplexParser.ARROW, 0); }
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
		enterRule(_localctx, 26, RULE_function_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(FUNCTION);
			setState(147);
			match(ID);
			setState(148);
			match(PARENTHESES_BEGIN);
			setState(149);
			record_list();
			setState(150);
			match(PARENTHESES_END);
			setState(151);
			match(ARROW);
			setState(152);
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
	public static class Function_definitionContext extends ParserRuleContext {
		public Function_declarationContext function_declaration() {
			return getRuleContext(Function_declarationContext.class,0);
		}
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public Function_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterFunction_definition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitFunction_definition(this);
		}
	}

	public final Function_definitionContext function_definition() throws RecognitionException {
		Function_definitionContext _localctx = new Function_definitionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_function_definition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			function_declaration();
			setState(155);
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
	public static class ParameterContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public TerminalNode COLON() { return getToken(SimplexParser.COLON, 0); }
		public Type_expressionContext type_expression() {
			return getRuleContext(Type_expressionContext.class,0);
		}
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
		enterRule(_localctx, 30, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(ID);
			setState(158);
			match(COLON);
			setState(159);
			type_expression();
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
		public TerminalNode BRACES_BEGIN() { return getToken(SimplexParser.BRACES_BEGIN, 0); }
		public TerminalNode BRACES_END() { return getToken(SimplexParser.BRACES_END, 0); }
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
		enterRule(_localctx, 32, RULE_scope);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(BRACES_BEGIN);
			setState(165);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 72057594037931012L) != 0)) {
				{
				{
				setState(162);
				statement();
				}
				}
				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(168);
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
		public IfContext if_() {
			return getRuleContext(IfContext.class,0);
		}
		public ElseContext else_() {
			return getRuleContext(ElseContext.class,0);
		}
		public List<ElifContext> elif() {
			return getRuleContexts(ElifContext.class);
		}
		public ElifContext elif(int i) {
			return getRuleContext(ElifContext.class,i);
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
		enterRule(_localctx, 34, RULE_if_statement);
		int _la;
		try {
			int _alt;
			setState(183);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(170);
				if_();
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(171);
					else_();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(174);
				if_();
				setState(176); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(175);
						elif();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(178); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(180);
					else_();
					}
				}

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
	public static class IfContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(SimplexParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public IfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitIf(this);
		}
	}

	public final IfContext if_() throws RecognitionException {
		IfContext _localctx = new IfContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_if);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(IF);
			setState(186);
			expression(0);
			setState(187);
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
	public static class ElseContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(SimplexParser.ELSE, 0); }
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public ElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitElse(this);
		}
	}

	public final ElseContext else_() throws RecognitionException {
		ElseContext _localctx = new ElseContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_else);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(ELSE);
			setState(190);
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
	public static class ElifContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(SimplexParser.ELSE, 0); }
		public TerminalNode IF() { return getToken(SimplexParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public ElifContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elif; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterElif(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitElif(this);
		}
	}

	public final ElifContext elif() throws RecognitionException {
		ElifContext _localctx = new ElifContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_elif);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			match(ELSE);
			setState(193);
			match(IF);
			setState(194);
			expression(0);
			setState(195);
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
	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public Grouped_expressionContext grouped_expression() {
			return getRuleContext(Grouped_expressionContext.class,0);
		}
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
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
		int _startState = 42;
		enterRecursionRule(_localctx, 42, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(198);
				match(ID);
				}
				break;
			case 2:
				{
				setState(199);
				literal();
				}
				break;
			case 3:
				{
				setState(200);
				grouped_expression();
				}
				break;
			case 4:
				{
				setState(201);
				function_call();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(218);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(216);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(204);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(205);
						arithmetic_operator();
						setState(206);
						expression(6);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(208);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(209);
						comparison_operator();
						setState(210);
						expression(5);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(212);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(213);
						logical_operator();
						setState(214);
						expression(4);
						}
						break;
					}
					} 
				}
				setState(220);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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
	public static class Expression_listContext extends ParserRuleContext {
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
		public Expression_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterExpression_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitExpression_list(this);
		}
	}

	public final Expression_listContext expression_list() throws RecognitionException {
		Expression_listContext _localctx = new Expression_listContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_expression_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			expression(0);
			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(222);
				match(COMMA);
				setState(223);
				expression(0);
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
	public static class Function_callContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public TerminalNode PARENTHESES_BEGIN() { return getToken(SimplexParser.PARENTHESES_BEGIN, 0); }
		public Expression_listContext expression_list() {
			return getRuleContext(Expression_listContext.class,0);
		}
		public TerminalNode PARENTHESES_END() { return getToken(SimplexParser.PARENTHESES_END, 0); }
		public Function_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterFunction_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitFunction_call(this);
		}
	}

	public final Function_callContext function_call() throws RecognitionException {
		Function_callContext _localctx = new Function_callContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_function_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			match(ID);
			setState(230);
			match(PARENTHESES_BEGIN);
			setState(231);
			expression_list();
			setState(232);
			match(PARENTHESES_END);
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
	public static class Grouped_expressionContext extends ParserRuleContext {
		public TerminalNode PARENTHESES_BEGIN() { return getToken(SimplexParser.PARENTHESES_BEGIN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PARENTHESES_END() { return getToken(SimplexParser.PARENTHESES_END, 0); }
		public Grouped_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grouped_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterGrouped_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitGrouped_expression(this);
		}
	}

	public final Grouped_expressionContext grouped_expression() throws RecognitionException {
		Grouped_expressionContext _localctx = new Grouped_expressionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_grouped_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			match(PARENTHESES_BEGIN);
			setState(235);
			expression(0);
			setState(236);
			match(PARENTHESES_END);
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
		enterRule(_localctx, 50, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8257536L) != 0)) ) {
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
		enterRule(_localctx, 52, RULE_arithmetic_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 532575944704L) != 0)) ) {
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
		enterRule(_localctx, 54, RULE_comparison_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 34634616274944L) != 0)) ) {
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
		enterRule(_localctx, 56, RULE_logical_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 492581209243648L) != 0)) ) {
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
		case 21:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 5);
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00018\u00f7\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0001\u0000\u0005\u0000<\b\u0000\n\u0000\f\u0000"+
		"?\t\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001D\b\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002J\b\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003"+
		"\u0003R\b\u0003\u0003\u0003T\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\\\b\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005b\b\u0005\u0001\u0006"+
		"\u0001\u0006\u0005\u0006f\b\u0006\n\u0006\f\u0006i\t\u0006\u0001\u0006"+
		"\u0001\u0006\u0005\u0006m\b\u0006\n\u0006\f\u0006p\t\u0006\u0003\u0006"+
		"r\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\t\u0001\t\u0001\t\u0003\t~\b\t\u0001\n\u0001\n\u0001\n"+
		"\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0087\b\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0005\f\u008e\b\f\n\f"+
		"\f\f\u0091\t\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r"+
		"\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0005\u0010\u00a4\b\u0010\n"+
		"\u0010\f\u0010\u00a7\t\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001"+
		"\u0011\u0003\u0011\u00ad\b\u0011\u0001\u0011\u0001\u0011\u0004\u0011\u00b1"+
		"\b\u0011\u000b\u0011\f\u0011\u00b2\u0001\u0011\u0003\u0011\u00b6\b\u0011"+
		"\u0003\u0011\u00b8\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0003\u0015\u00cb\b\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u00d9\b\u0015\n\u0015"+
		"\f\u0015\u00dc\t\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0005\u0016"+
		"\u00e1\b\u0016\n\u0016\f\u0016\u00e4\t\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001b\u0001"+
		"\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0000\u0001*\u001d\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		" \"$&(*,.02468\u0000\u0004\u0001\u0000\u0011\u0016\u0001\u0000\"&\u0001"+
		"\u0000\',\u0001\u0000.0\u00f6\u0000=\u0001\u0000\u0000\u0000\u0002C\u0001"+
		"\u0000\u0000\u0000\u0004I\u0001\u0000\u0000\u0000\u0006S\u0001\u0000\u0000"+
		"\u0000\bU\u0001\u0000\u0000\u0000\na\u0001\u0000\u0000\u0000\fq\u0001"+
		"\u0000\u0000\u0000\u000es\u0001\u0000\u0000\u0000\u0010v\u0001\u0000\u0000"+
		"\u0000\u0012z\u0001\u0000\u0000\u0000\u0014\u007f\u0001\u0000\u0000\u0000"+
		"\u0016\u0083\u0001\u0000\u0000\u0000\u0018\u008a\u0001\u0000\u0000\u0000"+
		"\u001a\u0092\u0001\u0000\u0000\u0000\u001c\u009a\u0001\u0000\u0000\u0000"+
		"\u001e\u009d\u0001\u0000\u0000\u0000 \u00a1\u0001\u0000\u0000\u0000\""+
		"\u00b7\u0001\u0000\u0000\u0000$\u00b9\u0001\u0000\u0000\u0000&\u00bd\u0001"+
		"\u0000\u0000\u0000(\u00c0\u0001\u0000\u0000\u0000*\u00ca\u0001\u0000\u0000"+
		"\u0000,\u00dd\u0001\u0000\u0000\u0000.\u00e5\u0001\u0000\u0000\u00000"+
		"\u00ea\u0001\u0000\u0000\u00002\u00ee\u0001\u0000\u0000\u00004\u00f0\u0001"+
		"\u0000\u0000\u00006\u00f2\u0001\u0000\u0000\u00008\u00f4\u0001\u0000\u0000"+
		"\u0000:<\u0003\u0002\u0001\u0000;:\u0001\u0000\u0000\u0000<?\u0001\u0000"+
		"\u0000\u0000=;\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000>\u0001"+
		"\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000@D\u0003\u001c\u000e"+
		"\u0000AD\u0003\u0010\b\u0000BD\u0003\b\u0004\u0000C@\u0001\u0000\u0000"+
		"\u0000CA\u0001\u0000\u0000\u0000CB\u0001\u0000\u0000\u0000D\u0003\u0001"+
		"\u0000\u0000\u0000EF\u0003\u0006\u0003\u0000FG\u0005\u001a\u0000\u0000"+
		"GJ\u0001\u0000\u0000\u0000HJ\u0003\"\u0011\u0000IE\u0001\u0000\u0000\u0000"+
		"IH\u0001\u0000\u0000\u0000J\u0005\u0001\u0000\u0000\u0000KT\u0003\u0010"+
		"\b\u0000LT\u0003.\u0017\u0000MT\u0003\u0014\n\u0000NT\u0005\n\u0000\u0000"+
		"OQ\u0005\u000b\u0000\u0000PR\u0003*\u0015\u0000QP\u0001\u0000\u0000\u0000"+
		"QR\u0001\u0000\u0000\u0000RT\u0001\u0000\u0000\u0000SK\u0001\u0000\u0000"+
		"\u0000SL\u0001\u0000\u0000\u0000SM\u0001\u0000\u0000\u0000SN\u0001\u0000"+
		"\u0000\u0000SO\u0001\u0000\u0000\u0000T\u0007\u0001\u0000\u0000\u0000"+
		"UV\u0005\u000f\u0000\u0000V[\u00058\u0000\u0000WX\u0003\n\u0005\u0000"+
		"XY\u0005\u001a\u0000\u0000Y\\\u0001\u0000\u0000\u0000Z\\\u0003\u0016\u000b"+
		"\u0000[W\u0001\u0000\u0000\u0000[Z\u0001\u0000\u0000\u0000\\\t\u0001\u0000"+
		"\u0000\u0000]b\u00058\u0000\u0000^_\u0003\f\u0006\u0000_`\u00058\u0000"+
		"\u0000`b\u0001\u0000\u0000\u0000a]\u0001\u0000\u0000\u0000a^\u0001\u0000"+
		"\u0000\u0000b\u000b\u0001\u0000\u0000\u0000cg\u0003\u000e\u0007\u0000"+
		"df\u0003\f\u0006\u0000ed\u0001\u0000\u0000\u0000fi\u0001\u0000\u0000\u0000"+
		"ge\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000\u0000hr\u0001\u0000\u0000"+
		"\u0000ig\u0001\u0000\u0000\u0000jn\u0005!\u0000\u0000km\u0003\f\u0006"+
		"\u0000lk\u0001\u0000\u0000\u0000mp\u0001\u0000\u0000\u0000nl\u0001\u0000"+
		"\u0000\u0000no\u0001\u0000\u0000\u0000or\u0001\u0000\u0000\u0000pn\u0001"+
		"\u0000\u0000\u0000qc\u0001\u0000\u0000\u0000qj\u0001\u0000\u0000\u0000"+
		"r\r\u0001\u0000\u0000\u0000st\u0005\u001b\u0000\u0000tu\u0005\u001c\u0000"+
		"\u0000u\u000f\u0001\u0000\u0000\u0000vw\u00058\u0000\u0000wx\u0005\u0019"+
		"\u0000\u0000xy\u0003\n\u0005\u0000y\u0011\u0001\u0000\u0000\u0000z}\u0003"+
		"\u0010\b\u0000{|\u0005-\u0000\u0000|~\u0003*\u0015\u0000}{\u0001\u0000"+
		"\u0000\u0000}~\u0001\u0000\u0000\u0000~\u0013\u0001\u0000\u0000\u0000"+
		"\u007f\u0080\u00058\u0000\u0000\u0080\u0081\u0005-\u0000\u0000\u0081\u0082"+
		"\u0003*\u0015\u0000\u0082\u0015\u0001\u0000\u0000\u0000\u0083\u0084\u0005"+
		"\u0010\u0000\u0000\u0084\u0086\u0005\u001d\u0000\u0000\u0085\u0087\u0003"+
		"\u0018\f\u0000\u0086\u0085\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000"+
		"\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u0089\u0005\u001e"+
		"\u0000\u0000\u0089\u0017\u0001\u0000\u0000\u0000\u008a\u008f\u0003\u0010"+
		"\b\u0000\u008b\u008c\u0005\u0018\u0000\u0000\u008c\u008e\u0003\u0010\b"+
		"\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008e\u0091\u0001\u0000\u0000"+
		"\u0000\u008f\u008d\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000"+
		"\u0000\u0090\u0019\u0001\u0000\u0000\u0000\u0091\u008f\u0001\u0000\u0000"+
		"\u0000\u0092\u0093\u0005\r\u0000\u0000\u0093\u0094\u00058\u0000\u0000"+
		"\u0094\u0095\u0005\u001f\u0000\u0000\u0095\u0096\u0003\u0018\f\u0000\u0096"+
		"\u0097\u0005 \u0000\u0000\u0097\u0098\u0005\u000e\u0000\u0000\u0098\u0099"+
		"\u00058\u0000\u0000\u0099\u001b\u0001\u0000\u0000\u0000\u009a\u009b\u0003"+
		"\u001a\r\u0000\u009b\u009c\u0003 \u0010\u0000\u009c\u001d\u0001\u0000"+
		"\u0000\u0000\u009d\u009e\u00058\u0000\u0000\u009e\u009f\u0005\u0019\u0000"+
		"\u0000\u009f\u00a0\u0003\n\u0005\u0000\u00a0\u001f\u0001\u0000\u0000\u0000"+
		"\u00a1\u00a5\u0005\u001d\u0000\u0000\u00a2\u00a4\u0003\u0004\u0002\u0000"+
		"\u00a3\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a7\u0001\u0000\u0000\u0000"+
		"\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000"+
		"\u00a6\u00a8\u0001\u0000\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000"+
		"\u00a8\u00a9\u0005\u001e\u0000\u0000\u00a9!\u0001\u0000\u0000\u0000\u00aa"+
		"\u00ac\u0003$\u0012\u0000\u00ab\u00ad\u0003&\u0013\u0000\u00ac\u00ab\u0001"+
		"\u0000\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000\u0000\u00ad\u00b8\u0001"+
		"\u0000\u0000\u0000\u00ae\u00b0\u0003$\u0012\u0000\u00af\u00b1\u0003(\u0014"+
		"\u0000\u00b0\u00af\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001\u0000\u0000"+
		"\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000"+
		"\u0000\u00b3\u00b5\u0001\u0000\u0000\u0000\u00b4\u00b6\u0003&\u0013\u0000"+
		"\u00b5\u00b4\u0001\u0000\u0000\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b8\u0001\u0000\u0000\u0000\u00b7\u00aa\u0001\u0000\u0000\u0000"+
		"\u00b7\u00ae\u0001\u0000\u0000\u0000\u00b8#\u0001\u0000\u0000\u0000\u00b9"+
		"\u00ba\u0005\u0002\u0000\u0000\u00ba\u00bb\u0003*\u0015\u0000\u00bb\u00bc"+
		"\u0003 \u0010\u0000\u00bc%\u0001\u0000\u0000\u0000\u00bd\u00be\u0005\u0003"+
		"\u0000\u0000\u00be\u00bf\u0003 \u0010\u0000\u00bf\'\u0001\u0000\u0000"+
		"\u0000\u00c0\u00c1\u0005\u0003\u0000\u0000\u00c1\u00c2\u0005\u0002\u0000"+
		"\u0000\u00c2\u00c3\u0003*\u0015\u0000\u00c3\u00c4\u0003 \u0010\u0000\u00c4"+
		")\u0001\u0000\u0000\u0000\u00c5\u00c6\u0006\u0015\uffff\uffff\u0000\u00c6"+
		"\u00cb\u00058\u0000\u0000\u00c7\u00cb\u00032\u0019\u0000\u00c8\u00cb\u0003"+
		"0\u0018\u0000\u00c9\u00cb\u0003.\u0017\u0000\u00ca\u00c5\u0001\u0000\u0000"+
		"\u0000\u00ca\u00c7\u0001\u0000\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000"+
		"\u0000\u00ca\u00c9\u0001\u0000\u0000\u0000\u00cb\u00da\u0001\u0000\u0000"+
		"\u0000\u00cc\u00cd\n\u0005\u0000\u0000\u00cd\u00ce\u00034\u001a\u0000"+
		"\u00ce\u00cf\u0003*\u0015\u0006\u00cf\u00d9\u0001\u0000\u0000\u0000\u00d0"+
		"\u00d1\n\u0004\u0000\u0000\u00d1\u00d2\u00036\u001b\u0000\u00d2\u00d3"+
		"\u0003*\u0015\u0005\u00d3\u00d9\u0001\u0000\u0000\u0000\u00d4\u00d5\n"+
		"\u0003\u0000\u0000\u00d5\u00d6\u00038\u001c\u0000\u00d6\u00d7\u0003*\u0015"+
		"\u0004\u00d7\u00d9\u0001\u0000\u0000\u0000\u00d8\u00cc\u0001\u0000\u0000"+
		"\u0000\u00d8\u00d0\u0001\u0000\u0000\u0000\u00d8\u00d4\u0001\u0000\u0000"+
		"\u0000\u00d9\u00dc\u0001\u0000\u0000\u0000\u00da\u00d8\u0001\u0000\u0000"+
		"\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db+\u0001\u0000\u0000\u0000"+
		"\u00dc\u00da\u0001\u0000\u0000\u0000\u00dd\u00e2\u0003*\u0015\u0000\u00de"+
		"\u00df\u0005\u0018\u0000\u0000\u00df\u00e1\u0003*\u0015\u0000\u00e0\u00de"+
		"\u0001\u0000\u0000\u0000\u00e1\u00e4\u0001\u0000\u0000\u0000\u00e2\u00e0"+
		"\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3-\u0001"+
		"\u0000\u0000\u0000\u00e4\u00e2\u0001\u0000\u0000\u0000\u00e5\u00e6\u0005"+
		"8\u0000\u0000\u00e6\u00e7\u0005\u001f\u0000\u0000\u00e7\u00e8\u0003,\u0016"+
		"\u0000\u00e8\u00e9\u0005 \u0000\u0000\u00e9/\u0001\u0000\u0000\u0000\u00ea"+
		"\u00eb\u0005\u001f\u0000\u0000\u00eb\u00ec\u0003*\u0015\u0000\u00ec\u00ed"+
		"\u0005 \u0000\u0000\u00ed1\u0001\u0000\u0000\u0000\u00ee\u00ef\u0007\u0000"+
		"\u0000\u0000\u00ef3\u0001\u0000\u0000\u0000\u00f0\u00f1\u0007\u0001\u0000"+
		"\u0000\u00f15\u0001\u0000\u0000\u0000\u00f2\u00f3\u0007\u0002\u0000\u0000"+
		"\u00f37\u0001\u0000\u0000\u0000\u00f4\u00f5\u0007\u0003\u0000\u0000\u00f5"+
		"9\u0001\u0000\u0000\u0000\u0016=CIQS[agnq}\u0086\u008f\u00a5\u00ac\u00b2"+
		"\u00b5\u00b7\u00ca\u00d8\u00da\u00e2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}