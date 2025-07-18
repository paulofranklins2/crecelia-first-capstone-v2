package org.example.repository;

import org.example.model.Transaction;

import java.io.FileWriter;

public class TransactionRepository {
    private static final String FILE_PATH = "src/main/resources/transactions.csv";

    /**
     * Appends the given transaction to the CSV file.
     *
     * @param transaction transaction to persist
     */
    public void save(Transaction transaction) {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH, true)) {
            fileWriter.write(transaction.saveTransactionToString().toUpperCase());
        } catch (Exception e) {
            System.out.println("Error while trying to log transaction to file: " + e.getMessage());
        }
    }
}
