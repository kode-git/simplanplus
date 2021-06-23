package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class BoolTypeNode implements Node, GenericTypeNode, Cloneable {

  public BoolTypeNode () {
  }

    // toPrint, typeCheck, checkSemantics, checkEffects, codeGeneration

    public String toPrint(String s) {
	return s+"BoolType ";
  }
    
  //not used
  public Node typeCheck() {
    return null;
  }
  
  @Override
 	public ArrayList<SemanticError> checkSemantics(Environment env) {

 	  return new ArrayList<SemanticError>();
 	}

    @Override
    public Node clone() {
        try{
            BoolTypeNode cloned = (BoolTypeNode) super.clone();
            return cloned;
        } catch(CloneNotSupportedException e){
            return null;
        }
    }

    //not used
  public String codeGeneration() {
		return "";
  }


    public int checkEffects(Environment env) {
        return 0;
    }

    @Override
    public void setEffectDecFun(int effectDecFun) {
        // not used
    }


}  