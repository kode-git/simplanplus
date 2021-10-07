
# Linguaggio SimpLanPlus 

<p>
        <img src="https://img.shields.io/static/v1?label=build&message=passing&color=%3CCOLOR%3E" alt="alternatetext">
	<img src="https://img.shields.io/badge/state-complete-red" alt="alternatetext">
	<img src="https://img.shields.io/badge/version-1.0%20-blue" alt="alternatetext">
  <img src="https://img.shields.io/badge/ANTLR-9.2.1-yellow" alt="alternatetext">
  <img src="https://img.shields.io/badge/Java-13.2-white" alt="alternatetext">
</p>
Progetto per il corso di Compilatori ed Interpreti presso Alma Mater Studiorum di Bologna, laura magistrale in Informatica. Il compilatore è basato su grammatiche ANTL4 e programmato in Java (JDK 13.0.6).

## Caratteristiche

Il sistema controllerà i seguenti casi in ambito compilativo:
<ul>
  <li>Variabili/Funzioni non dichiarate</li>
  <li>Variabili dichiarate piu` volte nello stesso ambiente </li>
  <li>Uso di variabili non inizializzate</li>
  <li>Corretto uso dei puntatori</li>
  <li>Parametri attuali non conformi ai parametri formali (inclusa la verifica sui parametri passati per var)</li>
  <li>La correttezza dei tipi </li>
</ul>

Inoltre controlla gli accessi a identificatori "cancellati" con particolare
attenzione all'<b>aliasing</b> implementandone il sistema.

In tutto il progetto, si assuma che i programmi possano essere <b>ricorsivi</b> ma <b>non mutuamente ricorsivi</b>.

Infine:

A. Il bytecode ha istruzioni per una macchina a pila che memorizza in un 
   apposito registro il valore dell'ultima istruzione calcolata<br>
B. Implementare un interprete per il bytecode.<br>
C. Compilare ed esegue i programmi del linguaggio ad alto livello.<br>

## Autori

- Andrea Gurioli (@andreagurioli1995)
- Giovanni Pietrucci (@giovanniPi997)
- Mario Sessa (@kode-git)

## Licenza

&copy; Apache License Version 2.0, January 2004
