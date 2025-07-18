package org.example.service;

import org.example.model.TransactionType;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

/** Utility methods for reading user input from the console. */
public final class InputHelper {

    /**
     * Prompts the user for a string value using the provided scanner.
     *
     * @param scanner the scanner to read input from
     * @param prompt  the prompt text
     * @return the trimmed user input
     */
    public static String stringInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextLine().trim();
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    /**
     * Reads a numeric value and converts to a positive or negative amount based on the transaction type.
     *
     * @param scanner        the scanner to read input from
     * @param prompt         the prompt text
     * @param transactionType the transaction type identifier
     * @return the validated amount
     */
    public static BigDecimal bigDecimalInput(Scanner scanner, String prompt, String transactionType) {
        while (true) {
            System.out.print(prompt);
            try {
                BigDecimal amount = scanner.nextBigDecimal();
                return transactionType.equalsIgnoreCase(TransactionType.PAYMENT.getValue())
                        ? amount.negate()
                        : amount.abs();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Invalid amount. Please enter a valid number.");
            }
        }
    }
}