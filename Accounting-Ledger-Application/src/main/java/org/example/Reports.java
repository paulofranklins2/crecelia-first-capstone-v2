package org.example;

import org.example.model.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Reports {
    static Scanner asking = new Scanner(System.in);
    public static List<Transaction> readFromCSV() {
        List<Transaction> transactionList = new ArrayList<>();
        String transactionFile = "transactions.csv";
        try {
            FileReader fileReader = new FileReader(transactionFile);
            BufferedReader reader = new BufferedReader(fileReader);
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String date = parts[0];
                    String time = parts[1];
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);

                    Transaction transaction = new Transaction(date, time, description, vendor, amount);
                    transactionList.add(transaction);

                }

            }
        } catch (IOException e) {
            //print an error message if the file can't be read
            System.out.println("Something went wrong while reading the file");
            e.printStackTrace();
        }

        return transactionList;
    }
    public static void monthToDate() {

        List<Transaction> transactions = readFromCSV();
        LocalDate now = LocalDate.now();
        System.out.println("Month To Date Report: ");
        boolean found = false;

        for (Transaction t : transactions) {
            LocalDate transactionDate = LocalDate.parse(t.getTransactionDate());

            if (transactionDate.getMonthValue() == now.getMonthValue() && transactionDate
                    .getYear() == now.getYear()) {
                System.out.println(t);

                found = true;
            }

        }
        if (!found) {
            System.out.println("No transactions found for month to date");
        }
    }
    public static void previousMonth() {
        List<Transaction> transactions = readFromCSV();
        LocalDate now = LocalDate.now();
        boolean found = false;
        System.out.println("Previous Month Report: ");
        for (Transaction t : transactions) {
            LocalDate transactionDate = LocalDate.parse(t.getTransactionDate());

            if (transactionDate.getMonthValue() == now.getMonthValue() - 1 && transactionDate.getYear() == now.getYear()) {
                System.out.println(t);
                found = true;
            }


        }
        if (!found) {
            System.out.println("No transactions found for previous month");
        }
    }
    public static void yearToDate() {
        List<Transaction> transactions = readFromCSV();
        LocalDate now = LocalDate.now();
        boolean found = false;
        for (Transaction t : transactions) {
            LocalDate transactionDate = LocalDate.parse(t.getTransactionDate());

            if (transactionDate.getYear() == now.getYear()) {
                System.out.println(t);
                found = true;
            }

        }
        if (!found) {
            System.out.println("No transactions found for year to date");
        }
    }
    public static void previousYear() {
        List<Transaction> transactions = readFromCSV();
        LocalDate now = LocalDate.now();
        boolean found = false;

        for (Transaction t : transactions) {
            LocalDate transactionDate = LocalDate.parse(t.getTransactionDate());


            if (transactionDate.getYear() == now.getYear() - 1) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions found for previous year");
        }
    }

    public static void searchByVendor() {
            List<Transaction> transactions = readFromCSV();

            System.out.println("Enter vendor name to search: ");
            String vendorNameSearch = asking.nextLine().trim();

            boolean found = false;
            for (Transaction t : transactions) {
                if (t.getVendor().equalsIgnoreCase(vendorNameSearch)) {
                    System.out.println(t);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No transactions found for vendor: " + vendorNameSearch);
            }


    }
}
