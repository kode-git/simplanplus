package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class LhsNode<T>implements Node{
    T lhVar;
    public LhsNode(T myNode){

        this.lhVar=myNode;
    }

    @Override
    public String toPrint(String s) {
        if(lhVar instanceof Node){
            return s+ ((Node) lhVar).toPrint(s+ "") + "^ \n";
        }else {
            return s+ lhVar +"\n";
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
