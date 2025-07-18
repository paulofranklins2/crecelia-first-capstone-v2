package org.example.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
public class Transaction {
    private String description;
    private String vendor;
    private String transactionType;
    private BigDecimal amount;
    private LocalDate transactionDate;
    private LocalTime transactionTime;

    public Transaction(LocalDate transactionDate, LocalTime transactionTime, String description, String vendor, String transactionType, BigDecimal amount) {
        this.transactionTime = transactionTime;
        this.transactionDate = transactionDate;
        this.description = description;
        this.vendor = vendor;
        this.transactionType = transactionType;
        this.amount = amount;
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
