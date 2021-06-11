package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;


public class DerExpNode implements Node {

    Node derExp;
    int effectsST;
    private int effectDecFun;
    public DerExpNode(Node derExp) {
        this.derExp=derExp;
    }

    @Override
    public String toPrint(String s) {

        return s+"" + derExp.toPrint(s + "") + "";


    }

    @Override
    public Node typeCheck() {
        // LhsNode.typeCheck() calling
        return derExp.typeCheck();
    }

    public Node getDerExp() {
        return derExp;
    }

    public void setDerExp(Node derExp) {
        this.derExp = derExp;
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public int checkEffects(Environment env) {
        STentry myEntry=null;
        if(effectDecFun == 0) {
            if (derExp instanceof LhsNode) {
                // lhs
                //effectsST=derExp.checkEffects(env);
                effectsST = ((LhsNode<?>) derExp).getEffectsST();

            } else {
                // id
                myEntry = env.checkId(env.getNestingLevel(), derExp + "");
                effectsST = myEntry.getEffectState(0);
            }
            if (effectsST == 0) {
                System.out.println("error: variable " + derExp.toPrint("") + " not initialized");
                System.exit(0);
            } else if (effectsST == 2) {
                System.out.println("error: variable " + derExp.toPrint("") + " previously deleted");
                System.exit(0);
            }
        } else {
            // do nothing
        }
        return effectsST;
    }

    @Override
    public void setEffectDecFun(int effectDecFun) {
        this.effectDecFun = effectDecFun;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        STentry myEntry=null;
        if(derExp instanceof LhsNode) {
            // lhs
            derExp.setEffectDecFun(this.effectDecFun);
            res.addAll(derExp.checkSemantics(env));
        } else {
            // id
            myEntry=env.checkId(env.getNestingLevel(), derExp + "");
            if(myEntry==null){
                    res.add(new SemanticError("Id " + derExp + " not declared"));
            }
        }
        if(res.size()==0) {
            this.checkEffects(env);
        }
        return res;
    }
}
