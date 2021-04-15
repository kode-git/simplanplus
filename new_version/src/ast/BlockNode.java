package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class BlockNode implements Node {

    private ArrayList<Node> declarations;
    private ArrayList<Node> statements;

    public BlockNode (ArrayList<Node> d, ArrayList<Node> s) {
        declarations=d;
        statements=s;
    }

    public BlockNode(){
        declarations = new ArrayList<Node>();
        statements = new ArrayList<Node>();
    }

    public String toPrint(String s) {
        System.out.println(s);


        String declstr="";
        for (Node dec:declarations)
            declstr += dec.toPrint(s+"  ");

        for (Node st:statements)
            declstr += st.toPrint(s+"  ");


        return s+"BlockNode\n" + declstr ;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        return null;
    }

    public Node typeCheck() {
        return null;
    }

    public String codeGeneration() {
        return null;
    }

}
