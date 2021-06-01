package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;



public class StatementNode implements Node{
    private Node st;
    private Boolean hasRet;
    public StatementNode(Node st){
        this.st=st;
        //checkRet();
    }
    public void checkRet(){
        if(st instanceof BlockNode) {
            this.hasRet = ((BlockNode) st).checkRet();
        }else {
            if (st instanceof RetNode) {
                this.hasRet = true;
                System.out.println(this + "test second");
            } else {
                this.hasRet = false;
                System.out.println(this + "test second");
            }
         }


    }

    public Node getSt() {
        return st;
    }

    public Boolean getChRet(){
        checkRet();
        return this.hasRet;
    }

    @Override
    public String toPrint(String s) {

        return s+"\n Statement:" + st.toPrint(s + "") ;


    }

    @Override
    public Node typeCheck() {
        return st.typeCheck();
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

        ArrayList<SemanticError> res = new ArrayList();
        res.addAll(st.checkSemantics(env));
       return res;

    }
}
