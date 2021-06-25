package util;

public class Offset {
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
}
