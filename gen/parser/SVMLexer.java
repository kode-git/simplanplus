// Generated from /Users/kode/Desktop/project-compiler-antlr4/main/src/parser/SVM.g4 by ANTLR 4.9.1
package parser;

import java.util.HashMap;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SVMLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PUSH=1, POP=2, ADD=3, ADDR=4, SUB=5, SUBR=6, MULT=7, MULTR=8, DIV=9, DIVR=10, 
		BRANCHEQ=11, BRANCHLESSEQ=12, BRANCHLESS=13, AND=14, ANDR=15, OR=16, ORR=17, 
		NOT=18, NOTR=19, STOREW=20, LOADW=21, BRANCH=22, JS=23, LOADT=24, STORET=25, 
		LOADA=26, STOREA=27, LIA=28, LOADRA=29, STORERA=30, LOADRV=31, STORERV=32, 
		LOADFP=33, STOREFP=34, COPYFP=35, LOADHP=36, STOREHP=37, PRINT=38, PRINTT=39, 
		PRINTA=40, HALT=41, COL=42, LABEL=43, NUMBER=44, WHITESP=45, ERR=46;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PUSH", "POP", "ADD", "ADDR", "SUB", "SUBR", "MULT", "MULTR", "DIV", 
			"DIVR", "BRANCHEQ", "BRANCHLESSEQ", "BRANCHLESS", "AND", "ANDR", "OR", 
			"ORR", "NOT", "NOTR", "STOREW", "LOADW", "BRANCH", "JS", "LOADT", "STORET", 
			"LOADA", "STOREA", "LIA", "LOADRA", "STORERA", "LOADRV", "STORERV", "LOADFP", 
			"STOREFP", "COPYFP", "LOADHP", "STOREHP", "PRINT", "PRINTT", "PRINTA", 
			"HALT", "COL", "LABEL", "NUMBER", "WHITESP", "ERR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'push'", "'pop'", "'add'", "'addr'", "'sub'", "'subr'", "'mult'", 
			"'multr'", "'div'", "'divr'", "'beq'", "'bleq'", "'bless'", "'and'", 
			"'andr'", "'or'", "'orr'", "'not'", "'notr'", "'sw'", "'lw'", "'b'", 
			"'js'", "'lt'", "'st'", "'la'", "'sa'", "'lia'", "'lra'", "'sra'", "'lrv'", 
			"'srv'", "'lfp'", "'sfp'", "'cfp'", "'lhp'", "'shp'", "'print'", "'printt'", 
			"'printa'", "'halt'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PUSH", "POP", "ADD", "ADDR", "SUB", "SUBR", "MULT", "MULTR", "DIV", 
			"DIVR", "BRANCHEQ", "BRANCHLESSEQ", "BRANCHLESS", "AND", "ANDR", "OR", 
			"ORR", "NOT", "NOTR", "STOREW", "LOADW", "BRANCH", "JS", "LOADT", "STORET", 
			"LOADA", "STOREA", "LIA", "LOADRA", "STORERA", "LOADRV", "STORERV", "LOADFP", 
			"STOREFP", "COPYFP", "LOADHP", "STOREHP", "PRINT", "PRINTT", "PRINTA", 
			"HALT", "COL", "LABEL", "NUMBER", "WHITESP", "ERR"
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


	public int lexicalErrors=0;


	public SVMLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SVM.g4"; }

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

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 45:
			ERR_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void ERR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 System.err.println("Invalid char: "+ getText()); lexicalErrors++;  
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\60\u0130\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25"+
		"\3\25\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3!\3!\3!\3!\3\"\3\"\3\"\3\"\3#\3"+
		"#\3#\3#\3$\3$\3$\3$\3%\3%\3%\3%\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3"+
		"(\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3+\3+\3,\3,\7"+
		",\u0113\n,\f,\16,\u0116\13,\3-\3-\5-\u011a\n-\3-\3-\7-\u011e\n-\f-\16"+
		"-\u0121\13-\5-\u0123\n-\3.\6.\u0126\n.\r.\16.\u0127\3.\3.\3/\3/\3/\3/"+
		"\3/\2\2\60\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
		"\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67"+
		"\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60\3\2\5\4\2C\\c|\5\2\62"+
		";C\\c|\5\2\13\f\17\17\"\"\2\u0134\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2"+
		"\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2"+
		"O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3"+
		"\2\2\2\2]\3\2\2\2\3_\3\2\2\2\5d\3\2\2\2\7h\3\2\2\2\tl\3\2\2\2\13q\3\2"+
		"\2\2\ru\3\2\2\2\17z\3\2\2\2\21\177\3\2\2\2\23\u0085\3\2\2\2\25\u0089\3"+
		"\2\2\2\27\u008e\3\2\2\2\31\u0092\3\2\2\2\33\u0097\3\2\2\2\35\u009d\3\2"+
		"\2\2\37\u00a1\3\2\2\2!\u00a6\3\2\2\2#\u00a9\3\2\2\2%\u00ad\3\2\2\2\'\u00b1"+
		"\3\2\2\2)\u00b6\3\2\2\2+\u00b9\3\2\2\2-\u00bc\3\2\2\2/\u00be\3\2\2\2\61"+
		"\u00c1\3\2\2\2\63\u00c4\3\2\2\2\65\u00c7\3\2\2\2\67\u00ca\3\2\2\29\u00cd"+
		"\3\2\2\2;\u00d1\3\2\2\2=\u00d5\3\2\2\2?\u00d9\3\2\2\2A\u00dd\3\2\2\2C"+
		"\u00e1\3\2\2\2E\u00e5\3\2\2\2G\u00e9\3\2\2\2I\u00ed\3\2\2\2K\u00f1\3\2"+
		"\2\2M\u00f5\3\2\2\2O\u00fb\3\2\2\2Q\u0102\3\2\2\2S\u0109\3\2\2\2U\u010e"+
		"\3\2\2\2W\u0110\3\2\2\2Y\u0122\3\2\2\2[\u0125\3\2\2\2]\u012b\3\2\2\2_"+
		"`\7r\2\2`a\7w\2\2ab\7u\2\2bc\7j\2\2c\4\3\2\2\2de\7r\2\2ef\7q\2\2fg\7r"+
		"\2\2g\6\3\2\2\2hi\7c\2\2ij\7f\2\2jk\7f\2\2k\b\3\2\2\2lm\7c\2\2mn\7f\2"+
		"\2no\7f\2\2op\7t\2\2p\n\3\2\2\2qr\7u\2\2rs\7w\2\2st\7d\2\2t\f\3\2\2\2"+
		"uv\7u\2\2vw\7w\2\2wx\7d\2\2xy\7t\2\2y\16\3\2\2\2z{\7o\2\2{|\7w\2\2|}\7"+
		"n\2\2}~\7v\2\2~\20\3\2\2\2\177\u0080\7o\2\2\u0080\u0081\7w\2\2\u0081\u0082"+
		"\7n\2\2\u0082\u0083\7v\2\2\u0083\u0084\7t\2\2\u0084\22\3\2\2\2\u0085\u0086"+
		"\7f\2\2\u0086\u0087\7k\2\2\u0087\u0088\7x\2\2\u0088\24\3\2\2\2\u0089\u008a"+
		"\7f\2\2\u008a\u008b\7k\2\2\u008b\u008c\7x\2\2\u008c\u008d\7t\2\2\u008d"+
		"\26\3\2\2\2\u008e\u008f\7d\2\2\u008f\u0090\7g\2\2\u0090\u0091\7s\2\2\u0091"+
		"\30\3\2\2\2\u0092\u0093\7d\2\2\u0093\u0094\7n\2\2\u0094\u0095\7g\2\2\u0095"+
		"\u0096\7s\2\2\u0096\32\3\2\2\2\u0097\u0098\7d\2\2\u0098\u0099\7n\2\2\u0099"+
		"\u009a\7g\2\2\u009a\u009b\7u\2\2\u009b\u009c\7u\2\2\u009c\34\3\2\2\2\u009d"+
		"\u009e\7c\2\2\u009e\u009f\7p\2\2\u009f\u00a0\7f\2\2\u00a0\36\3\2\2\2\u00a1"+
		"\u00a2\7c\2\2\u00a2\u00a3\7p\2\2\u00a3\u00a4\7f\2\2\u00a4\u00a5\7t\2\2"+
		"\u00a5 \3\2\2\2\u00a6\u00a7\7q\2\2\u00a7\u00a8\7t\2\2\u00a8\"\3\2\2\2"+
		"\u00a9\u00aa\7q\2\2\u00aa\u00ab\7t\2\2\u00ab\u00ac\7t\2\2\u00ac$\3\2\2"+
		"\2\u00ad\u00ae\7p\2\2\u00ae\u00af\7q\2\2\u00af\u00b0\7v\2\2\u00b0&\3\2"+
		"\2\2\u00b1\u00b2\7p\2\2\u00b2\u00b3\7q\2\2\u00b3\u00b4\7v\2\2\u00b4\u00b5"+
		"\7t\2\2\u00b5(\3\2\2\2\u00b6\u00b7\7u\2\2\u00b7\u00b8\7y\2\2\u00b8*\3"+
		"\2\2\2\u00b9\u00ba\7n\2\2\u00ba\u00bb\7y\2\2\u00bb,\3\2\2\2\u00bc\u00bd"+
		"\7d\2\2\u00bd.\3\2\2\2\u00be\u00bf\7l\2\2\u00bf\u00c0\7u\2\2\u00c0\60"+
		"\3\2\2\2\u00c1\u00c2\7n\2\2\u00c2\u00c3\7v\2\2\u00c3\62\3\2\2\2\u00c4"+
		"\u00c5\7u\2\2\u00c5\u00c6\7v\2\2\u00c6\64\3\2\2\2\u00c7\u00c8\7n\2\2\u00c8"+
		"\u00c9\7c\2\2\u00c9\66\3\2\2\2\u00ca\u00cb\7u\2\2\u00cb\u00cc\7c\2\2\u00cc"+
		"8\3\2\2\2\u00cd\u00ce\7n\2\2\u00ce\u00cf\7k\2\2\u00cf\u00d0\7c\2\2\u00d0"+
		":\3\2\2\2\u00d1\u00d2\7n\2\2\u00d2\u00d3\7t\2\2\u00d3\u00d4\7c\2\2\u00d4"+
		"<\3\2\2\2\u00d5\u00d6\7u\2\2\u00d6\u00d7\7t\2\2\u00d7\u00d8\7c\2\2\u00d8"+
		">\3\2\2\2\u00d9\u00da\7n\2\2\u00da\u00db\7t\2\2\u00db\u00dc\7x\2\2\u00dc"+
		"@\3\2\2\2\u00dd\u00de\7u\2\2\u00de\u00df\7t\2\2\u00df\u00e0\7x\2\2\u00e0"+
		"B\3\2\2\2\u00e1\u00e2\7n\2\2\u00e2\u00e3\7h\2\2\u00e3\u00e4\7r\2\2\u00e4"+
		"D\3\2\2\2\u00e5\u00e6\7u\2\2\u00e6\u00e7\7h\2\2\u00e7\u00e8\7r\2\2\u00e8"+
		"F\3\2\2\2\u00e9\u00ea\7e\2\2\u00ea\u00eb\7h\2\2\u00eb\u00ec\7r\2\2\u00ec"+
		"H\3\2\2\2\u00ed\u00ee\7n\2\2\u00ee\u00ef\7j\2\2\u00ef\u00f0\7r\2\2\u00f0"+
		"J\3\2\2\2\u00f1\u00f2\7u\2\2\u00f2\u00f3\7j\2\2\u00f3\u00f4\7r\2\2\u00f4"+
		"L\3\2\2\2\u00f5\u00f6\7r\2\2\u00f6\u00f7\7t\2\2\u00f7\u00f8\7k\2\2\u00f8"+
		"\u00f9\7p\2\2\u00f9\u00fa\7v\2\2\u00faN\3\2\2\2\u00fb\u00fc\7r\2\2\u00fc"+
		"\u00fd\7t\2\2\u00fd\u00fe\7k\2\2\u00fe\u00ff\7p\2\2\u00ff\u0100\7v\2\2"+
		"\u0100\u0101\7v\2\2\u0101P\3\2\2\2\u0102\u0103\7r\2\2\u0103\u0104\7t\2"+
		"\2\u0104\u0105\7k\2\2\u0105\u0106\7p\2\2\u0106\u0107\7v\2\2\u0107\u0108"+
		"\7c\2\2\u0108R\3\2\2\2\u0109\u010a\7j\2\2\u010a\u010b\7c\2\2\u010b\u010c"+
		"\7n\2\2\u010c\u010d\7v\2\2\u010dT\3\2\2\2\u010e\u010f\7<\2\2\u010fV\3"+
		"\2\2\2\u0110\u0114\t\2\2\2\u0111\u0113\t\3\2\2\u0112\u0111\3\2\2\2\u0113"+
		"\u0116\3\2\2\2\u0114\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115X\3\2\2\2"+
		"\u0116\u0114\3\2\2\2\u0117\u0123\7\62\2\2\u0118\u011a\7/\2\2\u0119\u0118"+
		"\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u011f\4\63;\2\u011c"+
		"\u011e\4\62;\2\u011d\u011c\3\2\2\2\u011e\u0121\3\2\2\2\u011f\u011d\3\2"+
		"\2\2\u011f\u0120\3\2\2\2\u0120\u0123\3\2\2\2\u0121\u011f\3\2\2\2\u0122"+
		"\u0117\3\2\2\2\u0122\u0119\3\2\2\2\u0123Z\3\2\2\2\u0124\u0126\t\4\2\2"+
		"\u0125\u0124\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128"+
		"\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u012a\b.\2\2\u012a\\\3\2\2\2\u012b"+
		"\u012c\13\2\2\2\u012c\u012d\b/\3\2\u012d\u012e\3\2\2\2\u012e\u012f\b/"+
		"\2\2\u012f^\3\2\2\2\b\2\u0114\u0119\u011f\u0122\u0127\4\2\3\2\3/\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}