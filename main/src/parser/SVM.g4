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
	  | ADDR
	  | SUB
	  | SUBR
	  | MULT
	  | MULTR
	  | DIV
	  | DIVR
	  | AND
	  | ANDR
	  | OR
	  | ORR
	  | NOT
	  | NOTR
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
	  | LOADT
      | STORET
      | LOADA
      | STOREA
      | LIA n=NUMBER
	  | PRINT
	  | PRINTT
	  | PRINTA
	  | HALT
	  ) ;
 	 
/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/
 
PUSH  	 : 'push' ; 	// pushes constant in the stack
POP	 : 'pop' ; 	// pops from stack

// -------------- ARITMETIC OPERATIONS --------------
ADD	 : 'add' ;  	// add two values from the stack
ADDR : 'addr';      // add $a0 $a0 $t :: $a0 <- $a0 + $t
SUB	 : 'sub' ;	// add two values from the stack
SUBR : 'subr';  // sub $a0 $a0 $t :: $a0 <- $a0 - $t
MULT	 : 'mult' ;  	// add two values from the stack
MULTR : 'multr'; // mult $a0 $a0 $t :: $a0 <- $a0 * $t
DIV	 : 'div' ;	// add two values from the stack
DIVR:  'divr'; //  div $a0 $a0 $t :: $a0 <- $a0 / $t

// -------------- BOOLEAN OPERATIONS --------------
BRANCHEQ : 'beq' ;	// jump to label if top == next
BRANCHLESSEQ:'bleq' ;	// jump to label if top <= next
BRANCHLESS : 'bless'; // jump to label if top < next
AND : 'and'; // and two values from the stack and put the result on the top
ANDR : 'andr'; // and $a0 $t and put the result in $a0
OR : 'or'; // or two values from the stack and put the result on the top
ORR : 'orr'; // or $a0 $t and put the result in $a0
NOT : 'not'; // not of the top value of the stack and put the result on the top
NOTR : 'notr'; // not $a0 and put the result in $a0

// -------------- REGISTER AND STACK MANIPULATIONS --------------

STOREW	 : 'sw' ; 	//pop the two values x,y on top of the stack and do MEMORY[x]=y
LOADW	 : 'lw' ;	//pop the value x on top of the stack and push MEMORY[x]
BRANCH	 : 'b' ;	// jump to label

JS	     : 'js' ;	// jump to instruction pointed by top of stack and store next instruction in $ra
LOADT    : 'lt' ;   // load $t value in the stack
STORET   : 'st' ;   // store the top of stack in $t
LOADA    : 'la' ;   // load $a value in the stack
STOREA   : 'sa' ;   // store the top of stack in $a
LIA      : 'lia' ;  // load n in $a0
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
PRINTT   : 'printt' ;   // print $t
PRINTA   : 'printa' ;   // print $a0
HALT	 : 'halt' ;	// stop execution


COL	 : ':' ;
LABEL	 : ('a'..'z'|'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')* ;
NUMBER	 : '0' | ('-')?(('1'..'9')('0'..'9')*) ;

WHITESP  : ( '\t' | ' ' | '\r' | '\n' )+   -> channel(HIDDEN);

ERR   	 : . { System.err.println("Invalid char: "+ getText()); lexicalErrors++;  } -> channel(HIDDEN); 

