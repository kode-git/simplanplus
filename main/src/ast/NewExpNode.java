package ast;

import util.Environment;
import util.Offset;
import util.SemanticError;

import java.util.ArrayList;

public class NewExpNode implements Node, Cloneable {
 
    public NewExpNode(){
    }

    // toPrint, typeCheck, checkSemantics, checkEffects, codeGeneration

    @Override
    public String toPrint(String s) {
        return "new";
    }

    @Override
    public Node typeCheck() {
        return new PointerTypeNode<GenericTypeNode>();
    }


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
    public ArrayList<SemanticError> checkSemantics(Environment env, Offset offset) {
        return null;
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

    // init the pointer in the heap
    @Override
    public String codeGeneration() {
        return "";
    }

}
