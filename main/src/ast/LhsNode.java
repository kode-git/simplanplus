package ast;

import com.sun.source.tree.ReturnTree;
import util.Environment;
import util.Offset;
import util.SemanticError;

import java.util.ArrayList;
import java.util.HashMap;

public class LhsNode<T extends Object>implements Node,Cloneable{
    private T lhVar;
    private STentry entry;
    private int nestingLevel;
    private int counter;
    private int counterST;
    private int effectsST;
    private int effectDecFun;

    public LhsNode(T myNode){
        this.lhVar=myNode;
        counter=count(lhVar);
    }

    private int count(T var) {
        if (var instanceof String) {
            return 0;
        } else {
            var = (T) ((LhsNode<?>) var).getLhVar();
            return count(var) + 1;
        }
    }

    @Override
    public void setEffectDecFun(int effectDecFun) {
        this.effectDecFun = effectDecFun;
    }

    @Override
    public String toPrint(String s) {
        if(lhVar instanceof Node){
            return s+ ((Node) lhVar).toPrint("") + "^ ";
        }else {
            return s+ lhVar +"";
        }

    }


    // getter and setter


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounterST() {
        return counterST;
    }

    public void setCounterST(int counterST) {
        this.counterST = counterST;
    }

    public int getEffectDecFun() {
        return effectDecFun;
    }

    public int getEffectsST() {

        return effectsST;
    }
    public int getEffectsST(int count) {

        return  entry.getEffectState(count);

    }

    public void setEffectsST(int effectsST) {
        entry.setEffectState(counter, effectsST);
        this.effectsST = effectsST;

    }
    public void setEffectsST(int myCounter, int effectsST) {
        entry.setEffectState(myCounter, effectsST);
        this.effectsST = effectsST;

    }

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

    // toPrint, typeCheck, checkSemantics, checkEffects, codeGeneration


    @Override
    public Node typeCheck() {
        if (!(lhVar instanceof LhsNode)) {
            if (entry.getType() instanceof ArrowTypeNode) { //
                System.out.println("error: Wrong usage of function identifier");
                System.exit(0);
            }

            return entry.getType();
        } else {

            if (counter == counterST){
                T val=(T)((PointerTypeNode<GenericTypeNode>)entry.getType()).getVal();
                while ( val instanceof PointerTypeNode){
                    val= (T)((PointerTypeNode<?>) val).getVal();
                }
                return ((Node)val);
            }
            if(counter>counterST){
                System.out.println("Incompatible pointer type error, you haven't declared enough pointers" + counter + " " + counterST);
                System.exit(0);
            }
            return entry.getType();

        }
    }


    public String getId(){
        T value = lhVar;
        if(value instanceof String){
            return (String) value;
        } else {
            while ((value instanceof LhsNode)) {
                value = (T) ((LhsNode<?>) value).getLhVar();
            }
            return (String) value;
        }
    }

    public int checkEffects(Environment env) {
        if(effectDecFun == 0) {
            STentry myEntry = null;
            if (lhVar instanceof String) {
                myEntry = env.lookup(env.getNestingLevel(), (String) lhVar);
                effectsST = myEntry.getEffectState(counter); // 0 because is a string
            } else {
                T myVar = lhVar;
                String id = this.getId();
                myEntry = env.lookup(env.getNestingLevel(), id);
                effectsST = myEntry.getEffectState(counter);
            }
        } else {
            // do nothing
        }
        return effectsST;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList();
        STentry myEntry=null;
        if (lhVar instanceof String) {
             myEntry = env.lookup( env.getNestingLevel(), (String)lhVar);
            if (myEntry == null) {
                res.add(new SemanticError("error: Id " + (String)lhVar + " not declared"));
            } else {
                this.entry = myEntry;
                this.nestingLevel = env.getNestingLevel();
                this.counterST= myEntry.getPointerCounter();
            }
        }else{

            String id = this.getId();
            myEntry = env.lookup( env.getNestingLevel(), id);

            if (myEntry == null) {
                res.add(new SemanticError("error: Id " + id + " not declared"));
            } else {
                this.entry = myEntry;
                this.nestingLevel = env.getNestingLevel();
                this.counterST= myEntry.getPointerCounter();

            }
        }

        if(res.size()== 0 && effectDecFun == 0){
            this.checkEffects(env);
        }

        return res;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env, Offset offset) {
        return null;
    }

    @Override
    public Node clone() {
        try{
            LhsNode cloned = (LhsNode) super.clone();
            if(this.lhVar instanceof LhsNode){
            cloned.lhVar = ((LhsNode) this.lhVar).clone();
            } else {
                // is a string and not must be cloned
            }
            if(this.entry != null) {
                cloned.entry = (STentry) this.entry.clone();
            }
            return cloned;


        } catch(CloneNotSupportedException e){
            return null;
        }
    }

    @Override
    public String codeGeneration() {

        if(lhVar instanceof LhsNode){
            // Pointer case
            return ""; // TODO
        } else {
            // ID case
            String ar = "";
            System.out.println(this.nestingLevel + "<- this, entry -> " + entry.getNestinglevel());
            for(int i = 0; i < this.nestingLevel - entry.getNestinglevel(); i++ ){
                ar += "lw 0\n";     // lw al 0(al) :: al = MEMORY[al + 0]
            }
            return "lfp\n" +                        // fp -> top_of_stack :: s -> [fp]
                   "sal\n" +                        // al <- top_of_stack :: al <- fp; s -> []
                    ar     +                        // lw al 0(al) :: al = MEMORY[al + 0] to check the AR; s -> []
                    "lw1 "+ entry.getOffset()+"\n";  // lw r1 entry.offset(al) :: r1 <- MEMORY[entry.offset + al]; s -> []

        }

    }


}
