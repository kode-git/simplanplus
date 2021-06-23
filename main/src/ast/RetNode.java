package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class RetNode implements Node, Cloneable{


    Node exp;
    private int effectDecFun;
    public RetNode(Node exp){
        this.exp = exp;
    }

    public RetNode(){
        this.exp = null;
    }


    public Node getExp() {
        return exp;
    }

    public void setExp(Node exp) {
        this.exp = exp;
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
        String first =  s + "Ret:" + "return";
        if(this.exp == null){
            return first + "";
        } else {
            return first + " " + exp.toPrint(s + "") +
                    "";
        }
    }
    @Override
    public Node typeCheck() {
        return exp.typeCheck();
    }

    // not used
    public int checkEffects(Environment env) {
        return 0;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        if(this.exp == null) {
            // do nothing
        } else {
            exp.setEffectDecFun(this.effectDecFun);
            res.addAll(exp.checkSemantics(env));
        }
        return res;
    }

    @Override
    public Node clone() {
        try{
            RetNode cloned = (RetNode) super.clone();
            if(this.exp != null)
                cloned.exp = (Node) exp.clone();
            return cloned;
        } catch(CloneNotSupportedException e){
            return null;
        }
    }


    // TODO: ToCheck with the callNode and decFunNode code generation
    @Override
    public String codeGeneration() {
        return exp.codeGeneration();
    }

}
