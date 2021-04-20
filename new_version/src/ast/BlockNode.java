package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
            declstr += dec.toPrint(s+"");

        for (Node st:statements)
            declstr += st.toPrint(s+"");


        return s+"\nBlockNode:" + declstr ;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        env.setNestingLevel(env.getNestingLevel()+1);
        env.addTable(new HashMap());
        ArrayList<SemanticError> res = new ArrayList();
        if (this.declarations.size() > 0) {
            env.setOffset(env.getOffset()-2);
            Iterator decV = this.declarations.iterator();
            while(decV.hasNext()) {
                Node n = (Node)decV.next();
                res.addAll(n.checkSemantics(env));
            }
        }
        if (this.statements.size() > 0) {
            env.setOffset(env.getOffset()-2);
            Iterator staT = this.statements.iterator();
            while(staT.hasNext()) {
                Node n = (Node)staT.next();
                res.addAll(n.checkSemantics(env));
            }
        }
        env.removeTable();
        return res;
    }

    public Node typeCheck() {
        return null;
    }

    public String codeGeneration() {
        return null;
    }

}
