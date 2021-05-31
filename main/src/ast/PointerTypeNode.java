package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class PointerTypeNode <T extends GenericTypeNode> implements Node, GenericTypeNode {

    private T val;

    public PointerTypeNode (T n) {
        val=n;
    }

    public PointerTypeNode () {val=null; }

    public T getVal() {
        return val;
    }

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

    public String codeGeneration() {
        return "push "+val+"\n";
    }

    @Override
    public void checkEffects() {

    }

}