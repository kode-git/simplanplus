package ast;
import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class ArrowTypeNode implements Node {

  private ArrayList<ArgNode> argList;
  private Node ret;
  
  public ArrowTypeNode (ArrayList<ArgNode> p, Node r) {
    argList=p;
    ret=r;
  }
    
  public String toPrint(String s) { //
	String parlstr="";
    for (Node par:argList)
      parlstr+=par.toPrint(s+"");
	return s+"ArrowType: \n" + parlstr + ret.toPrint(s+"->") ;
  }
  
  public Node getRet () { //
      System.out.println(ret);
      return ret;
  }
  
  public ArrayList<ArgNode> getArgList () { //
    return argList;
  }

  @Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO Auto-generated method stub
		return new ArrayList<SemanticError>();
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
  public void checkEffects() {

  }

}  