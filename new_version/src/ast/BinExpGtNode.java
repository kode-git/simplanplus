package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class BinExpGtNode implements Node {
    public BinExpGtNode(ExpNode left, ExpNode right) {
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
