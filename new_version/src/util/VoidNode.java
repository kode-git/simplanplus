package util;

import ast.Node;
import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

/*
This class is used for.
1. Case of void block
2. Case of void type for CallNode

 */
public class VoidNode implements Node {

    public VoidNode(){}

    @Override
    public String toPrint(String indent) {
        return indent + "VoidNode";
    }

    @Override
    public Node typeCheck() {
        return this;
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<SemanticError>();
    }
}
