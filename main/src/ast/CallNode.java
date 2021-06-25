package ast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import util.*;

public class CallNode implements Node, Cloneable {

  private String id;
  private ArrayList<Node> exp;
  private STentry entry;
  private int effectDecFun;
  private FixedPoint fixed;
  private Environment fixedPointEnv;
  private int nestinglevel;
  
  public CallNode(String id, ArrayList<Node> exp){
    this.id = id;
    this.exp = exp;
    fixed = new FixedPoint(0);
    nestinglevel = 0;
  }

  public CallNode(String id, ArrayList<Node> exp, STentry entry){
      this.id = id;
      this.exp = exp;
      this.entry = entry;
      fixed = new FixedPoint(0);
      nestinglevel = 0;
  }

  public CallNode(String id){
      this.id = id;
      this.exp = new ArrayList<Node>();
      fixed = new FixedPoint(0);
      nestinglevel = 0;
  }

  // getter and setter


    public int getNestinglevel() {
        return nestinglevel;
    }

    public void setNestinglevel(int nestinglevel) {
        this.nestinglevel = nestinglevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Node> getExp() {
        return exp;
    }

    public void setExp(ArrayList<Node> exp) {
        this.exp = exp;
    }

    public STentry getEntry() {
        return entry;
    }

    public void setEntry(STentry entry) {
        this.entry = entry;
    }

    public int getEffectDecFun() {
        return effectDecFun;
    }

    public FixedPoint getFixed() {
        return fixed;
    }

    public void setFixed(FixedPoint fixed) {
        this.fixed = fixed;
    }

    public Environment getFixedPointEnv() {
        return fixedPointEnv;
    }

    public void setFixedPointEnv(Environment fixedPointEnv) {
        this.fixedPointEnv = fixedPointEnv;
    }

    @Override
    public void setEffectDecFun(int effectDecFun) {
        this.effectDecFun = effectDecFun;
    }


    // toPrint, checkSemantics, checkEffects, clone and internal methods

    public String toPrint(String s) {  //
      String first = s + "CallNode: " + id + " ( ";
      String last =  ")" + "";
      String exp = "";

      // if exp is void the string in return is first + last = id + "( )\n"
      for(Node expNode : this.exp){
            exp += expNode.toPrint(s + "") + " ";
      }
      String nestingLevel = " :: " + "nesting level " + this.nestinglevel;
        return first + exp + last + nestingLevel ;
    }

  public ArrayList<SemanticError> checkSemantics(Environment env) {

      ArrayList<SemanticError> res = new ArrayList<SemanticError>();
      int nestingLevel = env.getNestingLevel();
      this.nestinglevel =nestingLevel; // setting of the nesting Level of the calling invocation
      this.entry = env.lookup(nestingLevel, this.id);
      if(entry == null){
          // decFun doesn't exist in the Environment
          res.add(new SemanticError("error: Function " +this.id + " not declared"));
      } else{
          for(Node e : this.exp){
              e.setEffectDecFun(this.effectDecFun); // Setting 1 of effectDecFun of exp
              if(e instanceof DerExpNode) {
                  DerExpNode derExp = (DerExpNode) e;
                  LhsNode value = (LhsNode) derExp.getDerExp();
                  value.setEffectDecFun(this.effectDecFun);
                  res.addAll(value.checkSemantics(env));
              }
              res.addAll(e.checkSemantics(env));
          }
          if(res.size() == 0)
            res.addAll(checkEffects(env));
      }
      return res;
  }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env, Offset offset) {
        return null;
    }

    @Override
    public Node clone() {
        try{
            CallNode cloned = (CallNode) super.clone();
            if(this.entry != null)
                cloned.entry = (STentry) cloned.entry.clone();

            cloned.fixed = this.fixed;
            cloned.exp = (ArrayList<Node>) this.exp.clone();
            return cloned;
        } catch(CloneNotSupportedException e){
            return  null;
        }
    }


    public Node typeCheck () {  //

      ArrowTypeNode t=null;
      if (entry.getType() instanceof ArrowTypeNode) t=(ArrowTypeNode) entry.getType();
      else {
          System.out.println("error: Invocation of a non-function "+id);
          System.exit(0);
      }
      ArrayList<Node> p = t.getArgList();

      // Checking of number of arguments equals to the number of parameters in DecFun
      if ( !(p.size() == exp.size()) ) {
          System.out.println("Wrong number of parameters in the invocation of "+id);
          System.exit(0);
      }
      for (int i=0; i<exp.size(); i++) {
          if (!(SimpLanlib.isSubtype((exp.get(i)).typeCheck(), ((ArgNode)p.get(i)).getType()))) {
              System.out.println("Wrong type for " + (i + 1) + "-th parameter in the invocation of " + id);
              System.exit(0);
          }
      }
      return t.getRet();
  }


    public ArrayList<SemanticError> checkEffects(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        // we enter in the checkEffects only when the function is declared and is in the Environment
        ArrayList<int[]> pointerEffectStates = new ArrayList<>();
        for(Node e : this.exp){
            if(e instanceof DerExpNode) {
                DerExpNode derExp = (DerExpNode) e;
                LhsNode value = (LhsNode) derExp.getDerExp();
                if (value.getEntry().getPointerCounter() > 0) {
                    // this is a pointer
                    STentry entry = value.getEntry();
                    pointerEffectStates.add(entry.getEffectState()); // this is the effect state of LhsNode reference in DerExpNode

                } else {
                    // is not a pointer and didn't manage effects for these kinds of parameters
                }
            }
            res.addAll(e.checkSemantics(env));
        }
        DecFunNode function = entry.getReference();
        function.setParameters((ArrayList<Node>) exp.clone()); // need only for the size

        if(this.effectDecFun != 0){
            // main invocation
            // do nothing
        } else {
            // internal invocation with fixed point
            function.setCallingDecFun(0); // calling DecFun is 0 because we didn't recall the internal invocation yet
            function.setPointerEffectStatesArg(pointerEffectStates); // Setting of effects from the pointer arguments
            if(this.fixed.getPoint() == 0) {
                this.fixed.setPoint(fixed.getPoint() + 1);
                res.addAll(function.checkSemantics(env));
            }

            function.setCallingDecFun(0);
            function.setPointerEffectStatesArg(pointerEffectStates);
            res.addAll(this.fixed.fixedPointFunc(env, function, this.fixed.getPoint())); // calling fixed point procedure
            this.fixed.setPoint(fixed.getPoint() + 1); // setting minimum fixed point
        }

        return res;
    }

    // TODO: Checking Offset and Pointer case
    public String codeGeneration() {
        String parameters = "" ;
        for (int i=exp.size()-1; i>=0; i--)
            parameters += exp.get(i).codeGeneration() + "push 0\n"; // codeGen of the exp

        String getAR = "";
        for (int i=0; i < nestinglevel-entry.getNestinglevel(); i++)
            getAR += "lw\n"; // pop the value x on top of the stack and push MEMORY[x] :: AL going back

        return "lfp\n"+ 				// push $fp to save it in the stack [fp]

                parameters +            // cgen(stable, exp.get(i)) :: for i in exp.size() - 1 to 0
                "lfp\n"+                //
                getAR+ 		// setting AL going up on the static chain
                // get the jump address and put it on the stack
                "push "+ entry.getOffset()+"\n"+ // setting of the offset on the stack
                "lfp\n"+getAR+ 		// going up on the static chain on the decFun AR
                "add\n"+
                "lw\n"+ 				// update on the stack the obtained address
                "js\n";                 // doing js on the address ($ra)s
    }






}  