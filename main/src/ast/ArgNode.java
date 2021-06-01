package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class ArgNode implements Node {

    private Node type;
    private String id;
    private int counter=0;

    public ArgNode(Node type, String id) {
        this.type = type;
        this.id = id;
        this.counter= count(this.type);
    }

    private int count(Node t){
        System.out.println(t.getClass() + "is it arg?");
        if(t instanceof PointerTypeNode){
            return 1+count(((PointerTypeNode<?>) t).getVal());
        }else {
            return 0;
        }
    }

    @Override
    public String toPrint(String s) {
        return s+"Arg:" + type.toPrint(s+"") + id +"";
    }


    // not used
    @Override
    public Node typeCheck() {

        return null;
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

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public int checkEffects(Environment env) {
        return 0;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        ArrayList<SemanticError> res = new ArrayList();
        int offset=env.getOffset();
        STentry entry = new STentry(env.getNestingLevel(), this.type, offset,counter);
        env.setOffset(--offset);
        SemanticError err = env.newVarNode( env.getNestingLevel(),this.id,  entry);
        if (err!=null){
            res.add(err);
        }
        return res;
    }
}
