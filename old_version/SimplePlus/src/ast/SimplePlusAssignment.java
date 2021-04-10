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

public class SimplePlusAssignment extends SimplePlusStatement {
	
	private String id;
	private SimplePlusExp exp;
	
	
	
	// Constructor
	
	public SimplePlusAssignment(String id, SimplePlusExp exp) {
		this.id = id;
		this.exp = exp;
	}
	
	
	
	// Semantic analysis

	@Override
	public List<SimplePlusError> semanticAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction, boolean varExp) {
		List<SimplePlusError> errors = new LinkedList<SimplePlusError>();
		
		errors.addAll(this.exp.semanticAnalysis(symbolTable, scopeFunction, varExp));
		
		if(!symbolTable.isVariable(this.id)) {
			errors.add(new SimplePlusError(SimplePlusErrorTypes.VAR_NOT_FOUND, this.id));
		}
		
		return errors;
	}



	// Type analysis
	
	@Override
	public SimplePlusTypes typeAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction) {
		SimplePlusTypes type = this.exp.typeAnalysis(symbolTable, scopeFunction);
		
		if(symbolTable.variableType(this.id) != type) {
			new SimplePlusError(SimplePlusErrorTypes.TYPE_MISMATCH, symbolTable.variableType(this.id), type);
		}
				
		return SimplePlusTypes.VOID;
	}



	// Behavioral analysis
	
	@Override
	public void behavioralAnalysis(SimplePlusSymbolTable symbolTable, boolean branchElse, HashMap<String, SimplePlusSTVariable> scopeFunction, HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes> behaviorReference) {
		this.exp.behavioralAnalysis(symbolTable, branchElse, scopeFunction, behaviorReference);
		
		if(scopeFunction != null ) {
			if(symbolTable.isVariableInFunction(this.id, scopeFunction)) {
				SimplePlusBehaviorTypes behavior = symbolTable.variableBehavior(this.id);
				SimplePlusBehaviorTypes behaviorNew;
				if(branchElse) {
					behaviorNew = BehaviorTypeOperation.max(behavior, SimplePlusBehaviorTypes.RDWR);
				} else {
					behaviorNew = BehaviorTypeOperation.seq(behavior, SimplePlusBehaviorTypes.RDWR);
				}
				if(behaviorNew == SimplePlusBehaviorTypes.ERROR) {
					new SimplePlusError(SimplePlusErrorTypes.BEHAVIOR_SEQ_ERROR, behavior, SimplePlusBehaviorTypes.RDWR);
				} else {
					symbolTable.variableSetBehavior(this.id, behaviorNew);
				}
			} else {
				SimplePlusSTVariable variable = symbolTable.variableVariable(this.id);
				if(behaviorReference.containsKey(variable)) {
					SimplePlusBehaviorTypes behavior = behaviorReference.get(variable);
					SimplePlusBehaviorTypes behaviorNew;
					if(branchElse) {
						behaviorNew = BehaviorTypeOperation.max(behavior, SimplePlusBehaviorTypes.RDWR);
					} else {
						behaviorNew = BehaviorTypeOperation.seq(behavior, SimplePlusBehaviorTypes.RDWR);
					}
					if(behaviorNew == SimplePlusBehaviorTypes.ERROR) {
						new SimplePlusError(SimplePlusErrorTypes.BEHAVIOR_SEQ_ERROR, behavior, SimplePlusBehaviorTypes.RDWR);
					} else {
						behaviorReference.replace(variable, behaviorNew);
					}
				} else {
					behaviorReference.put(variable, SimplePlusBehaviorTypes.RDWR);
				}
			}
		} else {
			SimplePlusBehaviorTypes behavior = symbolTable.variableBehavior(this.id);
			SimplePlusBehaviorTypes behaviorNew;
			if(branchElse) {
				behaviorNew = BehaviorTypeOperation.max(behavior, SimplePlusBehaviorTypes.RDWR);
			} else {
				behaviorNew = BehaviorTypeOperation.seq(behavior, SimplePlusBehaviorTypes.RDWR);
			}
			if(behaviorNew == SimplePlusBehaviorTypes.ERROR) {
				new SimplePlusError(SimplePlusErrorTypes.BEHAVIOR_SEQ_ERROR, behavior, SimplePlusBehaviorTypes.RDWR);
			} else {
				symbolTable.variableSetBehavior(this.id,  behaviorNew);
			}
		}
	}



	// Bytecode generation
	
	@Override
	public String bytecodeGeneration(SimplePlusSymbolTable symbolTable, BytecodeLabelTable labelTable) {
			String bytecode = this.exp.bytecodeGeneration(symbolTable, labelTable)
				+ "PUSH\n"
				+ "PUSH_NUMBER " + symbolTable.variableOffset(this.id) + "\n";
		
		if(symbolTable.variableIsReference(this.id)) {
			bytecode = bytecode
					+ "READ_VARIABLE\n"
					+ "PUSH\n";
		}
		
		bytecode = bytecode
				+ "WRITE_VARIABLE\n";
		
		return bytecode;
	}

}
