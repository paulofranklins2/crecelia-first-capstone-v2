package org.example.repository;

import org.example.model.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TransactionRepository {
    private static final String FILE_PATH = "Accounting-Ledger-Application/src/main/resources/transactions.csv";

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

    /**
     * Reads all transactions from the CSV file.
     *
     * @return list of transactions
     */
    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] split = line.split("\\|");
                    if (split.length != 6) {
                        System.out.println("Skipping malformed line: " + line);
                        continue;
                    }
                    LocalDate date = LocalDate.parse(split[0]);
                    LocalTime time = LocalTime.parse(split[1]);
                    String description = split[2];
                    String vendor = split[3];
                    String type = split[4];
                    BigDecimal amount = new BigDecimal(split[5]);
                    transactions.add(new Transaction(date, time, description, vendor, type, amount));
                } catch (Exception ex) {
                    System.out.println("Error parsing line, skipping: " + line);
                }
            }
            transactions.sort(Comparator.comparing(Transaction::getTransactionDate)
                    .thenComparing(Transaction::getTransactionTime)
                    .reversed());
        } catch (Exception e) {
            System.out.println("Error while trying to read transactions from file: " + e.getMessage());
        }
        return transactions;
    }
}
