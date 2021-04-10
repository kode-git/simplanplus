package utils;

// Gestione delle operazioni che si possono effettuare durante la behavioral analysis

public class BehaviorTypeOperation {
	
	private static final String errorLabel = "ERROR: BehaviorTypeOperation: ";
	
	public static SimplePlusBehaviorTypes max(SimplePlusBehaviorTypes a, SimplePlusBehaviorTypes b) {
		if(a.getValue() >= b.getValue()) {
			return a;
		} else {
			return b;
		}
	}
	
	public static SimplePlusBehaviorTypes min(SimplePlusBehaviorTypes a, SimplePlusBehaviorTypes b) {
		if(a.getValue() < b.getValue()) {
			return a;
		} else {
			return b;
		}
	}
	
	public static SimplePlusBehaviorTypes seq(SimplePlusBehaviorTypes a, SimplePlusBehaviorTypes b) {
		if(BehaviorTypeOperation.max(a, b).getValue() <= SimplePlusBehaviorTypes.RDWR.getValue()) {
			return BehaviorTypeOperation.max(a, b);
		} else if((a.getValue() <= SimplePlusBehaviorTypes.RDWR.getValue() && b == SimplePlusBehaviorTypes.DELETE) || (a == SimplePlusBehaviorTypes.DELETE && b == SimplePlusBehaviorTypes.UNDEFINED)) {
			return SimplePlusBehaviorTypes.DELETE;
		} else {
			return SimplePlusBehaviorTypes.ERROR;
		}
	}
	
	public static SimplePlusBehaviorTypes par(SimplePlusBehaviorTypes a, SimplePlusBehaviorTypes b) {
		if(b == SimplePlusBehaviorTypes.UNDEFINED) {
			return a;
		} else if(a == SimplePlusBehaviorTypes.UNDEFINED) {
			return b;
		} else if(a == SimplePlusBehaviorTypes.RDWR && b == SimplePlusBehaviorTypes.RDWR) {
			return SimplePlusBehaviorTypes.RDWR;
		} else {
			return SimplePlusBehaviorTypes.ERROR;
		}
	}
	
	public static String typeToString(SimplePlusBehaviorTypes type) {
		switch(type) {
		case UNDEFINED:
			return "UNDEFINED";
		case RDWR:
			return "READ-WRITE";
		case DELETE:
			return "DELETE";
		case ERROR:
			return "ERROR";
		default:
			throw new IllegalArgumentException(BehaviorTypeOperation.errorLabel + "Unable to stringify the inserted type");
		}
	}

}
