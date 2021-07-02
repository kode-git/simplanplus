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
  private String f_entry;
  
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
            FixedPoint.functionsFp.put(id,0);

            // inner invocation
            // do nothing
        } else {
            // main invocation with fixed point
            function.setCallingDecFun(0); // calling DecFun is 0 because we didn't recall the internal invocation yet
            function.setPointerEffectStatesArg(pointerEffectStates); // Setting of effects from the pointer arguments
            if( !FixedPoint.functionsFp.containsKey(id)) {
                 res.addAll(function.checkSemantics(env));
            }else {
                this.fixed.setPoint(this.fixed.getPoint() + 1);
                function.setPointerEffectStatesArg(pointerEffectStates);
                res.addAll(this.fixed.fixedPointFunc(env, function, this.fixed.getPoint())); // calling fixed point procedure
                this.fixed.setPoint(this.fixed.getPoint() + 1); // setting minimum fixed point
            }
        }

        return res;
    }

    // TODO: Checking Offset and Pointer case
    public String codeGeneration() {
        String parameters = "" ;
        for (int i=0; i< exp.size(); i++)
            parameters += exp.get(i).codeGeneration() // r1 <- cgen(stable, e(i)) i in 1, exp.size() - 1; s -> s[e(i)]
                       + "lr1\n" ;      // r1 -> top_of_stack; s -> [e(i), .., e(0), fp]

        String ar = "";
        for(int i = 0; i < this.nestinglevel - entry.getNestinglevel(); i++ ){
            ar += "lw 0\n";     // lw al 0(al) :: al = MEMORY[al + 0]
        }

        if(this.f_entry == null){
            System.out.println("f_entry set to the reference of the DecFun");
            this.f_entry = this.entry.getReference().getfEntry();
        }

        return "lfp\n"+ 				// push $fp to save it in the stack [fp]
                "lfp\n" +                        // fp -> top_of_stack :: s -> [fp]
                "sal\n" +                        // al <- top_of_stack :: al <- fp; s -> [fp]
                ar     +                        // lw al 0(al) :: al = MEMORY[al + 0] to check the AR; s -> []
              //  "lw1 "+ entry.getOffset()+"\n"+  // lw r1 entry.offset(al) :: r1 <- MEMORY[entry.offset + al]; s -> []
               // "lr1\n"+ //inserisco activation link in stack
                "lal\n"+ //load the access link into the top of the stack; al -> too_of_stack
                parameters +            // cgen(stable, exp.get(i)) :: for i in exp.size() - 1 to 0; s-> [e(n), .., e(0), fp]
                "mfp " + exp.size() + "\n" +
                "cra\n"  +              // ra <- ip + 3 //
                "b " + f_entry + "\n";  // doing js on the address ra; ip <- ra; s -> [e(n), .., e(0), fp]
    }






}  