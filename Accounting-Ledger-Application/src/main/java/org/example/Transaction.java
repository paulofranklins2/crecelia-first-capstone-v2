package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String description;
    private String vendor;
    private double amount;

    public Transaction(String date, String time, String description, String vendor, double amount) {

        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public Transaction() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public static String getDate() {

        LocalDate date = LocalDate.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(df);
    }
    public void setDate(String date) {

    }
    public static String getTime() {
        LocalTime time = LocalTime.now();
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
        return time.format(tf);
    }
    public void setTime(String time) {

    }
}
