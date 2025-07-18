package org.example.controller;

import org.example.model.TransactionType;

import java.util.Scanner;

import static org.example.service.InputHelper.stringInput;

/** Utility methods for console-based screens. */
public class ConsoleUtils {
    private final Scanner scanner;

    public ConsoleUtils(Scanner scanner) {
        this.scanner = scanner;
    }

    public void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public void pauseAndClearScreen() {
        stringInput(scanner, "\nPress ENTER to continue: ");
        clearConsole();
    }

    public void printTransactionScreenHeader(String type) {
        System.out.println("******************* " + TransactionType.fromValue(type)
                + " Transactions Screen *******************");
    }
}