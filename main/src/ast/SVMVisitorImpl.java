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
    	visitChildren(ctx);
    	for (Integer refAdd: labelRef.keySet()) {
    		System.out.println("Label reference for cgen " + refAdd.intValue());
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
			case SVMLexer.STOREW:
				code[i++] = SVMParser.STOREW;
				break;
			case SVMLexer.LOADW:
				code[i++] = SVMParser.LOADW;
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
			case SVMLexer.JS:
				code[i++] = SVMParser.JS;
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
			case SVMLexer.STOREFP:
				code[i++] = SVMParser.STOREFP;
				break;
			case SVMLexer.COPYFP:
				code[i++] = SVMParser.COPYFP;
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
			case SVMLexer.HALT:
				code[i++] = SVMParser.HALT;
				break;             
			default:
	            break;	// Invalid instruction
    	}
    	return null;
    }

}
