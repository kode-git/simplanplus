package Interpreter;

import parser.SVMParser;

public class ExecuteVM {

    public static final int CODESIZE = 10000;
    public static final int MEMSIZE = 10000;

    public int[] code;                      // instructions memory
    public int[] memory = new int[MEMSIZE]; // activation records memory
    private int ip = 0;                      // pointer to the next code instruction
    public int sp = 0;                      // pointer of the next free record of the stack
    private int hp = MEMSIZE - 1; // heap-> next :: heap - 1
    private int fp = 2;  // frame ->next :: frame + 1
    private int ra=0;     // return address
    private int rv;     // return value
    private int r1;      // register r1
    private int r2;      // register r2
    private int offset; // temporal offset for sw and lw
    private int number; // for li on r1 and r2
    private int al = 2;     // access link

    public ExecuteVM(int[] code) {
        this.code = code;
    }

    public void cpu() {
        while (true) {
            if (hp < sp + 1) {
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
                        r1 = r1 + r2;
                        break;
                    case SVMParser.MULT:
                        r1 = r1 * r2;
                        break;
                    case SVMParser.DIV:
                        try {
                            r1 = r1 / r2;
                        } catch(ArithmeticException e){
                            System.out.println("Arithmetic error: / by zero");
                            return;
                        }
                        break;
                    case SVMParser.SUB:
                        r1 = r1 - r2;
                        break;
                    case SVMParser.STOREW: //
                        offset = code[ip++];
                        address = al + offset;
                        try {
                            memory[address] = al;
                        } catch(ArrayIndexOutOfBoundsException e){
                            System.out.println("\nRuntime error: Index out of memory");
                            return;
                        }
                        break;
                    case SVMParser.LOADW: //
                        offset = code[ip++];

                        address = al + offset;
                        if (memory[address] == -10000) {
                            System.out.println("\nRuntime Error: Null pointer exception");
                            return;
                        }
                        al = memory[address]; // lw al address :: lw al offset + al :: lw al offset(al)
                        break;
                    case SVMParser.LWR1: //
                        offset = code[ip++];

                        address = al + offset;

                        if (memory[address] == -10000) {
                            System.out.println("\nRuntime Error: Null pointer exception");
                            return;
                        }
                        r1 = memory[al + offset];
                        break;
                    case SVMParser.SWR1: // r1 == al ::  sw al offset(al)
                        offset = code[ip++];
                        address = al + offset;
                        try {
                            memory[address] = r1;
                        } catch(ArrayIndexOutOfBoundsException e){
                            System.out.println("\nRuntime error: Index out of memory");
                            return;
                        }
                        break;
                    case SVMParser.LWAFP:
                        offset = code[ip++];

                        address = fp + offset;
                        if (memory[address] == -10000) {
                            System.out.println("\nRuntime Error: Null pointer exception");
                            return;
                        }
                        al = memory[fp + offset];
                        break;
                    case SVMParser.LWFP: //
                        offset = code[ip++];

                        address = fp + offset;
                        if (memory[address] == -10000) {
                            System.out.println("\nRuntime Error: Null pointer exception");
                            return;
                        }
                        r1 = memory[fp + offset];
                        break;
                    case SVMParser.SWFP: //
                        offset = code[ip++];
                        address = fp + offset;
                        try {
                            memory[address] = r1;
                        } catch(ArrayIndexOutOfBoundsException e){
                            System.out.println("\nRuntime Error: Null pointer exception");
                            return;
                        }
                        break;
                    case SVMParser.LIR1:
                        number = code[ip++];
                        r1 = number;
                        break;
                    case SVMParser.LIR2:
                        number = code[ip++];
                        r2 = number;
                        break;
                    case SVMParser.BRANCH:
                        address = code[ip]; // TODO Why is ip and not ip++
                        ip = address;
                        break;
                    case SVMParser.BRANCHEQ: //
                        address = code[ip++];
                        if (r1 == r2) ip = address;
                        break;
                    case SVMParser.BRANCHLESSEQ:
                        address = code[ip++];
                        if (r1 <= r2) ip = address;
                        break;
                    case SVMParser.BRANCHLESS:
                        address = code[ip++];
                        if (r1 < r2) ip = address;
                        break;
                    case SVMParser.AND:
                        if (r1 == 0 || r2 == 0) // 1 & 1 = 1
                            r1 = 0;
                        else r1 = 1; // false
                        break;
                    case SVMParser.OR:
                        if (r1 == 0 && r2 == 0) // 0 | 0 = 0
                            r1 = 0;    // false
                        else r1 = 1;  // true
                        break;
                    case SVMParser.NOT:
                        if (r1 == 0) r1 = 1;
                        else r1 = 0;
                        break;
                    case SVMParser.JR: //
                        address = ra;
                        ip = address;
                        break;
                    case SVMParser.CRA:
                        ra = ip + 2; // Avoid the jal instruction
                        // cra
                        // jal f_entry
                        // point here
                        break;
                    case SVMParser.STORER1:
                        r1 = pop();
                        break;
                    case SVMParser.LOADR1:
                        push(r1);
                        break;
                    case SVMParser.STOREAL:
                        al = pop();
                        break;
                    case SVMParser.LOADAL:
                        push(al);
                        break;
                    case SVMParser.STORER2:
                        r2 = pop();
                        break;
                    case SVMParser.LOADR2:
                        push(r2);
                        break;
                    case SVMParser.STORERA: //
                        ra = pop();
                        break;
                    case SVMParser.LOADRA: //
                        push(ra);
                        break;
                    case SVMParser.STORERV: //
                        rv = r1;
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
                    case SVMParser.COPYAL: //
                        al = sp;
                        break;
                    case SVMParser.MOVEFP:
                        offset = code[ip++];
                        fp = sp - offset;
                        break;
                    case SVMParser.STOREHP: //
                        hp = pop();
                        break;
                    case SVMParser.LOADHP: //
                        push(hp);
                        break;
                    case SVMParser.PRINT:
                        System.out.println(r1);
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