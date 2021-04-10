package vm;

import parser.BytecodeParser;

public class SimplePlusVirtualMachine {
	
	public static final int CODE_SIZE = 100000; 	// Dimensione della memoria riservata alle istruzioni
	public static final int MEM_SIZE = 100000; 		// Dimensione della memoria riservata ai record di attivazione
	
	private int[] code;								// Memoria riservata alle istruzioni
	private int[] mem; 								// Memoria riservata ai record di attivazione
	
	private int ip;									// Puntatore alla prossima istruzione
	private int sp;									// Puntatore al prossimo record libero dello stack
	private int vp;									// Puntatore all'inizio della memoria per le variabili dello scope corrente
	private int fp;									// Puntatore all'inizio del frame corrente
													// mem[fp] = ip da cui ripartire dopo aver fatto tutto
													// mem[fp] - 1 = fp_old
	
	private int fn;									// Annidamento nelle dichiarazioni di funzioni, se � > 0 l'esecuzione viene sospesa
		
	private int ra; 								// Registro a -> valore di ritorno di una computazione
	private int ro;									// Registro o -> gestione offset variabili
	private int rv;									// Registro v -> registro temporaneo dei valori
	private int rj;									// Registro j -> registro contenente l'istruzione a cui saltare
	
	
	
	// Constructor
	
	public SimplePlusVirtualMachine(int[] code, int vp) {
		if(code.length > SimplePlusVirtualMachine.CODE_SIZE) {
			throw new IllegalArgumentException("Virtual machine out of mem");
		}
		this.code = code;
		this.mem = new int[SimplePlusVirtualMachine.MEM_SIZE];
		this.ip = 0;
		this.sp = SimplePlusVirtualMachine.MEM_SIZE - 1;
		this.vp = vp;
		this.fp = SimplePlusVirtualMachine.MEM_SIZE - 1;
		this.fn = 0;
	}
	
	
	
	// Carica la prossima istruzione
	
	private int fetch() {
		int instructionNew = this.code[this.ip];
		this.ip = this.ip + 1;
		return instructionNew;
	}
	
	// Push nello stack
	
	private void push(int word) {
		this.mem[this.sp] = word;
		this.sp = this.sp - 1;
	}
	
	// Pop dallo stack
	
	private int pop() {
		this.sp = this.sp + 1;
		return this.mem[sp];
	}
	
	// Esegue il codice caricato
	
	public void run() {
		do {
			if(this.vp > this.sp) {
				throw new IllegalArgumentException("Virtual Machine: out of mem");
			} else {
				int instruction = this.fetch();
				if(this.fn == 0) {
					switch(instruction) {
					case BytecodeParser.WRITE_RA:
						this.ra = this.fetch();
						break;
					case BytecodeParser.WRITE_VARIABLE:
						this.ro = this.pop();
						this.rv = this.pop();
						this.mem[this.ro] = this.rv;
						break;
					case BytecodeParser.READ_VARIABLE:
						this.ro = this.pop();
						this.ra = this.mem[this.ro];
						break;
					case BytecodeParser.PUSH:
						this.push(this.ra);
						break;
					case BytecodeParser.PUSH_NUMBER:
						this.rv = this.fetch();
						this.push(this.rv);
						break;
					case BytecodeParser.PUSH_LABEL:
						this.rj = this.fetch();
						this.push(this.rj);
						break;
					case BytecodeParser.HALT:
						return;
					case BytecodeParser.NEG:
						this.ra = - this.pop();
						break;
					case BytecodeParser.NOT:
						this.ra = this.pop() == 0 ? 1 : 0;
						break;
					case BytecodeParser.SUM:
						this.ra = this.pop();
						this.ra = this.pop() + this.ra;
						break;
					case BytecodeParser.SUB:
						this.ra = this.pop();
						this.ra = this.pop() - this.ra;
						break;
					case BytecodeParser.MULT:
						this.ra = this.pop();
						this.ra = this.pop() * this.ra;
						break;
					case BytecodeParser.DIV:
						this.ra = this.pop();
						this.ra = this.pop() / this.ra;
						break;
					case BytecodeParser.AND:
						this.ra = this.pop();
						this.ra = this.pop() + this.ra == 2 ? 1 : 0;
						break;
					case BytecodeParser.OR:
						this.ra = this.pop();
						this.ra = this.pop() + this.ra > 0 ? 1 : 0;
						break;
					case BytecodeParser.EQ:
						this.ra = this.pop();
						this.ra = this.pop() == this.ra ? 1 : 0;
						break;
					case BytecodeParser.NEQ:
						this.ra = this.pop();
						this.ra = this.pop() != this.ra ? 1 : 0;
						break;
					case BytecodeParser.LT:
						this.ra = this.pop();
						this.ra = this.pop() < this.ra ? 1 : 0;
						break;
					case BytecodeParser.LEQ:
						this.ra = this.pop();
						this.ra = this.pop() <= this.ra ? 1 : 0;
						break;
					case BytecodeParser.GT:
						this.ra = this.pop();
						this.ra = this.pop() > this.ra ? 1 : 0;
						break;
					case BytecodeParser.GEQ:
						this.ra = this.pop();
						this.ra = this.pop() >= this.ra ? 1 : 0;
						break;
					case BytecodeParser.PRINT:
						System.out.println(Integer.toString(this.pop()));
						break;
					case BytecodeParser.JUMP:
						this.rj = this.pop();
						this.ip = this.rj;
						break;
					case BytecodeParser.JUMP_TRUE:
						this.rj = this.pop();
						this.rv = this.pop();
						if(this.rv == 1) {
							this.ip = this.rj;
						}
						break;
					case BytecodeParser.DECFUN_BEGIN:
						this.fn = this.fn + 1;
						break;
					case BytecodeParser.RET:
						if(this.fp < SimplePlusVirtualMachine.MEM_SIZE - 1) {
							this.ip = this.mem[this.fp + 2];
							this.fp = this.mem[this.fp + 1];
							this.sp = this.fp;
						}
						break;
					case BytecodeParser.CALL_BEGIN:
						this.push(0);
						this.push(this.fp);
						this.fp = this.sp;
						break;
					case BytecodeParser.CALL_JUMP:
						this.rj = this.pop();
						this.mem[this.fp + 2] = this.ip;
						this.ip = this.rj;
						break;
					}
				} else {
					switch(instruction) {
					case BytecodeParser.DECFUN_BEGIN:
						this.fn = this.fn + 1;
						break;
					case BytecodeParser.DECFUN_END:
						this.fn = this.fn - 1;
						break;
					}
				}
			}
		} while(true);
	}

}
