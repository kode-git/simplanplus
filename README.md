
# SimpLanPlus 

<p>
        <img src="https://img.shields.io/static/v1?label=build&message=passing&color=%3CCOLOR%3E" alt="alternatetext">
	<img src="https://img.shields.io/badge/state-complete-red" alt="alternatetext">
	<img src="https://img.shields.io/badge/version-1.0%20-blue" alt="alternatetext">
  <img src="https://img.shields.io/badge/ANTLR-9.2.1-yellow" alt="alternatetext">
  <img src="https://img.shields.io/badge/Java-13.2-white" alt="alternatetext">
</p>
Compiler based on ANTLR4 gramar and implemented in Java (JDK 13.0.6). It is used to Compiler and Interpreter exam at Alma Mater Studiorum - DISI Course

## Features

The system will check the following cases in the compilation field:
<ul>
   <li> Undeclared Variables / Functions </li>
   <li> Variables declared multiple times in the same environment </li>
   <li> Using uninitialized variables </li>
   <li> Correct use of pointers </li>
   <li> Current parameters not conforming to formal parameters (including checking on parameters passed for var) </li>
   <li> The correctness of types </li>
</ul>

It also controls access to "deleted" identifiers with particular
pay attention to <b> aliasing </b> by implementing the system.

Throughout the project, assume that programs can be <b> recursive </b> but <b> not mutually recursive </b>.<br><br>
We introduced also the <b>interpreter simulator</b>:
<br>
A. The bytecode has instructions for a stacked machine that stores in a
    appropriate register the value of the last calculated instruction <br>
B. Implement an interpreter for the bytecode. <br>
C. Compile and execute high-level language programs. <br>

## Authors

- Andrea Gurioli (@andreagurioli1995)
- Giovanni Pietrucci (@giovanniPi997)
- Mario Sessa (@kode-git)

## License

&copy; Apache License Version 2.0, January 2004
