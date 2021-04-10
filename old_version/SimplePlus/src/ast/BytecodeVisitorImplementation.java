package ast;

import parser.BytecodeBaseVisitor;
import parser.BytecodeLexer;
import parser.BytecodeParser;
import utils.BytecodeLabelTable;
import vm.SimplePlusVirtualMachine;

public class BytecodeVisitorImplementation extends BytecodeBaseVisitor<Void> {
	
	private int[] code;
	private int i;
	private BytecodeLabelTable labelTable;
	private int vp;
	
	
	
	// Constructor
	
	public BytecodeVisitorImplementation() {
		this.code  = new int[SimplePlusVirtualMachine.CODE_SIZE];
		this.i = 0;
		this.labelTable = new BytecodeLabelTable();
		this.vp = 0;
	}
	
	
	
	// Setters and getters
	
	public int[] getCode() {
		return this.code;
	}
	
	public int getVp() {
		return this.vp;
	}
	
	
	
	// Aggiunge un'istruzione al codice
	
	private void addInstruction(int instruction) {
		this.code[this.i]= instruction;
		this.i = this.i + 1;
		if(this.i > SimplePlusVirtualMachine.CODE_SIZE - 1) {
			throw new IllegalArgumentException("VirtualMachine: out of mem in code loading");
		}
	}
	
	
	
	// Visit methods
	
	@Override
	public Void visitBytecode(BytecodeParser.BytecodeContext ctx) {
		for(BytecodeParser.InstructionContext instructionCtx : ctx.instruction()) {
			visitInstruction(instructionCtx);
		}
		
		for(int i = 0; i < code.length; i++) {
			if(this.code[i] == BytecodeParser.PUSH_LABEL) {
				i = i + 1;
				this.code[i] = labelTable.getLabelToInstruction(labelTable.getIdToLabel(this.code[i]));
			}
		}
		
		return null;
	}
	
	@Override
	public Void visitInstruction(BytecodeParser.InstructionContext ctx) {
		switch(ctx.getStart().getType()) {
		case BytecodeLexer.WRITE_RA:
			this.addInstruction(BytecodeParser.WRITE_RA);
			this.addInstruction(Integer.parseInt(ctx.NUMBER().getText()));
			break;
		case BytecodeLexer.WRITE_VARIABLE:
			this.addInstruction(BytecodeParser.WRITE_VARIABLE);
			break;
		case BytecodeLexer.READ_VARIABLE:
			this.addInstruction(BytecodeParser.READ_VARIABLE);
			break;
		case BytecodeLexer.PUSH:
			this.addInstruction(BytecodeParser.PUSH);
			break;
		case BytecodeLexer.PUSH_NUMBER:
			this.addInstruction(BytecodeParser.PUSH_NUMBER);
			this.addInstruction(Integer.parseInt(ctx.NUMBER().getText()));
			break;
		case BytecodeLexer.PUSH_LABEL:
			int labelId = labelTable.newLabelId();
			this.labelTable.storeIdToLabel(labelId, ctx.LABEL().getText());
			this.addInstruction(BytecodeParser.PUSH_LABEL);
			this.addInstruction(labelId);
			break;
		case BytecodeLexer.HALT:
			this.addInstruction(BytecodeParser.HALT);
			break;
		case BytecodeLexer.DECVAR:
			this.vp = this.vp + 1;
			break;
		case BytecodeLexer.NEG:
			this.addInstruction(BytecodeParser.NEG);
			break;
		case BytecodeLexer.NOT:
			this.addInstruction(BytecodeParser.NOT);
			break;
		case BytecodeLexer.SUM:
			this.addInstruction(BytecodeParser.SUM);
			break;
		case BytecodeLexer.SUB:
			this.addInstruction(BytecodeParser.SUB);
			break;
		case BytecodeLexer.MULT:
			this.addInstruction(BytecodeParser.MULT);
			break;
		case BytecodeLexer.DIV:
			this.addInstruction(BytecodeParser.DIV);
			break;
		case BytecodeLexer.AND:
			this.addInstruction(BytecodeParser.AND);
			break;
		case BytecodeLexer.OR:
			this.addInstruction(BytecodeParser.OR);
			break;
		case BytecodeLexer.EQ:
			this.addInstruction(BytecodeParser.EQ);
			break;
		case BytecodeLexer.NEQ:
			this.addInstruction(BytecodeParser.NEQ);
			break;
		case BytecodeLexer.LT:
			this.addInstruction(BytecodeParser.LT);
			break;
		case BytecodeLexer.LEQ:
			this.addInstruction(BytecodeParser.LEQ);
			break;
		case BytecodeLexer.GT:
			this.addInstruction(BytecodeParser.GT);
			break;
		case BytecodeLexer.GEQ:
			this.addInstruction(BytecodeParser.GEQ);
			break;
		case BytecodeLexer.PRINT:
			this.addInstruction(BytecodeParser.PRINT);
			break;
		case BytecodeLexer.LABEL:
			this.labelTable.storeLabelToInstruction(ctx.LABEL().getText(), i);
			break;
		case BytecodeLexer.JUMP:
			this.addInstruction(BytecodeParser.JUMP);
			break;
		case BytecodeLexer.JUMP_TRUE:
			this.addInstruction(BytecodeParser.JUMP_TRUE);
			break;
		case BytecodeLexer.DECFUN_BEGIN:
			this.addInstruction(BytecodeParser.DECFUN_BEGIN);
			break;
		case BytecodeLexer.DECFUN_END:
			this.addInstruction(BytecodeParser.DECFUN_END);
			break;
		case BytecodeLexer.RET:
			this.addInstruction(BytecodeParser.RET);
			break;
		case BytecodeLexer.CALL_BEGIN:
			this.addInstruction(BytecodeParser.CALL_BEGIN);
			break;
		case BytecodeLexer.CALL_JUMP:
			this.addInstruction(BytecodeParser.CALL_JUMP);
			break;
		}
		
		return null;
	}

}
