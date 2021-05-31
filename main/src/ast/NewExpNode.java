package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class NewExpNode implements Node {
 
    public NewExpNode(){
    }
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
    public void checkEffects() {

    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<SemanticError>();
    }
}
