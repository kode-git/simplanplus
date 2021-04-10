package ast;

import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;

import utils.BytecodeLabelTable;
import utils.SimplePlusBehaviorTypes;
import utils.SimplePlusError;
import utils.SimplePlusSTVariable;
import utils.SimplePlusSymbolTable;
import utils.SimplePlusTypes;
import utils.TypeConverter;

public class SimplePlusBlock extends SimplePlusStatement {
	
	private List<SimplePlusStatement> statements;
	
	
	
	// Constructor
	
	public SimplePlusBlock(List<SimplePlusStatement> statements) {
		this.statements = statements;
	}
	
	
	
	// Semantic analysis

	@Override
	public List<SimplePlusError> semanticAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction, boolean varExp) {
		List<SimplePlusError> errors = new LinkedList<SimplePlusError>();
		
		symbolTable.openScope();
		
		for(SimplePlusStatement statement : this.statements) {
			errors.addAll(statement.semanticAnalysis(symbolTable, scopeFunction, varExp));
		}
		
		symbolTable.closeScope();
		
		return errors;
	}



	// Type analysis
	
	@Override
	public SimplePlusTypes typeAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction) {		
		symbolTable.openScope();
		
		SimplePlusTypes typeBlock = SimplePlusTypes.VOID;
		for(SimplePlusStatement statement : this.statements) {
			SimplePlusTypes typeStatement = statement.typeAnalysis(symbolTable, scopeFunction);
			if(!TypeConverter.isReturn(typeBlock)) {
				if(TypeConverter.isReturn(typeStatement)) {
					typeBlock = typeStatement;
				}
			}
		}
		
		symbolTable.closeScope();
		
		return typeBlock;
	}



	// Behavioral analysis
	
	@Override
	public void behavioralAnalysis(SimplePlusSymbolTable symbolTable, boolean branchElse, HashMap<String, SimplePlusSTVariable> scopeFunction, HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes> behaviorReference) {
		symbolTable.openScope();
		
		for(SimplePlusStatement statement : this.statements) {
			statement.behavioralAnalysis(symbolTable, branchElse, scopeFunction, behaviorReference);
		}
		
		symbolTable.closeScope();
	}



	// Bytecode generation
	
	@Override
	public String bytecodeGeneration(SimplePlusSymbolTable symbolTable, BytecodeLabelTable labelTable) {
		String bytecode = "";
		
		symbolTable.openScope();
		
		for(SimplePlusStatement statement : this.statements) {
			bytecode = bytecode
					+ statement.bytecodeGeneration(symbolTable, labelTable);
		}
		
		symbolTable.closeScope();
		
		return bytecode;
	}

}
