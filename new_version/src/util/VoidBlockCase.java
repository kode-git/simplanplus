package util;

import ast.Node;
import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

// Service Class
public class VoidBlockCase implements Node {

    public VoidBlockCase(){}

    @Override
    public String toPrint(String indent) {
        return indent + "(void) BlockNode";
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
