package main;

public class Main {
	
	private static final boolean test = true;
	
	public static void main(String[] args) {
		
		if(Main.test) {
			Compiler.compile("test.sp");
			Compiler.run("a.spbc");
		} else {
			switch(args[0]) {
			case "--compile":
				Compiler.compile(args[1]);
				break;
			case "--run":
				Compiler.run(args[1]);
				break;
			case "--compile-and-run":
				Compiler.compile(args[1]);
				Compiler.run("a.spbc");
				break;
			}
		}
		
	}

}
