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
        while (homeMenu) {
            System.out.println("---------Home---Menu--------");
            System.out.println("D) Add Deposit\nP) Make Payment (Debit)\nL) Leger\nX) Exit");
            String userInput = asking.nextLine();
            if (userInput.equalsIgnoreCase("D")) {

            } else if (userInput.equalsIgnoreCase("P")) {

            } else if (userInput.equalsIgnoreCase("L")) {

            } else if (userInput.equalsIgnoreCase("X")) {
                System.out.println("Exiting...");
                homeMenu = false;
            } else {
                System.out.println("⚠\uFE0F Invalid Option ⚠\uFE0F");
            }

        }

    }
}