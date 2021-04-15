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
        if(this.decFun != null){
            // It is a decFun
            return s+"\n Declaration:" + decFun.toPrint(s + "") + "";
        } else {
            return s+"\n Declaration:" + decVar.toPrint(s + "") + "";
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
