grammar SVM;

@header {
import java.util.HashMap;
}

@lexer::members {
public int lexicalErrors=0;
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/
  
assembly: (instruction)* ;

instruction:
    ( PUSH n=NUMBER
	  | PUSH l=LABEL
	  | POP
	  | ADD
	  | SUB
	  | MULT
	  | DIV
	  | AND
	  | OR
	  | NOT
	  | STOREW offset=NUMBER // sw a0 fp + offset
	  | LOADW  offset=NUMBER
	  | l=LABEL COL     
	  | BRANCH l=LABEL
	  | BRANCHEQ l=LABEL
	  | BRANCHLESSEQ l=LABEL
	  | BRANCHLESS l=LABEL
	  | JR
	  | LOADRA          
	  | STORERA         
	  | LOADRV          
	  | STORERV         
	  | LOADFP          
	  | STOREFP         
	  | COPYFP          
	  | LOADHP          
	  | STOREHP
	  | PRINT
	  | HALT
	  | CRA
	  | STORER1
	  | LOADR1
	  | STORER2
	  | LOADR2
	  | LIR1 n=NUMBER
	  | LIR2 n=NUMBER
	  ) ;
 	 
/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

// -------------- PUSH AND POP ON STACK --------------
PUSH  	 : 'push' ; 	// pushes constant in the stack
POP	 : 'pop' ; 	// pops from stack

// -------------- ARITMETIC OPERATIONS --------------
ADD	 : 'add' ;  	// r1 <- r1 + r2
SUB	 : 'sub' ;	// r1 <- r1 - r2
MULT	 : 'mult' ;  	// r1 <- r1 * r2
DIV	 : 'div' ;	// r1 <- r1 / r2

// -------------- BOOLEAN OPERATIONS --------------
BRANCHEQ : 'beq' ;	// jump to label if r1 == r2
BRANCHLESSEQ:'bleq' ;	// jump to label if r1 <= r2
BRANCHLESS : 'bless' ; // jump to label if r1 < r2
AND : 'and'; // r1 = 1 if r1 && r2 is true else r1 = 0
OR : 'or'; // r1 = 0 if r1 || r2 is false else r1 = 1
NOT : 'not'; // if r1 = 1 then r1 = 0 else r1 = 1

// -------------- LOAD WORD AND STORE WORD -------------
STOREW	 : 'sw' ; 	// store the value of r1 in the MEMORY[offset + sp] :: MEMORY[sp + offset] = r1
LOADW	 : 'lw' ;	// set the value of r1 at MEMORY[sp + offset] :: r1 = MEMORY[sp + offset]

// -------------- JUMPING INSTRUCTIONS -------------
CRA      : 'cra' ; // set ra to the address of caller
JR	     : 'jr' ;	// jump to the instruction pointed by ra
BRANCH	 : 'b' ;	// jump to label

// -------------- LOAD/STORE REGISTERS ON/FROM STACK -------------
LOADRA	 : 'lra' ;	// load  ra in the top of stack
STORERA  : 'sra' ;	// store top of stack into ra

LOADRV	 : 'lrv' ;	// load rv in the top of stack
STORERV  : 'srv' ;	// store top of stack into rv

LOADFP	 : 'lfp' ;	// load fp in the top of stack
STOREFP	 : 'sfp' ;	// store top of stack in fp

LOADHP	 : 'lhp' ;	// load hp in the top of stack
STOREHP	 : 'shp' ;	// store top into $hp

STORER1  : 'sr1' ; // store top of stack into r1
LOADR1   : 'lr1' ; // load r1 in the top of stack

STORER2  : 'sr2' ; // store top into r2
LOADR2   : 'lr2' ; // load r2 in the top of stack

// -------------- LI ISTRUCTIONS -------------
LIR1        : 'lir1' ; //set r1 with the value number n
LIR2        : 'lir2' ; //set r2 with the value number n

// -------------- PRINT -------------
PRINT	 : 'print' ;	// print top of stack

// -------------- REGISTER COPYING -------------
COPYFP   : 'cfp' ;  // copy sp in fp :: fp <- sp

// -------------- HALT -------------
HALT	 : 'halt' ;	// stop execution


COL	 : ':' ;
LABEL	 : ('a'..'z'|'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')* ;
NUMBER	 : '0' | ('-')?(('1'..'9')('0'..'9')*);
WHITESP  : ( '\t' | ' ' | '\r' | '\n' )+   -> channel(HIDDEN);

ERR   	 : . { System.err.println("Invalid char: "+ getText()); lexicalErrors++;  } -> channel(HIDDEN); 

