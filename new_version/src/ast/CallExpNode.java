package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class CallExpNode implements ExpNode {

    private ExpNode call;
    public CallExpNode(ExpNode node) {
        this.call = call;
    }

    @Override
    public String toPrint(String s) {
        return s + "CallExp: " + call.toPrint(s + " ") + "\n";
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
