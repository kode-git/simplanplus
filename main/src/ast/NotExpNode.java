package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;


public class NotExpNode implements Node {
    private Node expNode;
    private int effectDecFun;
    public NotExpNode(Node expNode){

    }

    @Override
    public String toPrint(String s) {

        return s+"!" + expNode.toPrint(s+"") + " ";

    }

    @Override
    public Node typeCheck() {
        return null;
    }

    @Override
    public void setEffectDecFun(int effectDecFun) {
        this.effectDecFun = effectDecFun;
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
        expNode.setEffectDecFun(this.effectDecFun);
        res.addAll(expNode.checkSemantics(env));
        return res;
    }
}
