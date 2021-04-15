package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class NegExpNode implements ExpNode{

    private ExpNode negExp;

    public NegExpNode(ExpNode negExp) {
        this.negExp=negExp;
    }

    @Override
    public String toPrint(String s) {
        return s+ "-"+ negExp.toPrint(s+ " ") +"\n";

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
