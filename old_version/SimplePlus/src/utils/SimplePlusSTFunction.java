package utils;

import java.util.HashMap;
import java.util.List;

// Record per il salvataggio dei dati delle funzioni

public class SimplePlusSTFunction {
	
	private SimplePlusTypes type; 														// Tipo di return
	private List<SimplePlusSTVariable> arguments; 										// Argomenti della funzione
	private boolean evaluated; 															// True se la funzione è stata valutata durante la behavioral analysis
	private HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes> behaviorReference; 	// Lista di comportamenti da applicare alle variabili esterne allo scope in caso di call
	private String label;																// Label dalla funzione per effettuare salti e chiamate
	
	
	
	// Constructor
	
	public SimplePlusSTFunction(SimplePlusTypes type, List<SimplePlusSTVariable> arguments, boolean evaluated, HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes> behaviorReference, String label) {
		this.type = type;
		this.arguments = arguments;
		this.evaluated = evaluated;
		this.behaviorReference = behaviorReference;
		this.label = label;
	}
	
	
	
	// Setters and getters
		
	public void setReturnType(SimplePlusTypes type) {
		this.type = type;
	}
	
	public SimplePlusTypes getReturnType() {
		return this.type;
	}
	
	public void setArgument(int index, SimplePlusSTVariable argument) {
		this.arguments.set(index, argument);
	}
	
	public List<SimplePlusSTVariable> getArguments() {
		return this.arguments;
	}
	
	public void setEvaluated(boolean evaluated) {
		this.evaluated = evaluated;
	}
	
	public boolean getEvaluated() {
		return this.evaluated;
	}
	
	public void setBehaviorReference(HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes> behaviorReference) {
		this.behaviorReference = behaviorReference;
	}
	
	public HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes> getBehaviorReference() {
		return this.behaviorReference;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}

}
