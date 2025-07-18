package org.example.controller;

import lombok.Setter;
import org.example.service.TransactionService;

import java.util.Scanner;

public class LedgerController {

    private final ConsoleUtils consoleUtils;
    private final TransactionService transactionService;
    @Setter
    private ReportController reportController;
    @Setter
    private HomeController homeController;
    private final Scanner scanner;

    public LedgerController(ConsoleUtils consoleUtils,
                            TransactionService transactionService,
                            Scanner scanner) {
        this.consoleUtils = consoleUtils;
        this.transactionService = transactionService;
        this.scanner = scanner;
    }

    private String promptLedgerMenuOption() {
        System.out.println("******************* Ledger Screen *******************");
        System.out.println("[A] All - Display all entries");
        System.out.println("[D] Deposits - Display only deposit entries");
        System.out.println("[P] Payments - Display only payment entries");
        System.out.println("[R] Reports - View reports and search");
        System.out.println("[H] Home - Return to home screen");

        try {
            System.out.print("Select an option: ");
            return scanner.nextLine().trim().toUpperCase();
        } finally {
            consoleUtils.clearConsole();
        }
    }

    public void handleLedgerMenu() {
        while (true) {
            var option = promptLedgerMenuOption();
            switch (option) {
                case "A", "D", "P" -> transactionService.displayTransactionsByType(option);
                case "R" -> reportController.handleReportMenu();
                case "H" -> {
                    homeController.handleMainMenu();
                    return;
                }
                default -> System.out.println("Select an option");
            }
        }
    }
}
