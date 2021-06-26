// Generated from /Users/kode/Desktop/project-compiler-antlr4/main/src/parser/SVM.g4 by ANTLR 4.9.1
package parser;

import java.util.HashMap;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SVMParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PUSH=1, POP=2, ADD=3, SUB=4, MULT=5, DIV=6, BRANCHEQ=7, BRANCHLESSEQ=8, 
		BRANCHLESS=9, AND=10, OR=11, NOT=12, STOREW=13, LOADW=14, SWR1=15, LWR1=16, 
		CRA=17, JR=18, BRANCH=19, LOADRA=20, STORERA=21, LOADRV=22, STORERV=23, 
		LOADFP=24, STOREFP=25, LOADHP=26, STOREHP=27, STORER1=28, LOADR1=29, STORER2=30, 
		LOADR2=31, STOREAL=32, LOADAL=33, LIR1=34, LIR2=35, PRINT=36, COPYFP=37, 
		HALT=38, COL=39, LABEL=40, NUMBER=41, WHITESP=42, ERR=43;
	public static final int
		RULE_assembly = 0, RULE_instruction = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"assembly", "instruction"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'push'", "'pop'", "'add'", "'sub'", "'mult'", "'div'", "'beq'", 
			"'bleq'", "'bless'", "'and'", "'or'", "'not'", "'sw'", "'lw'", "'sw1'", 
			"'lw1'", "'cra'", "'jr'", "'b'", "'lra'", "'sra'", "'lrv'", "'srv'", 
			"'lfp'", "'sfp'", "'lhp'", "'shp'", "'sr1'", "'lr1'", "'sr2'", "'lr2'", 
			"'sal'", "'lal'", "'lir1'", "'lir2'", "'print'", "'cfp'", "'halt'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PUSH", "POP", "ADD", "SUB", "MULT", "DIV", "BRANCHEQ", "BRANCHLESSEQ", 
			"BRANCHLESS", "AND", "OR", "NOT", "STOREW", "LOADW", "SWR1", "LWR1", 
			"CRA", "JR", "BRANCH", "LOADRA", "STORERA", "LOADRV", "STORERV", "LOADFP", 
			"STOREFP", "LOADHP", "STOREHP", "STORER1", "LOADR1", "STORER2", "LOADR2", 
			"STOREAL", "LOADAL", "LIR1", "LIR2", "PRINT", "COPYFP", "HALT", "COL", 
			"LABEL", "NUMBER", "WHITESP", "ERR"
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
	public String getGrammarFileName() { return "SVM.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SVMParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class AssemblyContext extends ParserRuleContext {
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public AssemblyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assembly; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterAssembly(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitAssembly(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitAssembly(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssemblyContext assembly() throws RecognitionException {
		AssemblyContext _localctx = new AssemblyContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_assembly);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(7);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PUSH) | (1L << POP) | (1L << ADD) | (1L << SUB) | (1L << MULT) | (1L << DIV) | (1L << BRANCHEQ) | (1L << BRANCHLESSEQ) | (1L << BRANCHLESS) | (1L << AND) | (1L << OR) | (1L << NOT) | (1L << STOREW) | (1L << LOADW) | (1L << SWR1) | (1L << LWR1) | (1L << CRA) | (1L << JR) | (1L << BRANCH) | (1L << LOADRA) | (1L << STORERA) | (1L << LOADRV) | (1L << STORERV) | (1L << LOADFP) | (1L << STOREFP) | (1L << LOADHP) | (1L << STOREHP) | (1L << STORER1) | (1L << LOADR1) | (1L << STORER2) | (1L << LOADR2) | (1L << STOREAL) | (1L << LOADAL) | (1L << LIR1) | (1L << LIR2) | (1L << PRINT) | (1L << COPYFP) | (1L << HALT) | (1L << LABEL))) != 0)) {
				{
				{
				setState(4);
				instruction();
				}
				}
				setState(9);
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

	public static class InstructionContext extends ParserRuleContext {
		public Token n;
		public Token l;
		public Token offset;
		public TerminalNode PUSH() { return getToken(SVMParser.PUSH, 0); }
		public TerminalNode POP() { return getToken(SVMParser.POP, 0); }
		public TerminalNode ADD() { return getToken(SVMParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(SVMParser.SUB, 0); }
		public TerminalNode MULT() { return getToken(SVMParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(SVMParser.DIV, 0); }
		public TerminalNode AND() { return getToken(SVMParser.AND, 0); }
		public TerminalNode OR() { return getToken(SVMParser.OR, 0); }
		public TerminalNode NOT() { return getToken(SVMParser.NOT, 0); }
		public TerminalNode STOREW() { return getToken(SVMParser.STOREW, 0); }
		public TerminalNode LOADW() { return getToken(SVMParser.LOADW, 0); }
		public TerminalNode LWR1() { return getToken(SVMParser.LWR1, 0); }
		public TerminalNode SWR1() { return getToken(SVMParser.SWR1, 0); }
		public TerminalNode COL() { return getToken(SVMParser.COL, 0); }
		public TerminalNode BRANCH() { return getToken(SVMParser.BRANCH, 0); }
		public TerminalNode BRANCHEQ() { return getToken(SVMParser.BRANCHEQ, 0); }
		public TerminalNode BRANCHLESSEQ() { return getToken(SVMParser.BRANCHLESSEQ, 0); }
		public TerminalNode BRANCHLESS() { return getToken(SVMParser.BRANCHLESS, 0); }
		public TerminalNode JR() { return getToken(SVMParser.JR, 0); }
		public TerminalNode LOADRA() { return getToken(SVMParser.LOADRA, 0); }
		public TerminalNode STORERA() { return getToken(SVMParser.STORERA, 0); }
		public TerminalNode LOADRV() { return getToken(SVMParser.LOADRV, 0); }
		public TerminalNode STORERV() { return getToken(SVMParser.STORERV, 0); }
		public TerminalNode LOADFP() { return getToken(SVMParser.LOADFP, 0); }
		public TerminalNode STOREFP() { return getToken(SVMParser.STOREFP, 0); }
		public TerminalNode COPYFP() { return getToken(SVMParser.COPYFP, 0); }
		public TerminalNode LOADHP() { return getToken(SVMParser.LOADHP, 0); }
		public TerminalNode STOREHP() { return getToken(SVMParser.STOREHP, 0); }
		public TerminalNode PRINT() { return getToken(SVMParser.PRINT, 0); }
		public TerminalNode HALT() { return getToken(SVMParser.HALT, 0); }
		public TerminalNode CRA() { return getToken(SVMParser.CRA, 0); }
		public TerminalNode STORER1() { return getToken(SVMParser.STORER1, 0); }
		public TerminalNode LOADR1() { return getToken(SVMParser.LOADR1, 0); }
		public TerminalNode STORER2() { return getToken(SVMParser.STORER2, 0); }
		public TerminalNode LOADR2() { return getToken(SVMParser.LOADR2, 0); }
		public TerminalNode STOREAL() { return getToken(SVMParser.STOREAL, 0); }
		public TerminalNode LOADAL() { return getToken(SVMParser.LOADAL, 0); }
		public TerminalNode LIR1() { return getToken(SVMParser.LIR1, 0); }
		public TerminalNode LIR2() { return getToken(SVMParser.LIR2, 0); }
		public TerminalNode NUMBER() { return getToken(SVMParser.NUMBER, 0); }
		public TerminalNode LABEL() { return getToken(SVMParser.LABEL, 0); }
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitInstruction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_instruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(10);
				match(PUSH);
				setState(11);
				((InstructionContext)_localctx).n = match(NUMBER);
				}
				break;
			case 2:
				{
				setState(12);
				match(PUSH);
				setState(13);
				((InstructionContext)_localctx).l = match(LABEL);
				}
				break;
			case 3:
				{
				setState(14);
				match(POP);
				}
				break;
			case 4:
				{
				setState(15);
				match(ADD);
				}
				break;
			case 5:
				{
				setState(16);
				match(SUB);
				}
				break;
			case 6:
				{
				setState(17);
				match(MULT);
				}
				break;
			case 7:
				{
				setState(18);
				match(DIV);
				}
				break;
			case 8:
				{
				setState(19);
				match(AND);
				}
				break;
			case 9:
				{
				setState(20);
				match(OR);
				}
				break;
			case 10:
				{
				setState(21);
				match(NOT);
				}
				break;
			case 11:
				{
				setState(22);
				match(STOREW);
				setState(23);
				((InstructionContext)_localctx).offset = match(NUMBER);
				}
				break;
			case 12:
				{
				setState(24);
				match(LOADW);
				setState(25);
				((InstructionContext)_localctx).offset = match(NUMBER);
				}
				break;
			case 13:
				{
				setState(26);
				match(LWR1);
				setState(27);
				((InstructionContext)_localctx).offset = match(NUMBER);
				}
				break;
			case 14:
				{
				setState(28);
				match(SWR1);
				setState(29);
				((InstructionContext)_localctx).offset = match(NUMBER);
				}
				break;
			case 15:
				{
				setState(30);
				((InstructionContext)_localctx).l = match(LABEL);
				setState(31);
				match(COL);
				}
				break;
			case 16:
				{
				setState(32);
				match(BRANCH);
				setState(33);
				((InstructionContext)_localctx).l = match(LABEL);
				}
				break;
			case 17:
				{
				setState(34);
				match(BRANCHEQ);
				setState(35);
				((InstructionContext)_localctx).l = match(LABEL);
				}
				break;
			case 18:
				{
				setState(36);
				match(BRANCHLESSEQ);
				setState(37);
				((InstructionContext)_localctx).l = match(LABEL);
				}
				break;
			case 19:
				{
				setState(38);
				match(BRANCHLESS);
				setState(39);
				((InstructionContext)_localctx).l = match(LABEL);
				}
				break;
			case 20:
				{
				setState(40);
				match(JR);
				}
				break;
			case 21:
				{
				setState(41);
				match(LOADRA);
				}
				break;
			case 22:
				{
				setState(42);
				match(STORERA);
				}
				break;
			case 23:
				{
				setState(43);
				match(LOADRV);
				}
				break;
			case 24:
				{
				setState(44);
				match(STORERV);
				}
				break;
			case 25:
				{
				setState(45);
				match(LOADFP);
				}
				break;
			case 26:
				{
				setState(46);
				match(STOREFP);
				}
				break;
			case 27:
				{
				setState(47);
				match(COPYFP);
				}
				break;
			case 28:
				{
				setState(48);
				match(LOADHP);
				}
				break;
			case 29:
				{
				setState(49);
				match(STOREHP);
				}
				break;
			case 30:
				{
				setState(50);
				match(PRINT);
				}
				break;
			case 31:
				{
				setState(51);
				match(HALT);
				}
				break;
			case 32:
				{
				setState(52);
				match(CRA);
				}
				break;
			case 33:
				{
				setState(53);
				match(STORER1);
				}
				break;
			case 34:
				{
				setState(54);
				match(LOADR1);
				}
				break;
			case 35:
				{
				setState(55);
				match(STORER2);
				}
				break;
			case 36:
				{
				setState(56);
				match(LOADR2);
				}
				break;
			case 37:
				{
				setState(57);
				match(STOREAL);
				}
				break;
			case 38:
				{
				setState(58);
				match(LOADAL);
				}
				break;
			case 39:
				{
				setState(59);
				match(LIR1);
				setState(60);
				((InstructionContext)_localctx).n = match(NUMBER);
				}
				break;
			case 40:
				{
				setState(61);
				match(LIR2);
				setState(62);
				((InstructionContext)_localctx).n = match(NUMBER);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3-D\4\2\t\2\4\3\t\3"+
		"\3\2\7\2\b\n\2\f\2\16\2\13\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3B\n\3\3\3\2\2\4\2\4\2\2\2i\2\t\3"+
		"\2\2\2\4A\3\2\2\2\6\b\5\4\3\2\7\6\3\2\2\2\b\13\3\2\2\2\t\7\3\2\2\2\t\n"+
		"\3\2\2\2\n\3\3\2\2\2\13\t\3\2\2\2\f\r\7\3\2\2\rB\7+\2\2\16\17\7\3\2\2"+
		"\17B\7*\2\2\20B\7\4\2\2\21B\7\5\2\2\22B\7\6\2\2\23B\7\7\2\2\24B\7\b\2"+
		"\2\25B\7\f\2\2\26B\7\r\2\2\27B\7\16\2\2\30\31\7\17\2\2\31B\7+\2\2\32\33"+
		"\7\20\2\2\33B\7+\2\2\34\35\7\22\2\2\35B\7+\2\2\36\37\7\21\2\2\37B\7+\2"+
		"\2 !\7*\2\2!B\7)\2\2\"#\7\25\2\2#B\7*\2\2$%\7\t\2\2%B\7*\2\2&\'\7\n\2"+
		"\2\'B\7*\2\2()\7\13\2\2)B\7*\2\2*B\7\24\2\2+B\7\26\2\2,B\7\27\2\2-B\7"+
		"\30\2\2.B\7\31\2\2/B\7\32\2\2\60B\7\33\2\2\61B\7\'\2\2\62B\7\34\2\2\63"+
		"B\7\35\2\2\64B\7&\2\2\65B\7(\2\2\66B\7\23\2\2\67B\7\36\2\28B\7\37\2\2"+
		"9B\7 \2\2:B\7!\2\2;B\7\"\2\2<B\7#\2\2=>\7$\2\2>B\7+\2\2?@\7%\2\2@B\7+"+
		"\2\2A\f\3\2\2\2A\16\3\2\2\2A\20\3\2\2\2A\21\3\2\2\2A\22\3\2\2\2A\23\3"+
		"\2\2\2A\24\3\2\2\2A\25\3\2\2\2A\26\3\2\2\2A\27\3\2\2\2A\30\3\2\2\2A\32"+
		"\3\2\2\2A\34\3\2\2\2A\36\3\2\2\2A \3\2\2\2A\"\3\2\2\2A$\3\2\2\2A&\3\2"+
		"\2\2A(\3\2\2\2A*\3\2\2\2A+\3\2\2\2A,\3\2\2\2A-\3\2\2\2A.\3\2\2\2A/\3\2"+
		"\2\2A\60\3\2\2\2A\61\3\2\2\2A\62\3\2\2\2A\63\3\2\2\2A\64\3\2\2\2A\65\3"+
		"\2\2\2A\66\3\2\2\2A\67\3\2\2\2A8\3\2\2\2A9\3\2\2\2A:\3\2\2\2A;\3\2\2\2"+
		"A<\3\2\2\2A=\3\2\2\2A?\3\2\2\2B\5\3\2\2\2\4\tA";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}