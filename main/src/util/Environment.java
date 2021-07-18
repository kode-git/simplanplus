package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ast.STentry;

public class Environment implements Cloneable {
	

	private ArrayList<HashMap<String,STentry>>  symTable = new ArrayList<HashMap<String,STentry>>();
	private int nestingLevel = -1;

	public ArrayList<HashMap<String, STentry>> getSymTable() {
		return symTable;
	}

	public void setSymTable(ArrayList<HashMap<String, STentry>> symTable) {
		this.symTable = symTable;
	}

	public SemanticError addEntry(int nestingLevelIntern, String id, STentry entry){
		HashMap<String, STentry> hm = (HashMap)symTable.get(nestingLevelIntern);
		if(hm.put(id, entry)!=null) return new SemanticError( "Environment Error: entry " + id + " already declared");
		return null;
	}

	public STentry lookup(int nestingLevelIntern,String id){
		STentry tmp;
		for(tmp = null; nestingLevelIntern >= 0 && tmp == null; tmp = (STentry)((HashMap)this.symTable.get(nestingLevelIntern--)).get(id)) {
			// lookup
		}
		if (tmp == null) {
			return null;
		} else {
			return tmp;

		}

	}

	public void addTable(HashMap<String, STentry> hm){
		this.nestingLevel++;
		this.symTable.add(hm);
	}


	public void removeTable(){
		this.symTable.remove(this.nestingLevel);
		this.nestingLevel--;
	}


	public SemanticError resetPropagation(String id, int nestingLevel){
		STentry entry = this.lookup(nestingLevel, id);
		if(entry == null){
			return new SemanticError("Environment: Delete reference on propagation failed");
		}
		int entryLvl = entry.getNestinglevel(); // when the variable is declared

		if(entry.getPointerCounter() > 1){
			// is a pointer
			for(int i = nestingLevel ; i >= entryLvl; i--){
				for(Map.Entry<String, STentry> right: this.getSymTable().get(i).entrySet() ){
					STentry rightValue = right.getValue();
					// find if id is in propagation, in case it is... delete it
					HashMap<String, Integer> propagation = rightValue.getPropagation();
					if(propagation.containsKey(id)){
						// there is an old deletion propagation
						propagation.remove(id);
					}
				}
			}
		}


		return null;
	}


	public void setNestingLevel(int nestingLevel) {
		this.nestingLevel = nestingLevel;
	}


	public int getNestingLevel(){
		return nestingLevel;
	}



	public Environment clone(){
		try{
			Environment cloned = (Environment) super.clone();
			cloned.symTable = new ArrayList<HashMap<String,STentry>>();

			for(int c=0; c<this.symTable.size();c++) {
				HashMap<String, STentry> newMap = new HashMap<String, STentry>();
				for (Map.Entry<String, STentry> entry : this.symTable.get(c).entrySet()) {
					// entry of the HashMap c
					newMap.put(entry.getKey(), (STentry) entry.getValue().clone());
				}
				cloned.symTable.add(c, newMap);
			}
			return cloned;

		}catch(CloneNotSupportedException e){

			return null; // Never happen because Environment implements Cloneable interface
		}
	}
	
	
}



