package ast;

import util.Environment;
import util.Offset;
import util.SemanticError;

import java.util.ArrayList;

public class NegExpNode implements Node, Cloneable {

    private Node negExp;
    private int effectDecFun;

    public NegExpNode(Node negExp) {
        this.negExp=negExp;
    }


    // getter and setter

    public Node getNegExp() {
        return negExp;
    }

    public void setNegExp(Node negExp) {
        this.negExp = negExp;
    }

    @Override
    public void setEffectDecFun(int effectDecFun) {
        this.effectDecFun = effectDecFun;
    }

    // toPrint, typeCheck, checkSemantics, checkEffects, codeGeneration

    @Override
    public String toPrint(String s) {
        return s+ "NegExp: -"+ negExp.toPrint(s+ "") +" ";

    }
    @Override
    public Node typeCheck() {
        return negExp.typeCheck();
    }


    public int checkEffects(Environment env) {
        return 0;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        negExp.setEffectDecFun(this.effectDecFun);
        res.addAll(negExp.checkSemantics(env));
        return res;

    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env, Offset offset) {
        return null;
    }

    @Override
    public Node clone() {
        try{
            NegExpNode cloned = (NegExpNode) super.clone();
            cloned.negExp = (Node) this.negExp.clone();
            return cloned;
        } catch(CloneNotSupportedException e){
            return null;
        }
    }

    @Override
    public String codeGeneration() {
        // This multiplication is done because the int value is only in positive form in IntNode
        return negExp.codeGeneration() + "push -1\n" + "mult\n";
    }

}
