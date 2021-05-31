package ast;

import util.Environment;
import util.SemanticError;
import util.VoidNode;

import java.util.ArrayList;

public class DeletionNode implements Node{
    private String id;
    public DeletionNode(String id){
        this.id=id;
    }
    @Override
    public String toPrint(String s) {

        return s+"Delete: " + id + "\n";
    }


    @Override
    public Node typeCheck() {
        return new VoidNode(); // called only in case of delete as last statement of block
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public void checkEffects() {

    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
       ArrayList<SemanticError> res = new ArrayList<SemanticError>();
       STentry entry = env.checkId(env.getNestingLevel(), id);
        if(entry == null){
            res.add(new SemanticError("Id " +this.id + " not declared"));
        }
        return res;
    }
}
