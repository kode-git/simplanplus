package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class AssignmentNode implements Node{

    private Node lhs;
    private Node exp;

    public AssignmentNode(Node lhs, Node exp){
        this.lhs = lhs;
        this.exp = exp;
    }

    @Override
    public String toPrint(String s) {
        return s+"Assignment:" + lhs.toPrint(s + "") + " = "
                + exp.toPrint(s+"") + "";
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
