package ast;

import util.*;

import java.util.ArrayList;
import java.util.HashMap;

public class DecFunNode implements Node, Cloneable {

    private Node type;
    private String id;
    private ArrayList<Node> args;
    private BlockNode block;
    private int effectDecFun;
    private ArrayList<int[]> pointerEffectStatesArg;
    private Offset offset;
    private Offset clonedOffset;
    private ArrayList<Node> parameters = new ArrayList<Node>();


    // type id (args) {}
    public DecFunNode(Node type, String id, ArrayList<Node> args, BlockNode block) {
        this.type = type;
        this.id = id;
        this.args = args;
        this. block = block;
        this.effectDecFun = 1;
        this.pointerEffectStatesArg = new ArrayList<int[]>();
    }

    // void id (args) {}
    public DecFunNode(String id,  ArrayList<Node> args, BlockNode block){
        this.type = new VoidNode();
        this.id = id;
        this.args = args;
        this.block = block;
        this.effectDecFun = 1;
        this.pointerEffectStatesArg = new ArrayList<int[]>();
    }

    // void id ( ) {}
    public DecFunNode(String id, BlockNode block){
        this.type = new VoidNode();
        this.id = id;
        this.args = new ArrayList<Node>();
        this.block = block;
        this.effectDecFun = 1;
        this.pointerEffectStatesArg = new ArrayList<int[]>();
    }

    //  type id ( ) {}
    public DecFunNode(Node type, String id, BlockNode block){
        this.type = type;
        this.id = id;
        this.args = new ArrayList<Node>();
        this.block = block;
        this.effectDecFun = 1;
        this.pointerEffectStatesArg = new ArrayList<int[]>();
    }

    public Node getType() {
        return type;
    }

    public void setType(Node type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Node> getArgs() {
        return args;
    }

    public void setArgs(ArrayList<Node> args) {
        this.args = args;
    }

    public BlockNode getBlock() {
        return block;
    }

    public void setBlock(BlockNode block) {
        this.block = block;
    }

    public int getEffectDecFun() {
        return effectDecFun;
    }

    public ArrayList<int[]> getPointerEffectStatesArg() {
        return pointerEffectStatesArg;
    }

    public void setPointerEffectStatesArg(ArrayList<int[]> pointerEffectStatesArg) {
        this.pointerEffectStatesArg = pointerEffectStatesArg;
    }

    @Override
    public void setEffectDecFun(int effectDecFun) {
        // not used
    }

    public void setCallingDecFun(int effectDecFun){
        this.effectDecFun = effectDecFun;
    }

    public int getCallingDecFun(){ return this.effectDecFun;}

    // toPrint, typeCheck, checkSemantics, checkEffects, codeGeneration

    @Override
    public String toPrint(String s) {
        if(!(this.type instanceof VoidNode)){
            // type is not void
            if(args.size() == 0){
                // no args
                return s + "\nDecFun:\n\t " + type.toPrint(s + "") + id + "( )" + block.toPrint(s + "") + "\n";
            } else {
                // some args
                String first =  s + "\nDecFun: " + type.toPrint(s + "") + id + "(";
                String last = ")" + block.toPrint(s + "") + "\n";
                String argsPrint = this.args.get(0).toPrint(s + "");
                for(int i = 1; i < this.args.size(); i++){
                    argsPrint += "," + this.args.get(i).toPrint(s + "");
                }
                return first + argsPrint + last;
            }
        } else {
            // type is void
            if (args.size() == 0){
                // no args
                return s + "\nDecFun: " + "void " + id + "( )" + block.toPrint(s + "") + "\n";
            } else {
                // some args
                String first =  s + "\nDecFun: " + "void " + id + "(";
                String last = ")" + block.toPrint(s + " ") + "\n";
                String argsPrint = this.args.get(0).toPrint(s + "");
                for(int i = 1; i < this.args.size(); i++){
                    argsPrint += "," + this.args.get(i).toPrint(s + "");
                }
                return first + argsPrint + last;
            }
        }

    }

    // TODO BugFix
    @Override
    public Node typeCheck() {

        // case: void with return in function
        if(this.type instanceof VoidNode && this.block.checkRet()) {
            // return error - case of return in void func
            System.out.println("error: Function " + id + " is void and can't have return statement");
            System.exit(0);
        }

        // here if type is not void, need to check if return statement is present
        else if(!this.block.checkRet() && !(this.type instanceof VoidNode)){
            // no return statement in type != void
            System.out.println("error: Function " + id + " don't have return statement");
            System.exit(0);
        }


        // here type != void and there is return as block.typeCheck()
        if (!(SimpLanlib.isSubtype(block.typeCheck(), type))) {
            System.out.println("error: Wrong return type for function " + id);
            System.exit(0);
        }

        return block.typeCheck();
    }


    // not used
    public int checkEffects(Environment env) {
        return 0;
    }

    //not used
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        ArrayList<SemanticError> res = new ArrayList();
        STentry entry = new STentry(env.getNestingLevel(), clonedOffset.getOffset() );

        // define return type :: (void, GenericType :: (int, bool))
        if(!(type instanceof GenericTypeNode)){
            // type :: void
            entry.setReference(this);
            entry.addType(new ArrowTypeNode(args, new VoidNode()));
        }
        else {
            // type :: GenericType
            entry.setReference(this);
            entry.addType(new ArrowTypeNode(args, type));
        }
        clonedOffset.increment();
        SemanticError err;
        if(effectDecFun == 1)
            // adding of the function declaration inside the table at the current nesting level
            err = env.addEntry(env.getNestingLevel(), this.id, entry);
        else
            err = null;
        if (err != null) {
            res.add(err);
        } else {
            // making new scope :-> \Gamma - []
            env.addTable(new HashMap<String, STentry>());
            int i = 0;
            Offset argOffset = new Offset();
            argOffset.increment();
            try {
                for (Node arg : this.args) {
                    ArgNode argNode = (ArgNode) arg;
                    if (effectDecFun != 1) {
                        if (argNode.getCounter() > 0) {
                            // this is a pointer arguments, we need to take the effect state of the var
                            int[] argEffectState = this.getPointerEffectStatesArg().get(i);
                            i = i + 1;
                            argNode.setPointerEffectStateArg(argEffectState);
                        }
                    }
                    res.addAll(argNode.checkSemantics(env,argOffset)); // adding in table inside the args checkSemantics
                }
            } catch(IndexOutOfBoundsException e){
                // the code goes to IndexOutOfBoundException when the counterST = 0 (normal integer/boolean)
                // for callNode parameter and counterST != 0 for the arg reference in DecFun (pointer type)
                res.add(new SemanticError("error: Wrong reference for the pointer argument in the function " + id));
                return res;
            }
            // this is because in BlockNode checkSemantics we have NestingLevel + 1 and we need to going back to the previous Hashmap
            // the environment NestingLevel + 1 is the environment where there are arguments of the functions
            // the current one NestingLevel - 1 is the environment where there is the declaration of the function
            env.setNestingLevel(env.getNestingLevel() - 1);
            this.block.setEffectDecFun(this.effectDecFun);
            res.addAll(this.block.checkSemantics(env));
        }

        return res;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env, Offset offset) {
        ArrayList<SemanticError> res = new ArrayList();
        STentry entry = new STentry(env.getNestingLevel(), offset.getOffset() );
        this.offset = offset;
        this.clonedOffset = offset.clone();
        // define return type :: (void, GenericType :: (int, bool))
        if(!(type instanceof GenericTypeNode)){
            // type :: void
            entry.setReference(this);
            entry.addType(new ArrowTypeNode(args, new VoidNode()));
        }
        else {
            // type :: GenericType
            entry.setReference(this);
            entry.addType(new ArrowTypeNode(args, type));
        }
        offset.increment();
        SemanticError err;
        if(effectDecFun == 1)
            // adding of the function declaration inside the table at the current nesting level
            err = env.addEntry(env.getNestingLevel(), this.id, entry);
        else
            err = null;
        if (err != null) {
            res.add(err);
        } else {
            // making new scope :-> \Gamma - []
            env.addTable(new HashMap<String, STentry>());
            int i = 0;
            Offset argOffset = new Offset();
            argOffset.increment();
            try {
                for (Node arg : this.args) {
                    ArgNode argNode = (ArgNode) arg;
                    if (effectDecFun != 1) {
                        if (argNode.getCounter() > 0) {
                            // this is a pointer arguments, we need to take the effect state of the var
                            int[] argEffectState = this.getPointerEffectStatesArg().get(i);
                            i = i + 1;
                            argNode.setPointerEffectStateArg(argEffectState);
                        }
                    }
                    res.addAll(argNode.checkSemantics(env,argOffset)); // adding in table inside the args checkSemantics
                }
            } catch(IndexOutOfBoundsException e){
                // the code goes to IndexOutOfBoundException when the counterST = 0 (normal integer/boolean)
                // for callNode parameter and counterST != 0 for the arg reference in DecFun (pointer type)
                res.add(new SemanticError("error: Wrong reference for the pointer argument in the function " + id));
                return res;
            }
            // this is because in BlockNode checkSemantics we have NestingLevel + 1 and we need to going back to the previous Hashmap
            // the environment NestingLevel + 1 is the environment where there are arguments of the functions
            // the current one NestingLevel - 1 is the environment where there is the declaration of the function
            env.setNestingLevel(env.getNestingLevel() - 1);
            this.block.setEffectDecFun(this.effectDecFun);
            res.addAll(this.block.checkSemantics(env));
        }

        return res;
    }

    @Override
    public Node clone() {
        try{
            DecFunNode cloned = (DecFunNode) super.clone();
            cloned.type = (Node) this.type.clone();
            cloned.block = (BlockNode) this.block.clone();
            cloned.pointerEffectStatesArg = (ArrayList<int[]>) this.pointerEffectStatesArg.clone();
            cloned.args = (ArrayList<Node>) this.args.clone();
            cloned.pointerEffectStatesArg = (ArrayList<int[]>) this.pointerEffectStatesArg.clone();
            cloned.offset = this.offset.clone();
            cloned.clonedOffset = this.clonedOffset.clone();
            return cloned;
        } catch(CloneNotSupportedException e){
            return null;
        }
    }

    /*
    cgen(stable,f(e1,...,en)) =
                push $fp
                cgen(stable,en)
                push $a0 ...
                cgen(stable,e1)
                push $a0
                jal f_entry
     */
    @Override
    public String codeGeneration() {
        String argCode="";
        if (args!=null) for (Node arg : args) // cgen(stable, e(i)) :: i in 1.. num(args)
            argCode += arg.codeGeneration();

        String popArg="";
        if (args!=null)
            for (Node arg : args) popArg += "pop\n"; // pop on the stack for num(args) times

        String popPar="";
        // the parameters are pushed in the stack in the callNode.codeGeneration()
        for (Node par : parameters) popPar += "pop\n"; // pop on the stack for num(par) times

        String funl= SimpLanlib.freshFunLabel(); // this is the label called by the jar of the callNode.codeGeneration()
        SimpLanlib.putCode(funl+":\n"+
                "cfp\n"+ 		// setting $fp to $sp
                "lra\n"+ 		// insert $ra :: return address
                argCode+ 		// insert local variables (not pointers)
                block.codeGeneration()+ // cgen(stable, block)
                "srv\n"+ 		// pop $rv :: return value
                popArg+         // pop args
                "sra\n"+ 		// pop $ra :: return address
                "pop\n"+ 		// pop $al :: access link
                popPar+         // pop parameters
                "sfp\n"+  		// setting $fp to the value of the control link
                "lrv\n"+ 		// result of the value in the stack
                "lra\n"+"js\n"  // jump to $ra
        );

        return "push "+ funl +"\n"; // codeGen return string
    }

}

