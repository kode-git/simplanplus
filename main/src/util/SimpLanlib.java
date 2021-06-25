package util;


import ast.Node;

public class SimpLanlib {
  
  private static int labCount=0; 
  
  private static int funLabCount=0; 

  private static String funCode=""; 

  //if a <: b return true, a,b :: int | bool && (int <: bool || bool <: int != true)
  public static boolean isSubtype (Node a, Node b) {
    return a.getClass().equals(b.getClass()) ; //||
    	  // ( (a instanceof BoolTypeNode) && (b instanceof IntTypeNode) ); //
  } 
  
  public static String freshLabel() { 
	return "label"+(labCount++);
  } 

  public static String freshFunLabel() { 
	return "f"+"entry"+(funLabCount++);
  } 
  
  public static void putCode(String c) { 
    funCode+="\n"+c; //insert void line before function
  }
  
  public static String getCode() { 
    return funCode;
  } 


}