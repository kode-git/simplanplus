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

public class SimplePlusNotExp extends SimplePlusExp {
	
	private SimplePlusExp exp;
	
	
	
	// Constructor
	
	public SimplePlusNotExp(SimplePlusExp exp) {
		this.exp = exp;
	}
	
	
	
	// Semantic analysis

	@Override
	public List<SimplePlusError> semanticAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction, boolean varExp) {
		List<SimplePlusError> errors = new LinkedList<SimplePlusError>();
		
		if(varExp) {
			errors.add(new SimplePlusError(SimplePlusErrorTypes.VAR_NOT_VAR));
		}
		
		errors.addAll(this.exp.semanticAnalysis(symbolTable, scopeFunction, varExp));
		
		return errors;
	}



	// Type analysis
	
	@Override
	public SimplePlusTypes typeAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction) {
		SimplePlusTypes type = this.exp.typeAnalysis(symbolTable, scopeFunction);
		if(type != SimplePlusTypes.BOOL) {
			new SimplePlusError(SimplePlusErrorTypes.TYPE_MISMATCH, SimplePlusTypes.BOOL, type);
		}
		
		return SimplePlusTypes.BOOL;
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
				+ "NOT\n";
	}

}
