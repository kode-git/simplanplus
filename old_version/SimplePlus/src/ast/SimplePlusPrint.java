package ast;

import java.util.HashMap;
import java.util.List;

import utils.BytecodeLabelTable;
import utils.SimplePlusBehaviorTypes;
import utils.SimplePlusError;
import utils.SimplePlusSTVariable;
import utils.SimplePlusSymbolTable;
import utils.SimplePlusTypes;

public class SimplePlusPrint extends SimplePlusStatement {
	
	private SimplePlusExp exp;
	
	
	
	// Constructor
	
	public SimplePlusPrint(SimplePlusExp exp) {
		this.exp = exp;
	}
	
	
	
	// Semantic analysis

	@Override
	public List<SimplePlusError> semanticAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction, boolean varExp) {
		return this.exp.semanticAnalysis(symbolTable, scopeFunction, varExp);
	}



	// Type analysis
	
	@Override
	public SimplePlusTypes typeAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction) {
		this.exp.typeAnalysis(symbolTable, scopeFunction);
		
		return SimplePlusTypes.VOID;
	}



	// Behavioral analysis
	
	@Override
	public void behavioralAnalysis(SimplePlusSymbolTable symbolTable, boolean branchElse, HashMap<String, SimplePlusSTVariable> scopeFunction, HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes> behaviorReference) {
		this.exp.behavioralAnalysis(symbolTable, branchElse, scopeFunction, behaviorReference);
	}



	// Bytecode generation
	
	@Override
	public String bytecodeGeneration(SimplePlusSymbolTable symbolTable, BytecodeLabelTable labelTable) {
		return this.exp.bytecodeGeneration(symbolTable, labelTable)
				+ "PUSH\n"
				+ "PRINT\n";
	}

}
