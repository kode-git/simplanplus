package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class PrintNode implements Node {

  private Node val;
  
  public PrintNode (Node v) {
    val=v;
  }
  
  public String toPrint(String s) {
    return s+"Print: " + val.toPrint(s+"") ;
  }
  
  public Node typeCheck() {
    return val.typeCheck();
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
    
}  