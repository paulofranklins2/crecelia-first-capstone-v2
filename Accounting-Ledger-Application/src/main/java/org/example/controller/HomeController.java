package org.example.controller;

import lombok.Setter;
import org.example.service.InputHelper;
import org.example.service.TransactionService;

import java.util.Scanner;

public class HomeController {

    private final ConsoleUtils consoleUtils;
    private final TransactionService transactionService;
    @Setter
    private LedgerController ledgerController;
    private final Scanner scanner;

    public HomeController(ConsoleUtils consoleUtils,
                          TransactionService transactionService,
                          Scanner scanner) {
        this.consoleUtils = consoleUtils;
        this.transactionService = transactionService;
        this.scanner = scanner;
    }

    private String promptMainMenuOption() {
        consoleUtils.clearConsole();
        System.out.println("******************* Main Screen *******************");
        System.out.println("[D] Add Deposit");
        System.out.println("[P] Make Payment");
        System.out.println("[L] Ledger");
        System.out.println("[X] Exit");

        try {
            return InputHelper.stringInput(scanner, "Select an option: ").trim().toUpperCase();
        } finally {
            consoleUtils.clearConsole();
        }
    }

    public void handleMainMenu() {
        while (true) {
            var option = promptMainMenuOption();
            switch (option) {
                case "D", "P" -> transactionService.createTransactionFromInput(option);
                case "L" -> ledgerController.handleLedgerMenu();
                case "X" -> {
                    System.out.println("Thank you for using accounting ledger app");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    public void start() {
        handleMainMenu();
    }
}
