package ast;

import java.util.ArrayList;

import util.Environment;
import util.Offset;
import util.SemanticError;

public class BoolTypeNode implements Node, GenericTypeNode, Cloneable {

  public BoolTypeNode () {
  }

    // toPrint, typeCheck, checkSemantics, checkEffects, codeGeneration

    public String toPrint(String s) {
	return s+"BoolType ";
  }
    

  public Node typeCheck() {
    return this;
  }
  
  @Override
 	public ArrayList<SemanticError> checkSemantics(Environment env) {

 	  return new ArrayList<SemanticError>();
 	}

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env, Offset offset) {
        return null;
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

    // not used
    public int checkEffects(Environment env) {
        return 0;
    }

    @Override
    public void setEffectDecFun(int effectDecFun) {
        // not used
    }


}  