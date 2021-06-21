package ast;
public class STentry implements Cloneable {

    private int nl;
    private Node type;
    private int offset;
    private int pointerCounter; // pointer counter inside the table
    private int effectState[]; // current effect state
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
        return s+"STentry: nestlev " + Integer.toString(nl) +"\n"+
                s+"STentry: type\n" +
                type.toPrint(s+"") +
                s+"STentry: offset " + Integer.toString(offset) + "\n";
    }



    // done
    public STentry clone(){
        try{
            STentry cloned = (STentry) super.clone();
            cloned.type = (Node) this.type.clone();
            if(this.effectState == null){
                if(this.pointerCounter >= 1){
                    this.effectState = new int[this.pointerCounter + 1];
                }
                else {
                    this.effectState = new int[1];
                }

            }


            cloned.effectState  = this.effectState.clone();
            if(this.reference!=null) {
                cloned.reference = (DecFunNode) this.reference.clone();
            }else {
                cloned.reference = null;
            }
            for(int i = 0; i < this.effectState.length; i++){
                cloned.setEffectState(i, effectState[i]);
            }

            return cloned;

        } catch(CloneNotSupportedException e){
            return null;
        }
    }
}