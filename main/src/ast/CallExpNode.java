package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class CallExpNode implements Node {

    private Node call;
    private int effectDecFun;
    public CallExpNode(Node node) {
        this.call = node;
    }

    public Node getCall() {
        return call;
    }

    public void setCall(Node call) {
        this.call = call;
    }

    public int getEffectDecFun() {
        return effectDecFun;
    }

    // toPrint, typeCheck, checkSemantics, checkEffects, codeGeneration


    @Override
    public String toPrint(String s) {
        return s + "CallExp: " + call.toPrint(s + "") + "";
    }

    @Override
    public Node typeCheck() {
        return call.typeCheck();
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public int checkEffects(Environment env) {
        return 0;
    }

    @Override
    public void setEffectDecFun(int effectDecFun) {
        this.effectDecFun = effectDecFun;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        call.setEffectDecFun(this.effectDecFun);
        res.addAll(call.checkSemantics(env));
        return res;
    }
}
