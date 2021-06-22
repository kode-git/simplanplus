package mainPackage;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

//import Interpreter.ExecuteVM;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import parser.SimpLanPlusLexer;
import parser.SimpLanPlusParser;
//import parser.SVMLexer;
// import parser.SVMParser;
import util.Environment;


import util.SemanticError;
import ast.SimpLanPlusVisitorImpl;
import ast.Node;
import util.ThrowingErrorListener;
// import ast.SVMVisitorImpl;


public class Test {
    public static void main(String[] args) throws Exception {

        String fileName = "code.slp";
        FileInputStream is = new FileInputStream(fileName);
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

            //System.out.println(ast.toPrint(""));

            Environment env = new Environment();
            ArrayList<SemanticError> err = ast.checkSemantics(env);

        if (err.size() > 0) {
            System.out.println("You had: " + err.size() + " errors:");
            for (SemanticError e : err)
                System.out.println("\t" + e);
            System.exit(0);
        } else {
            System.out.println("Visualizing AST...");

            System.out.println(ast.toPrint(":->"));
        }

        Node type = ast.typeCheck(); //type-checking bottom-up

        System.out.println(type.toPrint("Type checking ok! Type of the program is: "));

/*
				// CODE GENERATION  prova.SimpLanPlus.asm
				String code=ast.codeGeneration();
				BufferedWriter out = new BufferedWriter(new FileWriter(fileName+".asm"));
				out.write(code);
				out.close();
				System.out.println("Code generated! Assembling and running generated code.");

				FileInputStream isASM = new FileInputStream(fileName+".asm");
				ANTLRInputStream inputASM = new ANTLRInputStream(isASM);
				SVMLexer lexerASM = new SVMLexer(inputASM);
				CommonTokenStream tokensASM = new CommonTokenStream(lexerASM);
				SVMParser parserASM = new SVMParser(tokensASM);

				//parserASM.assembly();

				SVMVisitorImpl visitorSVM = new SVMVisitorImpl();
				visitorSVM.visit(parserASM.assembly());

				System.out.println("You had: "+lexerASM.lexicalErrors+" lexical errors and "+parserASM.getNumberOfSyntaxErrors()+" syntax errors.");
				if (lexerASM.lexicalErrors>0 || parserASM.getNumberOfSyntaxErrors()>0) System.exit(1);

				System.out.println("Starting Virtual Machine...");
				ExecuteVM vm = new ExecuteVM(visitorSVM.code);
				vm.cpu();
			}    */

        } catch(ParseCancellationException e){
            System.out.println("The program was not in the right format. Exiting the compilation process now");
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}