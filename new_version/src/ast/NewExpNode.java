package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class NewExpNode implements ExpNode{
 
    public NewExpNode(){
    }
    @Override
    public String toPrint(String s) {
        return s+ "NEW"+"\n";
    }

    @Override
    public Node typeCheck() {
        return null;
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }
}
