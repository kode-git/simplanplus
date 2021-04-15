package ast;
import java.util.ArrayList;

import util.Environment;
import util.SemanticError;
import util.SimpLanlib;

public class CallNode implements Node {

  private String id;
  private ArrayList<Node> exp;

  
  public CallNode(String id, ArrayList<Node> exp){
    this.id = id;
    this.exp = exp;
  }

  public CallNode(String id){
      this.id = id;
      this.exp = new ArrayList<Node>();
  }


public String toPrint(String s) {  //
      String first = s + "CallNode: " + id + " ( ";
      String last =  ")" + "\n";
      String exp = "";

      // if exp is void the string in return is first + last = id + "( )\n"
      for(Node expNode : this.exp){
            exp += expNode.toPrint(s + " ");
      }

      return first + exp + last;
  }

  public ArrayList<SemanticError> checkSemantics(Environment env) {
		return null;
  }
  
  public Node typeCheck () {  //                           
	 return null;
  }
  
  public String codeGeneration() {
	    return null;
  }

    
}  