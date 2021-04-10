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

public class SimplePlusDecVar extends SimplePlusStatement {
	
	private SimplePlusType type;
	private String id;
	private SimplePlusExp exp;
	
	
	
	// Constructor
	
	public SimplePlusDecVar(SimplePlusType type, String id, SimplePlusExp exp) {
		this.type = type;
		this.id = id;
		this.exp = exp;
	}
	
	
	
	// Semantic analysis

	@Override
	public List<SimplePlusError> semanticAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction, boolean varExp) {
		List<SimplePlusError> errors = new LinkedList<SimplePlusError>();
		
		SimplePlusBehaviorTypes behavior = SimplePlusBehaviorTypes.UNDEFINED;
		if(this.exp != null) {
			behavior = SimplePlusBehaviorTypes.RDWR;
			errors.addAll(this.exp.semanticAnalysis(symbolTable, scopeFunction, varExp));
		}
		
		if(symbolTable.isVariableHere(this.id)) {
			errors.add(new SimplePlusError(SimplePlusErrorTypes.VAR_FOUND, this.id));
		} else {
			symbolTable.addVariable(this.id, new SimplePlusSTVariable(symbolTable.newOffset(), this.type.getType(), false, behavior));
		}
		
		return errors;
	}



	// Type analysis
	
	@Override
	public SimplePlusTypes typeAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction) {
		SimplePlusTypes typeVar = this.type.typeAnalysis(symbolTable, scopeFunction);
		if(typeVar == SimplePlusTypes.VOID) {
			new SimplePlusError(SimplePlusErrorTypes.TYPE_VAR_VOID, this.id);
		}
		
		SimplePlusBehaviorTypes behavior = SimplePlusBehaviorTypes.UNDEFINED;
		if(this.exp != null) {
			behavior = SimplePlusBehaviorTypes.RDWR;
			SimplePlusTypes typeExp = this.exp.typeAnalysis(symbolTable, scopeFunction);
			if(typeVar != typeExp) {
				new SimplePlusError(SimplePlusErrorTypes.TYPE_MISMATCH, typeVar, typeExp);
			}
		}
		
		symbolTable.addVariable(this.id, new SimplePlusSTVariable(symbolTable.newOffset(), typeVar, false, behavior));
		
		return SimplePlusTypes.VOID;
	}



	// Behavioral analysis
	
	@Override
	public void behavioralAnalysis(SimplePlusSymbolTable symbolTable, boolean branchElse, HashMap<String, SimplePlusSTVariable> scopeFunction, HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes> behaviorReference) {
		SimplePlusBehaviorTypes behavior = SimplePlusBehaviorTypes.UNDEFINED;
		if(this.exp != null) {
			behavior = SimplePlusBehaviorTypes.RDWR;
			this.exp.behavioralAnalysis(symbolTable, branchElse, scopeFunction, behaviorReference);
		}
		
		if(symbolTable.isVariableHere(this.id)) {
			if(symbolTable.variableBehavior(this.id) == SimplePlusBehaviorTypes.DELETE) {
				symbolTable.variableSetVariable(this.id,  new SimplePlusSTVariable(symbolTable.newOffset(), this.type.getType(), false, behavior));
			} else {
				new SimplePlusError(SimplePlusErrorTypes.BEHAVIOR_VAR_FOUND, this.id);
			}
		} else {
			symbolTable.addVariable(this.id, new SimplePlusSTVariable(symbolTable.newOffset(), this.type.getType(), false, behavior));
		}
	}



	// Bytecode generation
	
	@Override
	public String bytecodeGeneration(SimplePlusSymbolTable symbolTable, BytecodeLabelTable labelTable) {
		String bytecode = "DECVAR\n";
		
		SimplePlusBehaviorTypes behavior = SimplePlusBehaviorTypes.UNDEFINED;
		if(this.exp != null) {
			behavior = SimplePlusBehaviorTypes.RDWR;
			bytecode = bytecode
					+ this.exp.bytecodeGeneration(symbolTable, labelTable);
		} else {
			bytecode = bytecode
					+ "WRITE_RA 0\n";
		}
		bytecode = bytecode
				+ "PUSH\n";
		
		symbolTable.addVariable(this.id, new SimplePlusSTVariable(symbolTable.newOffset(), this.type.getType(), false, behavior));
		
		bytecode = bytecode
				+ "PUSH_NUMBER " + symbolTable.variableOffset(this.id) + "\n"
				+ "WRITE_VARIABLE\n";
		
		return bytecode;
	}

}
