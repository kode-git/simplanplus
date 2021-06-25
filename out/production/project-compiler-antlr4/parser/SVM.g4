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
	  | STOREW	  
	  | LOADW           
	  | l=LABEL COL     
	  | BRANCH l=LABEL  
	  | BRANCHEQ l=LABEL 
	  | BRANCHLESSEQ l=LABEL
	  | BRANCHLESS l=LABEL
	  | JS              
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
	  ) ;
 	 
/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/
 
PUSH  	 : 'push' ; 	// pushes constant in the stack
POP	 : 'pop' ; 	// pops from stack

// -------------- ARITMETIC OPERATIONS --------------
ADD	 : 'add' ;  	// add two values from the stack
SUB	 : 'sub' ;	// add two values from the stack
MULT	 : 'mult' ;  	// add two values from the stack
DIV	 : 'div' ;	// add two values from the stack

// -------------- BOOLEAN OPERATIONS --------------
BRANCHEQ : 'beq' ;	// jump to label if top == next
BRANCHLESSEQ:'bleq' ;	// jump to label if top <= next
BRANCHLESS : 'bless' ; // jump to label if top < next
AND : 'and'; // and two values from the stack and put the result on the top
OR : 'or'; // or two values from the stack and put the result on the top
NOT : 'not'; // not of the top value of the stack and put the result on the top

// -------------- OTHER ACTIONS -------------
STOREW	 : 'sw' ; 	//pop the two values x,y on top of the stack and do MEMORY[x]=y
LOADW	 : 'lw' ;	//pop the value x on top of the stack and push MEMORY[x]
BRANCH	 : 'b' ;	// jump to label

JS	     : 'js' ;	// jump to instruction pointed by top of stack and store next instruction in $ra
LOADRA	 : 'lra' ;	// load from $ra
STORERA  : 'sra' ;	// store top into $ra
LOADRV	 : 'lrv' ;	// load from $rv
STORERV  : 'srv' ;	// store top into $rv
LOADFP	 : 'lfp' ;	// load $fp in the stack
STOREFP	 : 'sfp' ;	// store top into $fp
COPYFP   : 'cfp' ;  // copy $sp into $fp
LOADHP	 : 'lhp' ;	// load $hp in the stack
STOREHP	 : 'shp' ;	// store top into $hp
PRINT	 : 'print' ;	// print top of stack
HALT	 : 'halt' ;	// stop execution


COL	 : ':' ;
LABEL	 : ('a'..'z'|'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')* ;
NUMBER	 : '0' | ('-')?(('1'..'9')('0'..'9')*) ;

WHITESP  : ( '\t' | ' ' | '\r' | '\n' )+   -> channel(HIDDEN);

ERR   	 : . { System.err.println("Invalid char: "+ getText()); lexicalErrors++;  } -> channel(HIDDEN); 

