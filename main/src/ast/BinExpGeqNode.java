package ast;

import util.Environment;
import util.Offset;
import util.SemanticError;
import util.SimpLanlib;

import java.util.ArrayList;

public class BinExpGeqNode implements Node , Cloneable{

    private Node left;
    private Node right;
    private int effectDecFun;

    public BinExpGeqNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
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

        return s + "Geq: " + left.toPrint(s + "") + ">=" + right.toPrint(s + "") + "";
    }

    @Override
    public Node typeCheck() {
        if (!(SimpLanlib.isSubtype(left.typeCheck(), new IntTypeNode()) &&
                SimpLanlib.isSubtype(right.typeCheck(), new IntTypeNode()))) {
            System.out.println("error: bad operand types for binary operator '>='");
            System.exit(0);
        }
        return new BoolTypeNode();
    }

    // not used
    public int checkEffects(Environment env) {
        return 0;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        left.setEffectDecFun(this.effectDecFun);
        right.setEffectDecFun(this.effectDecFun);
        res.addAll(left.checkSemantics(env));
        res.addAll(right.checkSemantics(env));

        return res;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env, Offset offset) {
        return null;
    }

    @Override
    public Node clone() {
        try {
            BinExpGeqNode cloned = (BinExpGeqNode) super.clone();
            cloned.left = (Node) this.left.clone();
            cloned.right = (Node) this.right.clone();
            return cloned;
        } catch(CloneNotSupportedException e){
            return null;
        }
    }




    @Override
    public String codeGeneration() {

        String true_branch_geq = SimpLanlib.freshLabel();
        String end_geq = SimpLanlib.freshLabel();
        return  left.codeGeneration() +                  // r1 <- cgen(stable, right); s -> []
                "lr1\n" +                                   // r1 -> top_of_stack; s -> [r1] :: s -> [left]
                right.codeGeneration() +                     // r1 <- cgen(stable, left); s -> [r1]
                "sr2\n" +                                   // r2 <- top_of_stack; s -> [] :: r2 <- left
                "bless " + true_branch_geq + "\n" +         // if r1 < r2 == !r1 >= r2 :: right <= left go to true_branch_geq; s -> []
                "lir1 0\n" +                                // false :: 0; r1 <- 0; ; s -> []
                "b " + end_geq + "\n" +                     // jump end_geq; s -> []
                true_branch_geq + ":\n" +                  // true_branch_geq:; s -> []
                "lir1 1\n" +                                // true :: 1; r1 <- 1; s -> []
                end_geq + ":\n";                            // end_geq : ; s -> []
    }

}
