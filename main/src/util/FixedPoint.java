package util;

import java.io.Serializable;

public class FixedPoint implements Serializable {

    // there is 1 instance for every callNode cloned and different instance for different callNode in the AST
    int fixed; // using for fixed point on the effects controls for callNode

    public FixedPoint(int fixed){
        this.fixed = fixed;
    }

    public int getFixed() {
        return fixed;
    }

    public void setFixed(int fixed) {
        this.fixed = fixed;
    }
}
