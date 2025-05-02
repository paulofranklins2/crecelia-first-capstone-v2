package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ledger {

    public static void displayAllEntries() {
        String transactionFile = "transactions.csv";
        try {
            FileReader fileReader = new FileReader(transactionFile);
            BufferedReader reader = new BufferedReader(fileReader);
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {

                System.out.println(line);
            }
        } catch (IOException e) {
            //print an error message if the file can't be read
            System.out.println("Something went wrong while reading the file");
            e.printStackTrace();
        }

    }
    public static void displayDeposits() {
        String transactionFile = "transactions.csv";
        try {
            FileReader fileReader = new FileReader(transactionFile);
            BufferedReader reader = new BufferedReader(fileReader);
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    double amount = Double.parseDouble(parts[4]);
                    if (amount > 0) {
                        System.out.println(line);
                    }
                }

            }
        } catch (IOException e) {
            //print an error message if the file can't be read
            System.out.println("Something went wrong while reading the file");
            e.printStackTrace();
        }

    }
    public static void displayPayments() {
        String transactionFile = "transactions.csv";
        try {
            FileReader fileReader = new FileReader(transactionFile);
            BufferedReader reader = new BufferedReader(fileReader);
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    double amount = Double.parseDouble(parts[4]);
                    if (amount < 0) {
                        System.out.println(line);
                    }
                }

            }
        } catch (IOException e) {
            //print an error message if the file can't be read
            System.out.println("Something went wrong while reading the file");
            e.printStackTrace();
        }

    }
}
