// Generated from src/SimplexParser.g4 by ANTLR 4.13.1
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
		CASE=10, WHILE=11, FOR=12, BREAK=13, RETURN=14, PROC=15, TYPE=16, STRUCT=17, 
		STRING=18, LITERAL_HEX=19, LITERAL_BIN=20, LITERAL_INT=21, LITERAL_FLOAT=22, 
		LITERAL_STRING=23, DOT=24, COMMA=25, COLON=26, SEMICOLON=27, BRACKET_BEGIN=28, 
		BRACKET_END=29, BRACES_BEGIN=30, BRACES_END=31, PARENTHESES_BEGIN=32, 
		PARENTHESES_END=33, MINUS=34, PLUS=35, DIV=36, MUL=37, MOD=38, LT=39, 
		GT=40, LT_EQ=41, GT_EQ=42, EQ=43, DIFF=44, ASSIGN=45, AND=46, OR=47, NOT=48, 
		BIT_SHL=49, BIT_SHR=50, BIT_AND=51, BIT_OR=52, BIT_XOR=53, BIT_NOT=54, 
		SL_COMMENT=55, ML_COMMENT=56, ID=57;
	public static final int
		RULE_prog = 0;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'int'", "'long'", "'float'", "'double'", "'char'", "'if'", 
			"'else'", "'switch'", "'case'", "'while'", "'for'", "'break'", "'return'", 
			"'proc'", "'type'", "'struct'", "'string'", null, null, null, null, "'\".*?\"'", 
			"'.'", "','", "':'", "';'", "'['", "']'", "'{'", "'}'", "'('", "')'", 
			"'-'", "'+'", "'/'", "'*'", "'%'", "'<'", "'>'", "'>='", "'<='", "'=='", 
			"'!='", "'='", "'&&'", "'||'", "'!'", "'<<'", "'>>'", "'&'", "'|'", "'^'", 
			"'~'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "INT", "LONG", "FLOAT", "DOUBLE", "CHAR", "IF", "ELSE", "SWITCH", 
			"CASE", "WHILE", "FOR", "BREAK", "RETURN", "PROC", "TYPE", "STRUCT", 
			"STRING", "LITERAL_HEX", "LITERAL_BIN", "LITERAL_INT", "LITERAL_FLOAT", 
			"LITERAL_STRING", "DOT", "COMMA", "COLON", "SEMICOLON", "BRACKET_BEGIN", 
			"BRACKET_END", "BRACES_BEGIN", "BRACES_END", "PARENTHESES_BEGIN", "PARENTHESES_END", 
			"MINUS", "PLUS", "DIV", "MUL", "MOD", "LT", "GT", "LT_EQ", "GT_EQ", "EQ", 
			"DIFF", "ASSIGN", "AND", "OR", "NOT", "BIT_SHL", "BIT_SHR", "BIT_AND", 
			"BIT_OR", "BIT_XOR", "BIT_NOT", "SL_COMMENT", "ML_COMMENT", "ID"
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
	public static class ProgContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SimplexParser.ID, 0); }
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexParserListener ) ((SimplexParserListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2);
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

	public static final String _serializedATN =
		"\u0004\u00019\u0005\u0002\u0000\u0007\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0000\u0000\u0001\u0000\u0000\u0000\u0003\u0000\u0002\u0001\u0000"+
		"\u0000\u0000\u0002\u0003\u00059\u0000\u0000\u0003\u0001\u0001\u0000\u0000"+
		"\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}