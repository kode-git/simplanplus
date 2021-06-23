package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class BaseExpNode implements Node, Cloneable {

    private Node exp;
    private int effectST;
    private int effectDecFun;

    public BaseExpNode(Node exp){
        this.exp = exp;
    }

    // getter and setter

    public Node getExp() {
        return exp;
    }

    public void setExp(Node exp) {
        this.exp = exp;
    }

    public int getEffectST() {
        return effectST;
    }

    public void setEffectST(int effectST) {
        this.effectST = effectST;
    }

    public int getEffectDecFun() {
        return effectDecFun;
    }

    @Override
    public void setEffectDecFun(int effectDecFun) {
        this.effectDecFun = effectDecFun;
    }


    // toPrint, typeCheck, checkSemantics, checkEffects, codeGeneration

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

    // not used
    public int checkEffects(Environment env) {
        return 0;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        exp.setEffectDecFun(effectDecFun);
        res.addAll(exp.checkSemantics(env));

        return res;
    }


    @Override
    public Node clone() {
        try {
            BaseExpNode cloned = (BaseExpNode) super.clone();
            cloned.exp = this.exp.clone();
            return cloned;
        } catch(CloneNotSupportedException e){
            return null;
        }
    }
}
