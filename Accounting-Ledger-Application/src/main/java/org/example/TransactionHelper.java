package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TransactionHelper {
    public static void writeDeposite() {
        try {
            FileWriter fileW = new FileWriter("transactions.csv");
            BufferedWriter bufWriter = new BufferedWriter(fileW);


            String text;
            text = String.format();
            bufWriter.write(text);


            bufWriter.close();
        }

        catch (IOException ex) {
            System.out.println("ERROR:  An unexpected error occurred");
            ex.getStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
