package ast;

import util.Environment;
import util.SemanticError;
import util.VoidNode;

import java.util.ArrayList;

public class RetNode implements Node{


    Node exp;
    public RetNode(Node exp){
        this.exp = exp;
    }

    public RetNode(){
        this.exp = null;
    }
    public Node getExp(){
        return this.exp;
    }

    @Override
    public String toPrint(String s) {
        String first =  s + "Ret:" + "return";
        if(this.exp == null){
            return first + "";
        } else {
            return first + " " + exp.toPrint(s + "") +
                     "";
        }
    }

    @Override
    public Node typeCheck() {
        if(exp!=null) {
            return exp.typeCheck();
        }else return new VoidNode();
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        if(this.exp == null) {
            // do nothing
        } else {
            res.addAll(exp.checkSemantics(env));
        }

        return res;
    }
}
