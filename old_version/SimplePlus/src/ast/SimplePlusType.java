package ast;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import utils.BytecodeLabelTable;
import utils.SimplePlusBehaviorTypes;
import utils.SimplePlusError;
import utils.SimplePlusSTVariable;
import utils.SimplePlusSymbolTable;
import utils.SimplePlusTypes;

public class SimplePlusType extends SimplePlusBaseElement {
	
	private SimplePlusTypes type;
	
	
	
	// Constructor
	
	public SimplePlusType(SimplePlusTypes type) {
		this.type = type;
	}
	
	
	
	// Setters and getters
	
	public SimplePlusTypes getType() {
		return this.type;
	}
	
	
	
	// Semantic analysis

	@Override
	public List<SimplePlusError> semanticAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction, boolean varExp) {
		return new LinkedList<SimplePlusError>();
	}



	// Type analysis
	
	@Override
	public SimplePlusTypes typeAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction) {
		return this.type;
	}



	// Behavioral analysis
	
	@Override
	public void behavioralAnalysis(SimplePlusSymbolTable symbolTable, boolean branchElse, HashMap<String, SimplePlusSTVariable> scopeFunction, HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes> behaviorReference) {
		
	}



	// Bytecode generation
	
	@Override
	public String bytecodeGeneration(SimplePlusSymbolTable symbolTable, BytecodeLabelTable labelTable) {
		return "";
	}

}
