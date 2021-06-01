package ast;

import util.Environment;
import util.SemanticError;
import util.VoidNode;

import java.util.ArrayList;

public class DeletionNode implements Node{
    private String id;
    private int effectsST;
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
    public int checkEffects(Environment env) {
        STentry entry = env.checkId(env.getNestingLevel(), id);
        effectsST= entry.getEffectState();
        if(effectsST>=0 && effectsST<=1){
            entry.setEffectState(2);
        }else{
            System.out.println("error: cannot find symbol " + id);
            System.exit(0);
        }
        return entry.getEffectState();
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
       ArrayList<SemanticError> res = new ArrayList<SemanticError>();
       STentry entry = env.checkId(env.getNestingLevel(), id);
        if(entry == null){
            res.add(new SemanticError("Id " +this.id + " not declared"));
        }
        if(entry != null){
            this.checkEffects(env);
        }
        return res;
    }
}
