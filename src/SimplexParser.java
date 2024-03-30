// Generated from src//Simplex.g4 by ANTLR 4.13.1
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
		T__0=1, WS=2, ID=3, INT=4, LONG=5, FLOAT=6, DOUBLE=7, CHAR=8, IF=9, ELSE=10, 
		SWITCH=11, CASE=12, WHILE=13, FOR=14, BREAK=15, RETURN=16, PROC=17, TYPE=18, 
		STRUCT=19, STRING=20, LITERAL_HEX=21, LITERAL_BIN=22, LITERAL_INT=23, 
		LITERAL_FLOAT=24, LITERAL_STRING=25, DOT=26, COMMA=27, COLON=28, SEMICOLON=29, 
		BRACKET_BEGIN=30, BRACKET_END=31, BRACES_BEGIN=32, BRACES_END=33, PARENTHESES_BEGIN=34, 
		PARENTHESES_END=35, MINUS=36, PLUS=37, DIV=38, MUL=39, MOD=40, LT=41, 
		GT=42, LT_EQ=43, GT_EQ=44, EQ=45, DIFF=46, ASSIGN=47, AND=48, OR=49, NOT=50, 
		BIT_SHL=51, BIT_SHR=52, BIT_AND=53, BIT_OR=54, BIT_XOR=55, BIT_NOT=56, 
		SL_COMMENT=57, ML_COMMENT=58;
	public static final int
		RULE_r = 0;
	private static String[] makeRuleNames() {
		return new String[] {
			"r"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'hello'", null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "'\".*?\"'", "'.'", "','", "':'", "';'", "'['", "']'", "'{'", 
			"'}'", "'('", "')'", "'-'", "'+'", "'/'", "'*'", "'%'", "'<'", "'>'", 
			"'>='", "'<='", "'=='", "'!='", "'='", "'&&'", "'||'", "'!'", "'<<'", 
			"'>>'", "'&'", "'|'", "'^'", "'~'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "WS", "ID", "INT", "LONG", "FLOAT", "DOUBLE", "CHAR", "IF", 
			"ELSE", "SWITCH", "CASE", "WHILE", "FOR", "BREAK", "RETURN", "PROC", 
			"TYPE", "STRUCT", "STRING", "LITERAL_HEX", "LITERAL_BIN", "LITERAL_INT", 
			"LITERAL_FLOAT", "LITERAL_STRING", "DOT", "COMMA", "COLON", "SEMICOLON", 
			"BRACKET_BEGIN", "BRACKET_END", "BRACES_BEGIN", "BRACES_END", "PARENTHESES_BEGIN", 
			"PARENTHESES_END", "MINUS", "PLUS", "DIV", "MUL", "MOD", "LT", "GT", 
			"LT_EQ", "GT_EQ", "EQ", "DIFF", "ASSIGN", "AND", "OR", "NOT", "BIT_SHL", 
			"BIT_SHR", "BIT_AND", "BIT_OR", "BIT_XOR", "BIT_NOT", "SL_COMMENT", "ML_COMMENT"
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
	public String getGrammarFileName() { return "Simplex.g4"; }

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
	public static class RContext extends ParserRuleContext {
		public RContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_r; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexListener ) ((SimplexListener)listener).enterR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplexListener ) ((SimplexListener)listener).exitR(this);
		}
	}

	public final RContext r() throws RecognitionException {
		RContext _localctx = new RContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_r);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2);
			match(T__0);
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
		"\u0004\u0001:\u0005\u0002\u0000\u0007\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0000\u0000\u0001\u0000\u0000\u0000\u0003\u0000\u0002\u0001\u0000"+
		"\u0000\u0000\u0002\u0003\u0005\u0001\u0000\u0000\u0003\u0001\u0001\u0000"+
		"\u0000\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}