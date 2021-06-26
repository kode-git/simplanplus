package ast;

import util.Environment;
import util.Offset;
import util.SemanticError;
import util.SimpLanlib;

import java.util.ArrayList;

public class BinExpLeqNode implements Node , Cloneable{

    private Node left;
    private Node right;
    private int effectDecFun;

    public BinExpLeqNode(Node left, Node right) {
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
        return s + "Leq: " + left.toPrint(s + "") + "<=" + right.toPrint(s + "") + "";
    }

    @Override
    public Node typeCheck() {
        if (!(SimpLanlib.isSubtype(left.typeCheck(), new IntTypeNode()) &&
                SimpLanlib.isSubtype(right.typeCheck(), new IntTypeNode()))) {
            System.out.println("error: bad operand types for binary operator '<='");
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
            BinExpLeqNode cloned = (BinExpLeqNode) super.clone();
            cloned.left = (Node) this.left.clone();
            cloned.right = (Node) this.right.clone();
            return cloned;
        } catch(CloneNotSupportedException e){
            return null;
        }
    }

    @Override
    public String codeGeneration() {
        String true_branch_leq = SimpLanlib.freshLabel();
        String end_leq = SimpLanlib.freshLabel();
        return  right.codeGeneration() +                // r1 <- cgen(stable, left); r1 <- right; s -> []
                "lr1\n" +                               // r1 -> top_of_stack; s -> [r1] :: s -> [right]
                left.codeGeneration() +                 // r1 <- cgen(stable, right); r1 <- left; s -> [r1] :: s ->[right]
                "sr2\n" +                               // r2 <- top_of_stack; r2 <- right; s -> []
                "bleq " + true_branch_leq + "\n" +      // r1 <= r2 :: left <= right go to true_branch; s -> []
                "lir1 0\n" +                            // false :: 0 :: r1 <- 0; s -> []
                "b " + end_leq + "\n" +                 // jump end_leq; s -> []
                true_branch_leq + ":\n" +               //true_branch_leq:; s -> []
                "lir1 1\n" +                            // true :: 1 :: r1 <- 1 ; s -> []
                end_leq + ":\n";                        // end_leq :; s -> []


    }

}
