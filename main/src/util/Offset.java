package util;

public class Offset implements Cloneable{
    private int offset=0;

    public int getOffset(){
        return this.offset;
    }
    public void setOffset(int offset){
        this.offset=offset;
    }
    public void increment(){
        this.offset++;
    }
    public Offset clone(){
        try{
            Offset cloned = (Offset) super.clone();
            return cloned;

        } catch(CloneNotSupportedException e){
            return null;
        }

    }
}
