package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;


public class NotExpNode implements Node {
    private Node expNode;
    public NotExpNode(Node expNode){

    }

    @Override
    public String toPrint(String s) {

        return s+"!" + expNode.toPrint(s+"") + " ";

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
