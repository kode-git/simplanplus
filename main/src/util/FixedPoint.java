package util;

import ast.DecFunNode;
import ast.STentry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FixedPoint implements Serializable {

    // there is 1 instance for every callNode cld and different instance for different callNode in the AST
    int point; // using for fixed point on the effects controls for callNode

    public static  HashMap<String,Integer> functionsFp = new HashMap<>();


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

    public ArrayList<SemanticError> fixedPointFunc(Environment env, DecFunNode function, String id ){
        boolean diff;
        ArrayList<SemanticError> res = new ArrayList<>();

        ArrayList<Environment> allEnv = new ArrayList<Environment>();
        allEnv.add(env.clone());
        Environment finalResultEnv = env.clone();
        ArrayList<HashMap<String,STentry>>  symTableUpd = env.getSymTable();

        do {

            diff = false; // hypothesis that there are not difference
            Environment startingPointEnv = env.clone();
            allEnv.add(startingPointEnv);
            ArrayList<HashMap<String, STentry>> startTable = startingPointEnv.getSymTable();
            ArrayList<HashMap<String, STentry>> previousTable = allEnv.get(allEnv.size()-2).getSymTable();


                //fixed point computation
                //first iteration of the fixed point on effects

                res.addAll(function.checkSemantics(startingPointEnv));
                System.out.println("Did the decFun reference checkSemantics in the Fixed Point");
                for(int c=0; c<startTable.size();c++){
                    for (Map.Entry<String, STentry> entry : startTable.get(c).entrySet()) {
                        String key = entry.getKey();
                        int[] value = entry.getValue().getEffectState();
                        // retrieve of the corresponding value in the second SymTable
                        int[] value2 = previousTable.get(c).get(key).getEffectState();
                        for(int i=0;i< value.length;i++){
                            if(value[i]>value2[i]){ // set the effect state to the worst case :: max[a,b] = a : a > b
                                finalResultEnv.getSymTable().get(c).get(key).setEffectState(i,value[i]);
                                System.out.println(value[i]+" "+value2[i]);
                            }else {
                                finalResultEnv.getSymTable().get(c).get(key).setEffectState(i,value2[i]);
                                System.out.println(value[i]+" "+value2[i]);
                            }
                            if(value[i]!=value2[i]) {
                                diff = true;
                                System.out.println(diff?"Diff is true at the level: " + c: "Diff is false at the level: " + c);
                            }
                        }
                    }
                }

           // break;
            FixedPoint.functionsFp.put(id, FixedPoint.functionsFp.get(id) + 1);
        } while ( diff ); // until matching some differences between tables

        for(int c=0; c<symTableUpd.size();c++) {
            for (Map.Entry<String, STentry> entry : symTableUpd.get(c).entrySet()) {
                String key = entry.getKey();
                int[] value = entry.getValue().getEffectState();
                //retrieve of the corresponding value in the second SymTable
                int[] value2 = finalResultEnv.getSymTable().get(c).get(key).getEffectState();
                for (int i = 0; i < value.length; i++) {
                    if (value[i] > value2[i]) { // set the effect state to the worst case :: max[a,b] = a : a > b
                        symTableUpd.get(c).get(key).setEffectState(i, value[i]);
                        System.out.println(value[i]+" "+value2[i]);
                    } else {
                        symTableUpd.get(c).get(key).setEffectState(i, value2[i]);
                        System.out.println(value[i]+" "+value2[i]);
                    }
                }

            }
        }
        return res;
    }  // end of fixPoint method
}
