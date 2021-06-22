package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class STentry implements Cloneable {

    private int nl;
    private Node type;
    private int offset;
    private int pointerCounter; // pointer counter inside the table
    private int effectState[]; // current effect state
    /*
    HashMap<STEntry, Integer>::
    STEntry :: reference to the element to propagate the effect state
    Integer :: the level of assignment

    Case:
    x^ = y; [x -> rw, y -> rw]
    delete y; [x^ -> d, y -> d]
    Entry(y).HashMap |-> [Entry(x) : 1] || x^
     */

    private HashMap<String,Integer> propagation = new HashMap<String, Integer>();
    private DecFunNode reference;

    public STentry (int n, int os) {
        nl=n;
        offset=os;
        effectState = new int[1];
    }

    public STentry (int n, Node t, int os) {
        nl=n;
        type=t;
        offset=os;
        effectState = new int[1];
    }


    public STentry (int n, Node t, int os, int pointerCounter) {
        nl=n;
        type=t;
        offset=os;
        this.pointerCounter=pointerCounter;
        effectState = new int[pointerCounter + 1];
    }

    // getter and setter


    public HashMap<String, Integer> getPropagation() {
        return propagation;
    }

    public Integer getLevelPropagation(STentry e){
        if(this.propagation.containsKey(e))
            return this.propagation.get(e);
        else return -1; // no entry

    }

    public void addPropagation(String key, Integer value){
        if(!this.propagation.containsKey(key)){
            this.propagation.put(key, value);
        } else {
            this.propagation.replace(key, value);
        }
    }

    public void setPropagation(HashMap<String, Integer> propagation) {
        this.propagation = propagation;
    }

    public boolean checkKeyPropagation(STentry key){
        return propagation.containsKey(key);
    }

    public int getNl() {
        return nl;
    }

    public void setNl(int nl) {
        this.nl = nl;
    }

    public void setType(Node type) {
        this.type = type;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setPointerCounter(int pointerCounter) {
        this.pointerCounter = pointerCounter;
    }

    public int[] getEffectState() {
        return effectState;
    }

    public void setEffectState(int[] effectState) {
        this.effectState = effectState;
    }

    public void setReference(DecFunNode reference){
        this.reference = reference; // reference to the function declaration
    }

    public DecFunNode getReference(){
        return this.reference;
    }

    public void addType (Node t) {type=t;}

    public Node getType () {return type;}



    public int getEffectState(int i) {
        try {
            return effectState[i];
        } catch(IndexOutOfBoundsException e){
            System.out.println("Pointer referent error, used more pointers than " + pointerCounter + " ^ ");
            System.exit(1);
        }
        return effectState[i];
    }


    public void setEffectState(int i, int effectState) {
        try {
            this.effectState[i] = effectState;
        } catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("PointerType mismatch");
            System.exit(1);
        }
    }


    public int getPointerCounter(){
        return pointerCounter;
    }

    public int getOffset () {return offset;}

    public int getNestinglevel () {return nl;}


    // toPrint

    public String toPrint(String s) { //
        String tmp = "------- STENTRY -----------\n";
        tmp += s+"STentry: nestlev " + Integer.toString(nl) +"\n"+
                s+"STentry: type " +
                type.toPrint("") + "\n" +
                s + "STentry: propagation: [ ";
        int i = 0;
        for(Map.Entry<String, Integer> entry : propagation.entrySet()){
            tmp +=  "Entry " + i++ + " -> " + entry.getKey() + " : " + entry.getValue() + ", \n\n";
        }
        tmp += "]\n";
        tmp +=  s+"STentry: offset " + Integer.toString(offset) + "\n";
        tmp += "-----------------------\n";
        return tmp;
    }



    // done
    public STentry clone(){
        try{
            STentry cloned = (STentry) super.clone();
            cloned.type = (Node) this.type.clone();
            cloned.effectState  = this.effectState.clone();
            if(this.reference!=null) {
                cloned.reference = (DecFunNode) this.reference.clone();
            }else {
                cloned.reference = null;
            }

            return cloned;

        } catch(CloneNotSupportedException e){
            return null;
        }
    }
}