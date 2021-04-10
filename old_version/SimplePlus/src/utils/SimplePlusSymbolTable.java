package utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// Symbol table di SimplePlus

public class SimplePlusSymbolTable {
	
	private int offset;
	private LinkedList<HashMap<String, SimplePlusSTVariable>> variableScope; 	// Scope delle variabili
	private LinkedList<HashMap<String, SimplePlusSTFunction>> functionScope; 	// Scope delle funzioni
	
	
	
	// Constructor
	
	public SimplePlusSymbolTable() {
		this.offset = 0;
		this.variableScope = new LinkedList<HashMap<String, SimplePlusSTVariable>>();
		this.functionScope = new LinkedList<HashMap<String, SimplePlusSTFunction>>();
	}
	
	public SimplePlusSymbolTable(int offset, LinkedList<HashMap<String, SimplePlusSTVariable>> variableScope, LinkedList<HashMap<String, SimplePlusSTFunction>> functionScope) {
		this.offset = offset;
		this.variableScope = variableScope;
		this.functionScope = functionScope;
	}
	
	
	
	// Clona la symbol table
	
	public SimplePlusSymbolTable clone() {
		LinkedList<HashMap<String, SimplePlusSTVariable>> variableScopeClone = new LinkedList<HashMap<String, SimplePlusSTVariable>>();
		LinkedList<HashMap<String, SimplePlusSTFunction>> functionScopeClone = new LinkedList<HashMap<String, SimplePlusSTFunction>>();
		
		for(HashMap<String, SimplePlusSTVariable> scope : this.variableScope) {
			variableScopeClone.add(new HashMap<String, SimplePlusSTVariable>());
			for(String key : scope.keySet()) {
				variableScopeClone.peek().put(key, scope.get(key));
			}
		}
		
		for(HashMap<String, SimplePlusSTFunction> scope : this.functionScope) {
			functionScopeClone.add(new HashMap<String, SimplePlusSTFunction>());
			for(String key : scope.keySet()) {
				functionScopeClone.peek().put(key, scope.get(key));
			}
		}
		
		return new SimplePlusSymbolTable(this.offset, variableScopeClone, functionScopeClone);
	}
	
	// Effettua il max tra i behavior di due symboltable
	
	public void behaviorMax(SimplePlusSymbolTable merge, HashMap<String, SimplePlusSTVariable> functionScope) {
		for(int i = 0; i < this.variableScope.size(); i++) {
			if(functionScope != null) {
				if(this.variableScope.get(i) == functionScope) {
					return;
				}
			}
			for(String key : this.variableScope.get(i).keySet()) {
				if(merge.variableScope.get(i).containsKey(key)) {
					SimplePlusBehaviorTypes behaviorNew = BehaviorTypeOperation.max(this.variableScope.get(i).get(key).getBehavior(), merge.variableScope.get(i).get(key).getBehavior());
					this.variableScope.get(i).get(key).setBehavior(behaviorNew);
				}
			}
		}
	}
	
	
	
	// Apre un nuovo scope
	
	public void openScope() {
		this.variableScope.push(new HashMap<String, SimplePlusSTVariable>());
		this.functionScope.push(new HashMap<String, SimplePlusSTFunction>());
	}
	
	// Chiude lo scope corrente
	
	public void closeScope() {
		this.variableScope.pop();
		this.functionScope.pop();
	}
	
	
	
	// Restituisci l'ultimo scope delle variabili attivo
	
	public HashMap<String, SimplePlusSTVariable> functionScope() {
		return this.variableScope.peek();
	}
	
	// Restituisce il livello di annidamento dello scope
	
	public int functionScopeLevel(HashMap<String, SimplePlusSTVariable> scopeFunction) {
		int level = 0;
		for(HashMap<String, SimplePlusSTVariable> scope : this.variableScope) {
			if(scope == scopeFunction) {
				return level;
			} else {
				level = level + 1;
			}
		}
		return level - 1;
	}
	
	// Restituisce lo scope a un certo livello
	
	public HashMap<String, SimplePlusSTVariable> functionScopeAt(int level) {
		return this.variableScope.get(level);
	}
	
	// Assegna l'offset alla variabile e incrementa l'offset disponibile
	
	public int newOffset() {
		int offsetNew = this.offset;
		this.offset = this.offset + 1;
		return offsetNew;
	}
	
	
	
	// Aggiunge una variabile
	
	public void addVariable(String id, SimplePlusSTVariable variable) {
		this.variableScope.peek().put(id, variable);
	}
	
	// Elimina una variabile
	
	public void deleteVariable(String id) {
		for(HashMap<String, SimplePlusSTVariable> scope : this.variableScope) {
			if(scope.containsKey(id)) {
				scope.remove(id);
				return;
			}
		}
	}
	
	// Verifica l'esistenza di una variabile nella symbol table
	
	public boolean isVariable(String id) {
		for(HashMap<String, SimplePlusSTVariable> scope : this.variableScope) {
			if(scope.containsKey(id)) {
				return true;
			}
		}
		return false;
	}
	
	// Verifica l'esistenza di una variabile nello scope corrente
	
	public boolean isVariableHere(String id) {
		if(this.variableScope.peek().containsKey(id)) {
			return true;
		} else {
			return false;
		}
	}
	
	// Verifica l'esistenza di una variabile nello scope di una funzione
	
	public boolean isVariableInFunction(String id, HashMap<String, SimplePlusSTVariable> scopeFunction) {
		for(HashMap<String, SimplePlusSTVariable> scope : this.variableScope) {
			if(scope == scopeFunction) {
				return false;
			} else {
				if(scope.containsKey(id)) {
					return true;
				}
			}
		}
		return false;
	}
	
	// Restituisce l'offset di una variabile
	
	public int variableOffset(String id) {
		for(HashMap<String, SimplePlusSTVariable> scope : this.variableScope) {
			if(scope.containsKey(id)) {
				return scope.get(id).getOffset();
			}
		}
		return 0;
	}
	
	// Restituisce il tipo di una variabile
	
	public SimplePlusTypes variableType(String id) {
		for(HashMap<String, SimplePlusSTVariable> scope : this.variableScope) {
			if(scope.containsKey(id)) {
				return scope.get(id).getType();
			}
		}
		return SimplePlusTypes.VOID;
	}
	
	// Restituisce se la variabile e' un riferimento
	
	public boolean variableIsReference(String id) {
		for(HashMap<String, SimplePlusSTVariable> scope : this.variableScope) {
			if(scope.containsKey(id)) {
				return scope.get(id).getReference();
			}
		}
		return false;
	}
	
	// Modifica il comportamento di una variabile
	
	public void variableSetBehavior(String id, SimplePlusBehaviorTypes behavior) {
		for(HashMap<String, SimplePlusSTVariable> scope : this.variableScope) {
			if(scope.containsKey(id)) {
				scope.get(id).setBehavior(behavior);
				return;
			}
		}
	}
	
	// Restituisce il comportamento di una variabile
	
	public SimplePlusBehaviorTypes variableBehavior(String id) {
		for(HashMap<String, SimplePlusSTVariable> scope : this.variableScope) {
			if(scope.containsKey(id)) {
				return scope.get(id).getBehavior();
			}
		}
		return SimplePlusBehaviorTypes.UNDEFINED;
	}
	
	// Imposta la posizione della variabile come argomento di una funzione
	
	public void variableSetArgumentPosition(String id, int argumentPosition) {
		for(HashMap<String, SimplePlusSTVariable> scope : this.variableScope) {
			if(scope.containsKey(id)) {
				scope.get(id).setArgumentPosition(argumentPosition);
				return;
			}
		}
	}
	
	// Restituisce la posizione della variabile come argomento di una funzione
	
	public int variableArgumentPosition(String id) {
		for(HashMap<String, SimplePlusSTVariable> scope : this.variableScope) {
			if(scope.containsKey(id)) {
				return scope.get(id).getArgumentPosition();
			}
		}
		return -1;
	}
	
	// Sovrascrive il record di dati della variabile
	
	public void variableSetVariable(String id, SimplePlusSTVariable variable) {
		for(HashMap<String, SimplePlusSTVariable> scope : this.variableScope) {
			if(scope.containsKey(id)) {
				scope.replace(id, variable);
				return;
			}
		}
	}
	
	// Restituisce il record della variabile
	
	public SimplePlusSTVariable variableVariable(String id) {
		for(HashMap<String, SimplePlusSTVariable> scope : this.variableScope) {
			if(scope.containsKey(id)) {
				return scope.get(id);
			}
		}
		return null;
	}
	
	
	
	// Aggiunge una funzione
	
	public void addFunction(String id, SimplePlusSTFunction function) {
		this.functionScope.peek().put(id, function);
	}
	
	// Elimina una funzione
	
	public void deleteFunction(String id) {
		for(HashMap<String, SimplePlusSTFunction> scope : this.functionScope) {
			if(scope.containsKey(id)) {
				scope.remove(id);
				return;
			}
		}
	}
	
	// Verifica l'esistenza di una funzione nella symbol table
	
	public boolean isFunction(String id) {
		for(HashMap<String, SimplePlusSTFunction> scope : this.functionScope) {
			if(scope.containsKey(id)) {
				return true;
			}
		}
		return false;
	}
	
	// Verifica l'esistenza di una funzione nello scope corrente
	
	public boolean isFunctionHere(String id) {
		if(this.functionScope.peek().containsKey(id)) {
			return true;
		} else {
			return false;
		}
	}
	
	// Restituisce il tipo di una funzione
	
	public SimplePlusTypes functionReturnType(String id) {
		for(HashMap<String, SimplePlusSTFunction> scope : this.functionScope) {
			if(scope.containsKey(id)) {
				return scope.get(id).getReturnType();
			}
		}
		return SimplePlusTypes.VOID;
	}
	
	// Restituisce la lista di argomenti di una funzione
	
	public List<SimplePlusSTVariable> functionArguments(String id) {
		for(HashMap<String, SimplePlusSTFunction> scope : this.functionScope) {
			if(scope.containsKey(id)) {
				return scope.get(id).getArguments();
			}
		}
		return new LinkedList<SimplePlusSTVariable>();
	}
	
	// Imposta la funzione come valutata
	
	public void functionEvaluate(String id) {
		for(HashMap<String, SimplePlusSTFunction> scope : this.functionScope) {
			if(scope.containsKey(id)) {
				scope.get(id).setEvaluated(true);
				return;
			}
		}
	}
	
	// Restituisce se una funzione e' stata valutata
	
	public boolean functionIsEvaluated(String id) {
		for(HashMap<String, SimplePlusSTFunction> scope : this.functionScope) {
			if(scope.containsKey(id)) {
				return scope.get(id).getEvaluated();
			}
		}
		return false;
	}
	
	// Restituisce le behavioral reference di una funzione
	
	public HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes> functionBehaviorReference(String id) {
		for(HashMap<String, SimplePlusSTFunction> scope : this.functionScope) {
			if(scope.containsKey(id)) {
				return scope.get(id).getBehaviorReference();
			}
		}
		return new HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes>();
	}
	
	// Restituisce l'etichetta della funzione
	
	public String functionLabel(String id) {
		for(HashMap<String, SimplePlusSTFunction> scope : this.functionScope) {
			if(scope.containsKey(id)) {
				return scope.get(id).getLabel();
			}
		}
		return "LABEL";
	}
	
}
