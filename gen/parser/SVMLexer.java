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
		SWFP=17, LWFP=18, LWAFP=19, SWHP=20, LWHP=21, NEW=22, CRA=23, JR=24, BRANCH=25, 
		LOADRA=26, STORERA=27, LOADRV=28, STORERV=29, LOADFP=30, STOREFP=31, LOADHP=32, 
		STOREHP=33, STORER1=34, LOADR1=35, STORER2=36, LOADR2=37, STOREAL=38, 
		LOADAL=39, LIR1=40, LIR2=41, PRINT=42, PRINTHP=43, COPYFP=44, COPYAL=45, 
		MOVEFP=46, HALT=47, COL=48, LABEL=49, NUMBER=50, WHITESP=51, ERR=52;
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
			"SWFP", "LWFP", "LWAFP", "SWHP", "LWHP", "NEW", "CRA", "JR", "BRANCH", 
			"LOADRA", "STORERA", "LOADRV", "STORERV", "LOADFP", "STOREFP", "LOADHP", 
			"STOREHP", "STORER1", "LOADR1", "STORER2", "LOADR2", "STOREAL", "LOADAL", 
			"LIR1", "LIR2", "PRINT", "PRINTHP", "COPYFP", "COPYAL", "MOVEFP", "HALT", 
			"COL", "LABEL", "NUMBER", "WHITESP", "ERR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'push'", "'pop'", "'add'", "'sub'", "'mult'", "'div'", "'beq'", 
			"'bleq'", "'bless'", "'and'", "'or'", "'not'", "'sw'", "'lw'", "'sw1'", 
			"'lw1'", "'swfp'", "'lwfp'", "'lwafp'", "'swhp'", "'lwhp'", "'new'", 
			"'cra'", "'jr'", "'b'", "'lra'", "'sra'", "'lrv'", "'srv'", "'lfp'", 
			"'sfp'", "'lhp'", "'shp'", "'sr1'", "'lr1'", "'sr2'", "'lr2'", "'sal'", 
			"'lal'", "'lir1'", "'lir2'", "'print'", "'printhp'", "'cfp'", "'cal'", 
			"'mfp'", "'halt'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PUSH", "POP", "ADD", "SUB", "MULT", "DIV", "BRANCHEQ", "BRANCHLESSEQ", 
			"BRANCHLESS", "AND", "OR", "NOT", "STOREW", "LOADW", "SWR1", "LWR1", 
			"SWFP", "LWFP", "LWAFP", "SWHP", "LWHP", "NEW", "CRA", "JR", "BRANCH", 
			"LOADRA", "STORERA", "LOADRV", "STORERV", "LOADFP", "STOREFP", "LOADHP", 
			"STOREHP", "STORER1", "LOADR1", "STORER2", "LOADR2", "STOREAL", "LOADAL", 
			"LIR1", "LIR2", "PRINT", "PRINTHP", "COPYFP", "COPYAL", "MOVEFP", "HALT", 
			"COL", "LABEL", "NUMBER", "WHITESP", "ERR"
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
		case 51:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\66\u0157\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3"+
		"\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3"+
		"\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3"+
		"\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3!\3!\3!\3!\3\"\3\"\3\""+
		"\3\"\3#\3#\3#\3#\3$\3$\3$\3$\3%\3%\3%\3%\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3"+
		"(\3(\3(\3(\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3,\3,\3,\3"+
		",\3,\3,\3,\3,\3-\3-\3-\3-\3.\3.\3.\3.\3/\3/\3/\3/\3\60\3\60\3\60\3\60"+
		"\3\60\3\61\3\61\3\62\3\62\7\62\u013a\n\62\f\62\16\62\u013d\13\62\3\63"+
		"\3\63\5\63\u0141\n\63\3\63\3\63\7\63\u0145\n\63\f\63\16\63\u0148\13\63"+
		"\5\63\u014a\n\63\3\64\6\64\u014d\n\64\r\64\16\64\u014e\3\64\3\64\3\65"+
		"\3\65\3\65\3\65\3\65\2\2\66\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a"+
		"\62c\63e\64g\65i\66\3\2\5\4\2C\\c|\5\2\62;C\\c|\5\2\13\f\17\17\"\"\2\u015b"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3"+
		"\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\3k\3\2\2\2\5p\3\2\2"+
		"\2\7t\3\2\2\2\tx\3\2\2\2\13|\3\2\2\2\r\u0081\3\2\2\2\17\u0085\3\2\2\2"+
		"\21\u0089\3\2\2\2\23\u008e\3\2\2\2\25\u0094\3\2\2\2\27\u0098\3\2\2\2\31"+
		"\u009b\3\2\2\2\33\u009f\3\2\2\2\35\u00a2\3\2\2\2\37\u00a5\3\2\2\2!\u00a9"+
		"\3\2\2\2#\u00ad\3\2\2\2%\u00b2\3\2\2\2\'\u00b7\3\2\2\2)\u00bd\3\2\2\2"+
		"+\u00c2\3\2\2\2-\u00c7\3\2\2\2/\u00cb\3\2\2\2\61\u00cf\3\2\2\2\63\u00d2"+
		"\3\2\2\2\65\u00d4\3\2\2\2\67\u00d8\3\2\2\29\u00dc\3\2\2\2;\u00e0\3\2\2"+
		"\2=\u00e4\3\2\2\2?\u00e8\3\2\2\2A\u00ec\3\2\2\2C\u00f0\3\2\2\2E\u00f4"+
		"\3\2\2\2G\u00f8\3\2\2\2I\u00fc\3\2\2\2K\u0100\3\2\2\2M\u0104\3\2\2\2O"+
		"\u0108\3\2\2\2Q\u010c\3\2\2\2S\u0111\3\2\2\2U\u0116\3\2\2\2W\u011c\3\2"+
		"\2\2Y\u0124\3\2\2\2[\u0128\3\2\2\2]\u012c\3\2\2\2_\u0130\3\2\2\2a\u0135"+
		"\3\2\2\2c\u0137\3\2\2\2e\u0149\3\2\2\2g\u014c\3\2\2\2i\u0152\3\2\2\2k"+
		"l\7r\2\2lm\7w\2\2mn\7u\2\2no\7j\2\2o\4\3\2\2\2pq\7r\2\2qr\7q\2\2rs\7r"+
		"\2\2s\6\3\2\2\2tu\7c\2\2uv\7f\2\2vw\7f\2\2w\b\3\2\2\2xy\7u\2\2yz\7w\2"+
		"\2z{\7d\2\2{\n\3\2\2\2|}\7o\2\2}~\7w\2\2~\177\7n\2\2\177\u0080\7v\2\2"+
		"\u0080\f\3\2\2\2\u0081\u0082\7f\2\2\u0082\u0083\7k\2\2\u0083\u0084\7x"+
		"\2\2\u0084\16\3\2\2\2\u0085\u0086\7d\2\2\u0086\u0087\7g\2\2\u0087\u0088"+
		"\7s\2\2\u0088\20\3\2\2\2\u0089\u008a\7d\2\2\u008a\u008b\7n\2\2\u008b\u008c"+
		"\7g\2\2\u008c\u008d\7s\2\2\u008d\22\3\2\2\2\u008e\u008f\7d\2\2\u008f\u0090"+
		"\7n\2\2\u0090\u0091\7g\2\2\u0091\u0092\7u\2\2\u0092\u0093\7u\2\2\u0093"+
		"\24\3\2\2\2\u0094\u0095\7c\2\2\u0095\u0096\7p\2\2\u0096\u0097\7f\2\2\u0097"+
		"\26\3\2\2\2\u0098\u0099\7q\2\2\u0099\u009a\7t\2\2\u009a\30\3\2\2\2\u009b"+
		"\u009c\7p\2\2\u009c\u009d\7q\2\2\u009d\u009e\7v\2\2\u009e\32\3\2\2\2\u009f"+
		"\u00a0\7u\2\2\u00a0\u00a1\7y\2\2\u00a1\34\3\2\2\2\u00a2\u00a3\7n\2\2\u00a3"+
		"\u00a4\7y\2\2\u00a4\36\3\2\2\2\u00a5\u00a6\7u\2\2\u00a6\u00a7\7y\2\2\u00a7"+
		"\u00a8\7\63\2\2\u00a8 \3\2\2\2\u00a9\u00aa\7n\2\2\u00aa\u00ab\7y\2\2\u00ab"+
		"\u00ac\7\63\2\2\u00ac\"\3\2\2\2\u00ad\u00ae\7u\2\2\u00ae\u00af\7y\2\2"+
		"\u00af\u00b0\7h\2\2\u00b0\u00b1\7r\2\2\u00b1$\3\2\2\2\u00b2\u00b3\7n\2"+
		"\2\u00b3\u00b4\7y\2\2\u00b4\u00b5\7h\2\2\u00b5\u00b6\7r\2\2\u00b6&\3\2"+
		"\2\2\u00b7\u00b8\7n\2\2\u00b8\u00b9\7y\2\2\u00b9\u00ba\7c\2\2\u00ba\u00bb"+
		"\7h\2\2\u00bb\u00bc\7r\2\2\u00bc(\3\2\2\2\u00bd\u00be\7u\2\2\u00be\u00bf"+
		"\7y\2\2\u00bf\u00c0\7j\2\2\u00c0\u00c1\7r\2\2\u00c1*\3\2\2\2\u00c2\u00c3"+
		"\7n\2\2\u00c3\u00c4\7y\2\2\u00c4\u00c5\7j\2\2\u00c5\u00c6\7r\2\2\u00c6"+
		",\3\2\2\2\u00c7\u00c8\7p\2\2\u00c8\u00c9\7g\2\2\u00c9\u00ca\7y\2\2\u00ca"+
		".\3\2\2\2\u00cb\u00cc\7e\2\2\u00cc\u00cd\7t\2\2\u00cd\u00ce\7c\2\2\u00ce"+
		"\60\3\2\2\2\u00cf\u00d0\7l\2\2\u00d0\u00d1\7t\2\2\u00d1\62\3\2\2\2\u00d2"+
		"\u00d3\7d\2\2\u00d3\64\3\2\2\2\u00d4\u00d5\7n\2\2\u00d5\u00d6\7t\2\2\u00d6"+
		"\u00d7\7c\2\2\u00d7\66\3\2\2\2\u00d8\u00d9\7u\2\2\u00d9\u00da\7t\2\2\u00da"+
		"\u00db\7c\2\2\u00db8\3\2\2\2\u00dc\u00dd\7n\2\2\u00dd\u00de\7t\2\2\u00de"+
		"\u00df\7x\2\2\u00df:\3\2\2\2\u00e0\u00e1\7u\2\2\u00e1\u00e2\7t\2\2\u00e2"+
		"\u00e3\7x\2\2\u00e3<\3\2\2\2\u00e4\u00e5\7n\2\2\u00e5\u00e6\7h\2\2\u00e6"+
		"\u00e7\7r\2\2\u00e7>\3\2\2\2\u00e8\u00e9\7u\2\2\u00e9\u00ea\7h\2\2\u00ea"+
		"\u00eb\7r\2\2\u00eb@\3\2\2\2\u00ec\u00ed\7n\2\2\u00ed\u00ee\7j\2\2\u00ee"+
		"\u00ef\7r\2\2\u00efB\3\2\2\2\u00f0\u00f1\7u\2\2\u00f1\u00f2\7j\2\2\u00f2"+
		"\u00f3\7r\2\2\u00f3D\3\2\2\2\u00f4\u00f5\7u\2\2\u00f5\u00f6\7t\2\2\u00f6"+
		"\u00f7\7\63\2\2\u00f7F\3\2\2\2\u00f8\u00f9\7n\2\2\u00f9\u00fa\7t\2\2\u00fa"+
		"\u00fb\7\63\2\2\u00fbH\3\2\2\2\u00fc\u00fd\7u\2\2\u00fd\u00fe\7t\2\2\u00fe"+
		"\u00ff\7\64\2\2\u00ffJ\3\2\2\2\u0100\u0101\7n\2\2\u0101\u0102\7t\2\2\u0102"+
		"\u0103\7\64\2\2\u0103L\3\2\2\2\u0104\u0105\7u\2\2\u0105\u0106\7c\2\2\u0106"+
		"\u0107\7n\2\2\u0107N\3\2\2\2\u0108\u0109\7n\2\2\u0109\u010a\7c\2\2\u010a"+
		"\u010b\7n\2\2\u010bP\3\2\2\2\u010c\u010d\7n\2\2\u010d\u010e\7k\2\2\u010e"+
		"\u010f\7t\2\2\u010f\u0110\7\63\2\2\u0110R\3\2\2\2\u0111\u0112\7n\2\2\u0112"+
		"\u0113\7k\2\2\u0113\u0114\7t\2\2\u0114\u0115\7\64\2\2\u0115T\3\2\2\2\u0116"+
		"\u0117\7r\2\2\u0117\u0118\7t\2\2\u0118\u0119\7k\2\2\u0119\u011a\7p\2\2"+
		"\u011a\u011b\7v\2\2\u011bV\3\2\2\2\u011c\u011d\7r\2\2\u011d\u011e\7t\2"+
		"\2\u011e\u011f\7k\2\2\u011f\u0120\7p\2\2\u0120\u0121\7v\2\2\u0121\u0122"+
		"\7j\2\2\u0122\u0123\7r\2\2\u0123X\3\2\2\2\u0124\u0125\7e\2\2\u0125\u0126"+
		"\7h\2\2\u0126\u0127\7r\2\2\u0127Z\3\2\2\2\u0128\u0129\7e\2\2\u0129\u012a"+
		"\7c\2\2\u012a\u012b\7n\2\2\u012b\\\3\2\2\2\u012c\u012d\7o\2\2\u012d\u012e"+
		"\7h\2\2\u012e\u012f\7r\2\2\u012f^\3\2\2\2\u0130\u0131\7j\2\2\u0131\u0132"+
		"\7c\2\2\u0132\u0133\7n\2\2\u0133\u0134\7v\2\2\u0134`\3\2\2\2\u0135\u0136"+
		"\7<\2\2\u0136b\3\2\2\2\u0137\u013b\t\2\2\2\u0138\u013a\t\3\2\2\u0139\u0138"+
		"\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013c"+
		"d\3\2\2\2\u013d\u013b\3\2\2\2\u013e\u014a\7\62\2\2\u013f\u0141\7/\2\2"+
		"\u0140\u013f\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0146"+
		"\4\63;\2\u0143\u0145\4\62;\2\u0144\u0143\3\2\2\2\u0145\u0148\3\2\2\2\u0146"+
		"\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147\u014a\3\2\2\2\u0148\u0146\3\2"+
		"\2\2\u0149\u013e\3\2\2\2\u0149\u0140\3\2\2\2\u014af\3\2\2\2\u014b\u014d"+
		"\t\4\2\2\u014c\u014b\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u014c\3\2\2\2\u014e"+
		"\u014f\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0151\b\64\2\2\u0151h\3\2\2\2"+
		"\u0152\u0153\13\2\2\2\u0153\u0154\b\65\3\2\u0154\u0155\3\2\2\2\u0155\u0156"+
		"\b\65\2\2\u0156j\3\2\2\2\b\2\u013b\u0140\u0146\u0149\u014e\4\2\3\2\3\65"+
		"\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}