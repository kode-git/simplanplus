package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class PointerTypeNode <T extends GenericTypeNode> implements Node, GenericTypeNode {

    private T val;

    public PointerTypeNode (T n) {
        val=n;
    }

    public String toPrint(String s) {
        return s+"^" + val.toPrint("") +"";
    }

    public Node typeCheck() {
        return new IntTypeNode();
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        return new ArrayList<SemanticError>();
    }

    public String codeGeneration() {
        return "push "+val+"\n";
    }

}