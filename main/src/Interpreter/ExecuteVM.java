package Interpreter;

import parser.SVMParser;

public class ExecuteVM {

    public static final int CODESIZE = 10000;
    public static final int MEMSIZE = 10000;

    private int[] code;     // code area is separated from the memory
    private int[] memory = new int[MEMSIZE]; // memory with default layout except for code area
    private int ip = 0;     // code index
    private int sp = 0; // stack ->next :: stack + 1
    private int hp = MEMSIZE - 1; // heap-> next :: heap - 1
    private int fp = 0;  // frame ->next :: frame + 1
    private int ra;     // return address
    private int rv;     // return value

    public ExecuteVM(int[] code) {
        this.code = code;
    }

    public void cpu() {
        while (true) {
            if (hp + 1 < sp) {
                System.out.println("\nRuntime error: Out of memory");
                return;
            } else {

                int bytecode = code[ip++]; // fetch
                int v1, v2;
                int address;
                switch (bytecode) {
                    case SVMParser.PUSH:
                        push(code[ip++]);
                        break;
                    case SVMParser.POP:
                        pop();
                        break;
                    case SVMParser.ADD:
                        v1 = pop();
                        v2 = pop();
                        push(v2 + v1);
                        break;
                    case SVMParser.MULT:
                        v1 = pop();
                        v2 = pop();
                        push(v2 * v1);
                        break;
                    case SVMParser.DIV:
                        v1 = pop();
                        v2 = pop();

                        try {
                            push(v2 / v1);
                        } catch(ArithmeticException e){
                            System.out.println("Arithmetic error: / by zero");
                            return;
                        }
                        break;
                    case SVMParser.SUB:
                        v1 = pop();
                        v2 = pop();
                        push(v2 - v1);
                        break;
                    case SVMParser.STOREW: //
                        address = pop();
                        memory[address] = pop();
                        break;
                    case SVMParser.LOADW: //
                        // check if object address where we take the method label
                        // is null value (-10000)
                        if (memory[sp] == -10000) {
                            System.out.println("\nRuntime Error: Null pointer exception");
                            return;
                        }
                        push(memory[pop()]);
                        break;
                    case SVMParser.BRANCH:
                        address = code[ip];
                        ip = address;
                        break;
                    case SVMParser.BRANCHEQ: //
                        address = code[ip++];
                        v1 = pop();
                        v2 = pop();
                        if (v2 == v1) ip = address;
                        break;
                    case SVMParser.BRANCHLESSEQ:
                        address = code[ip++];
                        v1 = pop();
                        v2 = pop();
                        if (v2 <= v1) ip = address;
                        break;
                    case SVMParser.BRANCHLESS:
                        address = code[ip++];
                        v1 = pop();
                        v2 = pop();
                        if (v2 < v1) ip = address;
                        break;
                    case SVMParser.AND:
                        v1 = pop();
                        v2 = pop();
                        if (v1 == v2 && v2 == 1) // 1 & 1 = 1
                            push(1); // true
                        else push(0); // false
                        break;
                    case SVMParser.OR:
                        v1 = pop();
                        v2 = pop();
                        if (v1 == v2 && v2 == 0) // 0 | 0 = 0
                            push(0);     // false
                        else push(1);    // true
                        break;
                    case SVMParser.NOT:
                        v1 = pop();
                        if (v1 == 1) push(0);
                        else push(1);
                        break;
                    case SVMParser.JS: //
                        address = pop();
                        ra = ip;
                        ip = address;
                        break;
                    case SVMParser.STORERA: //
                        ra = pop();
                        break;
                    case SVMParser.LOADRA: //
                        push(ra);
                        break;
                    case SVMParser.STORERV: //
                        rv = pop();
                        break;
                    case SVMParser.LOADRV: //
                        push(rv);
                        break;
                    case SVMParser.LOADFP: //
                        push(fp);
                        break;
                    case SVMParser.STOREFP: //
                        fp = pop();
                        break;
                    case SVMParser.COPYFP: //
                        fp = sp;
                        break;
                    case SVMParser.STOREHP: //
                        hp = pop();
                        break;
                    case SVMParser.LOADHP: //
                        push(hp);
                        break;
                    case SVMParser.PRINT:
                        System.out.println((sp < MEMSIZE) ? memory[sp] : "Runtime error: Empty stack!");
                        break;
                    case SVMParser.HALT:
                        //to print the result
                        //System.out.println("\nResult: " + memory[sp] + "\n");
                        return;
                }
            }
        }
    }

    private int pop() {
        return memory[sp--];
    }

    private void push(int v) {
        memory[++sp] = v;
    }

}