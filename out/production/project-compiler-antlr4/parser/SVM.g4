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
	  | STOREW offset=NUMBER
	  | LOADW  offset=NUMBER
	  | LWR1   offset=NUMBER
	  | SWR1   offset=NUMBER
	  | LWFP  offset=NUMBER
	  | SWFP offset=NUMBER
	  | LWHP  offset=NUMBER
      | SWHP offset=NUMBER
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
	  | MOVEFP offset=NUMBER
	  | COPYAL
	  | LOADHP          
	  | STOREHP
	  | PRINT
	  | PRINTHP
	  | HALT
	  | CRA
	  | STORER1
	  | LOADR1
	  | STORER2
	  | LOADR2
	  | STOREAL
	  | LOADAL
	  | LWAFP n=NUMBER
	  | LIR1 n=NUMBER
	  | LIR2 n=NUMBER
	  | NEW
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
STOREW	 : 'sw' ; 	// store the value of r1 in the MEMORY[offset + al] :: MEMORY[al + offset] = r1
LOADW	 : 'lw' ;	// set the value of r1 at MEMORY[sp + offset] :: al = MEMORY[al + offset]
SWR1      : 'sw1' ; // store the value of r1 in the MEMORY[offset + al] :: MEMORY[al + offset] = r1
LWR1	 : 'lw1' ;	// set the value of r1 at MEMORY[al + offset] :: r1 = MEMORY[al + offset]
SWFP    : 'swfp' ;  // set the value of r1 in the memory[offset + rsp] :: MEMORY[sp + offset] = r1
LWFP	 : 'lwfp' ;	// set the value of r1 at MEMORY[sp + offset] :: r1 = MEMORY[sp + offset]
LWAFP    : 'lwafp'; // set the value of al at MEMORY[sp + offset] :: al = MEMORY[sp + offset]

// -------------- HEAP INSTRUCTIONS -------------
SWHP    : 'swhp' ;  // set the value of r1 in the memory[offset + hp] :: MEMORY[hp + offset] = r1
LWHP	 : 'lwhp' ;	// set the value of r1 at MEMORY[hp + offset] :: r1 = MEMORY[hp + offset]
NEW      : 'new';   // new pointer

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
LOADR1   : 'lr1' ; // load r1 into the top of stack

STORER2  : 'sr2' ; // store top into r2
LOADR2   : 'lr2' ; // load r2 into the top of stack

STOREAL : 'sal' ; // store top into al
LOADAL :  'lal' ; // load al into the top of stack


// -------------- LI ISTRUCTIONS -------------
LIR1        : 'lir1' ; //set r1 with the value number n
LIR2        : 'lir2' ; //set r2 with the value number n

// -------------- PRINT -------------
PRINT	 : 'print' ;	// print top of stack
PRINTHP : 'printhp'; // print hp value

// -------------- REGISTER COPYING -------------
COPYFP   : 'cfp' ;  // copy sp in fp :: fp <- sp
COPYAL    : 'cal' ; // copy sp in al :: al <- sp

// ----------- MOVE -----------------
MOVEFP  : 'mfp'; // fp <- sp - offset
// -------------- HALT -------------
HALT	 : 'halt' ;	// stop execution


COL	 : ':' ;
LABEL	 : ('a'..'z'|'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')* ;
NUMBER	 : '0' | ('-')?(('1'..'9')('0'..'9')*);
WHITESP  : ( '\t' | ' ' | '\r' | '\n' )+   -> channel(HIDDEN);

ERR   	 : . { System.err.println("Invalid char: "+ getText()); lexicalErrors++;  } -> channel(HIDDEN); 

