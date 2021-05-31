package ast;

import util.Environment;
import util.SemanticError;
import util.VoidNode;

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




    public Boolean checkRet() {
        boolean hasIteRet=false;
        boolean hasElse=false;
        if (statements.size() > 0) { // if the block is  not void
            for (int i = 0; i < statements.size(); i++) {
                StatementNode temp = (StatementNode)(statements.get(i));
                if ( temp.getSt() instanceof IteNode) {
                    hasIteRet= ((IteNode)temp.getSt()).getFg();
                    System.out.println(hasIteRet+" TEST");
                    if(((IteNode)temp.getSt()).getSize()>1){
                        hasElse=true;
                    }
                }
                    if (((StatementNode) statements.get(i)).getChRet()) {
                        if(hasIteRet && hasElse){  // hasIteRet is the return inside IteNode and hasElse is if IteNode has an else statement
                            System.out.println("Multiple return conflicts with iteration statement" );
                            System.exit(0);
                        }


                        if (i != statements.size() - 1) {
                            System.out.println("Mutiple return");
                            System.exit(0);
                        } else return true;
                    }
                }

        }
                return hasIteRet; // cases where ite has return statement but DecFunc doesn't
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

        ArrayList<Node> genericTypeNode = new ArrayList<Node>();

        // adding typeCheck of declarations
        for (Node dec : declarations)
            genericTypeNode.add(dec.typeCheck());

        // adding typeCheck of statements
        for (Node st : statements)
            genericTypeNode.add(st.typeCheck());

        if(genericTypeNode.size() > 0){
            // return the last declaration or statement of the block
            return genericTypeNode.get(genericTypeNode.size() -1 );

        } else {
            // if there is not declarations and statements (void block), return null
            return new VoidNode(); // void block
        }
    }

    public String codeGeneration() {
        return null;
    }

    @Override
    public void checkEffects() {
        // adding effects of declarations
        for (Node dec : declarations)
            dec.checkEffects();

        // adding effects of statements
        for (Node st : statements)
            st.checkEffects();
    }

}
