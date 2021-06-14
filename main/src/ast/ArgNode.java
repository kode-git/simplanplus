package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class ArgNode implements Node {

    private Node type;
    private String id;
    private int counter=0;
    private int[] pointerEffectStateArg = null;

    public ArgNode(Node type, String id) {
        this.type = type;
        this.id = id;
        this.counter= count(this.type);
    }

    // private methods

    private int count(Node t){
        System.out.println(t.getClass() + "is it arg?");
        if(t instanceof PointerTypeNode){
            return 1+count(((PointerTypeNode<?>) t).getVal());
        }else {
            return 0;
        }
    }

    // getter and setter


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int[] getPointerEffectStateArg() {
        return pointerEffectStateArg;
    }

    public void setPointerEffectStateArg(int[] pointerEffectStateArg) {
        this.pointerEffectStateArg = pointerEffectStateArg;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public Node getType() {
        return type;
    }

    public void setType(Node type) {
        this.type = type;
    }


    // toPrint, typeCheck, checkSemantics, checkEffects, codeGeneration

    @Override
    public String toPrint(String s) {
        return s+"Arg:" + type.toPrint(s+"") + id +"";
    }


    // not used
    @Override
    public Node typeCheck() {

        return null;
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
        // not used
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        ArrayList<SemanticError> res = new ArrayList();
        int offset=env.getOffset();
        STentry entry = new STentry(env.getNestingLevel(), this.type, offset,counter);
        env.setOffset(--offset);
        SemanticError err = null;
        if(!(this.type instanceof PointerTypeNode)) {
            entry.setEffectState(0,1);
            err = env.newVarNode(env.getNestingLevel(), this.id, entry); // this is the case of no pointer
        } else {
            // do aliasing
            entry.setEffectState(this.pointerEffectStateArg);
            err = env.newVarNode(env.getNestingLevel(), this.id, entry); // this is the case of pointer
        }
        if (err!=null){
            res.add(err);
        }
        return res;
    }


}
