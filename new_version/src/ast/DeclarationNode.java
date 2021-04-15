package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class DeclarationNode implements Node{

    private Node decVar;
    private Node decFun;

    public DeclarationNode(Node decVar){
        assignNode(decVar);
    }


    private void assignNode(Node node){
        if(node instanceof DecVarNode){
            // It is a DecVarNode
            this.decVar = node;
            this.decFun = null;
        } else {
            // else is a DecFunNode
            this.decFun = node;
            this.decVar = null;
        }
    }

    @Override
    public String toPrint(String s) {
        if(this.decFun == null){
            // It is a decFun
            return s+"Fun:" + decFun.toPrint(s + " ") + "\n";
        } else {
            return s+"Var:" + decVar.toPrint(s + " ") + "\n";
        }
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
