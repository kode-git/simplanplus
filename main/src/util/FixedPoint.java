package util;

import ast.DecFunNode;
import ast.STentry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FixedPoint implements Serializable {

    // there is 1 instance for every callNode cloned and different instance for different callNode in the AST
    int point; // using for fixed point on the effects controls for callNode

    public FixedPoint(int point){
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    /*
     fixedPointFunc :: [Environment, Environment, DecFunNode, int] -> void
     Environment env :: main environment
     DecFunNode function :: function declaration
     int fixed :: fixed variable of the FixedPoint

     Used :: [CallNode]
     */
    public ArrayList<SemanticError> fixedPointFunc(Environment env, DecFunNode function, int fixed ){
        boolean diff;
        ArrayList<SemanticError> res = new ArrayList<>();
        do {
            diff = false; // hypothesis that there are not difference
            Environment fixedPointEnv = env.clone();
            ArrayList<HashMap<String, STentry>> symTableFixed = fixedPointEnv.getSymTable();
            ArrayList<HashMap<String,STentry>>  symTableFinal = env.getSymTable();
            if(fixed == 0) {

                //fixed point computation
                //first iteration of the fixed point on effects
                res.addAll(function.checkSemantics(env));

                for(int c=0; c<symTableFixed.size();c++){
                    for (Map.Entry<String, STentry> entry : symTableFinal.get(c).entrySet()) {
                        String key = entry.getKey();
                        int[] value = entry.getValue().getEffectState();
                        //retrieve of the corresponding value in the second SymTable
                        int[] value2 = symTableFixed.get(c).get(key).getEffectState();
                        for(int i=0;i< value.length;i++){
                            if(value[i]!=value2[i]){
                                diff = true; //there are some differences, needs a new iteration
                            }

                        }
                    }
                }

            }

        } while ( diff ); // until matching some differences between tables
        return res;
    }  // end of fixPoint method
}
