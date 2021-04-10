package utils;

// Tipi di behavior

public enum SimplePlusBehaviorTypes {
	
	UNDEFINED(0), 	// Identificatore dichiarato
	RDWR(1), 		// Identificatore soggetto a lettura o scrittura
	DELETE(2), 		// Identificatore cancellato
	ERROR(4); 		// Stato di errore
	
	private int value;
	
	SimplePlusBehaviorTypes(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}

}
