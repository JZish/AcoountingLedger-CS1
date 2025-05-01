package com.pluralsight;

import java.util.Scanner;

public class AccountingLedgerApp {

    private static final Scanner scanner = new Scanner(System.in);      // CONSTANT SCANNER

    public static void main(String[] args) {

        while (true) {      // KEEP RUNNING

            homeScreen();

     }

    }

    public static void homeScreen() {
        // Welcome message
        System.out.println("➤".repeat(50));
        System.out.println("");
        System.out.println("◌".repeat(33) + " Welcome Home! " + "◌".repeat(33));
        System.out.println("");
        System.out.println("➤".repeat(50));
        System.out.println("");
        // Welcome message

        // OPTIONS
        System.out.println("What would you like to do today?\n");
        System.out.println("\uD808\uDC2D ".repeat(30));
        System.out.println("");
        System.out.println("D) Add Deposit");
        System.out.println("        ₪");
        System.out.println("P) Make Payment (Debit)");
        System.out.println("        ₪");
        System.out.println("L) View Ledger");
        System.out.println("        ₪");
        System.out.println("X) Exit\n");
        System.out.println("\uD808\uDC2D ".repeat(30));
        System.out.println("");
        System.out.print("SELECT「⌥=========⫸ ");
        // OPTIONS

        // SCANNER SELECT INPUT
        String select = scanner.nextLine().trim().toUpperCase();
        // SCANNER SELECT INPUT

        switch(select) {
            case "D":
                addDeposit();
                break;
            case "P":
                makePayment();
                break;
            case "L":
                viewLedger();
                break;
            case "X":
                exitApp();
                break;
            default:
                System.out.println("Sorry that's not an available option. \n");
        }

    }

    public static void addDeposit() {
    System.out.println("Let's Add Some Munyun!");
    }

    public static void makePayment() {
        System.out.println("");
    }

    public static void viewLedger() {
        System.out.println("");
    }

    public static void exitApp() {
        System.out.println("Have a great day!");
        System.exit(0
        );
    }

}
