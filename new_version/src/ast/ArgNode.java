package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class ArgNode implements Node {

    private Node type;
    private String id;

    public ArgNode(Node type, String id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public String toPrint(String s) {
        return s+"Arg:" + type.toPrint(s+"") + id +"";
    }

    @Override
    public Node typeCheck() {
        return null;
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        ArrayList<SemanticError> res = new ArrayList();
        int offset=env.getOffset();
        STentry entry = new STentry(env.getNestingLevel(), this.type, offset);
        env.setOffset(--offset);
        SemanticError err = env.newVarNode( env.getNestingLevel(),this.id,  entry);
        if (err!=null){
            res.add(err);
        }
        return res;
    }
}
