package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class BinExpGeqNode implements Node {

    private Node left;
    private Node right;

    public BinExpGeqNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toPrint(String s) {

        return s + "Geq: " + left.toPrint(s + "") + ">=" + right.toPrint(s + "") + "";
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
