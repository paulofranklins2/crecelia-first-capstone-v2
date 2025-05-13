package org.example;

import java.util.Scanner;

public class Main {
    static Scanner asking = new Scanner(System.in);
    public static void main(String[] args) {
        /*
0 - tools -- Scanner, filewrite(buffwrite), fileread(buffread),arraylist
1 - start by creating the loop for the main menu
2 - in the main menu loop create additional loops for the submenus like the ledger menu
3 - use scanner to grab user inputs for the menu
4 - log transactions into the csv file. for this make a method in the TransactionHelper class to grab user input
         and format it and create a transaction object to add to a list. write items from list into the file
5 - log account balance and time stamps of transactions
6 - for now create a rough code in main. split it after
 */
        boolean homeMenu = true;
        boolean ledgerMenu = false;
        boolean reportsMenu = false;
        while (homeMenu) {
            System.out.println("----------Home---Menu----------");
            System.out.println("D) Add Deposit\nP) Make Payment (Debit)\nL) Leger\nX) Exit");
            String userInput = asking.nextLine();
            if (userInput.equalsIgnoreCase("D")) {
                TransactionHelper.addDeposit();
            } else if (userInput.equalsIgnoreCase("P")) {
                TransactionHelper.makePayment();
            } else if (userInput.equalsIgnoreCase("L")) {
                ledgerMenu = true;
                homeMenu = false;
            } else if (userInput.equalsIgnoreCase("X")) {
                System.out.println("Exiting...");
                homeMenu = false;
            } else {
                System.out.println("⚠\uFE0F Invalid Option ⚠\uFE0F");
            }
            while (ledgerMenu) {
                System.out.println("**********Ledger---Menu**********");
                System.out.println("A) All\nD) Deposits\nP) Payments\nR) Reports\nH) Home");
                userInput = asking.nextLine();
                if (userInput.equalsIgnoreCase("A")) {
                    Ledger.displayAllEntries();
                } else if (userInput.equalsIgnoreCase("D")) {
                    Ledger.displayDeposits();
                } else if (userInput.equalsIgnoreCase("P")) {
                    Ledger.displayPayments();
                } else if (userInput.equalsIgnoreCase("R")) {
                    ledgerMenu = false;
                    reportsMenu = true;
                } else if (userInput.equalsIgnoreCase("H")) {
                    ledgerMenu = false;
                    homeMenu = true;

                } else {
                    System.out.println("⚠\uFE0F Invalid Option ⚠\uFE0F");
                }
                while (reportsMenu) {
                    System.out.println("==========Reports---Menu==========");
                    System.out.println("Enter x to exit");
                    System.out.println("1) Month To Date\n2) Previous Month\n3) Year To Date\n4) Previous Year\n5) Search by Vendor\n0) Back");
                    userInput = asking.nextLine();
                    if (userInput.equalsIgnoreCase("1")) {
                        Reports.monthToDate();
                    } else if (userInput.equalsIgnoreCase("2")) {
                        Reports.previousMonth();
                    } else if (userInput.equalsIgnoreCase("3")) {
                        Reports.yearToDate();
                    } else if (userInput.equalsIgnoreCase("4")) {
                        Reports.previousYear();
                    } else if (userInput.equalsIgnoreCase("5")) {
                        Reports.searchByVendor();
                    } else if (userInput.equalsIgnoreCase("0")) {
                        reportsMenu = false;
                        ledgerMenu = true;
                    } else if (userInput.equalsIgnoreCase("x")) {
                        System.out.println("Exiting...");
                        return;
                    } else {
                        System.out.println("⚠\uFE0F Invalid Option ⚠\uFE0F");
                    }

        }


        }


        }

    }
}