grammar Bytecode;

bytecode		: (instruction)* ;

instruction		: WRITE_RA NUMBER
				| WRITE_VP NUMBER
				| WRITE_VARIABLE
				| READ_VARIABLE
				| PUSH
				| PUSH_NUMBER NUMBER
				| PUSH_LABEL LABEL
				| HALT
				| DECVAR
				| NEG
				| NOT
				| SUM
				| SUB
				| MULT
				| DIV
				| AND
				| OR
				| EQ
				| NEQ
				| LT
				| LEQ
				| GT
				| GEQ
				| PRINT
				| LABEL COL
				| JUMP
				| JUMP_TRUE
				| DECFUN_BEGIN
				| DECFUN_END
				| RET 
				| CALL_BEGIN 
				| CALL_JUMP ;

WRITE_RA		: 'WRITE_RA' ;
WRITE_VP		: 'WRITE_VP' ;
WRITE_VARIABLE	: 'WRITE_VARIABLE' ;
READ_VARIABLE	: 'READ_VARIABLE' ;
PUSH			: 'PUSH' ;
PUSH_NUMBER		: 'PUSH_NUMBER' ;
PUSH_LABEL		: 'PUSH_LABEL' ;
HALT			: 'HALT' ;
DECVAR			: 'DECVAR' ;
NEG				: 'NEG' ;
NOT				: 'NOT' ;
SUM				: 'SUM' ;
SUB				: 'SUB' ;
MULT			: 'MULT' ;
DIV				: 'DIV' ;
AND				: 'AND' ;
OR				: 'OR' ;
EQ				: 'EQ';
NEQ				: 'NEQ' ;
LT				: 'LT' ;
LEQ				: 'LEQ' ;
GT				: 'GT' ;
GEQ				: 'GEQ' ;
PRINT			: 'PRINT' ;
JUMP			: 'JUMP' ;
JUMP_TRUE		: 'JUMP_TRUE' ;
DECFUN_BEGIN	: 'DECFUN_BEGIN' ;
DECFUN_END		: 'DECFUN_END' ;
RET				: 'RET' ;
CALL_BEGIN		: 'CALL_BEGIN' ;
CALL_JUMP		: 'CALL_JUMP' ;

COL				: ':' ;
LABEL			: ('a'..'z'|'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')* ;
NUMBER			: '0' | ('-')?(('1'..'9')('0'..'9')*) ;

WHITESP			: ( '\t' | ' ' | '\r' | '\n' )+   -> channel(HIDDEN);
