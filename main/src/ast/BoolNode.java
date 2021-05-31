package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class BoolNode implements Node {

  private boolean val;
  
  public BoolNode (boolean n) {
      val = n;
  }
  
  public String toPrint(String s) {
    if (val) return s+"Boolean: True";
    else return s+"Boolean: False";
  }
  
  public Node typeCheck() {
    return new BoolTypeNode();
  }    
  
  @Override
 	public ArrayList<SemanticError> checkSemantics(Environment env) {

 	  return new ArrayList<SemanticError>();
 	}
  
  public String codeGeneration() {
		return "push "+(val?1:0)+"\n";
	  }

    @Override
    public void checkEffects() {

    }

}  