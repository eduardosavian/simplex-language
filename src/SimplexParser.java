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
		WS=1, IF=2, ELSE=3, SWITCH=4, CASE=5, DEFAULT=6, WHILE=7, FOR=8, BREAK=9, 
		RETURN=10, DO=11, FUNCTION=12, ARROW=13, TYPE=14, STRUCT=15, LITERAL_HEX=16, 
		LITERAL_BIN=17, LITERAL_INT=18, LITERAL_FLOAT=19, LITERAL_STRING=20, LITERAL_CHAR=21, 
		DOT=22, COMMA=23, COLON=24, SEMICOLON=25, BRACKET_BEGIN=26, BRACKET_END=27, 
		BRACES_BEGIN=28, BRACES_END=29, PARENTHESES_BEGIN=30, PARENTHESES_END=31, 
		CARET=32, MINUS=33, PLUS=34, DIV=35, MUL=36, MOD=37, LT=38, GT=39, LT_EQ=40, 
		GT_EQ=41, EQ=42, DIFF=43, ASSIGN=44, AND=45, OR=46, NOT=47, BIT_SHL=48, 
		BIT_SHR=49, BIT_AND=50, BIT_OR=51, BIT_NOT=52, SL_COMMENT=53, ML_COMMENT=54, 
		ID=55;
	public static final int
		RULE_program = 0, RULE_top_level = 1, RULE_statement = 2, RULE_type_definition = 3, 
		RULE_type_expression = 4, RULE_type_prefix = 5, RULE_slice_prefix = 6, 
		RULE_variable_declaration = 7, RULE_variable_definition = 8, RULE_assignment_statement = 9, 
		RULE_struct_definition = 10, RULE_record_list = 11, RULE_function_declaration = 12, 
		RULE_function_definition = 13, RULE_parameter = 14, RULE_block = 15, RULE_expression = 16, 
		RULE_grouped_expression = 17, RULE_literal = 18, RULE_arithmetic_operator = 19, 
		RULE_comparison_operator = 20, RULE_logical_operator = 21;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "top_level", "statement", "type_definition", "type_expression", 
			"type_prefix", "slice_prefix", "variable_declaration", "variable_definition", 
			"assignment_statement", "struct_definition", "record_list", "function_declaration", 
			"function_definition", "parameter", "block", "expression", "grouped_expression", 
			"literal", "arithmetic_operator", "comparison_operator", "logical_operator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'if'", "'else'", "'switch'", "'case'", "'default'", "'while'", 
			"'for'", "'break'", "'return'", "'do'", "'func'", "'->'", "'type'", "'struct'", 
			null, null, null, null, "'\".*?\"'", null, "'.'", "','", "':'", "';'", 
			"'['", "']'", "'{'", "'}'", "'('", "')'", "'^'", "'-'", "'+'", "'/'", 
			"'*'", "'%'", "'<'", "'>'", "'>='", "'<='", "'=='", "'!='", "'='", "'&&'", 
			"'||'", "'!'", "'<<'", "'>>'", "'&'", "'|'", "'~'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "IF", "ELSE", "SWITCH", "CASE", "DEFAULT", "WHILE", "FOR", 
			"BREAK", "RETURN", "DO", "FUNCTION", "ARROW", "TYPE", "STRUCT", "LITERAL_HEX", 
			"LITERAL_BIN", "LITERAL_INT", "LITERAL_FLOAT", "LITERAL_STRING", "LITERAL_CHAR", 
			"DOT", "COMMA", "COLON", "SEMICOLON", "BRACKET_BEGIN", "BRACKET_END", 
			"BRACES_BEGIN", "BRACES_END", "PARENTHESES_BEGIN", "PARENTHESES_END", 
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
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 36028797018984448L) != 0)) {
				{
				{
				setState(44);
				top_level();
				}
				}
				setState(49);
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
			setState(53);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FUNCTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				function_definition();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
				variable_declaration();
				}
				break;
			case TYPE:
				enterOuterAlt(_localctx, 3);
				{
				setState(52);
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
		public TerminalNode SEMICOLON() { return getToken(SimplexParser.SEMICOLON, 0); }
		public Variable_declarationContext variable_declaration() {
			return getRuleContext(Variable_declarationContext.class,0);
		}
		public Assignment_statementContext assignment_statement() {
			return getRuleContext(Assignment_statementContext.class,0);
		}
		public TerminalNode BREAK() { return getToken(SimplexParser.BREAK, 0); }
		public TerminalNode RETURN() { return getToken(SimplexParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(55);
				variable_declaration();
				}
				break;
			case 2:
				{
				setState(56);
				assignment_statement();
				}
				break;
			case 3:
				{
				setState(57);
				match(BREAK);
				}
				break;
			case 4:
				{
				setState(58);
				match(RETURN);
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 36028798096834560L) != 0)) {
					{
					setState(59);
					expression(0);
					}
				}

				}
				break;
			}
			setState(64);
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
		enterRule(_localctx, 6, RULE_type_definition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(TYPE);
			setState(67);
			match(ID);
			setState(72);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BRACKET_BEGIN:
			case CARET:
			case ID:
				{
				setState(68);
				type_expression();
				setState(69);
				match(SEMICOLON);
				}
				break;
			case STRUCT:
				{
				setState(71);
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
		enterRule(_localctx, 8, RULE_type_expression);
		try {
			setState(78);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				match(ID);
				}
				break;
			case BRACKET_BEGIN:
			case CARET:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				type_prefix();
				setState(76);
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
		enterRule(_localctx, 10, RULE_type_prefix);
		try {
			int _alt;
			setState(94);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BRACKET_BEGIN:
				enterOuterAlt(_localctx, 1);
				{
				setState(80);
				slice_prefix();
				setState(84);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(81);
						type_prefix();
						}
						} 
					}
					setState(86);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				}
				}
				break;
			case CARET:
				enterOuterAlt(_localctx, 2);
				{
				setState(87);
				match(CARET);
				setState(91);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(88);
						type_prefix();
						}
						} 
					}
					setState(93);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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
		enterRule(_localctx, 12, RULE_slice_prefix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(BRACKET_BEGIN);
			setState(97);
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
		enterRule(_localctx, 14, RULE_variable_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(ID);
			setState(100);
			match(COLON);
			setState(101);
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
		enterRule(_localctx, 16, RULE_variable_definition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			variable_declaration();
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(104);
				match(ASSIGN);
				setState(105);
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
		enterRule(_localctx, 18, RULE_assignment_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(ID);
			setState(109);
			match(ASSIGN);
			setState(110);
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
		enterRule(_localctx, 20, RULE_struct_definition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(STRUCT);
			setState(113);
			match(BRACES_BEGIN);
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(114);
				record_list();
				}
			}

			setState(117);
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
		enterRule(_localctx, 22, RULE_record_list);
		int _la;
		try {
			setState(130);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(119);
				variable_declaration();
				setState(122); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(120);
					match(COMMA);
					setState(121);
					variable_declaration();
					}
					}
					setState(124); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==COMMA );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(126);
				variable_declaration();
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(127);
					match(COMMA);
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
		enterRule(_localctx, 24, RULE_function_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(FUNCTION);
			setState(133);
			match(ID);
			setState(134);
			match(PARENTHESES_BEGIN);
			setState(135);
			record_list();
			setState(136);
			match(PARENTHESES_END);
			setState(137);
			match(ARROW);
			setState(138);
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
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
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
		enterRule(_localctx, 26, RULE_function_definition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			function_declaration();
			setState(141);
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
		enterRule(_localctx, 28, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(ID);
			setState(144);
			match(COLON);
			setState(145);
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
		enterRule(_localctx, 30, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(BRACES_BEGIN);
			setState(151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 36028797018965504L) != 0)) {
				{
				{
				setState(148);
				statement();
				}
				}
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(154);
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
	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public Grouped_expressionContext grouped_expression() {
			return getRuleContext(Grouped_expressionContext.class,0);
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
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(157);
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
				setState(158);
				literal();
				}
				break;
			case PARENTHESES_BEGIN:
				{
				setState(159);
				grouped_expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(176);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(174);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(162);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(163);
						arithmetic_operator();
						setState(164);
						expression(5);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(166);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(167);
						comparison_operator();
						setState(168);
						expression(4);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(170);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(171);
						logical_operator();
						setState(172);
						expression(3);
						}
						break;
					}
					} 
				}
				setState(178);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
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
		enterRule(_localctx, 34, RULE_grouped_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			match(PARENTHESES_BEGIN);
			setState(180);
			expression(0);
			setState(181);
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
		enterRule(_localctx, 36, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4128768L) != 0)) ) {
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
		enterRule(_localctx, 38, RULE_arithmetic_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 266287972352L) != 0)) ) {
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
		enterRule(_localctx, 40, RULE_comparison_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 17317308137472L) != 0)) ) {
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
		enterRule(_localctx, 42, RULE_logical_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 246290604621824L) != 0)) ) {
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
		case 16:
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
		"\u0004\u00017\u00c0\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0001\u0000\u0005\u0000.\b\u0000\n\u0000\f\u00001\t\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u00016\b\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002=\b\u0002\u0003\u0002"+
		"?\b\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003I\b\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004O\b\u0004\u0001\u0005"+
		"\u0001\u0005\u0005\u0005S\b\u0005\n\u0005\f\u0005V\t\u0005\u0001\u0005"+
		"\u0001\u0005\u0005\u0005Z\b\u0005\n\u0005\f\u0005]\t\u0005\u0003\u0005"+
		"_\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0003\bk\b\b\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0003\nt\b\n\u0001\n"+
		"\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0004\u000b{\b\u000b\u000b"+
		"\u000b\f\u000b|\u0001\u000b\u0001\u000b\u0003\u000b\u0081\b\u000b\u0003"+
		"\u000b\u0083\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0005\u000f\u0096\b\u000f\n\u000f"+
		"\f\u000f\u0099\t\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0003\u0010\u00a1\b\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00af\b\u0010"+
		"\n\u0010\f\u0010\u00b2\t\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001"+
		"\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0000\u0001 \u0016\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		" \"$&(*\u0000\u0004\u0001\u0000\u0010\u0015\u0001\u0000!%\u0001\u0000"+
		"&+\u0001\u0000-/\u00c0\u0000/\u0001\u0000\u0000\u0000\u00025\u0001\u0000"+
		"\u0000\u0000\u0004>\u0001\u0000\u0000\u0000\u0006B\u0001\u0000\u0000\u0000"+
		"\bN\u0001\u0000\u0000\u0000\n^\u0001\u0000\u0000\u0000\f`\u0001\u0000"+
		"\u0000\u0000\u000ec\u0001\u0000\u0000\u0000\u0010g\u0001\u0000\u0000\u0000"+
		"\u0012l\u0001\u0000\u0000\u0000\u0014p\u0001\u0000\u0000\u0000\u0016\u0082"+
		"\u0001\u0000\u0000\u0000\u0018\u0084\u0001\u0000\u0000\u0000\u001a\u008c"+
		"\u0001\u0000\u0000\u0000\u001c\u008f\u0001\u0000\u0000\u0000\u001e\u0093"+
		"\u0001\u0000\u0000\u0000 \u00a0\u0001\u0000\u0000\u0000\"\u00b3\u0001"+
		"\u0000\u0000\u0000$\u00b7\u0001\u0000\u0000\u0000&\u00b9\u0001\u0000\u0000"+
		"\u0000(\u00bb\u0001\u0000\u0000\u0000*\u00bd\u0001\u0000\u0000\u0000,"+
		".\u0003\u0002\u0001\u0000-,\u0001\u0000\u0000\u0000.1\u0001\u0000\u0000"+
		"\u0000/-\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000\u00000\u0001\u0001"+
		"\u0000\u0000\u00001/\u0001\u0000\u0000\u000026\u0003\u001a\r\u000036\u0003"+
		"\u000e\u0007\u000046\u0003\u0006\u0003\u000052\u0001\u0000\u0000\u0000"+
		"53\u0001\u0000\u0000\u000054\u0001\u0000\u0000\u00006\u0003\u0001\u0000"+
		"\u0000\u00007?\u0003\u000e\u0007\u00008?\u0003\u0012\t\u00009?\u0005\t"+
		"\u0000\u0000:<\u0005\n\u0000\u0000;=\u0003 \u0010\u0000<;\u0001\u0000"+
		"\u0000\u0000<=\u0001\u0000\u0000\u0000=?\u0001\u0000\u0000\u0000>7\u0001"+
		"\u0000\u0000\u0000>8\u0001\u0000\u0000\u0000>9\u0001\u0000\u0000\u0000"+
		">:\u0001\u0000\u0000\u0000?@\u0001\u0000\u0000\u0000@A\u0005\u0019\u0000"+
		"\u0000A\u0005\u0001\u0000\u0000\u0000BC\u0005\u000e\u0000\u0000CH\u0005"+
		"7\u0000\u0000DE\u0003\b\u0004\u0000EF\u0005\u0019\u0000\u0000FI\u0001"+
		"\u0000\u0000\u0000GI\u0003\u0014\n\u0000HD\u0001\u0000\u0000\u0000HG\u0001"+
		"\u0000\u0000\u0000I\u0007\u0001\u0000\u0000\u0000JO\u00057\u0000\u0000"+
		"KL\u0003\n\u0005\u0000LM\u00057\u0000\u0000MO\u0001\u0000\u0000\u0000"+
		"NJ\u0001\u0000\u0000\u0000NK\u0001\u0000\u0000\u0000O\t\u0001\u0000\u0000"+
		"\u0000PT\u0003\f\u0006\u0000QS\u0003\n\u0005\u0000RQ\u0001\u0000\u0000"+
		"\u0000SV\u0001\u0000\u0000\u0000TR\u0001\u0000\u0000\u0000TU\u0001\u0000"+
		"\u0000\u0000U_\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000W[\u0005"+
		" \u0000\u0000XZ\u0003\n\u0005\u0000YX\u0001\u0000\u0000\u0000Z]\u0001"+
		"\u0000\u0000\u0000[Y\u0001\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000"+
		"\\_\u0001\u0000\u0000\u0000][\u0001\u0000\u0000\u0000^P\u0001\u0000\u0000"+
		"\u0000^W\u0001\u0000\u0000\u0000_\u000b\u0001\u0000\u0000\u0000`a\u0005"+
		"\u001a\u0000\u0000ab\u0005\u001b\u0000\u0000b\r\u0001\u0000\u0000\u0000"+
		"cd\u00057\u0000\u0000de\u0005\u0018\u0000\u0000ef\u0003\b\u0004\u0000"+
		"f\u000f\u0001\u0000\u0000\u0000gj\u0003\u000e\u0007\u0000hi\u0005,\u0000"+
		"\u0000ik\u0003 \u0010\u0000jh\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000"+
		"\u0000k\u0011\u0001\u0000\u0000\u0000lm\u00057\u0000\u0000mn\u0005,\u0000"+
		"\u0000no\u0003 \u0010\u0000o\u0013\u0001\u0000\u0000\u0000pq\u0005\u000f"+
		"\u0000\u0000qs\u0005\u001c\u0000\u0000rt\u0003\u0016\u000b\u0000sr\u0001"+
		"\u0000\u0000\u0000st\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000"+
		"uv\u0005\u001d\u0000\u0000v\u0015\u0001\u0000\u0000\u0000wz\u0003\u000e"+
		"\u0007\u0000xy\u0005\u0017\u0000\u0000y{\u0003\u000e\u0007\u0000zx\u0001"+
		"\u0000\u0000\u0000{|\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000"+
		"|}\u0001\u0000\u0000\u0000}\u0083\u0001\u0000\u0000\u0000~\u0080\u0003"+
		"\u000e\u0007\u0000\u007f\u0081\u0005\u0017\u0000\u0000\u0080\u007f\u0001"+
		"\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u0083\u0001"+
		"\u0000\u0000\u0000\u0082w\u0001\u0000\u0000\u0000\u0082~\u0001\u0000\u0000"+
		"\u0000\u0083\u0017\u0001\u0000\u0000\u0000\u0084\u0085\u0005\f\u0000\u0000"+
		"\u0085\u0086\u00057\u0000\u0000\u0086\u0087\u0005\u001e\u0000\u0000\u0087"+
		"\u0088\u0003\u0016\u000b\u0000\u0088\u0089\u0005\u001f\u0000\u0000\u0089"+
		"\u008a\u0005\r\u0000\u0000\u008a\u008b\u00057\u0000\u0000\u008b\u0019"+
		"\u0001\u0000\u0000\u0000\u008c\u008d\u0003\u0018\f\u0000\u008d\u008e\u0003"+
		"\u001e\u000f\u0000\u008e\u001b\u0001\u0000\u0000\u0000\u008f\u0090\u0005"+
		"7\u0000\u0000\u0090\u0091\u0005\u0018\u0000\u0000\u0091\u0092\u0003\b"+
		"\u0004\u0000\u0092\u001d\u0001\u0000\u0000\u0000\u0093\u0097\u0005\u001c"+
		"\u0000\u0000\u0094\u0096\u0003\u0004\u0002\u0000\u0095\u0094\u0001\u0000"+
		"\u0000\u0000\u0096\u0099\u0001\u0000\u0000\u0000\u0097\u0095\u0001\u0000"+
		"\u0000\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098\u009a\u0001\u0000"+
		"\u0000\u0000\u0099\u0097\u0001\u0000\u0000\u0000\u009a\u009b\u0005\u001d"+
		"\u0000\u0000\u009b\u001f\u0001\u0000\u0000\u0000\u009c\u009d\u0006\u0010"+
		"\uffff\uffff\u0000\u009d\u00a1\u00057\u0000\u0000\u009e\u00a1\u0003$\u0012"+
		"\u0000\u009f\u00a1\u0003\"\u0011\u0000\u00a0\u009c\u0001\u0000\u0000\u0000"+
		"\u00a0\u009e\u0001\u0000\u0000\u0000\u00a0\u009f\u0001\u0000\u0000\u0000"+
		"\u00a1\u00b0\u0001\u0000\u0000\u0000\u00a2\u00a3\n\u0004\u0000\u0000\u00a3"+
		"\u00a4\u0003&\u0013\u0000\u00a4\u00a5\u0003 \u0010\u0005\u00a5\u00af\u0001"+
		"\u0000\u0000\u0000\u00a6\u00a7\n\u0003\u0000\u0000\u00a7\u00a8\u0003("+
		"\u0014\u0000\u00a8\u00a9\u0003 \u0010\u0004\u00a9\u00af\u0001\u0000\u0000"+
		"\u0000\u00aa\u00ab\n\u0002\u0000\u0000\u00ab\u00ac\u0003*\u0015\u0000"+
		"\u00ac\u00ad\u0003 \u0010\u0003\u00ad\u00af\u0001\u0000\u0000\u0000\u00ae"+
		"\u00a2\u0001\u0000\u0000\u0000\u00ae\u00a6\u0001\u0000\u0000\u0000\u00ae"+
		"\u00aa\u0001\u0000\u0000\u0000\u00af\u00b2\u0001\u0000\u0000\u0000\u00b0"+
		"\u00ae\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001\u0000\u0000\u0000\u00b1"+
		"!\u0001\u0000\u0000\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000\u00b3\u00b4"+
		"\u0005\u001e\u0000\u0000\u00b4\u00b5\u0003 \u0010\u0000\u00b5\u00b6\u0005"+
		"\u001f\u0000\u0000\u00b6#\u0001\u0000\u0000\u0000\u00b7\u00b8\u0007\u0000"+
		"\u0000\u0000\u00b8%\u0001\u0000\u0000\u0000\u00b9\u00ba\u0007\u0001\u0000"+
		"\u0000\u00ba\'\u0001\u0000\u0000\u0000\u00bb\u00bc\u0007\u0002\u0000\u0000"+
		"\u00bc)\u0001\u0000\u0000\u0000\u00bd\u00be\u0007\u0003\u0000\u0000\u00be"+
		"+\u0001\u0000\u0000\u0000\u0012/5<>HNT[^js|\u0080\u0082\u0097\u00a0\u00ae"+
		"\u00b0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}