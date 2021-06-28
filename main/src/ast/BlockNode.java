package ast;

import util.Environment;
import util.Offset;
import util.SemanticError;
import util.VoidNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BlockNode implements Node {

    private ArrayList<Node> declarations;
    private ArrayList<Node> statements;
    private int effectDecFun;
    private int nestingLevel;

    public BlockNode (ArrayList<Node> d, ArrayList<Node> s) {
        declarations=d;
        statements=s;
    }

    public BlockNode(){
        declarations = new ArrayList<Node>();
        statements = new ArrayList<Node>();
    }

    // getter and setter

    public ArrayList<Node> getDeclarations() {
        return declarations;
    }

    public void setDeclarations(ArrayList<Node> declarations) {
        this.declarations = declarations;
    }

    public ArrayList<Node> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Node> statements) {
        this.statements = statements;
    }

    public int getEffectDecFun() {
        return effectDecFun;
    }

    public void setEffectDecFun(int effectDecFun) {
        this.effectDecFun = effectDecFun;
    }


    // toPrint, typeCheck, checkSemantics, checkEffects, codeGeneration


    public String toPrint(String s) {
        String out ="";
        for (Node dec:declarations)
            out += dec.toPrint(s+"") ;

        for (Node st:statements)
            out += st.toPrint(s+"");


        return "\nBlockNode:" + out ;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        env.addTable(new HashMap());
        ArrayList<SemanticError> res = new ArrayList();
        this.nestingLevel = env.getNestingLevel();
        if (this.declarations.size() > 0) {
            Offset blockOffset= new Offset();
            blockOffset.increment();
            //env.setOffset(env.getOffset()-2);
            Iterator decV = this.declarations.iterator();
            while(decV.hasNext()) {
                Node n = (Node) decV.next();
                n.setEffectDecFun(effectDecFun);
                res.addAll(n.checkSemantics(env,blockOffset));
            }
        }
        if (this.statements.size() > 0) {
            Iterator staT = this.statements.iterator();
            while(staT.hasNext()) {
                Node n = (Node)staT.next();
                n.setEffectDecFun(effectDecFun);
                res.addAll(n.checkSemantics(env));
            }
        }
        env.removeTable();
        return res;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env, Offset offset) {
        return null;
    }


    @Override
    public Node clone() {
        try{
            BlockNode cloned = (BlockNode) super.clone();
            cloned.declarations = (ArrayList<Node>) this.declarations.clone();
            cloned.statements = (ArrayList<Node>) this.statements.clone();
            return cloned;
        }
        catch(CloneNotSupportedException e){
            return null;
        }
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


    //Not used
    public int checkEffects(Environment env)
    {
        return 0;
    }


    // checking of Return statement

    public Boolean checkRet() {
        boolean hasIteRet=false;
        boolean hasElse=false;
        if (statements.size() > 0) { // if the block is  not void
            for (int i = 0; i < statements.size(); i++) {
                StatementNode temp = (StatementNode)(statements.get(i));
                if ( temp.getSt() instanceof IteNode) {
                    hasIteRet= ((IteNode)temp.getSt()).getFg();
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

    public String codeGeneration() {
        String out="";
        out += "lfp\n";                  // fp -> top_of_stack; s -> [ƒp]
        if(nestingLevel == 0) // first block
            out += "sw 0\n";            // sw al 0(al) :: al = 2 :: MEMORY[al + 0] = MEMORY[2] = 2
        out += "swsp\n";                 // sw r1 0(fp) :: sw <- MEM[0 + fp]
        out+= "lr1\n";                    // r1 -> top_of_stack:: s ->[..][r1]
        out+= "lal\n";                    // al <- r1 :: s-> [..] :: al <- MEM[0 + fp]
        out += "lal\n";                  // al -> top_of_stack; s -> [al,fp]
        out += "cfp\n";                   // fp <- sp; sp -> [fp]
        for (Node dec : declarations) {
            out +="push 0\n";               // s->[d(0)...d(n)] n in 0 .. dec.size() - 1
            out += dec.codeGeneration();   // cgen(stable, dec) :: r1 not valid here -> [ƒp]
        }
        for (Node st : statements)
            out+=st.codeGeneration();    // cgen(stable, st) :: r1 not valid here -> [ƒp]
        for(Node dec : declarations)
            out += "pop\n";             // s -> [al, fp]
        out +="sal\n";                  // al <- top_of_stack; s ->  [fp]
        out+= "sfp\n";                   // fp <- top_of_stack; s-> []

        return out; // halt is added in the Test.java
    }


}
