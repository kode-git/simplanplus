package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class BaseExpNode implements Node {

    private Node exp;
    private int effectST;
    private int effectDecFun;

    public BaseExpNode(Node exp){
        this.exp = exp;
    }

    @Override
    public String toPrint(String s) {
        return s+ "BaseExp: (" + exp.toPrint(s + "")+ ")";
    }

    @Override
    public Node typeCheck() { return exp.typeCheck(); }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public int checkEffects(Environment env) {
        return 0;
    }

    @Override
    public void setEffectDecFun(int effectDecFun) {
        this.effectDecFun = effectDecFun;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        exp.setEffectDecFun(effectDecFun);
        res.addAll(exp.checkSemantics(env));

        return res;
    }
}
