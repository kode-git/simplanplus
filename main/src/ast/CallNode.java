package ast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import util.Environment;
import util.SemanticError;
import util.SimpLanlib;

public class CallNode implements Node, Cloneable {

  private String id;
  private ArrayList<Node> exp;
  private STentry entry;
  private int effectDecFun;
  private int counterRecursiveEffect=0;
  private Environment fixedPointEnv;
  
  public CallNode(String id, ArrayList<Node> exp){
    this.id = id;
    this.exp = exp;
  }

  public CallNode(String id, ArrayList<Node> exp, STentry entry){
      this.id = id;
      this.exp = exp;
      this.entry = entry;
  }

  public CallNode(String id){
      this.id = id;
      this.exp = new ArrayList<Node>();
  }

    @Override
    public void setEffectDecFun(int effectDecFun) {
        this.effectDecFun = effectDecFun;
    }


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

    // TODO checking of the Fixed Point bug dividing checkSemantics and checkEffects
  public ArrayList<SemanticError> checkSemantics(Environment env) {

      ArrayList<SemanticError> res = new ArrayList<SemanticError>();
      int nestingLevel = env.getNestingLevel();
      this.entry = env.lookup(nestingLevel, this.id);
      if(entry == null){
          // decFun doesn't exist in the Environment
          res.add(new SemanticError("Id " +this.id + " not declared"));
      } else{
          // decFun exists in the Environment
          ArrayList<int[]> pointerEffectStates = new ArrayList<>();
          for(Node e : this.exp){
              e.setEffectDecFun(this.effectDecFun); // Setting 1 of effectDecFun of exp
              if(e instanceof DerExpNode) {
                  DerExpNode derExp = (DerExpNode) e;
                  LhsNode value = (LhsNode) derExp.getDerExp();
                  value.setEffectDecFun(this.effectDecFun);
                  value.checkSemantics(env);
                  if (value.getEntry().getPointerCounter() > 0) {
                      // this is a pointer
                      STentry entry = value.getEntry();
                      System.out.println("Entry value: " + entry);
                      pointerEffectStates.add(entry.getEffectState()); // this is the effect state of LhsNode reference in DerExpNode

                  } else {
                      // is not a pointer

                  }
              }

                  res.addAll(e.checkSemantics(env));

          }
          DecFunNode referenceDec = entry.getReference();

          if(this.effectDecFun != 0){
            // main invocation
              System.out.println("main recursive CallNode");

          } else {
              // internal invocation


              referenceDec.setCallingDecFun(0);
              referenceDec.setPointerEffectStatesArg(pointerEffectStates);
              if(this.counterRecursiveEffect == 0) {
                  System.out.println("internal invocation CallNode " + counterRecursiveEffect);
                  this.counterRecursiveEffect++;
                  referenceDec.checkSemantics(env);
              }

              /*

              int f;
              referenceDec.setCallingDecFun(0);
              referenceDec.setPointerEffectStatesArg(pointerEffectStates);
              int checkCounter=0;
              do {
                  checkCounter++;
                  f=0;
                  fixedPointEnv = env.clone();
                  ArrayList<HashMap<String,STentry>>  symTableFixed = fixedPointEnv.getSymTable();
                  ArrayList<HashMap<String,STentry>>  symTableFinal = env.getSymTable();
                  if(this.counterRecursiveEffect == 0) {

                      System.out.println("internal invocation CallNode");

                      //fixed point computation


                      //first iteration of the fixed point on effects
                      referenceDec.checkSemantics(env);

                      for(int c=0; c<symTableFixed.size();c++){
                          for (Map.Entry<String, STentry> entry : symTableFinal.get(c).entrySet()) {
                              String key = entry.getKey();
                              int[] value = entry.getValue().getEffectState();
                              int[] value2 = symTableFixed.get(c).get(key).getEffectState();//retrieve of the corresponding value in the second SymTable
                              for(int i=0;i< value.length;i++){
                                  if(value[i]!=value2[i]){
                                      f=1; //there are some differences, needs a new iteration
                                  }

                              }
                          }
                      }

                  }

              }while (f==1);

              this.counterRecursiveEffect++; */
          }
      }

      return res;

  }

    @Override
    public Node clone() {
        try{
            CallNode cloned = (CallNode) super.clone();
            if(this.entry != null)
                cloned.entry = (STentry) cloned.entry.clone();
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

    @Override
    public int checkEffects(Environment env) {
        return 0;
    }




}  