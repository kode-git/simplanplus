package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class PointerTypeNode <T extends GenericTypeNode> implements Node, GenericTypeNode, Cloneable {

    private T val;

    public PointerTypeNode (T n) {
        val=n;
    }

    public PointerTypeNode () {val=null; }

    // getter and setter

    public void setVal(T val) {
        this.val = val;
    }

    public T getVal() {
        return val;
    }

    @Override
    public void setEffectDecFun(int effectDecFun) {
        // not used
    }

    // toPrint, typeCheck, checkSemantics, checkEffects, codeGeneration

    public String toPrint(String s) {
        if(val!=null){
            return s+"^" + val.toPrint("") +"";
        }else{ return s+"new";}
    }

    public Node typeCheck() {

        if(val instanceof IntTypeNode){
            return new IntTypeNode();
        } else if(val instanceof BoolTypeNode){
            return new BoolTypeNode();
        }
        return val.typeCheck();

    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        return new ArrayList<SemanticError>();
    }

    @Override
    public Node clone() {
        try{
            PointerTypeNode cloned = (PointerTypeNode) super.clone();
            if(this.val != null)
                // PointerTypeNode, IntTypeNode, BoolTypeNode interface
                cloned.val = (GenericTypeNode) this.val.clone();
            return cloned;
        } catch(CloneNotSupportedException e){
            return null;
        }
    }

    public String codeGeneration() {
        return "push "+val+"\n";
    }

    @Override
    public int checkEffects(Environment env) {
        return 0;
    }



}