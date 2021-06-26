package ast;

import util.Environment;
import util.Offset;
import util.SemanticError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DeclarationNode implements Node, Cloneable{

    private Node decVar;
    private Node decFun;
    private int effectDecFun;

    public DeclarationNode(Node decVar){
        assignNode(decVar);
    }


    public Node getDecVar() {
        return decVar;
    }

    public void setDecVar(Node decVar) {
        this.decVar = decVar;
    }

    public Node getDecFun() {
        return decFun;
    }

    public void setDecFun(Node decFun) {
        this.decFun = decFun;
    }

    public int getEffectDecFun() {
        return effectDecFun;
    }



    // toPrint, typeCheck, checkSemantics, checkEffects, codeGeneration


    @Override
    public String toPrint(String s) {
        if(this.decFun != null){
            // It is a decFun
            return s+"\n Declaration:" + decFun.toPrint(s + "") + "";
        } else {
            return s+"\n Declaration:" + decVar.toPrint(s + "") + "";
        }
    }

    @Override
    public Node typeCheck() {
        if(decVar==null){
            // It is a DecFunNode
           return decFun.typeCheck();
        } else {
            // else is a DecFunNode
            return  decVar.typeCheck();
        }
    }


    // not used
    public int checkEffects(Environment env) {
        return 0;
    }

    @Override
    public void setEffectDecFun(int effectDecFun) {
        this.effectDecFun = effectDecFun;
    }

    //not used
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env, Offset offset) {
        ArrayList<SemanticError> res = new ArrayList();
           if (decVar!=null){
               decVar.setEffectDecFun(effectDecFun);
               res.addAll(decVar.checkSemantics(env,offset));
           }else {
               decFun.setEffectDecFun(effectDecFun);
               res.addAll(decFun.checkSemantics(env,offset));
           }
        return res;
    }

    @Override
    public Node clone() {
        try{
            DeclarationNode cloned = (DeclarationNode) super.clone();
            if(this.decFun == null){
                cloned.decFun = null;
            } else {
                cloned.decFun = (Node) this.decFun.clone();
            }

            if(this.decVar == null){
                cloned.decVar = null;
            } else {
                cloned.decVar = (Node) this.decVar.clone();
            }

            return cloned;

        } catch(CloneNotSupportedException e){
            return null;
        }
    }

    // assignNode
    private void assignNode(Node node){
        if(node instanceof DecVarNode){
            // It is a DecVarNode
            this.decVar = node;
            this.decFun = null;
        } else {
            // else is a DecFunNode
            this.decFun = node;
            this.decVar = null;
        }
    }

    @Override
    public String codeGeneration() {
        if(decVar==null){
            // It is a DecFunNode
            return decFun.codeGeneration();     // cgen(stable, decFun) :: r1 not valid here
        } else {
            // else is a DecFunNode
            return  decVar.codeGeneration();    // cgen(stable, decVar) :: r1 not valid here
        }
    }

}
