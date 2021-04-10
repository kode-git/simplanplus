package utils;

// Gestione delgi errori che vengono lasciati in fase di compilazione

public class SimplePlusError {
	
	private static final String errorLabel = "ERROR: ";
	
	private String error;
	
	
	
	// Constructor
	
	public SimplePlusError(SimplePlusErrorTypes type) {
		switch(type) {
		case VAR_NOT_VAR:
			this.error = SimplePlusError.errorLabel + "Not `varExp` found in `call` in `arg` with `ref`";
			break;
		default:
			this.error = "INVALID ERROR TYPE";
			break;
		}
	}
	
	public SimplePlusError(SimplePlusErrorTypes type, String id) {
		switch(type) {
		case VAR_FOUND:
			this.error = SimplePlusError.errorLabel + "Variable `" + id + "` already declared in this scope";
			break;
		case VAR_NOT_FOUND:
			this.error = SimplePlusError.errorLabel + "Variable `" + id + "` not declared";
			break;
		case FUN_FOUND:
			this.error = SimplePlusError.errorLabel + "Function `" + id + "` already declared in this scope";
			break;
		case FUN_NOT_FOUND:
			this.error = SimplePlusError.errorLabel + "Function `" + id + "` no declared";
			break;
		case FUN_FEW_ARGS:
			this.error = SimplePlusError.errorLabel + "Function `" + id + "` too few arguments in function call";
			break;
		case FUN_MANY_ARGS:
			this.error = SimplePlusError.errorLabel + "Function `" + id + "` too many arguments in function call";
			break;
		case TYPE_VAR_VOID:
			throw new IllegalArgumentException(SimplePlusError.errorLabel + "Variable `" + id + "` declared with type `void`");
		case BEHAVIOR_VAR_FOUND:
			throw new IllegalArgumentException(SimplePlusError.errorLabel + "Variable `" + id + "` already declared in this scope");
		default:
			this.error = "INVALID ERROR TYPE";
			break;
		}
	}
	
	public SimplePlusError(SimplePlusErrorTypes type, SimplePlusTypes type1, SimplePlusTypes type2) {
		switch(type) {
		case TYPE_MISMATCH:
			throw new IllegalArgumentException(SimplePlusError.errorLabel + "Expected type `" + TypeConverter.typeToString(type1) + "` instead of `" + TypeConverter.typeToString(type2) + "`");
		case TYPE_MISMATCH_ITE:
			throw new IllegalArgumentException(SimplePlusError.errorLabel + "Then branch type is `" + TypeConverter.typeToString(type1) + "`, else branch type is `" + TypeConverter.typeToString(type2) + "`");
		default:
			throw new IllegalArgumentException("INVALID ERROR TYPE");
		}
	}
	
	public SimplePlusError(SimplePlusErrorTypes type, SimplePlusBehaviorTypes behavior1, SimplePlusBehaviorTypes behavior2) {
		switch(type) {
		case BEHAVIOR_SEQ_ERROR:
			throw new IllegalArgumentException(SimplePlusError.errorLabel + "Trying to do `" + BehaviorTypeOperation.typeToString(behavior2) + "` action after `" + BehaviorTypeOperation.typeToString(behavior1) + "` action");
		default:
			throw new IllegalArgumentException("INVALID ERROR TYPE");
		}
	}
	
	
	
	// To string
	
	public String toString() {
		return this.error;
	}

}
