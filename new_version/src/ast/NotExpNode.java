package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class NotExpNode implements ExpNode{
    private ExpNode expNode;
    public NotExpNode(ExpNode expNode){
        this.expNode= expNode;
    }

    @Override
    public String toPrint(String s) {

        return s+"!" + expNode.toPrint(s+" ") + "\n";
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
