package mainPackage;

import java.io.*;

public class Generator {

    public static void main(String[] args) throws FileNotFoundException {

        try {
            File myObj = new File("code.slp");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
