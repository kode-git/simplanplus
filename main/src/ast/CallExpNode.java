package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class CallExpNode implements Node {

    private Node call;
    public CallExpNode(Node node) {
        this.call = node;
    }

    @Override
    public String toPrint(String s) {
        return s + "CallExp: " + call.toPrint(s + "") + "";
    }

    @Override
    public Node typeCheck() {
        return call.typeCheck();
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        res.addAll(call.checkSemantics(env));
        return res;
    }
}
