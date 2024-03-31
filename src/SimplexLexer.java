// Generated from /home/eduardo/Documents/Projects/simplex-language/src/SimplexLexer.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class SimplexLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"WS", "INT", "LONG", "FLOAT", "DOUBLE", "CHAR", "IF", "ELSE", "SWITCH", 
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


	public SimplexLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SimplexLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000=\u017f\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007"+
		"0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u0007"+
		"5\u00026\u00076\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007"+
		":\u0002;\u0007;\u0002<\u0007<\u0001\u0000\u0004\u0000}\b\u0000\u000b\u0000"+
		"\f\u0000~\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0004\u0013\u00e8\b\u0013"+
		"\u000b\u0013\f\u0013\u00e9\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0004\u0014\u00f0\b\u0014\u000b\u0014\f\u0014\u00f1\u0001\u0015\u0004"+
		"\u0015\u00f5\b\u0015\u000b\u0015\f\u0015\u00f6\u0001\u0016\u0004\u0016"+
		"\u00fa\b\u0016\u000b\u0016\f\u0016\u00fb\u0001\u0016\u0001\u0016\u0004"+
		"\u0016\u0100\b\u0016\u000b\u0016\f\u0016\u0101\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0003\u0018\u010d\b\u0018\u0001\u0019\u0001\u0019\u0001\u001a"+
		"\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d"+
		"\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001 \u0001"+
		" \u0001!\u0001!\u0001\"\u0001\"\u0001#\u0001#\u0001$\u0001$\u0001%\u0001"+
		"%\u0001&\u0001&\u0001\'\u0001\'\u0001(\u0001(\u0001)\u0001)\u0001*\u0001"+
		"*\u0001*\u0001+\u0001+\u0001+\u0001,\u0001,\u0001,\u0001-\u0001-\u0001"+
		"-\u0001.\u0001.\u0001/\u0001/\u0001/\u00010\u00010\u00010\u00011\u0001"+
		"1\u00012\u00012\u00012\u00013\u00013\u00013\u00014\u00014\u00015\u0001"+
		"5\u00016\u00016\u00017\u00017\u00018\u00018\u00018\u00018\u00058\u0159"+
		"\b8\n8\f8\u015c\t8\u00018\u00018\u00019\u00019\u00019\u00019\u00059\u0164"+
		"\b9\n9\f9\u0167\t9\u00019\u00019\u00019\u00019\u00019\u0001:\u0001:\u0005"+
		":\u0170\b:\n:\f:\u0173\t:\u0001;\u0001;\u0001;\u0001;\u0001;\u0001<\u0001"+
		"<\u0001<\u0001<\u0001<\u0001<\u0001\u0165\u0000=\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013"+
		"\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b"+
		"7\u001c9\u001d;\u001e=\u001f? A!C\"E#G$I%K&M\'O(Q)S*U+W,Y-[.]/_0a1c2e"+
		"3g4i5k6m7o8q9s:u;w<y=\u0001\u0000\b\u0003\u0000\t\n\r\r  \u0003\u0000"+
		"09AFaf\u0001\u000001\u0001\u000009\u0006\u0000\u001b\u001b\\\\bbnnrrt"+
		"t\u0002\u0000\n\n\r\r\u0002\u0000AZaz\u0004\u000009AZ__az\u0188\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/"+
		"\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000"+
		"\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000"+
		"\u00009\u0001\u0000\u0000\u0000\u0000;\u0001\u0000\u0000\u0000\u0000="+
		"\u0001\u0000\u0000\u0000\u0000?\u0001\u0000\u0000\u0000\u0000A\u0001\u0000"+
		"\u0000\u0000\u0000C\u0001\u0000\u0000\u0000\u0000E\u0001\u0000\u0000\u0000"+
		"\u0000G\u0001\u0000\u0000\u0000\u0000I\u0001\u0000\u0000\u0000\u0000K"+
		"\u0001\u0000\u0000\u0000\u0000M\u0001\u0000\u0000\u0000\u0000O\u0001\u0000"+
		"\u0000\u0000\u0000Q\u0001\u0000\u0000\u0000\u0000S\u0001\u0000\u0000\u0000"+
		"\u0000U\u0001\u0000\u0000\u0000\u0000W\u0001\u0000\u0000\u0000\u0000Y"+
		"\u0001\u0000\u0000\u0000\u0000[\u0001\u0000\u0000\u0000\u0000]\u0001\u0000"+
		"\u0000\u0000\u0000_\u0001\u0000\u0000\u0000\u0000a\u0001\u0000\u0000\u0000"+
		"\u0000c\u0001\u0000\u0000\u0000\u0000e\u0001\u0000\u0000\u0000\u0000g"+
		"\u0001\u0000\u0000\u0000\u0000i\u0001\u0000\u0000\u0000\u0000k\u0001\u0000"+
		"\u0000\u0000\u0000m\u0001\u0000\u0000\u0000\u0000o\u0001\u0000\u0000\u0000"+
		"\u0000q\u0001\u0000\u0000\u0000\u0000s\u0001\u0000\u0000\u0000\u0000u"+
		"\u0001\u0000\u0000\u0000\u0000w\u0001\u0000\u0000\u0000\u0000y\u0001\u0000"+
		"\u0000\u0000\u0001|\u0001\u0000\u0000\u0000\u0003\u0082\u0001\u0000\u0000"+
		"\u0000\u0005\u0086\u0001\u0000\u0000\u0000\u0007\u008b\u0001\u0000\u0000"+
		"\u0000\t\u0091\u0001\u0000\u0000\u0000\u000b\u0098\u0001\u0000\u0000\u0000"+
		"\r\u009d\u0001\u0000\u0000\u0000\u000f\u00a0\u0001\u0000\u0000\u0000\u0011"+
		"\u00a5\u0001\u0000\u0000\u0000\u0013\u00ac\u0001\u0000\u0000\u0000\u0015"+
		"\u00b1\u0001\u0000\u0000\u0000\u0017\u00b7\u0001\u0000\u0000\u0000\u0019"+
		"\u00bb\u0001\u0000\u0000\u0000\u001b\u00c1\u0001\u0000\u0000\u0000\u001d"+
		"\u00c8\u0001\u0000\u0000\u0000\u001f\u00cb\u0001\u0000\u0000\u0000!\u00d0"+
		"\u0001\u0000\u0000\u0000#\u00d5\u0001\u0000\u0000\u0000%\u00dc\u0001\u0000"+
		"\u0000\u0000\'\u00e3\u0001\u0000\u0000\u0000)\u00eb\u0001\u0000\u0000"+
		"\u0000+\u00f4\u0001\u0000\u0000\u0000-\u00f9\u0001\u0000\u0000\u0000/"+
		"\u0103\u0001\u0000\u0000\u00001\u0109\u0001\u0000\u0000\u00003\u010e\u0001"+
		"\u0000\u0000\u00005\u0110\u0001\u0000\u0000\u00007\u0112\u0001\u0000\u0000"+
		"\u00009\u0114\u0001\u0000\u0000\u0000;\u0116\u0001\u0000\u0000\u0000="+
		"\u0118\u0001\u0000\u0000\u0000?\u011a\u0001\u0000\u0000\u0000A\u011c\u0001"+
		"\u0000\u0000\u0000C\u011e\u0001\u0000\u0000\u0000E\u0120\u0001\u0000\u0000"+
		"\u0000G\u0122\u0001\u0000\u0000\u0000I\u0124\u0001\u0000\u0000\u0000K"+
		"\u0126\u0001\u0000\u0000\u0000M\u0128\u0001\u0000\u0000\u0000O\u012a\u0001"+
		"\u0000\u0000\u0000Q\u012c\u0001\u0000\u0000\u0000S\u012e\u0001\u0000\u0000"+
		"\u0000U\u0130\u0001\u0000\u0000\u0000W\u0133\u0001\u0000\u0000\u0000Y"+
		"\u0136\u0001\u0000\u0000\u0000[\u0139\u0001\u0000\u0000\u0000]\u013c\u0001"+
		"\u0000\u0000\u0000_\u013e\u0001\u0000\u0000\u0000a\u0141\u0001\u0000\u0000"+
		"\u0000c\u0144\u0001\u0000\u0000\u0000e\u0146\u0001\u0000\u0000\u0000g"+
		"\u0149\u0001\u0000\u0000\u0000i\u014c\u0001\u0000\u0000\u0000k\u014e\u0001"+
		"\u0000\u0000\u0000m\u0150\u0001\u0000\u0000\u0000o\u0152\u0001\u0000\u0000"+
		"\u0000q\u0154\u0001\u0000\u0000\u0000s\u015f\u0001\u0000\u0000\u0000u"+
		"\u016d\u0001\u0000\u0000\u0000w\u0174\u0001\u0000\u0000\u0000y\u0179\u0001"+
		"\u0000\u0000\u0000{}\u0007\u0000\u0000\u0000|{\u0001\u0000\u0000\u0000"+
		"}~\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000~\u007f\u0001\u0000"+
		"\u0000\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080\u0081\u0006\u0000"+
		"\u0000\u0000\u0081\u0002\u0001\u0000\u0000\u0000\u0082\u0083\u0005i\u0000"+
		"\u0000\u0083\u0084\u0005n\u0000\u0000\u0084\u0085\u0005t\u0000\u0000\u0085"+
		"\u0004\u0001\u0000\u0000\u0000\u0086\u0087\u0005l\u0000\u0000\u0087\u0088"+
		"\u0005o\u0000\u0000\u0088\u0089\u0005n\u0000\u0000\u0089\u008a\u0005g"+
		"\u0000\u0000\u008a\u0006\u0001\u0000\u0000\u0000\u008b\u008c\u0005f\u0000"+
		"\u0000\u008c\u008d\u0005l\u0000\u0000\u008d\u008e\u0005o\u0000\u0000\u008e"+
		"\u008f\u0005a\u0000\u0000\u008f\u0090\u0005t\u0000\u0000\u0090\b\u0001"+
		"\u0000\u0000\u0000\u0091\u0092\u0005d\u0000\u0000\u0092\u0093\u0005o\u0000"+
		"\u0000\u0093\u0094\u0005u\u0000\u0000\u0094\u0095\u0005b\u0000\u0000\u0095"+
		"\u0096\u0005l\u0000\u0000\u0096\u0097\u0005e\u0000\u0000\u0097\n\u0001"+
		"\u0000\u0000\u0000\u0098\u0099\u0005c\u0000\u0000\u0099\u009a\u0005h\u0000"+
		"\u0000\u009a\u009b\u0005a\u0000\u0000\u009b\u009c\u0005r\u0000\u0000\u009c"+
		"\f\u0001\u0000\u0000\u0000\u009d\u009e\u0005i\u0000\u0000\u009e\u009f"+
		"\u0005f\u0000\u0000\u009f\u000e\u0001\u0000\u0000\u0000\u00a0\u00a1\u0005"+
		"e\u0000\u0000\u00a1\u00a2\u0005l\u0000\u0000\u00a2\u00a3\u0005s\u0000"+
		"\u0000\u00a3\u00a4\u0005e\u0000\u0000\u00a4\u0010\u0001\u0000\u0000\u0000"+
		"\u00a5\u00a6\u0005s\u0000\u0000\u00a6\u00a7\u0005w\u0000\u0000\u00a7\u00a8"+
		"\u0005i\u0000\u0000\u00a8\u00a9\u0005t\u0000\u0000\u00a9\u00aa\u0005c"+
		"\u0000\u0000\u00aa\u00ab\u0005h\u0000\u0000\u00ab\u0012\u0001\u0000\u0000"+
		"\u0000\u00ac\u00ad\u0005c\u0000\u0000\u00ad\u00ae\u0005a\u0000\u0000\u00ae"+
		"\u00af\u0005s\u0000\u0000\u00af\u00b0\u0005e\u0000\u0000\u00b0\u0014\u0001"+
		"\u0000\u0000\u0000\u00b1\u00b2\u0005w\u0000\u0000\u00b2\u00b3\u0005h\u0000"+
		"\u0000\u00b3\u00b4\u0005i\u0000\u0000\u00b4\u00b5\u0005l\u0000\u0000\u00b5"+
		"\u00b6\u0005e\u0000\u0000\u00b6\u0016\u0001\u0000\u0000\u0000\u00b7\u00b8"+
		"\u0005f\u0000\u0000\u00b8\u00b9\u0005o\u0000\u0000\u00b9\u00ba\u0005r"+
		"\u0000\u0000\u00ba\u0018\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005b\u0000"+
		"\u0000\u00bc\u00bd\u0005r\u0000\u0000\u00bd\u00be\u0005e\u0000\u0000\u00be"+
		"\u00bf\u0005a\u0000\u0000\u00bf\u00c0\u0005k\u0000\u0000\u00c0\u001a\u0001"+
		"\u0000\u0000\u0000\u00c1\u00c2\u0005r\u0000\u0000\u00c2\u00c3\u0005e\u0000"+
		"\u0000\u00c3\u00c4\u0005t\u0000\u0000\u00c4\u00c5\u0005u\u0000\u0000\u00c5"+
		"\u00c6\u0005r\u0000\u0000\u00c6\u00c7\u0005n\u0000\u0000\u00c7\u001c\u0001"+
		"\u0000\u0000\u0000\u00c8\u00c9\u0005d\u0000\u0000\u00c9\u00ca\u0005o\u0000"+
		"\u0000\u00ca\u001e\u0001\u0000\u0000\u0000\u00cb\u00cc\u0005p\u0000\u0000"+
		"\u00cc\u00cd\u0005r\u0000\u0000\u00cd\u00ce\u0005o\u0000\u0000\u00ce\u00cf"+
		"\u0005c\u0000\u0000\u00cf \u0001\u0000\u0000\u0000\u00d0\u00d1\u0005t"+
		"\u0000\u0000\u00d1\u00d2\u0005y\u0000\u0000\u00d2\u00d3\u0005p\u0000\u0000"+
		"\u00d3\u00d4\u0005e\u0000\u0000\u00d4\"\u0001\u0000\u0000\u0000\u00d5"+
		"\u00d6\u0005s\u0000\u0000\u00d6\u00d7\u0005t\u0000\u0000\u00d7\u00d8\u0005"+
		"r\u0000\u0000\u00d8\u00d9\u0005u\u0000\u0000\u00d9\u00da\u0005c\u0000"+
		"\u0000\u00da\u00db\u0005t\u0000\u0000\u00db$\u0001\u0000\u0000\u0000\u00dc"+
		"\u00dd\u0005s\u0000\u0000\u00dd\u00de\u0005t\u0000\u0000\u00de\u00df\u0005"+
		"r\u0000\u0000\u00df\u00e0\u0005i\u0000\u0000\u00e0\u00e1\u0005n\u0000"+
		"\u0000\u00e1\u00e2\u0005g\u0000\u0000\u00e2&\u0001\u0000\u0000\u0000\u00e3"+
		"\u00e4\u00050\u0000\u0000\u00e4\u00e5\u0005x\u0000\u0000\u00e5\u00e7\u0001"+
		"\u0000\u0000\u0000\u00e6\u00e8\u0007\u0001\u0000\u0000\u00e7\u00e6\u0001"+
		"\u0000\u0000\u0000\u00e8\u00e9\u0001\u0000\u0000\u0000\u00e9\u00e7\u0001"+
		"\u0000\u0000\u0000\u00e9\u00ea\u0001\u0000\u0000\u0000\u00ea(\u0001\u0000"+
		"\u0000\u0000\u00eb\u00ec\u00050\u0000\u0000\u00ec\u00ed\u0005b\u0000\u0000"+
		"\u00ed\u00ef\u0001\u0000\u0000\u0000\u00ee\u00f0\u0007\u0002\u0000\u0000"+
		"\u00ef\u00ee\u0001\u0000\u0000\u0000\u00f0\u00f1\u0001\u0000\u0000\u0000"+
		"\u00f1\u00ef\u0001\u0000\u0000\u0000\u00f1\u00f2\u0001\u0000\u0000\u0000"+
		"\u00f2*\u0001\u0000\u0000\u0000\u00f3\u00f5\u0007\u0003\u0000\u0000\u00f4"+
		"\u00f3\u0001\u0000\u0000\u0000\u00f5\u00f6\u0001\u0000\u0000\u0000\u00f6"+
		"\u00f4\u0001\u0000\u0000\u0000\u00f6\u00f7\u0001\u0000\u0000\u0000\u00f7"+
		",\u0001\u0000\u0000\u0000\u00f8\u00fa\u0007\u0003\u0000\u0000\u00f9\u00f8"+
		"\u0001\u0000\u0000\u0000\u00fa\u00fb\u0001\u0000\u0000\u0000\u00fb\u00f9"+
		"\u0001\u0000\u0000\u0000\u00fb\u00fc\u0001\u0000\u0000\u0000\u00fc\u00fd"+
		"\u0001\u0000\u0000\u0000\u00fd\u00ff\u0005.\u0000\u0000\u00fe\u0100\u0007"+
		"\u0003\u0000\u0000\u00ff\u00fe\u0001\u0000\u0000\u0000\u0100\u0101\u0001"+
		"\u0000\u0000\u0000\u0101\u00ff\u0001\u0000\u0000\u0000\u0101\u0102\u0001"+
		"\u0000\u0000\u0000\u0102.\u0001\u0000\u0000\u0000\u0103\u0104\u0005\""+
		"\u0000\u0000\u0104\u0105\u0005.\u0000\u0000\u0105\u0106\u0005*\u0000\u0000"+
		"\u0106\u0107\u0005?\u0000\u0000\u0107\u0108\u0005\"\u0000\u0000\u0108"+
		"0\u0001\u0000\u0000\u0000\u0109\u010c\u0005\\\u0000\u0000\u010a\u010d"+
		"\u0007\u0004\u0000\u0000\u010b\u010d\t\u0000\u0000\u0000\u010c\u010a\u0001"+
		"\u0000\u0000\u0000\u010c\u010b\u0001\u0000\u0000\u0000\u010d2\u0001\u0000"+
		"\u0000\u0000\u010e\u010f\u0005.\u0000\u0000\u010f4\u0001\u0000\u0000\u0000"+
		"\u0110\u0111\u0005,\u0000\u0000\u01116\u0001\u0000\u0000\u0000\u0112\u0113"+
		"\u0005:\u0000\u0000\u01138\u0001\u0000\u0000\u0000\u0114\u0115\u0005;"+
		"\u0000\u0000\u0115:\u0001\u0000\u0000\u0000\u0116\u0117\u0005[\u0000\u0000"+
		"\u0117<\u0001\u0000\u0000\u0000\u0118\u0119\u0005]\u0000\u0000\u0119>"+
		"\u0001\u0000\u0000\u0000\u011a\u011b\u0005{\u0000\u0000\u011b@\u0001\u0000"+
		"\u0000\u0000\u011c\u011d\u0005}\u0000\u0000\u011dB\u0001\u0000\u0000\u0000"+
		"\u011e\u011f\u0005(\u0000\u0000\u011fD\u0001\u0000\u0000\u0000\u0120\u0121"+
		"\u0005)\u0000\u0000\u0121F\u0001\u0000\u0000\u0000\u0122\u0123\u0005-"+
		"\u0000\u0000\u0123H\u0001\u0000\u0000\u0000\u0124\u0125\u0005+\u0000\u0000"+
		"\u0125J\u0001\u0000\u0000\u0000\u0126\u0127\u0005/\u0000\u0000\u0127L"+
		"\u0001\u0000\u0000\u0000\u0128\u0129\u0005*\u0000\u0000\u0129N\u0001\u0000"+
		"\u0000\u0000\u012a\u012b\u0005%\u0000\u0000\u012bP\u0001\u0000\u0000\u0000"+
		"\u012c\u012d\u0005<\u0000\u0000\u012dR\u0001\u0000\u0000\u0000\u012e\u012f"+
		"\u0005>\u0000\u0000\u012fT\u0001\u0000\u0000\u0000\u0130\u0131\u0005>"+
		"\u0000\u0000\u0131\u0132\u0005=\u0000\u0000\u0132V\u0001\u0000\u0000\u0000"+
		"\u0133\u0134\u0005<\u0000\u0000\u0134\u0135\u0005=\u0000\u0000\u0135X"+
		"\u0001\u0000\u0000\u0000\u0136\u0137\u0005=\u0000\u0000\u0137\u0138\u0005"+
		"=\u0000\u0000\u0138Z\u0001\u0000\u0000\u0000\u0139\u013a\u0005!\u0000"+
		"\u0000\u013a\u013b\u0005=\u0000\u0000\u013b\\\u0001\u0000\u0000\u0000"+
		"\u013c\u013d\u0005=\u0000\u0000\u013d^\u0001\u0000\u0000\u0000\u013e\u013f"+
		"\u0005&\u0000\u0000\u013f\u0140\u0005&\u0000\u0000\u0140`\u0001\u0000"+
		"\u0000\u0000\u0141\u0142\u0005|\u0000\u0000\u0142\u0143\u0005|\u0000\u0000"+
		"\u0143b\u0001\u0000\u0000\u0000\u0144\u0145\u0005!\u0000\u0000\u0145d"+
		"\u0001\u0000\u0000\u0000\u0146\u0147\u0005<\u0000\u0000\u0147\u0148\u0005"+
		"<\u0000\u0000\u0148f\u0001\u0000\u0000\u0000\u0149\u014a\u0005>\u0000"+
		"\u0000\u014a\u014b\u0005>\u0000\u0000\u014bh\u0001\u0000\u0000\u0000\u014c"+
		"\u014d\u0005&\u0000\u0000\u014dj\u0001\u0000\u0000\u0000\u014e\u014f\u0005"+
		"|\u0000\u0000\u014fl\u0001\u0000\u0000\u0000\u0150\u0151\u0005^\u0000"+
		"\u0000\u0151n\u0001\u0000\u0000\u0000\u0152\u0153\u0005~\u0000\u0000\u0153"+
		"p\u0001\u0000\u0000\u0000\u0154\u0155\u0005/\u0000\u0000\u0155\u0156\u0005"+
		"/\u0000\u0000\u0156\u015a\u0001\u0000\u0000\u0000\u0157\u0159\b\u0005"+
		"\u0000\u0000\u0158\u0157\u0001\u0000\u0000\u0000\u0159\u015c\u0001\u0000"+
		"\u0000\u0000\u015a\u0158\u0001\u0000\u0000\u0000\u015a\u015b\u0001\u0000"+
		"\u0000\u0000\u015b\u015d\u0001\u0000\u0000\u0000\u015c\u015a\u0001\u0000"+
		"\u0000\u0000\u015d\u015e\u00068\u0000\u0000\u015er\u0001\u0000\u0000\u0000"+
		"\u015f\u0160\u0005/\u0000\u0000\u0160\u0161\u0005*\u0000\u0000\u0161\u0165"+
		"\u0001\u0000\u0000\u0000\u0162\u0164\t\u0000\u0000\u0000\u0163\u0162\u0001"+
		"\u0000\u0000\u0000\u0164\u0167\u0001\u0000\u0000\u0000\u0165\u0166\u0001"+
		"\u0000\u0000\u0000\u0165\u0163\u0001\u0000\u0000\u0000\u0166\u0168\u0001"+
		"\u0000\u0000\u0000\u0167\u0165\u0001\u0000\u0000\u0000\u0168\u0169\u0005"+
		"*\u0000\u0000\u0169\u016a\u0005/\u0000\u0000\u016a\u016b\u0001\u0000\u0000"+
		"\u0000\u016b\u016c\u00069\u0000\u0000\u016ct\u0001\u0000\u0000\u0000\u016d"+
		"\u0171\u0007\u0006\u0000\u0000\u016e\u0170\u0007\u0007\u0000\u0000\u016f"+
		"\u016e\u0001\u0000\u0000\u0000\u0170\u0173\u0001\u0000\u0000\u0000\u0171"+
		"\u016f\u0001\u0000\u0000\u0000\u0171\u0172\u0001\u0000\u0000\u0000\u0172"+
		"v\u0001\u0000\u0000\u0000\u0173\u0171\u0001\u0000\u0000\u0000\u0174\u0175"+
		"\u0005r\u0000\u0000\u0175\u0176\u0005e\u0000\u0000\u0176\u0177\u0005a"+
		"\u0000\u0000\u0177\u0178\u0005d\u0000\u0000\u0178x\u0001\u0000\u0000\u0000"+
		"\u0179\u017a\u0005w\u0000\u0000\u017a\u017b\u0005r\u0000\u0000\u017b\u017c"+
		"\u0005i\u0000\u0000\u017c\u017d\u0005t\u0000\u0000\u017d\u017e\u0005e"+
		"\u0000\u0000\u017ez\u0001\u0000\u0000\u0000\u000b\u0000~\u00e9\u00f1\u00f6"+
		"\u00fb\u0101\u010c\u015a\u0165\u0171\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}