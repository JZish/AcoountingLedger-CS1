package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AccountingLedgerApp {

    private static final Scanner scanner = new Scanner(System.in);      // CONSTANT SCANNER

    public static void main(String[] args) {

        boolean active = true;

        while (active) {      // KEEP RUNNING
            homeScreen();   // HOME
     }

    }

    public static void homeScreen() {
        // Welcome message
        System.out.println("➤".repeat(50));
        System.out.println("");
        System.out.println("◌".repeat(31) + " Welcome Home! " + "◌".repeat(35));
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
        String input = scanner.nextLine().trim().toUpperCase();
        // SCANNER SELECT INPUT

        // OPTIONS
        switch(input) {
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

        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter DTS = DateTimeFormatter.ofPattern("HH:mm:ss");

        System.out.println("Welcome to your deposit screen!");

        System.out.print("Enter a description for your deposit: ");
        String description = scanner.nextLine();

        System.out.print("Vendor for your deposit: ");
        String vendor = scanner.nextLine();

        System.out.print("How much would you like to deposit today? $");
        double deposit = Double.parseDouble(scanner.nextLine());

        System.out.printf("%s|%s|%s|%s|+$%.2f\n", currentDate, currentTime.format(DTS), description, vendor, deposit);

        String row = String.format("%s|%s|%s|%s|+$%.2f", currentDate, currentTime.format(DTS), description, vendor, deposit);

        try {
            FileWriter addDeposit = new FileWriter("src/main/resources/ACCOUNTING LEDGER TRANSACTION INFO.csv", true);
            BufferedWriter buffed = new BufferedWriter(addDeposit);
            buffed.write(row);
            buffed.newLine();
            System.out.println("✅ Deposit Processed Successfully! ✅");
            buffed.close();
        } catch (Exception e) {
            System.out.println("❌ Deposit Processed Successfully! ❌");
            e.printStackTrace();
        }

    }

    public static void makePayment() {

        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter DTS = DateTimeFormatter.ofPattern("HH:mm:ss");

        System.out.println("Welcome to your payment screen!");

        System.out.print("Enter a description for your payment: ");
        String description = scanner.nextLine();

        System.out.print("Payment going to: ");
        String recipient = scanner.nextLine();

        System.out.print("Payment amount: $");
        double pay = Double.parseDouble(scanner.nextLine());

        System.out.printf("%s|%s|%s|%s|-$%.2f\n", currentDate, currentTime.format(DTS), description, recipient, pay);

        String row = String.format("%s|%s|%s|%s|-$%.2f", currentDate, currentTime.format(DTS), description, recipient, pay);

        try {
            FileWriter addPayment = new FileWriter("src/main/resources/ACCOUNTING LEDGER TRANSACTION INFO.csv", true);
            BufferedWriter buffed = new BufferedWriter(addPayment);
            buffed.write(row);
            buffed.newLine();
            buffed.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public static void viewLedger () {
            System.out.println("Welcome to the ledger");

        }

        public static void exitApp () {
            System.out.println("Have a great day!");
            System.exit(0);
        }
}

