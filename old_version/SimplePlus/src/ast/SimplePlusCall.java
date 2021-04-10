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

public class SimplePlusCall extends SimplePlusStatement {
	
	private String id;
	private List<SimplePlusExp> exps;
	
	
	
	// Constructor
	
	public SimplePlusCall(String id, List<SimplePlusExp> exps) {
		this.id = id;
		this.exps = exps;
	}
	
	
	
	// Semantic analysis

	@Override
	public List<SimplePlusError> semanticAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction, boolean varExp) {
		List<SimplePlusError> errors = new LinkedList<SimplePlusError>();
		
		if(!symbolTable.isFunction(this.id)) {
			errors.add(new SimplePlusError(SimplePlusErrorTypes.FUN_NOT_FOUND, this.id));
		} else if(this.exps.size() < symbolTable.functionArguments(this.id).size()) {
			errors.add(new SimplePlusError(SimplePlusErrorTypes.FUN_FEW_ARGS, this.id));
		} else if (this.exps.size() > symbolTable.functionArguments(this.id).size()) {
			errors.add(new SimplePlusError(SimplePlusErrorTypes.FUN_MANY_ARGS, this.id));
		} else {
			int argument = 0;
			for(SimplePlusExp exp : this.exps) {
				errors.addAll(exp.semanticAnalysis(symbolTable, scopeFunction, symbolTable.functionArguments(this.id).get(argument).getReference()));
				argument = argument + 1;
			}
		}
		
		return errors;
	}



	// Type analysis
	
	@Override
	public SimplePlusTypes typeAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction) {
		List<SimplePlusSTVariable> arguments = symbolTable.functionArguments(this.id);
		for(int i = 0; i < this.exps.size(); i++) {
			SimplePlusTypes type = this.exps.get(i).typeAnalysis(symbolTable, scopeFunction);
			if(arguments.get(i).getType() != type) {
				new SimplePlusError(SimplePlusErrorTypes.TYPE_MISMATCH, arguments.get(i).getType(), type);
			}
		}
		
		return symbolTable.functionReturnType(this.id);
	}



	// Behavioral analysis
	
	@Override
	public void behavioralAnalysis(SimplePlusSymbolTable symbolTable, boolean branchElse, HashMap<String, SimplePlusSTVariable> scopeFunction, HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes> behaviorReference) {
		for(SimplePlusExp exp : this.exps) {
			exp.behavioralAnalysis(symbolTable, branchElse, scopeFunction, behaviorReference);
		}
		
		if(symbolTable.functionIsEvaluated(this.id)) {
			if(scopeFunction != null) {
				int i = 0;
				for(SimplePlusSTVariable argument : symbolTable.functionArguments(this.id)) {
					if(argument.getReference()) {
						String argumentActual = ((SimplePlusVarExp) this.exps.get(i)).getId();
						if(symbolTable.isVariableInFunction(argumentActual, scopeFunction)) {
							SimplePlusBehaviorTypes behavior = symbolTable.variableBehavior(argumentActual);
							SimplePlusBehaviorTypes behaviorNew = BehaviorTypeOperation.seq(behavior, argument.getBehavior());
							if(behaviorNew == SimplePlusBehaviorTypes.ERROR) {
								new SimplePlusError(SimplePlusErrorTypes.BEHAVIOR_SEQ_ERROR, behavior, argument.getBehavior());
							} else {
								symbolTable.variableSetBehavior(argumentActual, behaviorNew);
							}
						} else {
							SimplePlusSTVariable variable = symbolTable.variableVariable(argumentActual);
							if(behaviorReference.containsKey(variable)) {
								SimplePlusBehaviorTypes behavior = behaviorReference.get(variable);
								SimplePlusBehaviorTypes behaviorNew;
								if(branchElse) {
									behaviorNew = BehaviorTypeOperation.max(behavior, argument.getBehavior());
								} else {
									behaviorNew = BehaviorTypeOperation.seq(behavior, argument.getBehavior());
								}
								if(behaviorNew == SimplePlusBehaviorTypes.ERROR) {
									new SimplePlusError(SimplePlusErrorTypes.BEHAVIOR_SEQ_ERROR, behavior, argument.getBehavior());
								} else {
									behaviorReference.replace(variable, behaviorNew);
								}
							} else {
								behaviorReference.put(variable, argument.getBehavior());
							}
						}
						i = i + 1;
					}
				}
				
				for(SimplePlusSTVariable variable : symbolTable.functionBehaviorReference(this.id).keySet()) {
					if(behaviorReference.containsKey(variable)) {
						SimplePlusBehaviorTypes behavior = behaviorReference.get(variable);
						SimplePlusBehaviorTypes behaviorNew;
						if(branchElse) {
							behaviorNew = BehaviorTypeOperation.max(behavior, symbolTable.functionBehaviorReference(this.id).get(variable));
						} else {
							behaviorNew = BehaviorTypeOperation.seq(behavior, symbolTable.functionBehaviorReference(this.id).get(variable));
						}
						if(behaviorNew == SimplePlusBehaviorTypes.ERROR) {
							new SimplePlusError(SimplePlusErrorTypes.BEHAVIOR_SEQ_ERROR, behavior, symbolTable.functionBehaviorReference(this.id).get(variable));
						} else {
							behaviorReference.replace(variable, behaviorNew);
						}
					} else {
						behaviorReference.put(variable, symbolTable.functionBehaviorReference(this.id).get(variable));
					}
				}
			} else {
				int i = 0;
				for(SimplePlusSTVariable argument : symbolTable.functionArguments(this.id)) {
					if(argument.getReference()) {
						SimplePlusBehaviorTypes behavior = symbolTable.variableBehavior(((SimplePlusVarExp) this.exps.get(i)).getId());
						SimplePlusBehaviorTypes behaviorNew;
						if(branchElse) {
							behaviorNew = BehaviorTypeOperation.max(behavior, argument.getBehavior());
						} else {
							behaviorNew = BehaviorTypeOperation.seq(behavior, argument.getBehavior());
						}
						if(behaviorNew == SimplePlusBehaviorTypes.ERROR) {
							new SimplePlusError(SimplePlusErrorTypes.BEHAVIOR_SEQ_ERROR, behavior, argument.getBehavior());
						} else {
							symbolTable.variableSetBehavior(((SimplePlusVarExp) this.exps.get(i)).getId(),  behaviorNew);
						}
					}
					i = i + 1;
				}
				
				for(SimplePlusSTVariable variable : symbolTable.functionBehaviorReference(this.id).keySet()) {
					SimplePlusBehaviorTypes behaviorNew;
					if(branchElse) {
						behaviorNew = BehaviorTypeOperation.max(variable.getBehavior(), symbolTable.functionBehaviorReference(this.id).get(variable));
					} else {
						behaviorNew = BehaviorTypeOperation.seq(variable.getBehavior(), symbolTable.functionBehaviorReference(this.id).get(variable));
					}
					if(behaviorNew == SimplePlusBehaviorTypes.ERROR) {
						new SimplePlusError(SimplePlusErrorTypes.BEHAVIOR_SEQ_ERROR, variable.getBehavior(), symbolTable.functionBehaviorReference(this.id).get(variable));
					} else {
						variable.setBehavior(behaviorNew);
					}
				}
			}
		}
	}



	// Bytecode generation
	
	@Override
	public String bytecodeGeneration(SimplePlusSymbolTable symbolTable, BytecodeLabelTable labelTable) {
		String bytecode = "CALL_BEGIN\n";
		
		for(int i = this.exps.size() - 1; i > -1; i--) {
			if(symbolTable.functionArguments(this.id).get(i).getReference()) { // è richisto il riferimento -> exp è un id di variabile
				bytecode = bytecode
						+ "PUSH_NUMBER " + symbolTable.variableOffset(((SimplePlusVarExp) this.exps.get(i)).getId()) + "\n";
				if(symbolTable.variableIsReference(((SimplePlusVarExp) this.exps.get(i)).getId())) {
					bytecode = bytecode
							+ "READ_VARIABLE\n"
							+ "PUSH\n";
				}
			} else {
				bytecode = bytecode +
						this.exps.get(i).bytecodeGeneration(symbolTable, labelTable)
						+ "PUSH\n";
			}
			
		}
		
		bytecode = bytecode
				+ "PUSH_LABEL " + symbolTable.functionLabel(this.id) + "\n"
				+ "CALL_JUMP\n" ;
		
		return bytecode;
	}

}
