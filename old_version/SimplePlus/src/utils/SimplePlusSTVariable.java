package utils;

// Record per il salvataggio dei dati delle variabili

public class SimplePlusSTVariable {
	
	private int offset;							// Offset rispetto all'inizio della memoria dedicata
	private SimplePlusTypes type; 				// Tipo
	private boolean reference; 					// True se e' un riferimento
	private SimplePlusBehaviorTypes behavior; 	// Behavior duranre la behavioral analysis
	private int argumentPosition; 				// Posizione nella lista di argomenti della funzione se e' in una decFun
	
	
	
	// Constructor
	
	public SimplePlusSTVariable(int offset, SimplePlusTypes type, boolean reference, SimplePlusBehaviorTypes behavior) {
		this.offset = offset;
		this.type = type;
		this.reference = reference;
		this.behavior = behavior;
		this.argumentPosition = -1;
	}
	
	
	
	// Setters and getters
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public int getOffset() {
		return this.offset;
	}
		
	public void setType(SimplePlusTypes type) {
		this.type = type;
	}
	
	public SimplePlusTypes getType() {
		return this.type;
	}
	
	public void setReference(boolean reference) {
		this.reference = reference;
	}
	
	public boolean getReference() {
		return this.reference;
	}
	
	public void setBehavior(SimplePlusBehaviorTypes behavior) {
		this.behavior = behavior;
	}
	
	public SimplePlusBehaviorTypes getBehavior() {
		return this.behavior;
	}
	
	public void setArgumentPosition(int argumentPosition) {
		this.argumentPosition = argumentPosition;
	}
	
	public int getArgumentPosition() {
		return this.argumentPosition;
	}

}
