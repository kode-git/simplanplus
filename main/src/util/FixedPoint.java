package util;

import ast.DecFunNode;
import ast.STentry;
import org.antlr.v4.runtime.misc.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FixedPoint implements Serializable {

    // there is 1 instance for every callNode cld and different instance for different callNode in the AST
    int point; // using for fixed point on the effects controls for callNode
    static int i = 0;
    // fixed point of a callNode invocation
    public static  HashMap<String,Integer> functionsFp = new HashMap<>();
    // taking the same pointer effects state for every callNode of the fixed point procedure
    public static HashMap<String, ArrayList<int[]>> pointerEffectStateFp = new HashMap<>();

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
    Reset of environments and put every value of before in updated
        updated - Environment with changes
        before - Environment before changes
     */
    public static void resetEnvironment(Environment updated, Environment before){
        Environment initial = before; // before update of the checkSemantics on DecFunNode
        for(int j = 0; j < before.getSymTable().size(); j++) {
            if(updated.getSymTable().size() == before.getSymTable().size()){ // the size is equal because changes are popped
                for (Map.Entry<String, STentry> entryBefore: before.getSymTable().get(j).entrySet()) {
                    // taking every entry of the symbol table of before
                    String key = entryBefore.getKey();
                    STentry entryUpdated = updated.getSymTable().get(j).get(key);
                    int[] updatedEffect = entryUpdated.getEffectState();
                    int[] beforeEffect = entryBefore.getValue().getEffectState();
                    for(int i = 0; i < updatedEffect.length; i++) {
                        updatedEffect[i] = beforeEffect[i];
                    }
                }
            }

        }
    }


    public static void updateEnvironment(Environment prev, Environment cur, Environment finalEnvironment){

        ArrayList<HashMap<String, STentry>> finalTable = finalEnvironment.getSymTable();
        ArrayList<HashMap<String, STentry>> previous = prev.getSymTable();
        // Variables in the current nesting level
        ArrayList<HashMap<String, STentry>> current = cur.getSymTable(); // current symbol table

        int nl = 0; // init the nl
        for(nl = 0; nl < previous.size(); nl++){
            // We are in nesting level nl

            // this is temporal control
            if(current.get(nl) != previous.get(nl)) {
                System.out.println("Differences between current and previous matched");
            }
            for (Map.Entry<String, STentry> entry : current.get(nl).entrySet()) {
                // We have the entry of the nesting level nl
                // we need to compare this entry between current and previous tables
                String key = entry.getKey(); // taking the entry key
                // Effects state for an entry in the table for final, current and previous environment
                int[] previousEffect = previous.get(nl).get(key).getEffectState();
                int[] currentEffect = entry.getValue().getEffectState();
                int[] finalEffect = finalTable.get(nl).get(key).getEffectState();

                // control of the length of entry effectState (need to be both pointers of n level)
                if(currentEffect.length != previousEffect.length) {
                    System.out.println("Error in the reference effect state");
                    System.exit(1);
                }

                for(int i = 0; i < currentEffect.length; i++){
                    if(currentEffect[i] != previousEffect[i]){
                        if(currentEffect[i] > previousEffect[i]){
                            System.out.println(
                                    "Case previous < current ::Entry: " + key + ", " +
                                            "with state difference: current is:"
                                            + currentEffect[i] +  ", previous: " + previousEffect[i]);
                            finalEffect[i] = currentEffect[i];

                        } else {
                            finalEffect[i] = previousEffect[i];
                            System.out.println(
                                    "Case previous >= current :: Entry: " + key + ", " +
                                            "with state difference: current is:"
                                            + currentEffect[i] +  ", previous: " + previousEffect[i]);
                        }
                    }


                } // end of the effectState for a specific entry
            } // end of the iteration for entry of a nesting level hashmap in the current table
        } // end of the iteration for the nesting level 0 to previous.size() - 1
    }
    /*
     * Pre-condition:
     * functionsFp set with the respective id
     * pointerEffectStateFp set with the pointerEffectState on id
     * fp = 1 at the start of the invocation 
     * env is in the nesting level of decFunNode reference of the callNode
     *
     *
     */
    public ArrayList<SemanticError> fixedPointProc(Environment env, DecFunNode function, String id ){

        System.out.println("-----------------");
        System.out.println("ENTER IN THE FIX POINT PROC");
        System.out.println("-----------------");
        // is false if no difference is reached, true otherwise
        ArrayList<SemanticError> res = new ArrayList<>();
        Boolean diff = false;
        // Contains every environment of the fixed point
        Environment env1 = env.clone();
        ArrayList<Environment> envCollection = new ArrayList<Environment>();

        // this is the top environment not in the collection but is the top environment of the iterations
        // in the fixed point procedure
        Environment finalEnvironment = env.clone(); // main Environment
        Environment env2 = env.clone();

        envCollection.add(env.clone()); // env before changes
        do {
            System.out.println("-----------------");
            System.out.println("DO WHILE FIX POINT");
            System.out.println("-----------------");
            diff = false; // set false for the next iteration

            System.out.println("Banana");
            res.addAll(function.checkSemantics(env)); // called only in the inner callNode
            envCollection.add(env.clone()); // env after changes


            // env is updated here
            // find differences between finalEnvironment and env updated by line 62
            // env - it is the environment updated
            // env2 - it is the initial environment before update
            // we need to switch env and env states

            // Env2 is now updated and the size - 2 is the previous environment
            // Taking the previous symTable (size - 2) and current SymTable from env2

            Environment prev = envCollection.get(envCollection.size() - 2);
            Environment cur = envCollection.get(envCollection.size() - 1);
            ArrayList<HashMap<String, STentry>> finalTable = finalEnvironment.getSymTable();
            ArrayList<HashMap<String, STentry>> previous = prev.getSymTable();

            // Variables in the current nesting level
            ArrayList<HashMap<String, STentry>> current = cur.getSymTable(); // current symbol table

            // nl is the nesting level of the current and previous tables of the previous and current environment
            int nl = 0; // init the nl
            for(nl = 0; nl < previous.size(); nl++){
                // We are in nesting level nl

                // this is temporal control
                if(current.get(nl) != previous.get(nl)) {
                    System.out.println("Differences between current and previous matched");
                }
                for (Map.Entry<String, STentry> entry : current.get(nl).entrySet()) {
                    // We have the entry of the nesting level nl
                    // we need to compare this entry between current and previous tables
                    String key = entry.getKey(); // taking the entry key
                    // Effects state for an entry in the table for final, current and previous environment
                    int[] previousEffect = previous.get(nl).get(key).getEffectState();
                    int[] currentEffect = entry.getValue().getEffectState();
                    int[] finalEffect = finalTable.get(nl).get(key).getEffectState();

                    // control of the length of entry effectState (need to be both pointers of n level)
                    if(currentEffect.length != previousEffect.length) {
                        System.out.println("Error in the reference effect state");
                        System.exit(1);
                    }

                    for(int i = 0; i < currentEffect.length; i++){
                        if(currentEffect[i] != previousEffect[i]){
                            diff = true;
                            if(currentEffect[i] > previousEffect[i]){
                                System.out.println(
                                        "Case previous < current ::Entry: " + key + ", " +
                                                "with state difference: current is:"
                                                + currentEffect[i] +  ", previous: " + previousEffect[i]);
                                finalEffect[i] = currentEffect[i];

                            } else {
                                finalEffect[i] = previousEffect[i];
                                System.out.println(
                                        "Case previous >= current :: Entry: " + key + ", " +
                                                "with state difference: current is:"
                                                + currentEffect[i] +  ", previous: " + previousEffect[i]);
                            }
                        }


                    } // end of the effectState for a specific entry
                } // end of the iteration for entry of a nesting level hashmap in the current table
            } // end of the iteration for the nesting level 0 to previous.size() - 1

            // update the function pointer effect state for next iteration
            if(FixedPoint.pointerEffectStateFp.containsKey(id)) {

                    ArrayList<int[]> currentPointerEffect = FixedPoint.pointerEffectStateFp.get(id);
                    //System.out.println(function.getPointerEffectStatesArg().get(0) + " and " + function.getPointerEffectStatesArg().get(1));
                    //System.out.println("Function.get " + "0: " + function.getPointerEffectStatesArg().get(0)  + ", 1:" + function.getPointerEffectStatesArg().get(1));
                    function.setPointerEffectStatesArg(currentPointerEffect);
                    //System.out.println("Function.get " + "0: " + function.getPointerEffectStatesArg().get(0)  + ", 1:" + function.getPointerEffectStatesArg().get(1));

/*
                ArrayList<int[]> tmp = new ArrayList<int[]>();
                ArrayList<int[]> currentPointerEffect = FixedPoint.pointerEffectStateFp.get(id);
                if(i == 0) {
                    tmp.add(function.getPointerEffectStatesArg().get(1));
                    System.out.println("Set 0 the element 1:" + currentPointerEffect.get(1) + " in " + tmp.get(0));
                    tmp.add(function.getPointerEffectStatesArg().get(0));
                    System.out.println("Set 1 the element 0:" + currentPointerEffect.get(0) + " in " + tmp.get(1));
                    function.setPointerEffectStatesArg(tmp);
                    i++;
                }

 */



                } else {
                System.out.println("Pointer Effect State in the Fixed Point do not contain the id");
                System.exit(1);
            }
            // update the fixed point
            FixedPoint.functionsFp.put(id, FixedPoint.functionsFp.get(id) + 1);

            // reset environment
            resetEnvironment(env, env2);

        } while(diff);
        
        // Update the environment with the effect defined in the final environment

        resetEnvironment(env, finalEnvironment);
        //env = finalEnvironment;

        return res;
    }
    /*
     fixedPointFunc :: [Environment, Environment, DecFunNode, int] -> void
     Environment env :: main environment
     DecFunNode function :: function declaration
     int fixed :: fixed variable of the FixedPoint

     Used :: [CallNode]
     */
/*

    public ArrayList<SemanticError> fixedPointFunc(Environment env, DecFunNode function, String id ){
        boolean diff;
        ArrayList<SemanticError> res = new ArrayList<>();
        // environment is at nesting level of the function definition
        System.out.println("DecFun nl: " + function.getNestingLevel() + ", env nl: " + env.getNestingLevel());
        ArrayList<Environment> allEnv = new ArrayList<Environment>(); // list of environments states (the env is only one, state are mutable)
        allEnv.add(env.clone()); // adding  a new environment in the list of environment used in the fixed point
        Environment finalResultEnv = env.clone(); // final env status
        ArrayList<HashMap<String,STentry>>  symTableUpd = finalResultEnv.getSymTable();
        ArrayList<HashMap<String,STentry>>  updatingSymTable= env.getSymTable();

        do {
            System.out.println("Iteration of the while");
            System.out.println("-----------------------");
            System.out.println("Fixed Point for " + id + ": " + FixedPoint.functionsFp.get(id));
            diff = false; // hypothesis that there are no differences
            Environment startingPointEnv = env.clone();
            allEnv.add(startingPointEnv);
            ArrayList<HashMap<String, STentry>> startTable = startingPointEnv.getSymTable();
            ArrayList<HashMap<String, STentry>> previousTable = allEnv.get(allEnv.size()-2).getSymTable();
            System.out.println(allEnv.size()+ " size of all the env addes");

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
                                //diff= true;
                            }else {
                                finalResultEnv.getSymTable().get(c).get(key).setEffectState(i,value2[i]);
                                System.out.println("Setting effect state as value 2 for key  " + key + ": "+ value[i]+" "+value2[i]);
                            }
                            if(value[i]!=value2[i]) {

                                diff = true;
                                System.out.println(value[i] + " " + value2[i] + "diff is true here! "+ diff);
                            }
                            System.out.println(diff?"Diff is true at the level: " + c: "Diff is false at the level: " + c);
                           // System.out.println(finalResultEnv.getSymTable().get(c).get(key).getEffectState(i)+ " myupdated");
                        }
                    }
                }
            if(diff == true){
                System.out.println("stay in while");
            }
            // break;
            FixedPoint.functionsFp.put(id, FixedPoint.functionsFp.get(id) + 1);
            System.out.println("4 - Fixed Point for " + id + ": " + FixedPoint.functionsFp.get(id));
        } while (diff ); // until matching some differences between tables

        System.out.println("\n Printing of the value and value2:");
        for(int c=0; c<updatingSymTable.size();c++) {
            for (Map.Entry<String, STentry> entry : updatingSymTable.get(c).entrySet()) {
                String key = entry.getKey();
                int[] value = entry.getValue().getEffectState();
                //retrieve of the corresponding value in the second SymTable
                int[] value2 = finalResultEnv.getSymTable().get(c).get(key).getEffectState();
                for (int i = 0; i < value.length; i++) {
                    if (value[i] > value2[i]) { // set the effect state to the worst case :: max[a,b] = a : a > b
                        updatingSymTable.get(c).get(key).setEffectState(i, value[i]);
                        System.out.println(value[i]+" "+value2[i]);
                    } else {
                        updatingSymTable.get(c).get(key).setEffectState(i, value2[i]);
                        System.out.println(value[i]+" "+value2[i]);
                    }
                }

            }
        }
        return res;
    }  // end of fixPoint method


 */
}
