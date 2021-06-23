package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class ArgNode implements Node, Cloneable {

    private Node type; // type of argument
    private String id; // id of the argument
    private int counter=0; // pointer local counter
    private int[] pESArg = null; // Pointer EffectState

    public ArgNode(Node type, String id) {
        this.type = type;
        this.id = id;
        this.counter= count(this.type); // counter setting
    }


    // getter and setter

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int[] getPointerEffectStateArg() {
        return pESArg;
    }

    public void setPointerEffectStateArg(int[] p) {
        this.pESArg = p;
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


    // not used because typeCheck of arguments and parameters in the call functions
    // is done in the callNode instances of the AST.
    @Override
    public Node typeCheck() {
        return null;
    }


    public SemanticError checkEffects(Environment env) {
        int offset=env.getOffset();
        STentry entry = new STentry(env.getNestingLevel(), this.type, offset,counter);
        env.setOffset(--offset);
        SemanticError err = null;
        if(!(this.type instanceof PointerTypeNode)) {
            entry.setEffectState(0,1);
            err = env.addEntry(env.getNestingLevel(), this.id, entry); // this is the case of no pointer
        } else {
            // do aliasing
            if (this.pESArg!=null) {
                entry.setEffectState(this.pESArg);
            }else {
                //
                this.pESArg= new int[counter+1];

            }
            err = env.addEntry(env.getNestingLevel(), this.id, entry); // this is the case of pointer
        }
        return err;
    }

    @Override
    public void setEffectDecFun(int effectDecFun) {
        // not used because arguments is always child of a DecFun
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList();
        SemanticError err = checkEffects(env);
        if (err!=null){
            res.add(err);
        }
        return res;
    }

    @Override
    public Node clone() {
        try{
            ArgNode cloned = (ArgNode) super.clone();
            cloned.type = (Node) type.clone();
            if(this.pESArg != null) {
                cloned.pESArg = this.pESArg.clone();
            }
            return cloned;
        } catch(CloneNotSupportedException e){
            return null;
        }
    }

    // Pointer counter setting method
    // This is used to establish the number of "^" for an argument argNode of a DecFunNode
    private int count(Node t){
        if(t instanceof PointerTypeNode){
            return 1+count(((PointerTypeNode<?>) t).getVal());
        }else {
            return 0;
        }
    }


    // not used, arguments are taken from parameters in callNode.codeGeneration()
    @Override
    public String codeGeneration() {
        return "";
    }

}
