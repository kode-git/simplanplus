package utils;

// Conversioni e controlli sui tipi presenti in SimplePlus

public class TypeConverter {
	
	private static final String errorLabel = "ERROR: TypeConverter: ";
	
	public static SimplePlusTypes stringToType(String type) {
		switch(type) {
		case "void":
			return SimplePlusTypes.VOID;
		case "bool":
			return SimplePlusTypes.BOOL;
		case "int":
			return SimplePlusTypes.INT;
		default:
			throw new IllegalArgumentException(TypeConverter.errorLabel + "Type `" + type + "` not found");
		}
	}
	
	public static String typeToString(SimplePlusTypes type) {
		switch(type) {
		case VOID:
			return "void";
		case BOOL:
			return "bool";
		case INT:
			return "int";
		case RETURN_VOID:
			return "return void";
		case RETURN_BOOL:
			return "return bool";
		case RETURN_INT:
			return "return int";
		default:
			throw new IllegalArgumentException(TypeConverter.errorLabel + "Unable to stringify the inserted type");
		}
	}
	
	public static SimplePlusTypes typeToReturn(SimplePlusTypes type) {
		switch(type) {
		case VOID:
			return SimplePlusTypes.RETURN_VOID;
		case BOOL:
			return SimplePlusTypes.RETURN_BOOL;
		case INT:
			return SimplePlusTypes.RETURN_INT;
		case RETURN_VOID:
			return SimplePlusTypes.RETURN_VOID;
		case RETURN_BOOL:
			return SimplePlusTypes.RETURN_BOOL;
		case RETURN_INT:
			return SimplePlusTypes.RETURN_INT;
		default:
			throw new IllegalArgumentException(TypeConverter.errorLabel + "Unable to convert the inserted type");
		}
	}
	
	public static boolean isReturn(SimplePlusTypes type) {
		switch(type) {
		case VOID:
		case BOOL:
		case INT:
			return false;
		case RETURN_VOID:
		case RETURN_BOOL:
		case RETURN_INT:
			return true;
		default:
			throw new IllegalArgumentException(TypeConverter.errorLabel + "Type not found");
		}
	}

}
