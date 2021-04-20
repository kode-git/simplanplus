package util;

import java.util.ArrayList;
import java.util.HashMap;
import ast.STentry;

public class Environment {
	

	
	private ArrayList<HashMap<String,STentry>>  symTable = new ArrayList<HashMap<String,STentry>>();
	private int nestingLevel = -1;
	private int offset = 0;

	public SemanticError newVarNode(int nestingLevelIntern,String id, STentry entry){
		HashMap<String, STentry> hm = (HashMap)symTable.get(nestingLevelIntern);
		if(hm.put(id, entry)!=null) return new SemanticError("Var id " + id + " already declared");
		return null;
	}

	public STentry checkId(int nestingLevelIntern,String id){
		int j = nestingLevelIntern;
		STentry tmp;
		for(tmp = null; nestingLevelIntern >= 0 && tmp == null; tmp = (STentry)((HashMap)this.symTable.get(nestingLevelIntern--)).get(id)) {
		}

		if (tmp == null) {
			return null;//TODO making SemanticError when call this method
		} else {
			return tmp;

		}

	}

	public void addTable(HashMap<String, STentry> hm){
		this.symTable.add(hm);
	}


	public void removeTable(){
		this.symTable.remove(symTable.size()-1);
		this.nestingLevel--;
	}



	public void setNestingLevel(int nestingLevel) {
		this.nestingLevel = nestingLevel;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}



	public int getNestingLevel(){
		return nestingLevel;
	}


	//livello ambiente con dichiarazioni piu' esterno � 0 (prima posizione ArrayList) invece che 1 (slides)
	//il "fronte" della lista di tabelle � symTable.get(nestingLevel)
	
	
	
}
