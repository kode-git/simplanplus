package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

<<<<<<< HEAD
public class DerExpNode implements Node {
=======
public class DerExpNode implements ExpNode {
>>>>>>> 0e998b4 (Class Definition 1/2)
    Node derExp;
    public DerExpNode(Node derExp) {
        this.derExp=derExp;
    }

    @Override
    public String toPrint(String s) {
<<<<<<< HEAD
        return s+"" + derExp.toPrint(s + "") + "";
=======
        return s+"" + derExp.toPrint(s + " ") + "\n";
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
