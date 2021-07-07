package ast;

import java.util.ArrayList;

import util.Environment;
import util.Offset;
import util.SemanticError;
import util.VoidNode;

public class PrintNode implements Node, Cloneable {

  private Node val;
  private int effectDecFun;
  
  public PrintNode (Node v) {
    val=v;
  }


  // getter and setter

  public Node getVal() {
    return val;
  }

  public void setVal(Node val) {
    this.val = val;
  }

  public int getEffectDecFun() {
    return effectDecFun;
  }

  @Override
  public void setEffectDecFun(int effectDecFun) {
    this.effectDecFun = effectDecFun;
  }


  // toPrint, TypeCheck, checkSemantics, checkEffects, codeGeneration

  public String toPrint(String s) {
    return s+"Print: " + val.toPrint(s+"") ;
  }

  public Node typeCheck() {
    val.typeCheck();
    return new VoidNode();
  }

  @Override
 	public ArrayList<SemanticError> checkSemantics(Environment env) {
      ArrayList<SemanticError> res = new ArrayList<SemanticError>();
      val.setEffectDecFun(this.effectDecFun);
      res.addAll(val.checkSemantics(env));
 	  return res;
 	}

  @Override
  public ArrayList<SemanticError> checkSemantics(Environment env, Offset offset) {
    return null;
  }

  @Override
  public Node clone() {
    try{
      PrintNode cloned = (PrintNode) super.clone();
      cloned.val = (Node) this.val.clone();
      return cloned;
    } catch(CloneNotSupportedException e){
      return null;
    }
  }

  // not used
  public int checkEffects(Environment env) {
    return 0;
  }

  public String codeGeneration() {

    if(val instanceof DerExpNode) {
      int counterST = ((LhsNode)((DerExpNode)val).getDerExp()).getCounterST();
      int counter = ((LhsNode)((DerExpNode)val).getDerExp()).getCounter();
      if ((counterST > 0 && counterST - counter > 0)) {
        return val.codeGeneration()   // r1 <- cgen(stable, val); s -> []
                + "printhp\n";           // print r1; s -> []
      } else {
        return val.codeGeneration()   // r1 <- cgen(stable, val); s -> []
                + "print\n";           // print r1; s -> []
      }
    }
    return val.codeGeneration()   // r1 <- cgen(stable, val); s -> []
            + "print\n";           // print r1; s -> []
  }
}  