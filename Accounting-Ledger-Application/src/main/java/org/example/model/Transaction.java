package org.example.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
public class Transaction {
    private String transactionDate;
    private String transactionTime;
    private String transactionType;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.transactionDate = date;
        this.transactionTime = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public static String getTransactionDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(df);
    }

    public static String getTransactionTime() {
        LocalTime time = LocalTime.now();
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
        return time.format(tf);
    }

    public String toString() {
        return String.format("%s | %s | %s | %s | %.2f\n",
                transactionDate, transactionTime, description, vendor, amount);
    }

    public String saveTransactionToString() {
        return this.getTransactionDate() + "|" +
                this.getTransactionTime() + "|" +
                this.getDescription() + "|" +
                this.getVendor() + "|" +
                this.getTransactionType() + "|" +
                this.getAmount() + "\n";
    }
}
