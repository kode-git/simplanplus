package ast;

import util.Environment;
import util.SemanticError;
import util.SimpLanlib;
import util.VoidNode;

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
        return new VoidNode(); // return void because this statement don't need to be checked in high level
        // void -> f : void on upper level
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public int checkEffects(Environment env) {
        int lhsEffects=((LhsNode)lhs).getEffectsST();
        System.out.println("LHSEFFECT " + lhsEffects);
        if(lhsEffects>=0 && lhsEffects<=1){
            ((LhsNode<?>) lhs).setEffectsST(1);
        } else {
            System.out.println("error: cannot find symbol " + lhs.toPrint(""));
            System.exit(0);
        }

        return 1;
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
