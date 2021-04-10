package main;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import ast.BytecodeVisitorImplementation;
import ast.SimplePlusBlock;
import ast.SimplePlusVisitorImplementation;
import parser.BytecodeLexer;
import parser.BytecodeParser;
import parser.SimplePlusLexer;
import parser.SimplePlusParser;
import utils.BytecodeLabelTable;
import utils.SimplePlusError;
import utils.SimplePlusSymbolTable;
import vm.SimplePlusVirtualMachine;

@SuppressWarnings("deprecation")
public class Compiler {
	
	public static boolean compile(String fileName) {
		
		try{   
			FileInputStream inputStream = new FileInputStream(fileName);
			ANTLRInputStream input = new ANTLRInputStream(inputStream);
        
			// Create lexer
			SimplePlusLexer lexer = new SimplePlusLexer(input);
			
			// Create parser
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			SimplePlusParser parser = new SimplePlusParser(tokens);
			
			// Tell the parser to build the AST
			parser.setBuildParseTree(true);
			
			// Build custom visitor (true for verbose log)
			SimplePlusVisitorImplementation visitor = new SimplePlusVisitorImplementation();
			
			// Visit the root, this will recursively visit the whole tree
			SimplePlusBlock block = (SimplePlusBlock) visitor.visitBlock(parser.block());
			
			// Semantic analysis
			List<SimplePlusError> semanticErrors = block.semanticAnalysis(new SimplePlusSymbolTable(), null, false);
			if(semanticErrors.size() > 0) {
				System.out.println("FAILED semantic analysis");
				for(SimplePlusError semanticError : semanticErrors) {
					System.out.println(semanticError.toString());
				}
				return false;
			} else {
				System.out.println("SUCCEDED semantic analysis");
				try {
					// Type analysis
					block.typeAnalysis(new SimplePlusSymbolTable(), null);
				} catch (IllegalArgumentException e) {
					System.out.println("FAILED type analysis");
					System.out.println(e.getMessage());
					return false;
				}
				System.out.println("SUCCEDED type analysis");
				try { // Behavioral analysis
					block.behavioralAnalysis(new SimplePlusSymbolTable(), false, null, null);
				} catch(IllegalArgumentException e) {
					System.out.println("FAILED behavioral analysis");
					System.out.println(e.getMessage());
					return false;
				}
				System.out.println("SUCCEDED behavioral analysis");
				// Bytecode generation
				String bytecode = block.bytecodeGeneration(new SimplePlusSymbolTable(), new BytecodeLabelTable())
						+ "HALT\n";
				FileWriter bytecodeFile = new FileWriter("a.spbc");
				bytecodeFile.write(bytecode);
				bytecodeFile.close();
				System.out.println("SUCCEDED bytecode generation");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;

	}
	
	public static void run(String fileName) {
		try {
			FileInputStream inputStream = new FileInputStream(fileName);
			ANTLRInputStream input = new ANTLRInputStream(inputStream);
        
			// Create lexer
			BytecodeLexer lexer = new BytecodeLexer(input);
			
			// Create parser
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			BytecodeParser parser = new BytecodeParser(tokens);
			
			// Tell the parser to build the AST
			parser.setBuildParseTree(true);
			
			// Build custom visitor (true for verbose log)
			BytecodeVisitorImplementation visitor = new BytecodeVisitorImplementation();
			
			// Visit the root, this will recursively visit the whole tree
			try {
				visitor.visitBytecode(parser.bytecode());
			} catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
				return;
			}
			
			// Load bytecode into the vm
			SimplePlusVirtualMachine virtualMachine = new SimplePlusVirtualMachine(visitor.getCode(), visitor.getVp());
			
			// Start virtual machine
			System.out.println("STARTING virtual machine");
			try {
				virtualMachine.run();
			} catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
				return;
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
