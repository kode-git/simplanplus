package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class BaseExpNode implements Node{

    Node exp;

    public BaseExpNode(Node exp){
        this.exp = exp;
    }

    @Override
    public String toPrint(String s) {
        return s+ "BaseExp: (" + exp.toPrint(s + " ")+ ")\n";
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
