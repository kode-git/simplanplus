package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class StatementNode implements Node{
    private Node st;
    public StatementNode(Node st){
        this.st=st;
    }
    @Override
    public String toPrint(String s) {

        return s+"\n Statement:" + st.toPrint(s + "") ;


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
