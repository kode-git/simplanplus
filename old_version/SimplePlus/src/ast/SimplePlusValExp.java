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

public class SimplePlusValExp extends SimplePlusExp {
	
	private int value;
	
	
	
	// Constructor
	
	public SimplePlusValExp(int value) {
		this.value = value;
	}
	
	
	
	// Semantic analysis

	@Override
	public List<SimplePlusError> semanticAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction, boolean varExp) {
		List<SimplePlusError> errors = new LinkedList<SimplePlusError>();
		
		if(varExp) {
			errors.add(new SimplePlusError(SimplePlusErrorTypes.VAR_NOT_VAR));
		}
		
		return errors;
	}



	// Type analysis
	
	@Override
	public SimplePlusTypes typeAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction) {
		return SimplePlusTypes.INT;
	}



	// Behavioral analysis
	
	@Override
	public void behavioralAnalysis(SimplePlusSymbolTable symbolTable, boolean branchElse, HashMap<String, SimplePlusSTVariable> scopeFunction, HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes> behaviorReference) {
		
	}



	// Bytecode generation
	
	@Override
	public String bytecodeGeneration(SimplePlusSymbolTable symbolTable, BytecodeLabelTable labelTable) {
		return "WRITE_RA " + Integer.toString(this.value) + "\n";
	}

}
