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
import utils.TypeConverter;

public class SimplePlusIte extends SimplePlusStatement {
	
	private SimplePlusExp exp;
	private List<SimplePlusStatement> statements;
	
	
	
	// Constructor
	
	public SimplePlusIte(SimplePlusExp exp, List<SimplePlusStatement> statements) {
		this.exp = exp;
		this.statements = statements;
	}
	
	
	
	// Semantic analysis

	@Override
	public List<SimplePlusError> semanticAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction, boolean varExp) {
		List<SimplePlusError> errors = new LinkedList<SimplePlusError>();
		
		errors.addAll(this.exp.semanticAnalysis(symbolTable, scopeFunction, varExp));
		
		for(SimplePlusStatement statement : this.statements) {
			errors.addAll(statement.semanticAnalysis(symbolTable.clone(), scopeFunction, varExp));
		}
		
		return errors;
	}



	// Type analysis
	
	@Override
	public SimplePlusTypes typeAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction) {
		SimplePlusTypes typeExp = this.exp.typeAnalysis(symbolTable, scopeFunction);
		if(typeExp != SimplePlusTypes.BOOL) {
			new SimplePlusError(SimplePlusErrorTypes.TYPE_MISMATCH, SimplePlusTypes.BOOL, typeExp);
		}
		
		SimplePlusSymbolTable forkThen = symbolTable.clone();
		SimplePlusTypes typeThen = this.statements.get(0).typeAnalysis(forkThen, forkThen.functionScopeAt(symbolTable.functionScopeLevel(scopeFunction)));
		
		if(this.statements.size() > 1) {
			SimplePlusSymbolTable forkElse = symbolTable.clone();
			SimplePlusTypes typeElse = this.statements.get(1).typeAnalysis(forkElse, forkElse.functionScopeAt(symbolTable.functionScopeLevel(scopeFunction)));
			if(TypeConverter.typeToReturn(typeThen) != SimplePlusTypes.RETURN_VOID && TypeConverter.typeToReturn(typeElse) != SimplePlusTypes.RETURN_VOID) {
				if(TypeConverter.typeToReturn(typeThen) != TypeConverter.typeToReturn(typeElse)) {
					new SimplePlusError(SimplePlusErrorTypes.TYPE_MISMATCH_ITE, typeThen, typeElse);
				} else {
					return typeThen;
				}
			}
		}
		
		return SimplePlusTypes.VOID;
	}



	// Behavioral analysis
	
	@Override
	public void behavioralAnalysis(SimplePlusSymbolTable symbolTable, boolean branchElse, HashMap<String, SimplePlusSTVariable> scopeFunction, HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes> behaviorReference) {
		this.exp.behavioralAnalysis(symbolTable, branchElse, scopeFunction, behaviorReference);
		
		SimplePlusSymbolTable forkElse = symbolTable.clone();
		
		this.statements.get(0).behavioralAnalysis(symbolTable, branchElse, scopeFunction, behaviorReference);
		
		if(this.statements.size() > 1) {
			this.statements.get(1).behavioralAnalysis(forkElse, true, forkElse.functionScopeAt(symbolTable.functionScopeLevel(scopeFunction)), behaviorReference);
			symbolTable.behaviorMax(forkElse, scopeFunction);
		}
	}



	// Bytecode generation
	
	@Override
	public String bytecodeGeneration(SimplePlusSymbolTable symbolTable, BytecodeLabelTable labelTable) {
		String labelThen = labelTable.newLabel();
		String labelEnd = labelTable.newLabel();
		
		String bytecode = this.exp.bytecodeGeneration(symbolTable, labelTable)
				+ "PUSH\n"
				+ "PUSH_LABEL " + labelThen + "\n"
				+ "JUMP_TRUE\n";
		
		if(this.statements.size() > 1) {
			bytecode = bytecode
					+ this.statements.get(1).bytecodeGeneration(symbolTable, labelTable)
					+ "PUSH_LABEL " + labelEnd + "\n"
					+ "JUMP\n";
		}
		
		bytecode = bytecode
				+ labelThen + " :\n"
				+ this.statements.get(0).bytecodeGeneration(symbolTable, labelTable)
				+ labelEnd + " :\n";
		
		return bytecode;
	}

}
