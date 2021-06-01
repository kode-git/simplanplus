package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;
import util.VoidNode;

public class PrintNode implements Node {

  private Node val;
  
  public PrintNode (Node v) {
    val=v;
  }
  
  public String toPrint(String s) {
    return s+"Print: " + val.toPrint(s+"") ;
  }
  
  public Node typeCheck() {
    return new VoidNode();
  }  
  
  @Override
 	public ArrayList<SemanticError> checkSemantics(Environment env) {

      ArrayList<SemanticError> res = new ArrayList<SemanticError>();
      res.addAll(val.checkSemantics(env));
 	  return res;
 	}
  
  public String codeGeneration() {
		return val.codeGeneration()+"print\n";
  }

    @Override
    public int checkEffects(Environment env) {
        return 0;
    }

}  