package mainPackage;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;

import Interpreter.ExecuteVM;
import ast.SVMVisitorImpl;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;


import org.antlr.v4.runtime.misc.ParseCancellationException;
import parser.SVMLexer;
import parser.SVMParser;
import parser.SimpLanPlusLexer;
import parser.SimpLanPlusParser;
import util.Environment;


import util.SemanticError;
import ast.SimpLanPlusVisitorImpl;
import ast.Node;
import util.SimpLanlib;
import util.ThrowingErrorListener;


public class Test {
    public static void main(String[] args) throws Exception {

        String fileName = "code";
        String extension = ".slp";
        String file = fileName + extension;
        FileInputStream is = new FileInputStream(file);
        ANTLRInputStream input = new ANTLRInputStream(is);

        // lexer
        SimpLanPlusLexer lexer = new SimpLanPlusLexer(input);

        // Adding our ErrorListener
        lexer.removeErrorListeners();
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

        // tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // parser
        SimpLanPlusParser parser = new SimpLanPlusParser(tokens);


        // Adding our ErrorListener to the parser
        parser.removeErrorListeners();
        parser.addErrorListener(ThrowingErrorListener.INSTANCE);

        // visitor
        SimpLanPlusVisitorImpl visitor = new SimpLanPlusVisitorImpl();

        Node ast;

        try {
            ast = visitor.visit(parser.block()); //generazione AST
            System.out.println("Compiling on...");
            //System.out.println(ast.toPrint(""));

            Environment env = new Environment();


            System.out.println("Check if there are semantics errors...");
            ArrayList<SemanticError> err = ast.checkSemantics(env);

        if (err.size() > 0) {
            System.out.println("You had: " + err.size() + " errors:");
            for (SemanticError e : err)
                System.out.println("\t" + e);
            System.exit(0);
        } else {
            System.out.println("Check Semantics ok! Visualizing AST ");
            System.out.print("--------------------");
            System.out.println(ast.toPrint(""));
            System.out.println("--------------------");
        }

            System.out.println("Type checking...");
            Node type = ast.typeCheck(); //type-checking bottom-up

            System.out.println(type.toPrint("Type checking ok! Type of the program is: "));

        // Code Generation
            System.out.println("Generating code...");
                String code=ast.codeGeneration();
				BufferedWriter out = new BufferedWriter(new FileWriter(fileName+".asm"));
				out.write(code + "halt\n" + SimpLanlib.getCode());
				out.close();
				System.out.println("Code generated! Assembling and running generated code.");

				FileInputStream isASM = new FileInputStream(fileName+".asm");
				ANTLRInputStream inputASM = new ANTLRInputStream(isASM);
				SVMLexer lexerASM = new SVMLexer(inputASM);
				CommonTokenStream tokensASM = new CommonTokenStream(lexerASM);
				SVMParser parserASM = new SVMParser(tokensASM);

				//parserASM.assembly(); is called in the visitorSVM

				SVMVisitorImpl visitorSVM = new SVMVisitorImpl();
				visitorSVM.visit(parserASM.assembly());

				System.out.println("You had: "+lexerASM.lexicalErrors+" lexical errors and "+parserASM.getNumberOfSyntaxErrors()+" syntax errors.");
				if (lexerASM.lexicalErrors>0 || parserASM.getNumberOfSyntaxErrors()>0) System.exit(1);

				System.out.println("Starting Virtual Machine...");
				ExecuteVM vm = new ExecuteVM(visitorSVM.code);
				System.out.println("Output: ");
				vm.cpu();
				for(int i=0;i<20;i++)
				    System.out.print(vm.memory[i]+ " ");
            for(int i=0;i<20;i++)
				System.out.println(vm.code[i]);


        } catch(ParseCancellationException e){
            System.out.println("The program was not in the right format. Exiting the compilation process now");
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}