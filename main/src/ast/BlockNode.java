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
    private Boolean returnExp=false;
    private Boolean hasElse=false;
    private ArrayList<IteNode> iteNode= new ArrayList<IteNode>();

    public BlockNode (ArrayList<Node> d, ArrayList<Node> s) {
        declarations=d;
        statements=s;
        returnExp = hasExpRet();
    }

    public BlockNode(){
        declarations = new ArrayList<Node>();
        statements = new ArrayList<Node>();
    }

    public ArrayList<IteNode> getIteNode(){
        return iteNode;
    }

    public Boolean getReturnExp(){
        return this.returnExp;
    }



    public Boolean hasExpRet(){
        boolean hasRtExp=false;
        for (int i = 0; i < statements.size(); i++) {
            StatementNode temp = (StatementNode)(statements.get(i));
            if(temp.getSt() instanceof RetNode){
                hasRtExp= temp.getHasExp();
                if (temp.getHasExp()==false) return false;
            }
        }
        returnExp=true;
        return hasRtExp;
    }
public Boolean getHasElse(){
        return hasElse;
}
public Boolean checkRetExternal(){
    if (statements.size() > 0) {
           return ((StatementNode) statements.get(statements.size()-1)).getChRet();
    }else return false;
}
    public Boolean checkRet() {
        boolean hasIteRet=false;
        boolean hasElse=false;
        boolean hasExpRet=false;
        if (statements.size() > 0) {
            for (int i = 0; i < statements.size(); i++) {
                StatementNode temp = (StatementNode)(statements.get(i));
                if ( temp.getSt() instanceof IteNode) {
                    ArrayList<StatementNode> iteSt= new ArrayList<>();
                           iteSt.add((StatementNode) temp.getSt());
                    while (iteSt instanceof IteNode){
                        iteSt= ((IteNode)iteSt).getSt();
                    }
                    hasIteRet= ((IteNode)temp.getSt()).getFg(); //return true if ite has return statement

                    ////////////////////////////////////////////////   test
                    System.out.println(hasIteRet+"AA!434");
                     if(hasIteRet){
                         hasExpRet=((IteNode) temp.getSt()).getExpFg();
                         this.iteNode.add((IteNode) temp.getSt());
                     }
                     this.returnExp=hasExpRet;
                    //////////////////////////////////////////////////
                    if(((IteNode)temp.getSt()).getSize()>1){
                        this.hasElse=true;
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

}
