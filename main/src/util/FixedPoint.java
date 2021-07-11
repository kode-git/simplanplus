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
        // environment is at nesting level of the function definition
        System.out.println("DecFun nl: " + function.getNestingLevel() + ", env nl: " + env.getNestingLevel());
        ArrayList<Environment> allEnv = new ArrayList<Environment>(); // list of environments states (the env is only one, state are mutable)
        allEnv.add(env.clone()); // adding  a new environment in the list of environment used in the fixed point
        Environment finalResultEnv = env.clone(); // final env status
        ArrayList<HashMap<String,STentry>>  symTableUpd = finalResultEnv.getSymTable();

        do {
            System.out.println("Iteration of the while");
            System.out.println("-----------------------");
            System.out.println("Fixed Point for " + id + ": " + FixedPoint.functionsFp.get(id));
            diff = false; // hypothesis that there are not difference
            Environment startingPointEnv = env.clone();
            allEnv.add(startingPointEnv);
            ArrayList<HashMap<String, STentry>> startTable = env.getSymTable();
            ArrayList<HashMap<String, STentry>> previousTable = allEnv.get(allEnv.size()-2).getSymTable();


                // fixed point computation
                // first iteration of the fixed point on effects

                res.addAll(function.checkSemantics(startingPointEnv));
                System.out.println("Did the decFun reference checkSemantics in the Fixed Point");
                for(int c=0; c<startTable.size();c++){
                    System.out.println("2 - Fixed Point for " + id + ": " + FixedPoint.functionsFp.get(id));
                    for (Map.Entry<String, STentry> entry : startTable.get(c).entrySet()) {
                        System.out.println("3 - Fixed Point for " + id + ": " + FixedPoint.functionsFp.get(id));
                        String key = entry.getKey();
                        System.out.println("Key: " + key);
                        int[] value = entry.getValue().getEffectState();
                        // retrieve of the corresponding value in the second SymTable
                        int[] value2 = previousTable.get(c).get(key).getEffectState();
                        for(int i=0;i< value.length;i++){
                            if(value[i]>value2[i]){ // set the effect state to the worst case :: max[a,b] = a : a > b
                                finalResultEnv.getSymTable().get(c).get(key).setEffectState(i,value[i]);
                                System.out.println("Setting effect state as value 1 for key  " + key + ": " + value[i]+" "+value2[i]);
                            }else {
                                finalResultEnv.getSymTable().get(c).get(key).setEffectState(i,value2[i]);
                                System.out.println("Setting effect state as value 2 for key  " + key + ": "+ value[i]+" "+value2[i]);
                            }
                            if(value[i]!=value2[i]) {
                                System.out.println(value[i] + " " + value2[i]);
                                diff = true;
                            }
                            System.out.println(diff?"Diff is true at the level: " + c: "Diff is false at the level: " + c);
                        }
                    }
                }
            if(diff == true){
                System.out.println("exit from while");
            }
            // break;
            FixedPoint.functionsFp.put(id, FixedPoint.functionsFp.get(id) + 1);
            System.out.println("4 - Fixed Point for " + id + ": " + FixedPoint.functionsFp.get(id));
        } while (!diff ); // until matching some differences between tables

        System.out.println("\n Printing of the value and value2:");
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
