package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class NegExpNode implements Node {

    private Node negExp;

    public NegExpNode(Node negExp) {
        this.negExp=negExp;
    }

    @Override
    public String toPrint(String s) {
        return s+ "-"+ negExp.toPrint(s+ "") +" ";

    }

    // getter and setter

    public Node getNegExp() {
        return negExp;
    }

    public void setNegExp(Node negExp) {
        this.negExp = negExp;
    }

    // TypeCheck, codeGeneration, checkSemantics
    
    @Override
    public Node typeCheck() {
        return null;
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public int checkEffects(Environment env) {
        return 0;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        res.addAll(negExp.checkSemantics(env));
        return res;

    }
}
