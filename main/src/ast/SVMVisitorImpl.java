package ast;

import java.util.HashMap;

import Interpreter.ExecuteVM;
import parser.*;


public class SVMVisitorImpl extends SVMBaseVisitor<Void> {	
	
    public int[] code = new int[ExecuteVM.CODESIZE];    
    private int i = 0;
    private HashMap<String,Integer> labelAdd = new HashMap<String,Integer>();
    private HashMap<Integer,String> labelRef = new HashMap<Integer,String>();
    
    @Override 
    public Void visitAssembly(SVMParser.AssemblyContext ctx) { 
    	visitChildren(ctx); // case SVMLexer.LABEL don't match
    	for (Integer refAdd: labelRef.keySet()) {
            code[refAdd]=labelAdd.get(labelRef.get(refAdd));
    	}
    	return null;
    }
    
    @Override 
    public Void visitInstruction(SVMParser.InstructionContext ctx) {
    	switch (ctx.getStart().getType()) {
			case SVMLexer.PUSH:
				if(ctx.n != null) {
					code[i++] = SVMParser.PUSH; 
	                code[i++] = Integer.parseInt(ctx.n.getText());
				}
				else if(ctx.l != null){
					code[i++] = SVMParser.PUSH; 
		            labelRef.put(i++, ctx.l.getText());

				}
				break;
			case SVMLexer.LIR1:
				if(ctx.n != null) {
					code[i++] = SVMParser.LIR1;
					code[i++] = Integer.parseInt(ctx.n.getText());
				}
				break;
			case SVMLexer.LIR2:
				if(ctx.n != null) {
					code[i++] = SVMParser.LIR2;
					code[i++] = Integer.parseInt(ctx.n.getText());
				}
				break;
			case SVMLexer.POP:
				code[i++] = SVMParser.POP;
				break;
			case SVMLexer.ADD:
				code[i++] = SVMParser.ADD;
				break;
			case SVMLexer.SUB:
				code[i++] = SVMParser.SUB;
				break;
			case SVMLexer.MULT:
				code[i++] = SVMParser.MULT;
				break;
			case SVMLexer.DIV:
				code[i++] = SVMParser.DIV;
				break;
			case SVMLexer.AND:
				code[i++] = SVMParser.AND;
				break;
			case SVMLexer.CRA:
				code[i++] = SVMParser.CRA;
				break;
			case SVMLexer.OR:
				code[i++] = SVMParser.OR;
				break;
			case SVMLexer.NOT:
				code[i++] = SVMParser.NOT;
				break;
			case SVMLexer.STOREW:  // tokens.size() = 3
				code[i++] = SVMParser.STOREW; // token 1
				code[i++] = Integer.parseInt(ctx.offset.getText()); // token 2
				break;
			case SVMLexer.LOADW: // tokens.size() = 3
				code[i++] = SVMParser.LOADW;	// token 1
				code[i++] = Integer.parseInt(ctx.offset.getText()); // token 2
				break;
			case SVMLexer.SWR1:  // tokens.size() = 3
				code[i++] = SVMParser.SWR1; // token 1
				code[i++] = Integer.parseInt(ctx.offset.getText()); // token 2
				break;
			case SVMLexer.LWR1: // tokens.size() = 3
				code[i++] = SVMParser.LWR1;	// token 1
				code[i++] = Integer.parseInt(ctx.offset.getText()); // token 2
				break;
			case SVMLexer.LWFP:
				code[i++] = SVMParser.LWFP;
				code[i++] = Integer.parseInt(ctx.offset.getText());
				break;
			case SVMLexer.MOVEFP:
				code[i++] = SVMParser.MOVEFP;
				code[i++] = Integer.parseInt(ctx.offset.getText());
				break;
			case SVMLexer.SWFP:  // tokens.size() = 3
				code[i++] = SVMParser.SWFP; // token 1
				code[i++] = Integer.parseInt(ctx.offset.getText()); // token 2
				break;
			case SVMLexer.LWHP:
				code[i++] = SVMParser.LWHP;
				code[i++] = Integer.parseInt(ctx.offset.getText());
				break;
			case SVMLexer.SWHP:  // tokens.size() = 3
				code[i++] = SVMParser.SWHP; // token 1
				code[i++] = Integer.parseInt(ctx.offset.getText()); // token 2
				break;
			case SVMLexer.SWHR2:
				code[i++] = SVMParser.SWHR2;
				code[i++] = Integer.parseInt(ctx.offset.getText());
				break;
			case SVMLexer.LOADR1:
				code[i++] = SVMParser.LOADR1;
				break;
			case SVMLexer.LOADR2:
				code[i++] = SVMParser.LOADR2;
				break;
			case SVMLexer.LOADAL:
				code[i++] = SVMParser.LOADAL;
				break;
			case SVMLexer.STOREAL:
				code[i++] = SVMParser.STOREAL;
				break;
			case SVMLexer.STORER1:
				code[i++] = SVMParser.STORER1;
				break;
			case SVMLexer.STORER2:
				code[i++] = SVMParser.STORER2;
				break;
			case SVMLexer.LABEL:
				labelAdd.put(ctx.l.getText(),i);
				break;
			case SVMLexer.BRANCH:
				code[i++] = SVMParser.BRANCH;
                labelRef.put(i++,(ctx.l!=null? ctx.l.getText():null));
				break;
			case SVMLexer.BRANCHEQ:
				code[i++] = SVMParser.BRANCHEQ; 
                labelRef.put(i++,(ctx.l!=null? ctx.l.getText():null));
                break;
			case SVMLexer.BRANCHLESSEQ:
				code[i++] = SVMParser.BRANCHLESSEQ; 
                labelRef.put(i++,(ctx.l!=null? ctx.l.getText():null));
                break;
			case SVMLexer.BRANCHLESS:
				code[i++] = SVMParser.BRANCHLESS;
				labelRef.put(i++,(ctx.l!=null? ctx.l.getText():null));
				break;
			case SVMLexer.JR:
				code[i++] = SVMParser.JR;
				break;
			case SVMLexer.LOADRA:
				code[i++] = SVMParser.LOADRA;
				break;
			case SVMLexer.STORERA:
				code[i++] = SVMParser.STORERA;
				break;
			case SVMLexer.LOADRV:
				code[i++] = SVMParser.LOADRV;
				break;
			case SVMLexer.STORERV:
				code[i++] = SVMParser.STORERV;
				break;
			case SVMLexer.LOADFP:
				code[i++] = SVMParser.LOADFP;
				break;
			case SVMLexer.LWAFP:
				code[i++] = SVMParser.LWAFP;
				code[i++] = Integer.parseInt(ctx.n.getText());
				break;
			case SVMLexer.STOREFP:
				code[i++] = SVMParser.STOREFP;
				break;
			case SVMLexer.COPYFP:
				code[i++] = SVMParser.COPYFP;
				break;
			case SVMLexer.COPYAL:
				code[i++] = SVMParser.COPYAL;
				break;
			case SVMLexer.LOADHP:
				code[i++] = SVMParser.LOADHP;
				break;
			case SVMLexer.STOREHP:
				code[i++] = SVMParser.STOREHP;
				break;
			case SVMLexer.PRINT:
				code[i++] = SVMParser.PRINT;
				break;
			case SVMLexer.PRINTHP:
				code[i++] = SVMParser.PRINTHP;
				break;
			case SVMLexer.NEW:
				code[i++] = SVMParser.NEW;
				break;
			case SVMLexer.HALT:
				code[i++] = SVMParser.HALT;
				break;
			default:
	            break;	// Invalid instruction
    	}
    	return null;
    }

}
