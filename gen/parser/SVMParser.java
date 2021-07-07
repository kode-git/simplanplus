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
		SWHR2=17, SWFP=18, LWFP=19, LWAFP=20, SWHP=21, LWHP=22, NEW=23, CRA=24, 
		JR=25, BRANCH=26, LOADRA=27, STORERA=28, LOADRV=29, STORERV=30, LOADFP=31, 
		STOREFP=32, LOADHP=33, STOREHP=34, STORER1=35, LOADR1=36, STORER2=37, 
		LOADR2=38, STOREAL=39, LOADAL=40, LIR1=41, LIR2=42, PRINT=43, PRINTHP=44, 
		COPYFP=45, COPYAL=46, MOVEFP=47, HALT=48, COL=49, LABEL=50, NUMBER=51, 
		WHITESP=52, ERR=53;
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
			"'lw1'", "'swhr2'", "'swfp'", "'lwfp'", "'lwafp'", "'swhp'", "'lwhp'", 
			"'new'", "'cra'", "'jr'", "'b'", "'lra'", "'sra'", "'lrv'", "'srv'", 
			"'lfp'", "'sfp'", "'lhp'", "'shp'", "'sr1'", "'lr1'", "'sr2'", "'lr2'", 
			"'sal'", "'lal'", "'lir1'", "'lir2'", "'print'", "'printhp'", "'cfp'", 
			"'cal'", "'mfp'", "'halt'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PUSH", "POP", "ADD", "SUB", "MULT", "DIV", "BRANCHEQ", "BRANCHLESSEQ", 
			"BRANCHLESS", "AND", "OR", "NOT", "STOREW", "LOADW", "SWR1", "LWR1", 
			"SWHR2", "SWFP", "LWFP", "LWAFP", "SWHP", "LWHP", "NEW", "CRA", "JR", 
			"BRANCH", "LOADRA", "STORERA", "LOADRV", "STORERV", "LOADFP", "STOREFP", 
			"LOADHP", "STOREHP", "STORER1", "LOADR1", "STORER2", "LOADR2", "STOREAL", 
			"LOADAL", "LIR1", "LIR2", "PRINT", "PRINTHP", "COPYFP", "COPYAL", "MOVEFP", 
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PUSH) | (1L << POP) | (1L << ADD) | (1L << SUB) | (1L << MULT) | (1L << DIV) | (1L << BRANCHEQ) | (1L << BRANCHLESSEQ) | (1L << BRANCHLESS) | (1L << AND) | (1L << OR) | (1L << NOT) | (1L << STOREW) | (1L << LOADW) | (1L << SWR1) | (1L << LWR1) | (1L << SWHR2) | (1L << SWFP) | (1L << LWFP) | (1L << LWAFP) | (1L << SWHP) | (1L << LWHP) | (1L << NEW) | (1L << CRA) | (1L << JR) | (1L << BRANCH) | (1L << LOADRA) | (1L << STORERA) | (1L << LOADRV) | (1L << STORERV) | (1L << LOADFP) | (1L << STOREFP) | (1L << LOADHP) | (1L << STOREHP) | (1L << STORER1) | (1L << LOADR1) | (1L << STORER2) | (1L << LOADR2) | (1L << STOREAL) | (1L << LOADAL) | (1L << LIR1) | (1L << LIR2) | (1L << PRINT) | (1L << PRINTHP) | (1L << COPYFP) | (1L << COPYAL) | (1L << MOVEFP) | (1L << HALT) | (1L << LABEL))) != 0)) {
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
		public TerminalNode LWFP() { return getToken(SVMParser.LWFP, 0); }
		public TerminalNode SWFP() { return getToken(SVMParser.SWFP, 0); }
		public TerminalNode LWHP() { return getToken(SVMParser.LWHP, 0); }
		public TerminalNode SWHP() { return getToken(SVMParser.SWHP, 0); }
		public TerminalNode SWHR2() { return getToken(SVMParser.SWHR2, 0); }
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
		public TerminalNode MOVEFP() { return getToken(SVMParser.MOVEFP, 0); }
		public TerminalNode COPYAL() { return getToken(SVMParser.COPYAL, 0); }
		public TerminalNode LOADHP() { return getToken(SVMParser.LOADHP, 0); }
		public TerminalNode STOREHP() { return getToken(SVMParser.STOREHP, 0); }
		public TerminalNode PRINT() { return getToken(SVMParser.PRINT, 0); }
		public TerminalNode PRINTHP() { return getToken(SVMParser.PRINTHP, 0); }
		public TerminalNode HALT() { return getToken(SVMParser.HALT, 0); }
		public TerminalNode CRA() { return getToken(SVMParser.CRA, 0); }
		public TerminalNode STORER1() { return getToken(SVMParser.STORER1, 0); }
		public TerminalNode LOADR1() { return getToken(SVMParser.LOADR1, 0); }
		public TerminalNode STORER2() { return getToken(SVMParser.STORER2, 0); }
		public TerminalNode LOADR2() { return getToken(SVMParser.LOADR2, 0); }
		public TerminalNode STOREAL() { return getToken(SVMParser.STOREAL, 0); }
		public TerminalNode LOADAL() { return getToken(SVMParser.LOADAL, 0); }
		public TerminalNode LWAFP() { return getToken(SVMParser.LWAFP, 0); }
		public TerminalNode LIR1() { return getToken(SVMParser.LIR1, 0); }
		public TerminalNode LIR2() { return getToken(SVMParser.LIR2, 0); }
		public TerminalNode NEW() { return getToken(SVMParser.NEW, 0); }
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
			setState(80);
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
				match(LWFP);
				setState(31);
				((InstructionContext)_localctx).offset = match(NUMBER);
				}
				break;
			case 16:
				{
				setState(32);
				match(SWFP);
				setState(33);
				((InstructionContext)_localctx).offset = match(NUMBER);
				}
				break;
			case 17:
				{
				setState(34);
				match(LWHP);
				setState(35);
				((InstructionContext)_localctx).offset = match(NUMBER);
				}
				break;
			case 18:
				{
				setState(36);
				match(SWHP);
				setState(37);
				((InstructionContext)_localctx).offset = match(NUMBER);
				}
				break;
			case 19:
				{
				setState(38);
				match(SWHR2);
				setState(39);
				((InstructionContext)_localctx).offset = match(NUMBER);
				}
				break;
			case 20:
				{
				setState(40);
				((InstructionContext)_localctx).l = match(LABEL);
				setState(41);
				match(COL);
				}
				break;
			case 21:
				{
				setState(42);
				match(BRANCH);
				setState(43);
				((InstructionContext)_localctx).l = match(LABEL);
				}
				break;
			case 22:
				{
				setState(44);
				match(BRANCHEQ);
				setState(45);
				((InstructionContext)_localctx).l = match(LABEL);
				}
				break;
			case 23:
				{
				setState(46);
				match(BRANCHLESSEQ);
				setState(47);
				((InstructionContext)_localctx).l = match(LABEL);
				}
				break;
			case 24:
				{
				setState(48);
				match(BRANCHLESS);
				setState(49);
				((InstructionContext)_localctx).l = match(LABEL);
				}
				break;
			case 25:
				{
				setState(50);
				match(JR);
				}
				break;
			case 26:
				{
				setState(51);
				match(LOADRA);
				}
				break;
			case 27:
				{
				setState(52);
				match(STORERA);
				}
				break;
			case 28:
				{
				setState(53);
				match(LOADRV);
				}
				break;
			case 29:
				{
				setState(54);
				match(STORERV);
				}
				break;
			case 30:
				{
				setState(55);
				match(LOADFP);
				}
				break;
			case 31:
				{
				setState(56);
				match(STOREFP);
				}
				break;
			case 32:
				{
				setState(57);
				match(COPYFP);
				}
				break;
			case 33:
				{
				setState(58);
				match(MOVEFP);
				setState(59);
				((InstructionContext)_localctx).offset = match(NUMBER);
				}
				break;
			case 34:
				{
				setState(60);
				match(COPYAL);
				}
				break;
			case 35:
				{
				setState(61);
				match(LOADHP);
				}
				break;
			case 36:
				{
				setState(62);
				match(STOREHP);
				}
				break;
			case 37:
				{
				setState(63);
				match(PRINT);
				}
				break;
			case 38:
				{
				setState(64);
				match(PRINTHP);
				}
				break;
			case 39:
				{
				setState(65);
				match(HALT);
				}
				break;
			case 40:
				{
				setState(66);
				match(CRA);
				}
				break;
			case 41:
				{
				setState(67);
				match(STORER1);
				}
				break;
			case 42:
				{
				setState(68);
				match(LOADR1);
				}
				break;
			case 43:
				{
				setState(69);
				match(STORER2);
				}
				break;
			case 44:
				{
				setState(70);
				match(LOADR2);
				}
				break;
			case 45:
				{
				setState(71);
				match(STOREAL);
				}
				break;
			case 46:
				{
				setState(72);
				match(LOADAL);
				}
				break;
			case 47:
				{
				setState(73);
				match(LWAFP);
				setState(74);
				((InstructionContext)_localctx).n = match(NUMBER);
				}
				break;
			case 48:
				{
				setState(75);
				match(LIR1);
				setState(76);
				((InstructionContext)_localctx).n = match(NUMBER);
				}
				break;
			case 49:
				{
				setState(77);
				match(LIR2);
				setState(78);
				((InstructionContext)_localctx).n = match(NUMBER);
				}
				break;
			case 50:
				{
				setState(79);
				match(NEW);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\67U\4\2\t\2\4\3\t"+
		"\3\3\2\7\2\b\n\2\f\2\16\2\13\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3S\n\3\3\3\2\2\4\2\4\2\2\2\u0084"+
		"\2\t\3\2\2\2\4R\3\2\2\2\6\b\5\4\3\2\7\6\3\2\2\2\b\13\3\2\2\2\t\7\3\2\2"+
		"\2\t\n\3\2\2\2\n\3\3\2\2\2\13\t\3\2\2\2\f\r\7\3\2\2\rS\7\65\2\2\16\17"+
		"\7\3\2\2\17S\7\64\2\2\20S\7\4\2\2\21S\7\5\2\2\22S\7\6\2\2\23S\7\7\2\2"+
		"\24S\7\b\2\2\25S\7\f\2\2\26S\7\r\2\2\27S\7\16\2\2\30\31\7\17\2\2\31S\7"+
		"\65\2\2\32\33\7\20\2\2\33S\7\65\2\2\34\35\7\22\2\2\35S\7\65\2\2\36\37"+
		"\7\21\2\2\37S\7\65\2\2 !\7\25\2\2!S\7\65\2\2\"#\7\24\2\2#S\7\65\2\2$%"+
		"\7\30\2\2%S\7\65\2\2&\'\7\27\2\2\'S\7\65\2\2()\7\23\2\2)S\7\65\2\2*+\7"+
		"\64\2\2+S\7\63\2\2,-\7\34\2\2-S\7\64\2\2./\7\t\2\2/S\7\64\2\2\60\61\7"+
		"\n\2\2\61S\7\64\2\2\62\63\7\13\2\2\63S\7\64\2\2\64S\7\33\2\2\65S\7\35"+
		"\2\2\66S\7\36\2\2\67S\7\37\2\28S\7 \2\29S\7!\2\2:S\7\"\2\2;S\7/\2\2<="+
		"\7\61\2\2=S\7\65\2\2>S\7\60\2\2?S\7#\2\2@S\7$\2\2AS\7-\2\2BS\7.\2\2CS"+
		"\7\62\2\2DS\7\32\2\2ES\7%\2\2FS\7&\2\2GS\7\'\2\2HS\7(\2\2IS\7)\2\2JS\7"+
		"*\2\2KL\7\26\2\2LS\7\65\2\2MN\7+\2\2NS\7\65\2\2OP\7,\2\2PS\7\65\2\2QS"+
		"\7\31\2\2R\f\3\2\2\2R\16\3\2\2\2R\20\3\2\2\2R\21\3\2\2\2R\22\3\2\2\2R"+
		"\23\3\2\2\2R\24\3\2\2\2R\25\3\2\2\2R\26\3\2\2\2R\27\3\2\2\2R\30\3\2\2"+
		"\2R\32\3\2\2\2R\34\3\2\2\2R\36\3\2\2\2R \3\2\2\2R\"\3\2\2\2R$\3\2\2\2"+
		"R&\3\2\2\2R(\3\2\2\2R*\3\2\2\2R,\3\2\2\2R.\3\2\2\2R\60\3\2\2\2R\62\3\2"+
		"\2\2R\64\3\2\2\2R\65\3\2\2\2R\66\3\2\2\2R\67\3\2\2\2R8\3\2\2\2R9\3\2\2"+
		"\2R:\3\2\2\2R;\3\2\2\2R<\3\2\2\2R>\3\2\2\2R?\3\2\2\2R@\3\2\2\2RA\3\2\2"+
		"\2RB\3\2\2\2RC\3\2\2\2RD\3\2\2\2RE\3\2\2\2RF\3\2\2\2RG\3\2\2\2RH\3\2\2"+
		"\2RI\3\2\2\2RJ\3\2\2\2RK\3\2\2\2RM\3\2\2\2RO\3\2\2\2RQ\3\2\2\2S\5\3\2"+
		"\2\2\4\tR";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}