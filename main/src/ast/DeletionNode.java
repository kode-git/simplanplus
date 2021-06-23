package ast;

import util.Environment;
import util.SemanticError;
import util.VoidNode;

import java.util.ArrayList;

public class DeletionNode implements Node, Cloneable{
    private String id;
    private int effectsST;
    private int effectDecFun;
    public DeletionNode(String id){
        this.id=id;
    }



    // getter and setter


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEffectsST() {
        return effectsST;
    }

    public void setEffectsST(int effectsST) {
        this.effectsST = effectsST;
    }

    public int getEffectDecFun() {
        return effectDecFun;
    }


    @Override
    public void setEffectDecFun(int effectDecFun) {
        this.effectDecFun = effectDecFun;
    }


    // toPrint, typeCheck, checkSemantics, checkEffects, codeGeneration

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

    /*
    propagateDelete :: [Environment, STentry] -> void
    Environment env :: the current environment of the checkSemantics
    STentry entry :: the entry where to get list of propagations
     */
    public void propagateDelete(Environment env, STentry entry){
        for(String id : entry.getPropagation().keySet()){
            // Getting the id of the entry in the symbol table where these variables are assigned
            // to the current deleted one, we need to propagate the delete state for them in the
            // pointer level between the assigned level to pointed value :: level - 0
            STentry prop = env.lookup(env.getNestingLevel(), id);
            int level = entry.getPropagation().get(id);

            for(int i = level; i >= 0; i--){
                prop.setEffectState(i, 2); // propagation of delete state
            }

        }
    }
    public int checkEffects(Environment env) {
        if(effectDecFun == 0) {

            STentry entry = env.lookup(env.getNestingLevel(), id);
            effectsST = entry.getEffectState(0);
            if (effectsST >= 0 && effectsST <= 1) {
                int size = entry.getPointerCounter();
                for (int i = 0; i < size + 1; i++) {
                    entry.setEffectState(i, 2); // [entry -> d]

                }
                effectsST = 2; // setting local effect to 2 :: delete state

                propagateDelete(env, entry); // propagate deletion to assigned pointers

            } else {
                System.out.println("error: cannot find symbol " + id);
                System.exit(0);
            }
        } else {
            // do nothing
        }
        return effectsST;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
       ArrayList<SemanticError> res = new ArrayList<SemanticError>();
       STentry entry = env.lookup(env.getNestingLevel(), id);
        if(entry == null){
            res.add(new SemanticError("Id " +this.id + " not declared"));
        }
        if(res.size()==0){

            this.checkEffects(env);
        }
        return res;
    }

    @Override
    public Node clone() {
        try{
            DeletionNode cloned = (DeletionNode) super.clone();
            return cloned;
        } catch(CloneNotSupportedException e){
            return null;
        }
    }
}
