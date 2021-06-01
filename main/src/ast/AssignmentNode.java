package ast;

import util.Environment;
import util.SemanticError;
import util.SimpLanlib;

import java.util.ArrayList;

public class AssignmentNode implements Node{

    private Node lhs;
    private Node exp;
    private int effectsST;
    public AssignmentNode(Node lhs, Node exp){
        this.lhs = lhs;
        this.exp = exp;
    }

    @Override
    public String toPrint(String s) {
        return s+"Assignment:" + lhs.toPrint(s + "") + " = "
                + exp.toPrint(s+"") + "";
    }

    @Override
    public Node typeCheck() {
        Node expType= exp.typeCheck();
        Node lhsType= lhs.typeCheck();
        if (!SimpLanlib.isSubtype(expType,lhsType)) {
            System.out.println("Assignment type failed");
            System.exit(0);
        }
        return lhsType;
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public int checkEffects(Environment env) {
        int lhsEffects=((LhsNode)lhs).getEffectsST();
        if(lhsEffects>=0 && lhsEffects<=1){
            ((LhsNode<?>) lhs).setEffectsST(1);
        }

        return 0;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        // lhs
        res.addAll(lhs.checkSemantics(env));
        // exp
        res.addAll(exp.checkSemantics(env));
        if(res.size()==0){
            this.checkEffects(env);
        }
        return res;

    }
}
