package ast;

import util.Environment;
import util.SemanticError;
import util.SimpLanlib;
import util.VoidNode;

import java.util.ArrayList;
import java.util.HashMap;

public class DecVarNode implements Node, Cloneable {

    private Node typeNode;
    private String id;
    private Node exp;
    private int counter=0; // pointer counter
    private int effectsST;
    private int effectDecFun;

    public DecVarNode (Node myType, String id) {
        this.typeNode = myType;
        this.id = id;
        this.exp = null;
       this.counter= count(this.typeNode);
    }


    public DecVarNode (Node myType, String id, Node exp) {
        this.typeNode = myType;
        this.id = id;
        this.exp = exp;
        this.counter= count(this.typeNode);
    }

    public Node getTypeNode() {
        return typeNode;
    }

    public void setTypeNode(Node typeNode) {
        this.typeNode = typeNode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Node getExp() {
        return exp;
    }

    public void setExp(Node exp) {
        this.exp = exp;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getEffectsST() {
        return effectsST;
    }

    public void setEffectsST(int effectsST) {
        this.effectsST = effectsST;
    }

    public int getEffectDecFun() {
        return effectDecFun;
    }



    @Override
    public void setEffectDecFun(int effectDecFun) {
        this.effectDecFun = effectDecFun;
    }

    // toPrint, typeCheck, checkSemantics, checkEffects, codeGeneration

    public String toPrint(String s) {

        if (this.exp != null){
            return s+"DecVar:" + typeNode.toPrint("") + id +"="
                    + exp.toPrint("") + "";
        }
        else {
            return s+"DecVar:"
                    + typeNode.toPrint(s+"")
                    + id + "";
        }
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        ArrayList<SemanticError> res = new ArrayList();
        int offset=env.getOffset();
        STentry entry = new STentry(env.getNestingLevel(), this.typeNode, offset,counter);
        env.setOffset(--offset);
        if (this.exp!=null) {
            exp.setEffectDecFun(this.effectDecFun);
            res.addAll(this.exp.checkSemantics(env));
        }
        SemanticError err = env.addEntry( env.getNestingLevel(),this.id,  entry);
        if (err!=null) {
            res.add(err);
        }

        if(res.size()==0 && this.exp != null){
            this.checkEffects(env);
        }
        return res;
    }

    @Override
    public Node clone() {
        try{
            DecVarNode cloned = (DecVarNode) super.clone();
            cloned.typeNode = (Node) this.typeNode.clone();
            cloned.exp = (Node) this.exp.clone();

            return cloned;
        } catch(CloneNotSupportedException e){
            return null;
        }
    }


    public Node typeCheck() {
        if(exp != null) {
            if (!(SimpLanlib.isSubtype( typeNode, exp.typeCheck() ))) {
                System.out.println("incompatible value for variable " + id);
                System.exit(0);
            }
        }
        return new VoidNode(); // return voidNode because this statement don't need to be checked in higher levels
        // void -> f : void on upper level
    }

    public String codeGeneration() {
        return null;
    }

    @Override
    public int checkEffects(Environment env) {
        STentry myEntry = env.lookup(env.getNestingLevel(), id);
        if(effectDecFun == 0) {
            // is not a function block
            this.effectsST = myEntry.getEffectState(0);

            if (this.effectsST == 0) {
                myEntry.setEffectState(0, 1);
                this.effectsST = 1;
            }
        } else {
            // is a function block
        }
        return this.effectsST;
    }

    // counting of ^ referent
    private int count(Node t){
        if(t instanceof PointerTypeNode){
            return 1+count(((PointerTypeNode<?>) t).getVal());
        }else {
            return 0;
        }
    }



}