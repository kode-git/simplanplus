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

public class SimplePlusArg extends SimplePlusBaseElement {
	
	private SimplePlusType type;
	private SimplePlusRef ref;
	private String id;
	
	
	
	// Constructor
	
	public SimplePlusArg(SimplePlusType type, SimplePlusRef ref, String id) {
		this.type = type;
		this.ref = ref;
		this.id = id;
	}
	
	
	
	// Setters and getters
	
	public SimplePlusType getType() {
		return this.type;
	}
	
	public SimplePlusRef getRef() {
		return this.ref;
	}
	
	public String getId() {
		return this.id;
	}
	
	
	
	// Semantic analysis

	@Override
	public List<SimplePlusError> semanticAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction, boolean varExp) {
		List<SimplePlusError> errors = new LinkedList<SimplePlusError>();
		
		if(symbolTable.isVariableHere(this.id)) {
			errors.add(new SimplePlusError(SimplePlusErrorTypes.VAR_FOUND, this.id));
		} else {
			symbolTable.addVariable(this.id, new SimplePlusSTVariable(0, this.type.getType(), this.ref != null, SimplePlusBehaviorTypes.UNDEFINED));
		}
		
		return errors;
	}



	// Type analysis
	
	@Override
	public SimplePlusTypes typeAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction) {
		SimplePlusTypes type = this.type.typeAnalysis(symbolTable, scopeFunction);
		if(type == SimplePlusTypes.VOID) {
			new SimplePlusError(SimplePlusErrorTypes.TYPE_VAR_VOID, this.id);
		}
		
		symbolTable.addVariable(this.id, new SimplePlusSTVariable(0, type, this.ref != null, SimplePlusBehaviorTypes.UNDEFINED));
		
		return SimplePlusTypes.VOID;
	}



	// Behavioral analysis
	
	@Override
	public void behavioralAnalysis(SimplePlusSymbolTable symbolTable, boolean branchElse, HashMap<String, SimplePlusSTVariable> scopeFunction, HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes> behaviorReference) {
		symbolTable.addVariable(this.id, new SimplePlusSTVariable(0, this.type.getType(), this.ref != null, SimplePlusBehaviorTypes.UNDEFINED));
	}



	// Bytecode generation
	
	@Override
	public String bytecodeGeneration(SimplePlusSymbolTable symbolTable, BytecodeLabelTable labelTable) {
		symbolTable.addVariable(this.id, new SimplePlusSTVariable(symbolTable.newOffset(), this.type.getType(), this.ref != null, SimplePlusBehaviorTypes.UNDEFINED));
		
		return "DECVAR\n"
				+ "PUSH_NUMBER " + symbolTable.variableOffset(this.id) + "\n"
				+ "WRITE_VARIABLE\n";
	}

}
