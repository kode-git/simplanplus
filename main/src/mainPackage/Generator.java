package mainPackage;

import java.io.*;

public class Generator {

    public static void main(String[] args) throws FileNotFoundException {

        try {
            File myObj = new File("code.slp");
            if (myObj.createNewFile()) {
                System.out.println("Generator Log: File created: " + myObj.getName());
            } else {
                System.out.println("Generator Error: File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Generator Error: An error occurred.");
            e.printStackTrace();
        }
    }
}
