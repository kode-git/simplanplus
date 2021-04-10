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
import utils.TypeConverter;

public class SimplePlusRet extends SimplePlusStatement {
	
	private SimplePlusExp exp;
	
	
	
	// Constructor
	
	public SimplePlusRet(SimplePlusExp exp) {
		this.exp = exp;
	}
	
	
	
	// Semantic analysis

	@Override
	public List<SimplePlusError> semanticAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction, boolean varExp) {
		List<SimplePlusError> errors = new LinkedList<SimplePlusError>();
		
		if(this.exp != null) {
			errors.addAll(this.exp.semanticAnalysis(symbolTable, scopeFunction, varExp));
		}
		
		return errors;
	}



	// Type analysis
	
	@Override
	public SimplePlusTypes typeAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction) {
		SimplePlusTypes type = SimplePlusTypes.VOID;
		if(this.exp != null) {
			type = this.exp.typeAnalysis(symbolTable, scopeFunction);
		}
		
		return TypeConverter.typeToReturn(type);
	}



	// Behavioral analysis
	
	@Override
	public void behavioralAnalysis(SimplePlusSymbolTable symbolTable, boolean branchElse, HashMap<String, SimplePlusSTVariable> scopeFunction, HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes> behaviorReference) {
		if(exp != null) {
			this.exp.behavioralAnalysis(symbolTable, branchElse, scopeFunction, behaviorReference);
		}
	}



	@Override
	public String bytecodeGeneration(SimplePlusSymbolTable symbolTable, BytecodeLabelTable labelTable) {
		String bytecode = "";
		
		if(this.exp != null) {
			bytecode = this.exp.bytecodeGeneration(symbolTable, labelTable);
		}
		
		return bytecode
				+ "RET\n";
	}

}
