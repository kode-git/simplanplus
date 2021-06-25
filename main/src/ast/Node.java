package ast;

import java.util.ArrayList;

import util.Environment;
import util.*;
//import util.SemanticError;

public interface Node extends Cloneable {
   
  String toPrint(String indent);

  Node typeCheck();

  String codeGeneration();

  void setEffectDecFun(int effectDecFun);

  ArrayList<SemanticError> checkSemantics(Environment env);

  ArrayList<SemanticError> checkSemantics(Environment env,Offset offset);

  Node clone();
}  