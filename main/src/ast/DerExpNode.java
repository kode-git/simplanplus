package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;


public class DerExpNode implements Node {

    Node derExp;
    int effectsST;
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

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public int checkEffects(Environment env) {
        STentry myEntry=null;

        if(derExp instanceof LhsNode) {
            // lhs
            effectsST=derExp.checkEffects(env);
        } else {
            // id
            myEntry=env.checkId(env.getNestingLevel(), derExp + "");
            effectsST=myEntry.getEffectState();
        }
        if(effectsST==0){
            System.out.println("error: variable "  +derExp.toPrint("")+" not initialized" );
            System.exit(0);
        } else if(effectsST==2) {
            System.out.println("error: variable "  +derExp.toPrint("")+" previously deleted" );
            System.exit(0);
        }
        return effectsST;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        STentry myEntry=null;
        if(derExp instanceof LhsNode) {
            // lhs
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
