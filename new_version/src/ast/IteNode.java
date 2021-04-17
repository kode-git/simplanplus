package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class IteNode implements Node{
    ArrayList<Node> st;
    Node exp;
    public IteNode(ArrayList<Node> st, Node exp){
        this.st=st;
        this.exp=exp;
    }
    @Override
    public String toPrint(String s) {
        String declstr="";
        for (int i=0;i<st.size();i++) {
            if (i==0) {
<<<<<<< HEAD
                declstr +=  st.get(i).toPrint(s + "") + "}";
            }else {
                declstr += "else{" + st.get(i).toPrint(s + "") + "}";
=======
                declstr +=  st.get(i).toPrint(s + "  ");
            }else {
                declstr += "else \n" + st.get(i).toPrint(s + "  ");
>>>>>>> 0e998b4 (Class Definition 1/2)
            }

        }

<<<<<<< HEAD
        return s+"\nIf" + "("+ exp.toPrint(s+"")+"){" + declstr + "" ;
=======
        return s+"If \n" + "("+ exp.toPrint(s+"  ")+")" + declstr + "\n" ;
>>>>>>> 0e998b4 (Class Definition 1/2)
    }

    @Override
    public Node typeCheck() {
        return null;
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }
}
