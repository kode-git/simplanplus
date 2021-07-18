package ast;

import util.*;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IteNode implements Node, Cloneable{
    ArrayList<Node> st;
    Node exp;
    Boolean fg;
    private int effectDecFun;

    public IteNode(ArrayList<Node> st, Node exp){
        this.st=st;
        this.exp=exp;
        this.fg=checkRet();
    }

    public Boolean checkRet(){
        boolean fg=false;
        for( Node s:st ){
            StatementNode temp = ((StatementNode)s);
            if(temp.getChRet()){
                fg=true;
            }else{
                fg=false;
            }
        }
        return fg;
    }

    // getter and setter


    public ArrayList<Node> getSt() {
        return st;
    }

    public void setSt(ArrayList<Node> st) {
        this.st = st;
    }

    public Node getExp() {
        return exp;
    }

    public void setExp(Node exp) {
        this.exp = exp;
    }

    public int getEffectDecFun() {
        return effectDecFun;
    }

    public int getSize(){
        return this.st.size();
    }

    public Boolean getFg() {
        return fg;
    }

    public void setFg(Boolean fg) {
        this.fg = fg;
    }

    @Override
    public void setEffectDecFun(int effectDecFun) {
        this.effectDecFun = effectDecFun;
    }

    // toPrint, typeCheck, checkSemantics, checkEffects, codeGeneration

    @Override
    public String toPrint(String s) {
        String declstr="";
        for (int i=0;i<st.size();i++) {
            if (i==0) {
                declstr +=  st.get(i).toPrint(s + "") + "}";
            }else {
                declstr += "else{" + st.get(i).toPrint(s + "") + "}";
            }

        }

        return s+"\nIf" + "("+ exp.toPrint(s+"")+"){" + declstr + "" ;
    }

    @Override
    public Node typeCheck() {

        if (!(SimpLanlib.isSubtype(exp.typeCheck(), new BoolTypeNode()))) {
            System.out.println("Iteration Error: bad condition type ");
            System.exit(0);
        } else {
            if (st.size() > 1) {
                if (!(SimpLanlib.isSubtype(st.get(1).typeCheck(), st.get(0).typeCheck()))) {
                    System.out.println("Iteration Error: statements type are different");
                    System.exit(0);
                }
            }
        }
        return st.get(0).typeCheck(); // This is void except in case of return
    }


    /*
    checkEffects :: [Environment, Environment, Environment] -> void
    Environment env :: Environment before the iteration effects control
    Environment env1 :: Environment with effects of the if condition
    Environment env2 :: Environment with effects of the else condition
    Rule :: env :-> if(e) st1 else st2 : max(env1, env2)
    */
    public void checkEffects(Environment env, Environment env1, Environment env2) {

        ArrayList<HashMap<String,STentry>>  symTable1 = env1.getSymTable();
        ArrayList<HashMap<String,STentry>>  symTable2 = env2.getSymTable();
        ArrayList<HashMap<String,STentry>>  symTableFinal = env.getSymTable();
        for(int c=0; c<symTable1.size();c++){
            for (Map.Entry<String, STentry> entry : symTable1.get(c).entrySet()) {
                String key = entry.getKey();
                int[] value = entry.getValue().getEffectState();
                int[] value2 = symTable2.get(c).get(key).getEffectState(); //retrieve of the corresponding value in the second SymTable
                for(int i=0;i< value.length;i++){
                    if(value[i]>value2[i]){ // set the effect state to the worst case :: max[a,b] = a : a > b
                        symTableFinal.get(c).get(key).setEffectState(i,value[i]);
                    }else {
                        symTableFinal.get(c).get(key).setEffectState(i,value2[i]);
                    }
                }
            }
        }
    }

    
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        exp.setEffectDecFun(this.effectDecFun);
        res.addAll(exp.checkSemantics(env));
        if(st.size()!=1){

            //checking the semantic/effects of the first statement with the cloned env
            st.get(0).setEffectDecFun(this.effectDecFun);
            //checking the semantic/effects of the second statement with the cloned env
            st.get(1).setEffectDecFun(this.effectDecFun);
            Environment env1 = env.clone(); // \Gamma'
            Environment env2 = env.clone(); // \Gamma''

            res.addAll(st.get(0).checkSemantics(env));
            FixedPoint.updateEnvironment(env1,env, env2);
            FixedPoint.resetEnvironment(env, env1);
            res.addAll(st.get(1).checkSemantics(env));
            // \Gamma -> Max[\Gamma', \Gamma'']
            FixedPoint.updateEnvironment(env1,env, env2);
            FixedPoint.resetEnvironment(env, env1);

            if(res.size() == 0)
                this.checkEffects(env, env1, env2);

        }else {
                st.get(0).setEffectDecFun(this.effectDecFun);
                res.addAll(st.get(0).checkSemantics(env));

        }

        return res;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env, Offset offset) {
        return null;
    }

    @Override
    public Node clone() {
        try{
            IteNode cloned = (IteNode) super.clone();
            cloned.st = (ArrayList<Node>) this.st.clone();
            cloned.exp = (Node) this.exp.clone();
            return cloned;
        } catch(CloneNotSupportedException e){
            return null;
        }
    }

    @Override
    public String codeGeneration() {
        String true_branch = SimpLanlib.freshLabel();
        String end_if = SimpLanlib.freshLabel();
        // s->[]
        if(st.size() == 2) {
            // this is the case with :: if(e) st else st
            return this.exp.codeGeneration() +         // r1 <- cgen(stable, exp); s->[]
                    "lir2 1\n" +                       // r2 <- 1; s -> []
                    "beq " + true_branch + "\n" +      // beq r1 r2 true_branch :: if r1 == r2 goto true_branch; s -> []
                    st.get(1).codeGeneration() +       // r1 <- cgen(stable, st.get(1)); s -> []
                    "b " + end_if + "\n" +             // jump end_if; s -> []
                    true_branch + ":\n" +              // true_branch:; s -> []
                    st.get(0).codeGeneration() +       // r1 <- cgen(stable, st.get(0)); s -> []
                    end_if + ":\n";                    // end_if :
        } else {
            // this is the case with :: if(e) st
            return this.exp.codeGeneration() +          // r1 <- cgen(stable, exp); s -> []
                    "lir2 1\n" +                        // r2 <- 1; s -> []
                    "beq " + true_branch + "\n" +       // beq r1 r2 true_branch :: if r1 == r2 goto true_branch; s -> []
                    "b " + end_if + "\n" +              // jump to end_if; s -> []
                    true_branch + ":\n" +               // true_branch:; s -> []
                    st.get(0).codeGeneration() +        // r1 <- cgen(stable, st.get(0)); s -> []
                    end_if + ":\n";                     // end_if :; s -> []

        }
    }
}