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
    private int i = 0;
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





                } else {
                System.out.println("Pointer Effect State in the Fixed Point do not contain the id");
                System.exit(1);
            }
            // update the fixed point
            FixedPoint.functionsFp.put(id, FixedPoint.functionsFp.get(id) + 1);

            // reset environment
            resetEnvironment(env, env2);
            i++;
            if (i==10)break;
        } while(diff);
        i=0;
        
        // Update the environment with the effect defined in the final environment

        resetEnvironment(env, finalEnvironment);
        //env = finalEnvironment;

        return res;
    }

}
