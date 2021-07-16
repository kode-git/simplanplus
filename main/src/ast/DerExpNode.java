package ast;

import util.Environment;
import util.Offset;
import util.SemanticError;

import java.util.ArrayList;


public class DerExpNode implements Node, Cloneable {

    private Node derExp;
    private int effectsST;
    private int effectDecFun;
    public DerExpNode(Node derExp) {
        this.derExp=derExp;
    }

    @Override
    public String toPrint(String s) {

        return s+"" + derExp.toPrint(s + "") + "";


    }

    // getter and setter

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


    public ArrayList<SemanticError> checkEffects(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<>();
        STentry myEntry=null;
        if(effectDecFun == 0) {
            if (derExp instanceof LhsNode) {
                // derExp :: LhsNode
                effectsST = ((LhsNode<?>) derExp).getEffectsST();
            } else {
                // derExp :: String
                myEntry = env.lookup(env.getNestingLevel(), derExp + "");
                effectsST = myEntry.getEffectState(0);
            }
            if (effectsST == 0) {
                res.add(new SemanticError("DerExp Error: variable " + derExp.toPrint("") + " not initialized"));
                return res;

            } else if (effectsST == 2) {
                res.add(new SemanticError("DerExp Error: variable " + derExp.toPrint("") + " previously deleted"));
                return res;

            }
        } else {
            // do nothing
        }
        return res;
    }


    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        STentry myEntry=null;
        if(derExp instanceof LhsNode) {
            // derExp :: LhsNode
            derExp.setEffectDecFun(this.effectDecFun);
            res.addAll(derExp.checkSemantics(env));
        } else {
            // derExp :: String
            myEntry=env.lookup(env.getNestingLevel(), derExp + "");
            if(myEntry==null){
                res.add(new SemanticError("DerExp Error: id " + derExp + " not declared"));
            }
        }
        if(res.size()==0 && effectDecFun == 0) {
            res.addAll(this.checkEffects(env));
        }
        return res;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env, Offset offset) {
        return null;
    }

    @Override
    public Node clone() {
        try{
            DerExpNode cloned = (DerExpNode) super.clone();
            cloned.derExp = this.derExp.clone();
            return cloned;
        } catch(CloneNotSupportedException e){
            return null;
        }
    }

    @Override
    public String codeGeneration() {
        return derExp.codeGeneration(); // r1 <- cgen(stable, derExp)
    }

}
