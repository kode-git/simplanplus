package ast;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import utils.BehaviorTypeOperation;
import utils.BytecodeLabelTable;
import utils.SimplePlusBehaviorTypes;
import utils.SimplePlusError;
import utils.SimplePlusErrorTypes;
import utils.SimplePlusSTVariable;
import utils.SimplePlusSymbolTable;
import utils.SimplePlusTypes;

public class SimplePlusDeletion extends SimplePlusStatement {
	
	private String id;
	
	
	
	// Constructor
	
	public SimplePlusDeletion(String id) {
		this.id = id;
	}
	
	
	
	// Semantic analysis

	@Override
	public List<SimplePlusError> semanticAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction, boolean varExp) {
		List<SimplePlusError> errors = new LinkedList<SimplePlusError>();
		
		if(!symbolTable.isVariable(this.id)) {
			errors.add(new SimplePlusError(SimplePlusErrorTypes.VAR_NOT_FOUND, this.id));
		} else {
			if(scopeFunction != null) {
				if(symbolTable.isVariableInFunction(this.id, scopeFunction)) {
					symbolTable.deleteVariable(this.id);
				}
			} else {
				symbolTable.deleteVariable(this.id);
			}
		}
		
		return errors;
	}



	// Type analysis
	
	@Override
	public SimplePlusTypes typeAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction) {
		if(scopeFunction != null) {
			if(symbolTable.isVariableInFunction(this.id, scopeFunction)) {
				symbolTable.deleteVariable(this.id);
			}
		} else {
			symbolTable.deleteVariable(this.id);
		}
		
		return SimplePlusTypes.VOID;
	}



	// Behavioral analysis
	
	@Override
	public void behavioralAnalysis(SimplePlusSymbolTable symbolTable, boolean branchElse, HashMap<String, SimplePlusSTVariable> scopeFunction, HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes> behaviorReference) {
		if(scopeFunction != null) {
			if(symbolTable.isVariableInFunction(this.id, scopeFunction)) {
				SimplePlusBehaviorTypes behavior = symbolTable.variableBehavior(this.id);
				SimplePlusBehaviorTypes behaviorNew;
				if(branchElse) {
					behaviorNew = BehaviorTypeOperation.max(behavior, SimplePlusBehaviorTypes.DELETE);
				} else {
					behaviorNew = BehaviorTypeOperation.seq(behavior, SimplePlusBehaviorTypes.DELETE);
				}
				if(behaviorNew == SimplePlusBehaviorTypes.ERROR) {
					new SimplePlusError(SimplePlusErrorTypes.BEHAVIOR_SEQ_ERROR, behavior, SimplePlusBehaviorTypes.DELETE);
				} else {
					symbolTable.variableSetBehavior(this.id, behaviorNew);
				}
			} else {
				SimplePlusSTVariable variable = symbolTable.variableVariable(this.id);
				if(behaviorReference.containsKey(variable)) {
					SimplePlusBehaviorTypes behavior = behaviorReference.get(variable);
					SimplePlusBehaviorTypes behaviorNew;
					if(branchElse) {
						behaviorNew = BehaviorTypeOperation.max(behavior, SimplePlusBehaviorTypes.DELETE);
					} else {
						behaviorNew = BehaviorTypeOperation.seq(behavior, SimplePlusBehaviorTypes.DELETE);
					}
					if(behaviorNew == SimplePlusBehaviorTypes.ERROR) {
						new SimplePlusError(SimplePlusErrorTypes.BEHAVIOR_SEQ_ERROR, behavior, SimplePlusBehaviorTypes.DELETE);
					} else {
						behaviorReference.replace(variable, behaviorNew);
					}
				} else {
					behaviorReference.put(variable, SimplePlusBehaviorTypes.DELETE);
				}
			}
		} else {
			SimplePlusBehaviorTypes behavior = symbolTable.variableBehavior(this.id);
			SimplePlusBehaviorTypes behaviorNew;
			if(branchElse) {
				behaviorNew = BehaviorTypeOperation.max(behavior, SimplePlusBehaviorTypes.DELETE);
			} else {
				behaviorNew = BehaviorTypeOperation.seq(behavior, SimplePlusBehaviorTypes.DELETE);
			}
			if(behaviorNew == SimplePlusBehaviorTypes.ERROR) {
				new SimplePlusError(SimplePlusErrorTypes.BEHAVIOR_SEQ_ERROR, behavior, SimplePlusBehaviorTypes.DELETE);
			} else {
				symbolTable.variableSetBehavior(this.id, behaviorNew);
			}
		}
	}



	// Bytecode generation
	
	@Override
	public String bytecodeGeneration(SimplePlusSymbolTable symbolTable, BytecodeLabelTable labelTable) {
		return "";
	}

}
