package utils;

import java.util.HashMap;

public class BytecodeLabelTable {
	
	public int labelId;										// Id della nuova label utilizzabile
	public HashMap<String, Integer> labelToInstruction;		// Conversione tra label e istruzione
	public HashMap<Integer, String> idToLabel;				// Conversione tra id e label testuale
	
	
	
	// Constructor
	
	public BytecodeLabelTable() {
		this.labelId = 0;
		this.labelToInstruction = new HashMap<String, Integer>();
		this.idToLabel = new HashMap<Integer, String>();
	}
	
	
	
	// Restituisce la prossima label disponibile e incrementa il contatore
	
	public String newLabel() {
		String labelNew = "LABEL" + this.labelId;
		this.labelId = this.labelId + 1;
		return labelNew;
	}
	
	// Restituisce il prossimo labelId disponibile e incrementa il contatore
	
	public int newLabelId() {
		int labelIdNew = this.labelId;
		this.labelId = this.labelId + 1;
		return labelIdNew;
	}
	
	
	
	// Memorizza una nuova label del bytecode con l'istruzione dove si trova
	
	public void storeLabelToInstruction(String label, int instruction) {
		this.labelToInstruction.put(label,  instruction);
	}
	
	// Restituisce l'istruzione dove si trova una label del bytecode
	
	public int getLabelToInstruction(String label) {
		return this.labelToInstruction.get(label);
	}
	
	// Memorizza un nuovo id con la label testuale a cui fa riferimento
	
	public void storeIdToLabel(int id, String label) {
		this.idToLabel.put(id, label);
	}
	
	// Restituisce il nome testuale usando l'id di riferimento
	
	public String getIdToLabel(int id) {
		return this.idToLabel.get(id);
	}

}
