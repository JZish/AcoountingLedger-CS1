package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        DateTimeFormatter DTS = DateTimeFormatter.ofPattern("H:mm:ss");

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
            FileWriter addDeposit = new FileWriter("src/main/resources/AccountingLedgerTransactionInfo.csv", true);
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
            FileWriter addPayment = new FileWriter("src/main/resources/AccountingLedgerTransactionInfo.csv", true);
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
            System.out.println("A) Display All Transactions");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Go Back Home");

            String input = scanner.nextLine().toUpperCase().trim();

            switch (input) {
                case "A":
                    ArrayList<Transactions> entries = readTransactions();
                    entriesDisplay(entries);
                    break;
                case "D":
                    ArrayList<Transactions> deposits = readTransactions();
                    displayDeposits(deposits);
                    break;
                case "P":
                    ArrayList<Transactions> payments = readTransactions();
                    displayPayments();
                    break;
                case "R":
                    displayReports();
                    break;
                case "H":
                    System.out.println("Let's go back home!");
                    break;
                default:
                    System.out.println("Sorry that's not an available option.");
            }

        }

        public static void exitApp () {
            System.out.println("Have a great day!");
            System.exit(0);
        }

    public static ArrayList<Transactions> readTransactions() {

        ArrayList<Transactions> transactions = new ArrayList<>();

        try {
            FileReader read = new FileReader("src/main/resources/AccountingLedgerTransactionInfo.csv");
            BufferedReader bReader = new BufferedReader(read);

            String row;
            boolean topper = true; // ignore top header

            while ((row = bReader.readLine()) != null) {
                if (topper) {
                    topper = false; // Skip the header row
                    continue;
                }

                String[] parts = row.split("\\|");
                if (parts.length == 5) {
                    try {
                        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate date = LocalDate.parse(parts[0], dateFormat);

                        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("H:mm:ss");
                        LocalTime time = LocalTime.parse(parts[1], timeFormat);

                        String description = parts[2];
                        String vendor = parts[3];
                        double amount = Double.parseDouble(parts[4].replace("$", "").replace("+", "").replace("-", ""));

                        if (parts[4].contains("-")) amount *= -1;

                        Transactions transaction = new Transactions(date, time, description, vendor, amount);
                        transactions.add(transaction);
                    } catch (Exception e) {
                        System.out.println("Error parsing transaction line: " + row);
                        e.printStackTrace();
                    }
                }
            }

            bReader.close();

        } catch (Exception e) {
            System.out.println("❌ Error loading transactions: " + e.getMessage());
        }
        return transactions;
    }

    public static void entriesDisplay (ArrayList<Transactions> transactions) {

        for (Transactions t : transactions) {
            System.out.printf("%s | %s | %s | %s | $%.2f\n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
        }

        System.out.println("Return to Ledger Screen?\n Y / N");

        Scanner scanner = new Scanner(System.in);
        String reply = scanner.nextLine().trim().toUpperCase();

        if (reply.equals("Y")) {
            viewLedger();
        } else {
            homeScreen();
        }
    }

    public static void displayDeposits(ArrayList<Transactions> transactions) {

        for (Transactions t : transactions) {
            if (t.getAmount() > 0) {
                System.out.printf("%s | %s | %s | %s | $%.2f\n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }

        System.out.println("\nReturn to Ledger Screen?\nY / N");

        Scanner scanner = new Scanner(System.in);
        String reply = scanner.nextLine().trim().toUpperCase();

        if (reply.equals("Y")) {
            viewLedger();
        } else {
            homeScreen(); // optional
        }
    }

    public static void displayPayments() {

    }

    public static void displayReports() {

    }






}

