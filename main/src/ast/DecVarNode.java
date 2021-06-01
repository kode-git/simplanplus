package ast;

import util.Environment;
import util.SemanticError;
import util.SimpLanlib;

import java.util.ArrayList;
import java.util.HashMap;

public class DecVarNode implements Node {

    private Node typeNode;
    private String id;
    private Node exp;
    private int counter=0;

    public DecVarNode (Node myType, String id) {
        this.typeNode = myType;
        this.id = id;
        this.exp = null;
       this.counter= count(this.typeNode);
    }


    public DecVarNode (Node myType, String id, Node exp) {
        this.typeNode = myType;
        this.id = id;
        this.exp = exp;
        this.counter= count(this.typeNode);
    }
    private int count(Node t){
        System.out.println(t.getClass() + "is it arg?");
        if(t instanceof PointerTypeNode){
            return 1+count(((PointerTypeNode<?>) t).getVal());
        }else {
            return 0;
        }
    }

    public String toPrint(String s) {

        if (this.exp != null){
            return s+"DecVar:" + typeNode.toPrint(s+"") + id +"="
                    + exp.toPrint(s+"") + "";
        }
        else {
            return s+"DecVar:"
                    + typeNode.toPrint(s+"")
                    + id + "";
        }
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        ArrayList<SemanticError> res = new ArrayList();
        int offset=env.getOffset();
        STentry entry = new STentry(env.getNestingLevel(), this.typeNode, offset,counter);
        env.setOffset(--offset);
        if (this.exp!=null)
            res.addAll(this.exp.checkSemantics(env));
        SemanticError err = env.newVarNode( env.getNestingLevel(),this.id,  entry);
        if (err!=null) {
            res.add(err);
        }
        return res;
    }

    public Node typeCheck() {
        if(exp != null) {
            if (!(SimpLanlib.isSubtype( typeNode, exp.typeCheck() ))) {
                System.out.println("incompatible value for variable " + id);
                System.exit(0);
            }
        }
        return typeNode;
    }

    public String codeGeneration() {
        return null;
    }

    @Override
    public int checkEffects(Environment env) {
        return 0;
    }

}