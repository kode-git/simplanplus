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
		PUSH=1, POP=2, ADD=3, SUB=4, MULT=5, DIV=6, BRANCHEQ=7, BRANCHLESSEQ=8, 
		BRANCHLESS=9, AND=10, OR=11, NOT=12, STOREW=13, LOADW=14, SWR1=15, LWR1=16, 
		SWSP=17, LWSP=18, CRA=19, JR=20, BRANCH=21, LOADRA=22, STORERA=23, LOADRV=24, 
		STORERV=25, LOADFP=26, STOREFP=27, LOADHP=28, STOREHP=29, STORER1=30, 
		LOADR1=31, STORER2=32, LOADR2=33, STOREAL=34, LOADAL=35, LIR1=36, LIR2=37, 
		PRINT=38, COPYFP=39, COPYAL=40, HALT=41, COL=42, LABEL=43, NUMBER=44, 
		WHITESP=45, ERR=46;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PUSH", "POP", "ADD", "SUB", "MULT", "DIV", "BRANCHEQ", "BRANCHLESSEQ", 
			"BRANCHLESS", "AND", "OR", "NOT", "STOREW", "LOADW", "SWR1", "LWR1", 
			"SWSP", "LWSP", "CRA", "JR", "BRANCH", "LOADRA", "STORERA", "LOADRV", 
			"STORERV", "LOADFP", "STOREFP", "LOADHP", "STOREHP", "STORER1", "LOADR1", 
			"STORER2", "LOADR2", "STOREAL", "LOADAL", "LIR1", "LIR2", "PRINT", "COPYFP", 
			"COPYAL", "HALT", "COL", "LABEL", "NUMBER", "WHITESP", "ERR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'push'", "'pop'", "'add'", "'sub'", "'mult'", "'div'", "'beq'", 
			"'bleq'", "'bless'", "'and'", "'or'", "'not'", "'sw'", "'lw'", "'sw1'", 
			"'lw1'", "'swsp'", "'lwsp'", "'cra'", "'jr'", "'b'", "'lra'", "'sra'", 
			"'lrv'", "'srv'", "'lfp'", "'sfp'", "'lhp'", "'shp'", "'sr1'", "'lr1'", 
			"'sr2'", "'lr2'", "'sal'", "'lal'", "'lir1'", "'lir2'", "'print'", "'cfp'", 
			"'cal'", "'halt'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PUSH", "POP", "ADD", "SUB", "MULT", "DIV", "BRANCHEQ", "BRANCHLESSEQ", 
			"BRANCHLESS", "AND", "OR", "NOT", "STOREW", "LOADW", "SWR1", "LWR1", 
			"SWSP", "LWSP", "CRA", "JR", "BRANCH", "LOADRA", "STORERA", "LOADRV", 
			"STORERV", "LOADFP", "STOREFP", "LOADHP", "STOREHP", "STORER1", "LOADR1", 
			"STORER2", "LOADR2", "STOREAL", "LOADAL", "LIR1", "LIR2", "PRINT", "COPYFP", 
			"COPYAL", "HALT", "COL", "LABEL", "NUMBER", "WHITESP", "ERR"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\60\u012b\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3"+
		"\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3"+
		"\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3"+
		"\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3"+
		"\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3!\3!\3!\3!\3\"\3\"\3\"\3\"\3#\3#"+
		"\3#\3#\3$\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3"+
		"\'\3(\3(\3(\3(\3)\3)\3)\3)\3*\3*\3*\3*\3*\3+\3+\3,\3,\7,\u010e\n,\f,\16"+
		",\u0111\13,\3-\3-\5-\u0115\n-\3-\3-\7-\u0119\n-\f-\16-\u011c\13-\5-\u011e"+
		"\n-\3.\6.\u0121\n.\r.\16.\u0122\3.\3.\3/\3/\3/\3/\3/\2\2\60\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C"+
		"#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60\3\2\5\4\2C\\c|\5\2\62;C\\c|\5\2\13\f\17"+
		"\17\"\"\2\u012f\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3"+
		"\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2"+
		"\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\3"+
		"_\3\2\2\2\5d\3\2\2\2\7h\3\2\2\2\tl\3\2\2\2\13p\3\2\2\2\ru\3\2\2\2\17y"+
		"\3\2\2\2\21}\3\2\2\2\23\u0082\3\2\2\2\25\u0088\3\2\2\2\27\u008c\3\2\2"+
		"\2\31\u008f\3\2\2\2\33\u0093\3\2\2\2\35\u0096\3\2\2\2\37\u0099\3\2\2\2"+
		"!\u009d\3\2\2\2#\u00a1\3\2\2\2%\u00a6\3\2\2\2\'\u00ab\3\2\2\2)\u00af\3"+
		"\2\2\2+\u00b2\3\2\2\2-\u00b4\3\2\2\2/\u00b8\3\2\2\2\61\u00bc\3\2\2\2\63"+
		"\u00c0\3\2\2\2\65\u00c4\3\2\2\2\67\u00c8\3\2\2\29\u00cc\3\2\2\2;\u00d0"+
		"\3\2\2\2=\u00d4\3\2\2\2?\u00d8\3\2\2\2A\u00dc\3\2\2\2C\u00e0\3\2\2\2E"+
		"\u00e4\3\2\2\2G\u00e8\3\2\2\2I\u00ec\3\2\2\2K\u00f1\3\2\2\2M\u00f6\3\2"+
		"\2\2O\u00fc\3\2\2\2Q\u0100\3\2\2\2S\u0104\3\2\2\2U\u0109\3\2\2\2W\u010b"+
		"\3\2\2\2Y\u011d\3\2\2\2[\u0120\3\2\2\2]\u0126\3\2\2\2_`\7r\2\2`a\7w\2"+
		"\2ab\7u\2\2bc\7j\2\2c\4\3\2\2\2de\7r\2\2ef\7q\2\2fg\7r\2\2g\6\3\2\2\2"+
		"hi\7c\2\2ij\7f\2\2jk\7f\2\2k\b\3\2\2\2lm\7u\2\2mn\7w\2\2no\7d\2\2o\n\3"+
		"\2\2\2pq\7o\2\2qr\7w\2\2rs\7n\2\2st\7v\2\2t\f\3\2\2\2uv\7f\2\2vw\7k\2"+
		"\2wx\7x\2\2x\16\3\2\2\2yz\7d\2\2z{\7g\2\2{|\7s\2\2|\20\3\2\2\2}~\7d\2"+
		"\2~\177\7n\2\2\177\u0080\7g\2\2\u0080\u0081\7s\2\2\u0081\22\3\2\2\2\u0082"+
		"\u0083\7d\2\2\u0083\u0084\7n\2\2\u0084\u0085\7g\2\2\u0085\u0086\7u\2\2"+
		"\u0086\u0087\7u\2\2\u0087\24\3\2\2\2\u0088\u0089\7c\2\2\u0089\u008a\7"+
		"p\2\2\u008a\u008b\7f\2\2\u008b\26\3\2\2\2\u008c\u008d\7q\2\2\u008d\u008e"+
		"\7t\2\2\u008e\30\3\2\2\2\u008f\u0090\7p\2\2\u0090\u0091\7q\2\2\u0091\u0092"+
		"\7v\2\2\u0092\32\3\2\2\2\u0093\u0094\7u\2\2\u0094\u0095\7y\2\2\u0095\34"+
		"\3\2\2\2\u0096\u0097\7n\2\2\u0097\u0098\7y\2\2\u0098\36\3\2\2\2\u0099"+
		"\u009a\7u\2\2\u009a\u009b\7y\2\2\u009b\u009c\7\63\2\2\u009c \3\2\2\2\u009d"+
		"\u009e\7n\2\2\u009e\u009f\7y\2\2\u009f\u00a0\7\63\2\2\u00a0\"\3\2\2\2"+
		"\u00a1\u00a2\7u\2\2\u00a2\u00a3\7y\2\2\u00a3\u00a4\7u\2\2\u00a4\u00a5"+
		"\7r\2\2\u00a5$\3\2\2\2\u00a6\u00a7\7n\2\2\u00a7\u00a8\7y\2\2\u00a8\u00a9"+
		"\7u\2\2\u00a9\u00aa\7r\2\2\u00aa&\3\2\2\2\u00ab\u00ac\7e\2\2\u00ac\u00ad"+
		"\7t\2\2\u00ad\u00ae\7c\2\2\u00ae(\3\2\2\2\u00af\u00b0\7l\2\2\u00b0\u00b1"+
		"\7t\2\2\u00b1*\3\2\2\2\u00b2\u00b3\7d\2\2\u00b3,\3\2\2\2\u00b4\u00b5\7"+
		"n\2\2\u00b5\u00b6\7t\2\2\u00b6\u00b7\7c\2\2\u00b7.\3\2\2\2\u00b8\u00b9"+
		"\7u\2\2\u00b9\u00ba\7t\2\2\u00ba\u00bb\7c\2\2\u00bb\60\3\2\2\2\u00bc\u00bd"+
		"\7n\2\2\u00bd\u00be\7t\2\2\u00be\u00bf\7x\2\2\u00bf\62\3\2\2\2\u00c0\u00c1"+
		"\7u\2\2\u00c1\u00c2\7t\2\2\u00c2\u00c3\7x\2\2\u00c3\64\3\2\2\2\u00c4\u00c5"+
		"\7n\2\2\u00c5\u00c6\7h\2\2\u00c6\u00c7\7r\2\2\u00c7\66\3\2\2\2\u00c8\u00c9"+
		"\7u\2\2\u00c9\u00ca\7h\2\2\u00ca\u00cb\7r\2\2\u00cb8\3\2\2\2\u00cc\u00cd"+
		"\7n\2\2\u00cd\u00ce\7j\2\2\u00ce\u00cf\7r\2\2\u00cf:\3\2\2\2\u00d0\u00d1"+
		"\7u\2\2\u00d1\u00d2\7j\2\2\u00d2\u00d3\7r\2\2\u00d3<\3\2\2\2\u00d4\u00d5"+
		"\7u\2\2\u00d5\u00d6\7t\2\2\u00d6\u00d7\7\63\2\2\u00d7>\3\2\2\2\u00d8\u00d9"+
		"\7n\2\2\u00d9\u00da\7t\2\2\u00da\u00db\7\63\2\2\u00db@\3\2\2\2\u00dc\u00dd"+
		"\7u\2\2\u00dd\u00de\7t\2\2\u00de\u00df\7\64\2\2\u00dfB\3\2\2\2\u00e0\u00e1"+
		"\7n\2\2\u00e1\u00e2\7t\2\2\u00e2\u00e3\7\64\2\2\u00e3D\3\2\2\2\u00e4\u00e5"+
		"\7u\2\2\u00e5\u00e6\7c\2\2\u00e6\u00e7\7n\2\2\u00e7F\3\2\2\2\u00e8\u00e9"+
		"\7n\2\2\u00e9\u00ea\7c\2\2\u00ea\u00eb\7n\2\2\u00ebH\3\2\2\2\u00ec\u00ed"+
		"\7n\2\2\u00ed\u00ee\7k\2\2\u00ee\u00ef\7t\2\2\u00ef\u00f0\7\63\2\2\u00f0"+
		"J\3\2\2\2\u00f1\u00f2\7n\2\2\u00f2\u00f3\7k\2\2\u00f3\u00f4\7t\2\2\u00f4"+
		"\u00f5\7\64\2\2\u00f5L\3\2\2\2\u00f6\u00f7\7r\2\2\u00f7\u00f8\7t\2\2\u00f8"+
		"\u00f9\7k\2\2\u00f9\u00fa\7p\2\2\u00fa\u00fb\7v\2\2\u00fbN\3\2\2\2\u00fc"+
		"\u00fd\7e\2\2\u00fd\u00fe\7h\2\2\u00fe\u00ff\7r\2\2\u00ffP\3\2\2\2\u0100"+
		"\u0101\7e\2\2\u0101\u0102\7c\2\2\u0102\u0103\7n\2\2\u0103R\3\2\2\2\u0104"+
		"\u0105\7j\2\2\u0105\u0106\7c\2\2\u0106\u0107\7n\2\2\u0107\u0108\7v\2\2"+
		"\u0108T\3\2\2\2\u0109\u010a\7<\2\2\u010aV\3\2\2\2\u010b\u010f\t\2\2\2"+
		"\u010c\u010e\t\3\2\2\u010d\u010c\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d"+
		"\3\2\2\2\u010f\u0110\3\2\2\2\u0110X\3\2\2\2\u0111\u010f\3\2\2\2\u0112"+
		"\u011e\7\62\2\2\u0113\u0115\7/\2\2\u0114\u0113\3\2\2\2\u0114\u0115\3\2"+
		"\2\2\u0115\u0116\3\2\2\2\u0116\u011a\4\63;\2\u0117\u0119\4\62;\2\u0118"+
		"\u0117\3\2\2\2\u0119\u011c\3\2\2\2\u011a\u0118\3\2\2\2\u011a\u011b\3\2"+
		"\2\2\u011b\u011e\3\2\2\2\u011c\u011a\3\2\2\2\u011d\u0112\3\2\2\2\u011d"+
		"\u0114\3\2\2\2\u011eZ\3\2\2\2\u011f\u0121\t\4\2\2\u0120\u011f\3\2\2\2"+
		"\u0121\u0122\3\2\2\2\u0122\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0124"+
		"\3\2\2\2\u0124\u0125\b.\2\2\u0125\\\3\2\2\2\u0126\u0127\13\2\2\2\u0127"+
		"\u0128\b/\3\2\u0128\u0129\3\2\2\2\u0129\u012a\b/\2\2\u012a^\3\2\2\2\b"+
		"\2\u010f\u0114\u011a\u011d\u0122\4\2\3\2\3/\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}