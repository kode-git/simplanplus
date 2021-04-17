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
<<<<<<< HEAD
        return s+"\n Statement:" + st.toPrint(s + "") ;
=======
        return s+"Statement:" + st.toPrint(s + " ") + "\n";
>>>>>>> 0e998b4 (Class Definition 1/2)
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
