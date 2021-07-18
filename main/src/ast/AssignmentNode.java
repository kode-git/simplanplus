package ast;

import util.*;

import java.util.ArrayList;

public class AssignmentNode implements Node, Cloneable{

    private Node lhs;
    private Node exp;
    private int effectsST;
    private int effectDecFun;
    private int nestingLevel;

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
            System.out.println("Assignment Error: Assignment type failed");
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
            STentry lhsEntry = ((LhsNode<?>) lhs).getEntry();
            int[] effectsLhs = lhsEntry.getEffectState();
            if (lhsEffects >= 0 && lhsEffects <= 1) {

                //checking the effect's level, if previous new is missing, it starts an error
                if(effectsLhs.length>1){
                int counterLhsE = ((LhsNode) lhs).getCounter();

                for(int c=counterLhsE-1; c>=0; c--){
                    if(effectsLhs[c]!=1){
                        res.add(new SemanticError(("Assignment Error: new assignment not defined for previous pointer level" )));
                        return res;
                    }

                 }
                }

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
                        res.add(new SemanticError(("Assignment Error: Wrong pointer assignment" )));
                        return res;
                    }
                }
                ((LhsNode<?>) lhs).setEffectsST(1);
                this.effectsST = 1;
                // End of Pointer Reference Assignment

                // Effect propagation
                if (lhs instanceof LhsNode && exp instanceof DerExpNode){

                    LhsNode left = (LhsNode) lhs;
                    LhsNode right = (LhsNode) ((DerExpNode) exp).getDerExp();

                    if(left.getCounterST() > 0 && right.getCounterST() > 0) {
                        /*
                         in this case they are pointer
                         adding propagation to the left from the right
                         this is needed to focus the deletion on EffectState[] of the leftEntry from
                         the Counter to 0 index equals to d
                         getting the entry of the left and right terms
                        */

                        STentry rightEntry = right.getEntry();
                        STentry tmp = env.lookup(env.getNestingLevel(), left.getId());
                        if (tmp == null) {
                            // this case is caught from checkSemantics
                            res.add(new SemanticError("Assignment Error: cannot find symbol " + left.getId()));
                            return res;
                        }
                        // reset the propagation for id = left in any other variables
                        SemanticError err = env.resetPropagation(left.getId(), nestingLevel);
                        rightEntry.addPropagation(left.getId(), left.getCounterST() - left.getCounter());
                        if(err != null){
                            res.add(err);
                        }
                    }
                } else {
                    // do nothing
                }

            }else if(effectsLhs.length>1&&((LhsNode<?>) lhs).getCounter()==0&& exp instanceof NewExpNode){
                effectsLhs[0]=1;
                for (int c=1; c<effectsLhs.length;c++){
                    effectsLhs[c]=0;
                }

            }
            // End Effect Propagation checking
            else {
                res.add(new SemanticError("Assignment Error: cannot find symbol " + ((LhsNode<?>) lhs).getId()));
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
        this.nestingLevel = env.getNestingLevel();
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

    @Override
    public String codeGeneration() {



        if(lhs instanceof LhsNode){
            LhsNode lhsGen = (LhsNode) lhs;
            STentry entry = lhsGen.getEntry();
            int counterST = ((LhsNode<?>) lhs).getCounterST();
            if(counterST <= 0 && lhsGen.getLhVar() instanceof String){
                // case of no pointer

                String ar = "";
                for(int i = 0; i < this.nestingLevel - entry.getNestinglevel(); i++ ){
                    ar += "lw 0\n";     // lw al 0(al) :: al = MEMORY[al + 0]
                }
                return  exp.codeGeneration() +           // r1 <- cgen(stable, exp) s -> []
                        "lfp\n" +                        // fp -> top_of_stack :: s -> [fp]
                        "sal\n" +                        // al <- top_of_stack :: al <- fp; s -> []
                        //"lwafp 0\n" +                        // fp -> top_of_stack :: s -> [fp]
                        ar     +                        // lw al 0(al) :: al = MEMORY[al + 0] to check the AR; s -> []
                        "sw1 "+ entry.getOffset()+"\n";  // sw r1 entry.offset(al) :: r1 <- MEMORY[al + entry.offset]; s -> []

            } else {
                // case of pointer assignment
                String out="";
                String hr = "";
                if(lhsGen.getLhVar() instanceof String && lhsGen.getCounter() == 0) {
                    // assignment of the first pointer to new (independent from the number of levels)
                    // ^int x = new; x = new;
                    for (int i = 0; i < ((LhsNode) lhs).getCounter(); i++) {
                        hr += "lwh 0\n";     // lw r1 0(r1) :: r1 = MEMORY[r1 + 0]
                    }

                    String ar = "";
                    for (int i = 0; i < this.nestingLevel - entry.getNestinglevel(); i++) {
                        ar += "lw 0\n";     // lw al 0(al) :: al = MEMORY[al + 0]
                    }

                    out += exp.codeGeneration() +
                            "lfp\n" +                        // fp -> top_of_stack :: s -> [fp]
                            "sal\n" +                        // al <- top_of_stack :: al <- fp; s -> []
                            ar +                        // lw al 0(al) :: al = MEMORY[al + 0] to check the AR; s -> []
                            "sw1 " + entry.getOffset() + "\n";  // sw r1 entry.offset(al) :: r1 <- MEMORY[al + entry.offset]; s -> []


                    return out;
                }else{


                    // this is the internal pointer assignment
                    // assignment of the first pointer to new (independent from the number of levels)
                    // ^int x = new; x = new;
                    for (int i = 0; i < ((LhsNode) lhs).getCounter() - 1; i++) {
                        hr += "lwhp 0\n";     // lw r1 0(r1) :: r1 = MEMORY[r1 + 0]
                    }

                    String ar = "";
                    for (int i = 0; i < this.nestingLevel - entry.getNestinglevel(); i++) {
                        ar += "lw 0\n";     // lw al 0(al) :: al = MEMORY[al + 0]
                    }

                    out += exp.codeGeneration() +
                            "lr1\n" +                        // r1 -> top_of_stack :: s -> [r1]
                            "lfp\n" +                        // fp -> top_of_stack :: s -> [fp,r1]
                            "sal\n" +                        // al <- top_of_stack :: al <- fp; s -> [r1]
                            ar +                        // lw al 0(al) :: al = MEMORY[al + 0] to check the AR; s -> [r1]
                            "lw1 " + entry.getOffset() + "\n" +  // lw r1 entry.offset(al) :: r1 -> MEMORY[al + entry.offset]; s -> [r1]
                            hr +                        // lw r1 0(r1) :: r1 = MEMORY[r1 + 0] for i = 0 to counter - 1; s-> [r1]
                            "sr2\n" +                  // r2 <- top_of_stack :: s-> []
                            "swhr2 0\n";                  // sw r2 0(r1) :: MEMORY[r1 + 0] = r2; :: MEMORY[heap] = exp;
                    return out;
                }

            }

        }
        // is always an LhsNode if come here
        return "";
    }
}
