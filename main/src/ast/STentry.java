package ast;
public class STentry {

    private int nl;
    private Node type;
    private int offset;
    private int pointerCounter; // pointer counter inside the table
    private int effectState[]; // current effect state


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
        this.effectState[i] = effectState;
    }


    public int getPointerCounter(){
        return pointerCounter;
    }

    public int getOffset () {return offset;}

    public int getNestinglevel () {return nl;}

    public String toPrint(String s) { //
        return s+"STentry: nestlev " + Integer.toString(nl) +"\n"+
                s+"STentry: type\n" +
                type.toPrint(s+"") +
                s+"STentry: offset " + Integer.toString(offset) + "\n";
    }

}