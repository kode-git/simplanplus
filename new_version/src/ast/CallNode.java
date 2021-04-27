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
      String last =  ")" + "";
      String exp = "";

      // if exp is void the string in return is first + last = id + "( )\n"
      for(Node expNode : this.exp){
            exp += expNode.toPrint(s + "");
      }

      return first + exp + last;
  }

  public ArrayList<SemanticError> checkSemantics(Environment env) {

      ArrayList<SemanticError> res = new ArrayList<SemanticError>();
      int nestingLevel = env.getNestingLevel();
      STentry entry = env.checkId(nestingLevel, this.id);
      if(entry == null){
          res.add(new SemanticError("Id " +this.id + " not declared"));
      } else{
          for(Node e : this.exp){
              res.addAll(e.checkSemantics(env));
          }
      }

      return res;

  }
  
  public Node typeCheck () {  //                           
	 return null;
  }
  
  public String codeGeneration() {
	    return null;
  }

    
}  