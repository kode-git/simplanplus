package ast;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import utils.BytecodeLabelTable;
import utils.SimplePlusBehaviorTypes;
import utils.SimplePlusError;
import utils.SimplePlusErrorTypes;
import utils.SimplePlusSTVariable;
import utils.SimplePlusSymbolTable;
import utils.SimplePlusTypes;

public class SimplePlusBinExpSub extends SimplePlusExp {
	
	private SimplePlusExp left;
	private SimplePlusExp right;
	
	
	
	// Constructor
	
	public SimplePlusBinExpSub(SimplePlusExp left, SimplePlusExp right) {
		this.left = left;
		this.right = right;
	}
	
	
	
	// Semantic analysis

	@Override
	public List<SimplePlusError> semanticAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction, boolean varExp) {
		List<SimplePlusError> errors = new LinkedList<SimplePlusError>();
		
		if(varExp) {
			errors.add(new SimplePlusError(SimplePlusErrorTypes.VAR_NOT_VAR));
		}
		
		errors.addAll(this.left.semanticAnalysis(symbolTable, scopeFunction, varExp));
		
		errors.addAll(this.right.semanticAnalysis(symbolTable, scopeFunction, varExp));
		
		return errors;
	}
	
	
	
	// Type analysis
	
	@Override
	public SimplePlusTypes typeAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction) {
		SimplePlusTypes typeLeft = this.left.typeAnalysis(symbolTable, scopeFunction);
		if(typeLeft != SimplePlusTypes.INT) {
			new SimplePlusError(SimplePlusErrorTypes.TYPE_MISMATCH, SimplePlusTypes.INT, typeLeft);
		}
		
		SimplePlusTypes typeRight = this.right.typeAnalysis(symbolTable, scopeFunction);
		if(typeRight != SimplePlusTypes.INT) {
			new SimplePlusError(SimplePlusErrorTypes.TYPE_MISMATCH, SimplePlusTypes.INT, typeRight);
		}
		
		return SimplePlusTypes.INT;
	}
	
	
	
	// Behavioral analysis
	
	@Override
	public void behavioralAnalysis(SimplePlusSymbolTable symbolTable, boolean branchElse, HashMap<String, SimplePlusSTVariable> scopeFunction, HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes> behaviorReference) {
		this.left.behavioralAnalysis(symbolTable, branchElse, scopeFunction, behaviorReference);
		
		this.right.behavioralAnalysis(symbolTable, branchElse, scopeFunction, behaviorReference);
	}



	// Bytecode generation
	
	@Override
	public String bytecodeGeneration(SimplePlusSymbolTable symbolTable, BytecodeLabelTable labelTable) {
		return this.left.bytecodeGeneration(symbolTable, labelTable)
				+ "PUSH\n"
				+ this.right.bytecodeGeneration(symbolTable, labelTable)
				+ "PUSH\n"
				+ "SUB\n";
	}

}
