package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class IntTypeNode implements Node, GenericTypeNode {
  
  public IntTypeNode () {
  }
  
  public String toPrint(String s) {
	return s+"IntType ";
  }
  

  public Node typeCheck() {
    return null;
  }


  public String codeGeneration() {
		return "";
  }

    @Override
    public void checkEffects() {

    }

    @Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

	  return new ArrayList<SemanticError>();
	}
  
}  