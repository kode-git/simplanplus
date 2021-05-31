package ast;

import java.util.ArrayList;

import util.Environment;
import util.*;
//import util.SemanticError;

public interface Node {
   
  String toPrint(String indent);

  Node typeCheck();
  
  String codeGeneration();

  void checkEffects();
  
  ArrayList<SemanticError> checkSemantics(Environment env);
  
}  