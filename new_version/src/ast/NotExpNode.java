package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

<<<<<<< HEAD
public class NotExpNode implements Node {
    private Node expNode;
    public NotExpNode(Node expNode){
=======
public class NotExpNode implements ExpNode{
    private ExpNode expNode;
    public NotExpNode(ExpNode expNode){
>>>>>>> 0e998b4 (Class Definition 1/2)
        this.expNode= expNode;
    }

    @Override
    public String toPrint(String s) {

<<<<<<< HEAD
        return s+"!" + expNode.toPrint(s+"") + " ";
=======
        return s+"!" + expNode.toPrint(s+" ") + "\n";
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
