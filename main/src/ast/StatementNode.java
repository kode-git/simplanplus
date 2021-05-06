package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;



public class StatementNode implements Node{
    private Node st;
    private Boolean hasRet=false;
    public StatementNode(Node st){
        this.st=st;
        checkRet();
    }
    public void checkRet(){
        if(st instanceof RetNode){
            this.hasRet=true;
        }
    }
    public Boolean getChRet(){
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
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        ArrayList<SemanticError> res = new ArrayList();
        res.addAll(st.checkSemantics(env));
       return res;

    }
}
