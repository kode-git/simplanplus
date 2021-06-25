package ast;

import util.*;

import java.util.ArrayList;

public class AssignmentNode implements Node, Cloneable{

    private Node lhs;
    private Node exp;
    private int effectsST;
    private int effectDecFun;

    public AssignmentNode(Node lhs, Node exp){
        this.lhs = lhs;
        this.exp = exp;
    }

    // getter and setter

    public Node getLhs() {
        return lhs;
    }

    public void setLhs(Node lhs) {
        this.lhs = lhs;
    }

    public Node getExp() {
        return exp;
    }

    public void setExp(Node exp) {
        this.exp = exp;
    }

    public int getEffectsST() {
        return effectsST;
    }

    public void setEffectsST(int effectsST) {
        this.effectsST = effectsST;
    }

    public int getEffectDecFun() {
        return effectDecFun;
    }

    @Override
    public void setEffectDecFun(int effectDecFun) {
        this.effectDecFun = effectDecFun;
    }


    // toPrint, typeCheck, checkSemantics, checkEffects, codeGeneration

    @Override
    public String toPrint(String s) {
        return s+"Assignment:" + lhs.toPrint("") + " = "
                + exp.toPrint("") ;
    }

    @Override
    public Node typeCheck() {
        Node expType= exp.typeCheck();
        Node lhsType= lhs.typeCheck();
        if (!SimpLanlib.isSubtype(expType,lhsType)) {
            System.out.println("Assignment type failed");
            System.exit(0);
        }
        // return void because this statement don't need to be checked in high level
        return new VoidNode();
    }

    public ArrayList<SemanticError> checkEffects(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<>();
        if(effectDecFun == 0) {
            // getting the lhsEffects in the table
            int lhsEffects = ((LhsNode) lhs).getEffectsST();
            if (lhsEffects >= 0 && lhsEffects <= 1) {

                // check if the left-side of the assignment is deleted or not,
                // if we are here, the left-a-side is bottom or rw state
                Node typeExp = exp.typeCheck();

                // Pointer Reference Assignment
                if(((exp instanceof DerExpNode))&&(typeExp instanceof PointerTypeNode)){
                    // Pointer assignment when x = exp, exp is a DerExp with LhsNode as PointerType
                    int diffCount= (((LhsNode<?>) lhs).getCounterST()-((LhsNode<?>) lhs).getCounter());
                    LhsNode<?> myDerExp = ((LhsNode<?>)((DerExpNode)exp).getDerExp());
                    if(diffCount
                            ==
                            (myDerExp.getCounterST()-myDerExp.getCounter())){
                        int counterLhs= ((LhsNode<?>)lhs).getCounter();
                        int counterExp= myDerExp.getCounter();
                        for(int i=0; i<=diffCount;i++){
                            ((LhsNode<?>)lhs).setEffectsST(counterLhs,myDerExp.getEffectsST(counterExp));
                            counterLhs++;
                            counterExp++;
                        }
                    }else {
                        res.add(new SemanticError(("error: Wrong pointer assignment" )));
                        return res;
                    }
                }
                ((LhsNode<?>) lhs).setEffectsST(1);
                this.effectsST = 1;
                // End of Pointer Reference Assignment

                // Effect propagation checking
                if (lhs instanceof LhsNode && exp instanceof DerExpNode){

                    LhsNode left = (LhsNode) lhs;
                    LhsNode right = (LhsNode) ((DerExpNode) exp).getDerExp();

                    if(left.getCounterST() > 0 && right.getCounterST() > 0) {
                        // in this case they are pointer
                        // adding propagation to the left from the right
                        // this is needed to focus the deletion on EffectState[] of the leftEntry from
                        // the Counter to 0 index equals to d
                        // getting the entry of the left and right terms
                        STentry rightEntry = right.getEntry();
                        STentry tmp = env.lookup(env.getNestingLevel(), left.getId());
                        if (tmp == null) {
                            // this case is caught from checkSemantics
                            res.add(new SemanticError("error: cannot find symbol " + left.getId()));
                            return res;
                        }
                        rightEntry.addPropagation(left.getId(), left.getCounterST() - left.getCounter());
                    }
                } else {
                    // nothing
                }
                // End Effect Propagation checking
            } else {
                res.add(new SemanticError("error: cannot find symbol " + ((LhsNode<?>) lhs).getId()));
                return res;
            }
        } else {
            // do nothing
        }
        return res;
    }


    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        // lhs setting on effectDecFun
        lhs.setEffectDecFun(this.effectDecFun);
        res.addAll(lhs.checkSemantics(env));
        // exp setting on effectDecFun
        exp.setEffectDecFun(this.effectDecFun);
        res.addAll(exp.checkSemantics(env));
        if(res.size()==0){
            res.addAll(this.checkEffects(env));
        }
        return res;

    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env, Offset offset) {
        return null;
    }

    @Override
    public Node clone(){
        try{
            AssignmentNode cloned = (AssignmentNode) super.clone();
            cloned.lhs = (Node) this.lhs.clone();
            cloned.exp = (Node) this.exp.clone();
            return cloned;
        } catch(CloneNotSupportedException e){
            return null;
        }
    }

    /*
    gen(stable,x) =
          lw $al 0($fp)
          for (i=0;
               i < nesting_level - lookup(stable, x).nesting_level; i++) lw $al 0($al) ;
                    lw $a0 lookup(stable, x).offset($al) ;
     */
    @Override
    public String codeGeneration() {
        return "";
    }
}
