package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class DecVarNode implements Node {

    private Node typeNode;
    private String id;
    private Node exp;

    public DecVarNode (Node myType, String id) {
        this.typeNode = myType;
        this.id = id;
    }
    public DecVarNode (Node myType, String id, Node exp) {
        this.typeNode = myType;
        this.id = id;
        this.exp = exp;
    }

    public String toPrint(String s) {
        return s+"Var:" + id +"\n"
                +typeNode.toPrint(s+"  ");
            //    +exp.toPrint(s+"  ");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        return null;
    }

    public Node typeCheck() {
        return null;
    }

    public String codeGeneration() {
        return null;
    }

}