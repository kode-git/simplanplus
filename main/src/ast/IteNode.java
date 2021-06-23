package ast;

import util.Environment;
import util.SemanticError;
import util.SimpLanlib;

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
            System.out.println("error: bad condition type ");
            System.exit(0);
        } else {
            if (st.size() > 1) {
                if (!(SimpLanlib.isSubtype(st.get(1).typeCheck(), st.get(0).typeCheck()))) {
                    System.out.println("error: statements type are different");
                    System.exit(0);
                }
            }
        }
        return st.get(0).typeCheck();
    }

    @Override
    public String codeGeneration() {
        return null;
    }


    public int checkEffects(Environment env) {
        return 0;
    }



//TODO Moved env.clone() before the st.get(i).checkSemantics(env_cloned)
//Possible solution of the Bug: Divide the checkEffect from checkSemantics
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
            Environment env1 = env.clone();
            Environment env2 = env.clone();

            res.addAll(st.get(0).checkSemantics(env1));

            res.addAll(st.get(1).checkSemantics(env2));

            ArrayList<HashMap<String,STentry>>  symTable1 = env1.getSymTable();
            ArrayList<HashMap<String,STentry>>  symTable2 = env2.getSymTable();
            ArrayList<HashMap<String,STentry>>  symTableFinal = env.getSymTable();
            for(int c=0; c<symTable1.size();c++){
                for (Map.Entry<String, STentry> entry : symTable1.get(c).entrySet()) {
                    String key = entry.getKey();
                    int[] value = entry.getValue().getEffectState();
                    int[] value2 = symTable2.get(c).get(key).getEffectState(); //retrieve of the corresponding value in the second SymTable
                    for(int i=0;i< value.length;i++){
                        if(value[i]>value2[i]){
                            symTableFinal.get(c).get(key).setEffectState(i,value[i]);
                        }else {
                            symTableFinal.get(c).get(key).setEffectState(i,value2[i]);
                        }
                    }
                }
            }


        }else {
                st.get(0).setEffectDecFun(this.effectDecFun);
                res.addAll(st.get(0).checkSemantics(env));

        }

        if(res.size() == 0){
            // checking variables to block effect changing in stat.checkSemantics(env)
            this.checkEffects(env);
        }

        return res;
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
}
