package ast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import util.Environment;
import util.FixedPoint;
import util.SemanticError;
import util.SimpLanlib;

public class CallNode implements Node, Cloneable {

  private String id;
  private ArrayList<Node> exp;
  private STentry entry;
  private int effectDecFun;
  private FixedPoint fixed;
  private Environment fixedPointEnv;
  
  public CallNode(String id, ArrayList<Node> exp){
    this.id = id;
    this.exp = exp;
    fixed = new FixedPoint(0);
  }

  public CallNode(String id, ArrayList<Node> exp, STentry entry){
      this.id = id;
      this.exp = exp;
      this.entry = entry;
      fixed = new FixedPoint(0);
  }

  public CallNode(String id){
      this.id = id;
      this.exp = new ArrayList<Node>();
      fixed = new FixedPoint(0);
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
            exp += expNode.toPrint(s + "");
      }

      return first + exp + last;
    }

  public ArrayList<SemanticError> checkSemantics(Environment env) {

      ArrayList<SemanticError> res = new ArrayList<SemanticError>();
      int nestingLevel = env.getNestingLevel();
      this.entry = env.lookup(nestingLevel, this.id);
      if(entry == null){
          // decFun doesn't exist in the Environment
          res.add(new SemanticError("Function " +this.id + " not declared"));
      } else{
          for(Node e : this.exp){
              e.setEffectDecFun(this.effectDecFun); // Setting 1 of effectDecFun of exp
              if(e instanceof DerExpNode) {
                  DerExpNode derExp = (DerExpNode) e;
                  LhsNode value = (LhsNode) derExp.getDerExp();
                  value.setEffectDecFun(this.effectDecFun);
                  value.checkSemantics(env);
              }
              res.addAll(e.checkSemantics(env));
          }
          res.addAll(checkEffects(env));
      }
      return res;
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
          System.out.println("Invocation of a non-function "+id);
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
  
  public String codeGeneration() {
	    return null;
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

        if(this.effectDecFun != 0){
            // main invocation
            // do nothing
        } else {
            // internal invocation with fixed point
            function.setCallingDecFun(0); // calling DecFun is 0 because we didn't recall the internal invocation yet
            function.setPointerEffectStatesArg(pointerEffectStates); // Setting of effects from the pointer arguments
            if(this.fixed.getPoint() == 0) {
                this.fixed.setPoint(fixed.getPoint() + 1);
                function.checkSemantics(env);
            }

            function.setCallingDecFun(0);
            function.setPointerEffectStatesArg(pointerEffectStates);
            this.fixed.fixedPointFunc(env, function, this.fixed.getPoint()); // calling fixed point procedure
            this.fixed.setPoint(fixed.getPoint() + 1); // setting minimum fixed point
        }

        return res;
    }




}  