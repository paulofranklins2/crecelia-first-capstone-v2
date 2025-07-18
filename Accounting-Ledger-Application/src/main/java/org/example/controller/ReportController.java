package org.example.controller;

import lombok.Setter;
import org.example.service.InputHelper;
import org.example.service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class ReportController {

    private final ConsoleUtils consoleUtils;
    private final TransactionService transactionService;
    @Setter
    private LedgerController ledgerController;
    @Setter
    private HomeController homeController;
    private final Scanner scanner;

    public ReportController(ConsoleUtils consoleUtils,
                            TransactionService transactionService,
                            Scanner scanner) {
        this.consoleUtils = consoleUtils;
        this.transactionService = transactionService;
        this.scanner = scanner;
    }

    private String promptReportMenuOption() {
        System.out.println("******************* Report Screen *******************");
        System.out.println("[1] Month To Date");
        System.out.println("[2] Previous Month");
        System.out.println("[3] Year To Date");
        System.out.println("[4] Previous Year");
        System.out.println("[5] Search by Vendor");
        System.out.println("[6] Custom Search");
        System.out.println("[0] Back - Go back to the ledge page");
        System.out.println("[H] Home - Go back to the home page");

        try {
            return InputHelper.stringInput(scanner, "Select a report option: ").trim().toUpperCase();
        } finally {
            consoleUtils.clearConsole();
        }
    }

    public void handleReportMenu() {
        while (true) {
            var option = promptReportMenuOption();
            switch (option) {
                case "1" -> {
                    System.out.println("******************* Month To Date Screen *******************");
                    printMonthToDate();
                }
                case "2" -> {
                    System.out.println("******************* Previous Month To Date Screen ******************");
                    printPreviousMonth();
                }
                case "3" -> {
                    System.out.println("******************* Year To Date Screen ******************");
                    printYearToDate();
                }
                case "4" -> {
                    System.out.println("******************* Previous Year To Date Screen ******************");
                    printPreviousYear();
                }
                case "5" -> {
                    System.out.println("******************* Search by Vendor Screen ******************");
                    handleVendorSearch(InputHelper.stringInput(scanner, "Enter vendor name: "));
                }
                case "6" -> {
                    System.out.println("******************* Custom Search Screen ******************");
                    handleCustomSearch();
                }
                case "0" -> ledgerController.handleLedgerMenu();
                case "H" -> homeController.handleMainMenu();
                default -> System.out.println("Select a valid option");
            }
        }
    }

    public void printMonthToDate() {
        var now = LocalDate.now();
        var firstDayOfMonth = now.withDayOfMonth(1);
        displayTransactionsInRange(firstDayOfMonth, now);
    }

    public void printPreviousMonth() {
        var today = LocalDate.now();
        var first = today.minusMonths(1).withDayOfMonth(1);
        var last = today.withDayOfMonth(1).minusDays(1);
        displayTransactionsInRange(first, last);
    }

    public void printYearToDate() {
        var first = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        var today = LocalDate.now();
        displayTransactionsInRange(first, today);
    }

    public void printPreviousYear() {
        var lastYear = LocalDate.now().getYear() - 1;
        var first = LocalDate.of(lastYear, 1, 1);
        var last = LocalDate.of(lastYear, 12, 31);
        displayTransactionsInRange(first, last);
    }

    public void handleVendorSearch(String vendor) {
        for (var transaction : transactionService.getTransactions()) {
            if (transaction.getVendor().equalsIgnoreCase(vendor))
                System.out.println(transaction);
        }
        consoleUtils.pauseAndClearScreen();
    }

    public void handleCustomSearch() {
        var startInput = InputHelper.stringInput(scanner, "Enter start date (yyyy-mm-dd) or leave blank: ").trim();
        var endInput = InputHelper.stringInput(scanner, "Enter end date (yyyy-mm-dd) or leave blank: ").trim();
        var description = InputHelper.stringInput(scanner, "Enter description or leave blank: ").trim();
        var vendor = InputHelper.stringInput(scanner, "Enter vendor or leave blank: ").trim();
        var amountInput = InputHelper.stringInput(scanner, "Enter amount or leave blank: ").trim();

        LocalDate startDate = null;
        LocalDate endDate = null;
        Double amount = null;

        try {
            if (!startInput.isEmpty()) {
                startDate = LocalDate.parse(startInput);
            }
            if (!endInput.isEmpty()) {
                endDate = LocalDate.parse(endInput);
            }
            if (!amountInput.isEmpty()) {
                amount = Double.parseDouble(amountInput);
            }
        } catch (Exception e) {
            System.out.println("Invalid input format. Please try again.");
            return;
        }

        for (var transaction : transactionService.getTransactions()) {
            var date = transaction.getTransactionDate();
            var matches = true;

            if (startDate != null && date.isBefore(startDate)) matches = false;
            if (endDate != null && date.isAfter(endDate)) matches = false;
            if (!description.isEmpty() && !transaction.getDescription().toLowerCase().contains(description.toLowerCase()))
                matches = false;
            if (!vendor.isEmpty() && !transaction.getVendor().equalsIgnoreCase(vendor)) matches = false;
            if (amount != null && transaction.getAmount().compareTo(BigDecimal.valueOf(amount)) != 0) matches = false;

            if (matches) {
                System.out.println(transaction);
            }
        }
        consoleUtils.pauseAndClearScreen();
    }

    private void displayTransactionsInRange(LocalDate start, LocalDate end) {
        for (var transaction : transactionService.getTransactions()) {
            var date = transaction.getTransactionDate();
            if (!date.isBefore(start) && !date.isAfter(end)) {
                System.out.println(transaction);
            }
        }
        consoleUtils.pauseAndClearScreen();
    }

}
