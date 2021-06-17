package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DeclarationNode implements Node{

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

        ArrayList<SemanticError> res = new ArrayList();

           if (decVar!=null){
               decVar.setEffectDecFun(effectDecFun);
               res.addAll(decVar.checkSemantics(env));
           }else {
               decFun.setEffectDecFun(effectDecFun);
               res.addAll(decFun.checkSemantics(env));
           }

        return res;
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
}
