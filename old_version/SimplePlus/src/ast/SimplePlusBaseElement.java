package ast;

import java.util.HashMap;
import java.util.List;

import utils.BytecodeLabelTable;
import utils.SimplePlusBehaviorTypes;
import utils.SimplePlusError;
import utils.SimplePlusSTVariable;
import utils.SimplePlusSymbolTable;
import utils.SimplePlusTypes;

public abstract class SimplePlusBaseElement {
	
	// Semantic analysis
	// -> Dichiarazioni di variabili e funzioni
	// -> Eliminazione di variabili e funzioni
	// -> Controllo esistenza di variabili e funzioni
	// -> Controllo di coerenza nelle chiamate delle funzioni
	// -> Controllo assegnamento variabili prima dell'utilizzo
	// -> Nelle dichiarazioni di funzioni vengono cancellate solo le variabili locali, quelle globali non vengono processate
	
	public abstract List<SimplePlusError> semanticAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction, boolean varExp);
	
	
	
	// Type analysis
	// -> Controllo coerenza dei tipi negli assegnamenti
	// -> Controllo coerenza dei tipi nelle chiamate di funzione
	// -> Controllo coerenza dei tipi del costrutto return
	// -> Controllo validità del costrutto return nel costrutto if-then-else
	
	public abstract SimplePlusTypes typeAnalysis(SimplePlusSymbolTable symbolTable, HashMap<String, SimplePlusSTVariable> scopeFunction);
	
	
	
	// Behavioral analysis
	// -> Controllo di accesso alle variabili presenti in scope a livelli superiori
	// -> Controllo di accesso a variabile statiche passate per riferimento
	// -> Controllo cancellazione di variabili globali passate per riferimento
	
	public abstract void behavioralAnalysis(SimplePlusSymbolTable symbolTable, boolean branchElse, HashMap<String, SimplePlusSTVariable> scopeFunction, HashMap<SimplePlusSTVariable, SimplePlusBehaviorTypes> behaviorReference);
	
	
	
	// Bytecode generation
	
	public abstract String bytecodeGeneration(SimplePlusSymbolTable symbolTable, BytecodeLabelTable labelTable);

}
