package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class BinExpSubNode implements Node {

    private ExpNode left;
    private ExpNode right;

    public BinExpSubNode(ExpNode left, ExpNode right) {
    }

    @Override
    public String toPrint(String indent) {
        return null;
    }

    @Override
    public Node typeCheck() {
        return null;
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }
}