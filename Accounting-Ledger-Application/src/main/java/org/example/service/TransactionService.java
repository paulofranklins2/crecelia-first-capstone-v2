package org.example.service;

import org.example.controller.ConsoleUtils;
import org.example.model.Transaction;
import org.example.model.TransactionType;
import org.example.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Business logic related to transactions.
 */
public class TransactionService {

    private final TransactionRepository repository;
    private final ConsoleUtils consoleUtils;
    private final Scanner scanner;

    public TransactionService(TransactionRepository repository, ConsoleUtils consoleUtils, Scanner scanner) {
        this.repository = repository;
        this.consoleUtils = consoleUtils;
        this.scanner = scanner;
    }

    public void saveTransaction(Transaction transaction, boolean isUi) {
        repository.save(transaction);
        System.out.println("Saved: " + transaction.saveTransactionToString().toUpperCase().trim());
        if (!isUi) {
            consoleUtils.pauseAndClearScreen();
        }
    }

    public List<Transaction> getTransactions() {
        return repository.findAll();
    }

    public void displayTransactionsByType(String option) {
        consoleUtils.printTransactionScreenHeader(option);
        getTransactions().forEach(transaction -> {
            if (option.equals(TransactionType.PAYMENT.getValue()) && transaction.getTransactionType().equals(TransactionType.PAYMENT.getValue())) {
                System.out.println(transaction);
            }
            if (option.equals(TransactionType.DEPOSIT.getValue()) && transaction.getTransactionType().equals(TransactionType.DEPOSIT.getValue())) {
                System.out.println(transaction);
            }
            if (option.equals(TransactionType.ALL.getValue())) {
                System.out.println(transaction);
            }
        });
        consoleUtils.pauseAndClearScreen();
    }

    public void createTransactionFromInput(String option) {
        consoleUtils.printTransactionScreenHeader(option);
        String description = InputHelper.stringInput(scanner, "Enter Description: ");
        String vendor = InputHelper.stringInput(scanner, "Enter Vendor: ");
        BigDecimal amount = InputHelper.bigDecimalInput(scanner, "Enter Amount: ", option);
        scanner.nextLine();
        LocalDate date = promptForDate();
        LocalTime time = promptForTime();
        saveTransaction(new Transaction(date, time, description, vendor, option, amount), false);
        consoleUtils.clearConsole();
    }

    private LocalTime promptForTime() {
        while (true) {
            try {
                if (InputHelper.stringInput(scanner, "Auto Time? (Y/N): ").equalsIgnoreCase("Y")) {
                    return LocalTime.now();
                }
                String input = InputHelper.stringInput(scanner, "Enter time (HH:MM): ");
                return LocalTime.parse(input, DateTimeFormatter.ofPattern("HH:mm"))
                        .plusSeconds(LocalTime.now().getSecond());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please use HH:MM format.");
            }
        }
    }

    private LocalDate promptForDate() {
        while (true) {
            try {
                if (InputHelper.stringInput(scanner, "Auto Date? (Y/N): ").equalsIgnoreCase("Y")) {
                    return LocalDate.now();
                }
                String input = InputHelper.stringInput(scanner, "Enter date (YYYY-MM-DD): ");
                return LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
            }
        }
    }
}