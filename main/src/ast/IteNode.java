package ast;

import util.Environment;
import util.SemanticError;
import util.SimpLanlib;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;

public class IteNode implements Node{
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

    @Override
    public int checkEffects(Environment env) {
        return 0;
    }



    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        exp.setEffectDecFun(this.effectDecFun);
        res.addAll(exp.checkSemantics(env));
        for(Node stat : st){
            stat.setEffectDecFun(this.effectDecFun);
            res.addAll(stat.checkSemantics(env));
        }

        return res;
    }
}
