package ast;

import com.sun.source.tree.ReturnTree;
import util.Environment;
import util.SemanticError;

import java.util.ArrayList;
import java.util.HashMap;

public class LhsNode<T>implements Node,Cloneable{
    private T lhVar;
    private STentry entry;
    private int nestingLevel;
    private int counter;
    private int counterST;
    public LhsNode(T myNode){
        this.lhVar=myNode;
        counter=count(lhVar);
    }

    private int count(T var){
        if(var instanceof String){
            return 0;
        }else {
            var = (T)((LhsNode<?>)var).getLhVar();
            return count(var)+1;
        }
    }
    // used only on clone
    public LhsNode(){}

    @Override
    public String toPrint(String s) {
        if(lhVar instanceof Node){
            return s+ ((Node) lhVar).toPrint(s+ "") + "^ ";
        }else {
            return s+ lhVar +"";
        }

    }


    // getter and setter

    public T getLhVar() {
        return lhVar;
    }

    public void setLhVar(T lhVar) {
        this.lhVar = lhVar;
    }

    public STentry getEntry() {
        return entry;
    }

    public void setEntry(STentry entry) {
        this.entry = entry;
    }

    public int getNestingLevel() {
        return nestingLevel;
    }

    public void setNestingLevel(int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }

    // typeCheck, CodeGeneration, CheckSemantics



    //TODO DA COMPLETARE TYPE CHECKING
    @Override
    public Node typeCheck() {
        System.out.println("My counter is at " + counter + lhVar);
        if (!(lhVar instanceof LhsNode)) {
            if (entry.getType() instanceof ArrowTypeNode) { //
                System.out.println("Wrong usage of function identifier");
                System.exit(0);
            }

            return entry.getType();
        } else {
            //TODO HERE
            if (counter > 2) return entry.getType();
            System.out.println("else statement " + ((LhsNode) lhVar).typeCheck());

            if ((entry.getType() instanceof PointerTypeNode)) {
                System.out.println("this type" + entry.getType().typeCheck());
                return entry.getType().typeCheck();
            }
            return ((LhsNode) lhVar).typeCheck();
        }
    }


    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList();
        if (lhVar instanceof String) {
            STentry myEntry = env.checkId( env.getNestingLevel(), (String)lhVar);


            if (myEntry == null) {
                res.add(new SemanticError("Id " + (String)lhVar + " not declared"));
            } else {
                this.entry = myEntry;
                this.nestingLevel = env.getNestingLevel();
            }

        }else{


            T myVar =  lhVar;
            while ((myVar instanceof LhsNode)){
                myVar= (T) ((LhsNode<?>) myVar).getLhVar();
            }
            System.out.println(myVar);
            STentry myEntry = env.checkId( env.getNestingLevel(), (String)myVar);
            if (myEntry == null) {
                res.add(new SemanticError("Id " + (String)lhVar + " not declared"));
            } else {
                this.entry = myEntry;
                this.nestingLevel = env.getNestingLevel();
                counterST= myEntry.getPointerCounter();
            }
        }
        return res;


    }

}
