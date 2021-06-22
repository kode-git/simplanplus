package ast;
import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class ArrowTypeNode implements Node, Cloneable {

  private ArrayList<Node> argList;
  private Node ret;
  
  public ArrowTypeNode (ArrayList<Node> p, Node r) {
    argList=p;
    ret=r;
  }

  // getter and setter

  public void setArgList(ArrayList<Node> argList) {
    this.argList = argList;
  }

  public void setRet(Node ret) {
    this.ret = ret;
  }

  @Override
  public void setEffectDecFun(int effectDecFun) {}

  public Node getRet () {
    return ret;
  }

  public ArrayList<Node> getArgList () { //
    return argList;
  }

  // toPrint, typeCheck, checkSemantics, checkEffects, codeGeneration

  public String toPrint(String s) {
	String parlstr="";
    for (Node par : argList)
      parlstr += par.toPrint(s+"");
	return s+"ArrowType: \n" + parlstr + ret.toPrint(s+"->") ;
  }
  

  @Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
        // leaf node for the return res
		return new ArrayList<SemanticError>();
	}

  @Override
  public Node clone() {
    try{
      ArrowTypeNode cloned = (ArrowTypeNode) super.clone();
      cloned.ret = (Node) this.ret.clone();
      cloned.argList = (ArrayList<Node>) this.argList.clone();

      return cloned;
    } catch(CloneNotSupportedException e){
      return null;
    }
  }

  //not used
  public Node typeCheck () {
    return null;
  }

  //not used
  public String codeGeneration() {
		return "";
  }

  @Override
  public int checkEffects(Environment env) {
    return 0;
  }


}  