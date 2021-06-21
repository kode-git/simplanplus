package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class NewExpNode implements Node, Cloneable {
 
    public NewExpNode(){
    }

    // toPrint, typeCheck, checkSemantics, checkEffects, codeGeneration

    @Override
    public String toPrint(String s) {
        return s+ "new"+" ";
    }

    @Override
    public Node typeCheck() {
        return new PointerTypeNode<GenericTypeNode>();
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
        // not used because is a String
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<SemanticError>();
    }

    @Override
    public Node clone() {
        try{
            NewExpNode cloned = (NewExpNode) super.clone();
            return cloned;
        } catch(CloneNotSupportedException e){
            return null;
        }
    }
}
