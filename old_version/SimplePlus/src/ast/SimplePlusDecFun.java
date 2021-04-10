package ast;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import utils.BytecodeLabelTable;
import utils.SimplePlusBehaviorTypes;
import utils.SimplePlusError;
import utils.SimplePlusErrorTypes;
import utils.SimplePlusSTFunction;
import utils.SimplePlusSTVariable;
import utils.SimplePlusSymbolTable;
import utils.SimplePlusTypes;
import utils.TypeConverter;

public class SimplePlusDecFun extends SimplePlusStatement {
	
	private SimplePlusType type;
	private String id;
	private List<SimplePlusArg> args;
	private SimplePlusBlock block;
	
	
	
	// Constructor
	
	public SimplePlusDecFun(SimplePlusType type, String id, List<SimplePlusArg> args, SimplePlusBlock block) {
		this.type = type;
		this.id = id;
		this.args = args;
		this.block = block;
	}
	
	
	
	// Semantic analysis

	@Override
	public List<SimplePlusError> semanticAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction, boolean varExp) {
		List<SimplePlusError> errors = new LinkedList<SimplePlusError>();
		
		if(symbolTable.isFunctionHere(this.id)) {
			errors.add(new SimplePlusError(SimplePlusErrorTypes.FUN_FOUND, this.id));
		} else {
			List<SimplePlusSTVariable> arguments = new LinkedList<SimplePlusSTVariable>();
			for(SimplePlusArg arg : this.args) {
				arguments.add(new SimplePlusSTVariable(0, arg.getType().getType(), arg.getRef() != null, SimplePlusBehaviorTypes.UNDEFINED));
			}
			symbolTable.addFunction(this.id, new SimplePlusSTFunction(this.type.getType(), arguments, false, new HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes>(), "LABEL"));
		}
		
		HashMap<String, SimplePlusSTVariable> scopeFunctionHere = symbolTable.functionScope();
		
		symbolTable.openScope();
		
		for(SimplePlusArg arg : this.args) {
			errors.addAll(arg.semanticAnalysis(symbolTable, scopeFunctionHere, varExp));
		}
		
		errors.addAll(this.block.semanticAnalysis(symbolTable, scopeFunctionHere, varExp));
		
		symbolTable.closeScope();
		
		return errors;
	}



	// Type analysis
	
	@Override
	public SimplePlusTypes typeAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction) {
		SimplePlusTypes typeReturn = this.type.typeAnalysis(symbolTable, scopeFunction);
		
		List<SimplePlusSTVariable> arguments = new LinkedList<SimplePlusSTVariable>();
		for(SimplePlusArg arg : this.args) {
			arguments.add(new SimplePlusSTVariable(0, arg.getType().typeAnalysis(symbolTable, scopeFunction), arg.getRef() != null, SimplePlusBehaviorTypes.UNDEFINED));
		}
		symbolTable.addFunction(this.id, new SimplePlusSTFunction(typeReturn, arguments, false, new HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes>(), "LABEL"));
		
		HashMap<String, SimplePlusSTVariable> scopeFunctionHere = symbolTable.functionScope();
		
		symbolTable.openScope();
		
		for(SimplePlusArg arg : this.args) {
			arg.typeAnalysis(symbolTable, scopeFunctionHere);
		}
		
		SimplePlusTypes typeBlock = this.block.typeAnalysis(symbolTable, scopeFunctionHere);
		if(TypeConverter.typeToReturn(typeReturn) != TypeConverter.typeToReturn(typeBlock)) {
			new SimplePlusError(SimplePlusErrorTypes.TYPE_MISMATCH, typeReturn, typeBlock);
		}
		
		symbolTable.closeScope();
		
		return SimplePlusTypes.VOID;
	}



	// Behavioral analysis
	
	@Override
	public void behavioralAnalysis(SimplePlusSymbolTable symbolTable, boolean branchElse, HashMap<String, SimplePlusSTVariable> scopeFunction, HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes> behaviorReference) {
		List<SimplePlusSTVariable> arguments = new LinkedList<SimplePlusSTVariable>();
		for(SimplePlusArg arg : this.args) {
			arguments.add(new SimplePlusSTVariable(0, arg.getType().getType(), arg.getRef() != null, SimplePlusBehaviorTypes.UNDEFINED));
		}
		symbolTable.addFunction(this.id, new SimplePlusSTFunction(this.type.getType(), arguments, false, new HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes>(), "LABEL"));
		
		HashMap<String, SimplePlusSTVariable> scopeFunctionHere = symbolTable.functionScope();
		
		symbolTable.openScope();
		
		int argumentPosition = 0;
		for(SimplePlusArg arg : this.args) {
			arg.behavioralAnalysis(symbolTable, branchElse, scopeFunctionHere, behaviorReference);
			symbolTable.variableSetArgumentPosition(arg.getId(), argumentPosition);
			argumentPosition = argumentPosition + 1;
		}
		
		this.block.behavioralAnalysis(symbolTable, branchElse, scopeFunctionHere, symbolTable.functionBehaviorReference(this.id));
		
		HashMap<String, SimplePlusSTVariable> scopeFunctionArgument = symbolTable.functionScope();
		List<SimplePlusSTVariable> behaviorArgument = symbolTable.functionArguments(this.id);
		for(String id : scopeFunctionArgument.keySet()) {
			behaviorArgument.get(symbolTable.variableArgumentPosition(id)).setBehavior(symbolTable.variableBehavior(id));
		}
		
		symbolTable.closeScope();
		
		symbolTable.functionEvaluate(this.id);
		
		symbolTable.openScope();
		
		argumentPosition = 0;
		for(SimplePlusArg arg : this.args) {
			arg.behavioralAnalysis(symbolTable, branchElse, scopeFunctionHere, behaviorReference);
			symbolTable.variableSetArgumentPosition(arg.getId(), argumentPosition);
			argumentPosition = argumentPosition + 1;
		}
		
		this.block.behavioralAnalysis(symbolTable, branchElse, scopeFunctionHere, symbolTable.functionBehaviorReference(this.id));
				
		scopeFunctionArgument = symbolTable.functionScope();
		behaviorArgument = symbolTable.functionArguments(this.id);
		for(String id : scopeFunctionArgument.keySet()) {
			behaviorArgument.get(symbolTable.variableArgumentPosition(id)).setBehavior(symbolTable.variableBehavior(id));
		}
		
		symbolTable.closeScope();
	}



	// Bytecode generation
	
	@Override
	public String bytecodeGeneration(SimplePlusSymbolTable symbolTable, BytecodeLabelTable labelTable) {
		String labelFunction = labelTable.newLabel();
		
		List<SimplePlusSTVariable> arguments = new LinkedList<SimplePlusSTVariable>();
		for(SimplePlusArg arg : this.args) {
			arguments.add(new SimplePlusSTVariable(0, arg.getType().getType(), arg.getRef() != null, SimplePlusBehaviorTypes.UNDEFINED));
		}
		symbolTable.addFunction(this.id, new SimplePlusSTFunction(this.type.getType(), arguments, false, new HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes>(), labelFunction));
		
		String bytecode = "DECFUN_BEGIN\n"
				+ labelFunction + " :\n";
		
		symbolTable.openScope();
		
		for(SimplePlusArg arg : this.args) {
			bytecode = bytecode
					+ arg.bytecodeGeneration(symbolTable, labelTable);
		}
		
		bytecode = bytecode
				+ this.block.bytecodeGeneration(symbolTable, labelTable);
				
		symbolTable.closeScope();
		
		bytecode = bytecode
				+ "RET\n"
				+ "DECFUN_END\n";
		
		return bytecode;
	}

}
