package ast;

import util.Environment;
import util.Offset;
import util.SemanticError;

import java.util.ArrayList;



public class StatementNode implements Node, Cloneable{
    private Node st;
    private Boolean hasRet;
    private int effectDecFun;
    public StatementNode(Node st){
        this.st=st;
        //t();
    }

    public void setSt(Node st) {
        this.st = st;
    }

    public Boolean getHasRet() {
        return hasRet;
    }

    public void setHasRet(Boolean hasRet) {
        this.hasRet = hasRet;
    }

    public int getEffectDecFun() {
        return effectDecFun;
    }

    public void checkRet(){
        if(st instanceof BlockNode) {
            this.hasRet = ((BlockNode) st).checkRet();
        }else {
            if (st instanceof RetNode) {
                this.hasRet = true;
            } else {
                this.hasRet = false;
            }
         }

    }

    public Node getSt() {
        return st;
    }

    public Boolean getChRet(){
        checkRet();
        return this.hasRet;
    }

    @Override
    public void setEffectDecFun(int effectDecFun) {
        this.effectDecFun = effectDecFun;
    }


    // toPrint, typeCheck, checkSemantics, checkEffects, codeGeneration

    @Override
    public String toPrint(String s) {

        return s+"\n Statement:" + st.toPrint(s + "") ;


    }

    @Override
    public Node typeCheck() {
        return st.typeCheck();
    }

    // not used
    public int checkEffects(Environment env) {
        return 0;
    }


    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        ArrayList<SemanticError> res = new ArrayList();
        st.setEffectDecFun(this.effectDecFun);
        res.addAll(st.checkSemantics(env));
       return res;

    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env, Offset offset) {
        return null;
    }

    @Override
    public Node clone() {
        try{
            StatementNode cloned = (StatementNode) super.clone();

            cloned.st = (Node) this.st.clone();

            return cloned;

        } catch(CloneNotSupportedException e){
            return null;
        }
    }

    @Override
    public String codeGeneration() {
        return st.codeGeneration();
    }

}
