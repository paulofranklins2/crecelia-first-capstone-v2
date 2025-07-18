package org.example;

import org.example.model.Transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TransactionHelper {
    static Scanner asking = new Scanner(System.in);
    public static void addTransaction(double amount, String description, String vendor) {


        String transactionFile = "transactions.csv";
        String date = Transaction.getTransactionDate();
        String time = Transaction.getTransactionTime();
        try {
            FileWriter fileW = new FileWriter(transactionFile, true);
            BufferedWriter bufWriter = new BufferedWriter(fileW);

            String text;
            text = String.format("%s | %s | %s | %s | %.2f\n",
                    date, time, description, vendor, amount);
            bufWriter.write(text);


            bufWriter.close();
        }

        catch (IOException ex) {
            System.out.println("ERROR:  An unexpected error occurred");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

        }
    public static void addDeposit() {
        System.out.println("Enter deposit amount: ");
        double depositAmount = asking.nextDouble();
        asking.nextLine();
        System.out.println("Enter description: ");
        String description = asking.nextLine();
        System.out.println("Enter vendor: ");
        String vendor = asking.nextLine();

        addTransaction(depositAmount, description, vendor);

    }
    public static void makePayment() {
        System.out.println("Enter payment amount: ");
        double paymentAmount = asking.nextDouble();
        asking.nextLine();
        System.out.println("Enter description: ");
        String description = asking.nextLine();
        System.out.println("Enter vendor: ");
        String vendor = asking.nextLine();

        addTransaction(-paymentAmount, description, vendor);

    }
}
