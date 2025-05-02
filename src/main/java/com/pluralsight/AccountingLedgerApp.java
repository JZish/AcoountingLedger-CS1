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
        System.out.println("✾ What would you like to do today? ✾\n");
        System.out.println("\uD808\uDC2D ".repeat(30));
        System.out.println("");
        System.out.println("D) \uD83D\uDE68 Add Deposit \uD83D\uDE68");
        System.out.println("        ₪");
        System.out.println("P) ⚖ Make Payment (Debit) ⚖");
        System.out.println("        ₪");
        System.out.println("L) 〚 View Ledger 〛");
        System.out.println("        ₪");
        System.out.println("X) \uD808\uDD8E Exit \uD808\uDD8E\n");
        System.out.println("\uD808\uDC2D ".repeat(30));
        System.out.println("");
        System.out.print("SELECT「⌥=========⫸ ");
        // OPTIONS

        // SCANNER SELECT INPUT
        String input = scanner.nextLine().trim().toUpperCase();
        // SCANNER SELECT INPUT

        // OPTIONS
        switch (input) {
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
        // Deposit Method
    public static void addDeposit() {
        // Date Tools
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter DTS = DateTimeFormatter.ofPattern("H:mm:ss"); // Format to match requirements
        // user input
        System.out.println("Welcome to your deposit screen!");

        System.out.print("Enter a description for your deposit: ");
        String description = scanner.nextLine();

        System.out.print("Vendor for your deposit: ");
        String vendor = scanner.nextLine();

        System.out.print("How much would you like to deposit today? $");
        double deposit = Double.parseDouble(scanner.nextLine());
        // print format of final deposit
        System.out.printf("%s|%s|%s|%s|+$%.2f\n", currentDate, currentTime.format(DTS), description, vendor, deposit);

        String row = String.format("%s|%s|%s|%s|+$%.2f", currentDate, currentTime.format(DTS), description, vendor, deposit);
        // Append information to CSV
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
            // Date Tools
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter DTS = DateTimeFormatter.ofPattern("HH:mm:ss");
            //User Input
        System.out.println("Welcome to your payment screen!");

        System.out.print("Enter a description for your payment: ");
        String description = scanner.nextLine();

        System.out.print("Payment going to: ");
        String recipient = scanner.nextLine();

        System.out.print("Payment amount: $");
        double pay = Double.parseDouble(scanner.nextLine());
            // Final Parse
        System.out.printf("%s|%s|%s|%s|-$%.2f\n", currentDate, currentTime.format(DTS), description, recipient, pay);

        String row = String.format("%s|%s|%s|%s|-$%.2f", currentDate, currentTime.format(DTS), description, recipient, pay);
            // Append new row to csv
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
        // Ledger Screen
    public static void viewLedger() {
        System.out.println("Welcome to the ledger");
        System.out.println("A) Display All Transactions");
        System.out.println("D) Deposits");
        System.out.println("P) Payments");
        System.out.println("R) Reports");
        System.out.println("H) Go Back Home");

        String input = scanner.nextLine().toUpperCase().trim();
        // Using CSV INFO
        ArrayList<Transactions> transactions = readTransactions();

        switch (input) {
            case "A":
                entriesDisplay(transactions);
                break;
            case "D":
                displayDeposits(transactions);
                break;
            case "P":
                displayPayments(transactions);
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
        // EXIT METHOD
    public static void exitApp() {
        System.out.println("Have a great day!");
        System.exit(0);
    }
        // READING METHOD
    public static ArrayList<Transactions> readTransactions() {
            // DECLARE LIST
        ArrayList<Transactions> transactions = new ArrayList<>();
        // TRY AND CATCH FOR READERS
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
                    // SPLIT AT PIPE
                String[] parts = row.split("\\|");
                if (parts.length == 5) {    // 5 SECTIONS TO AN ENTRY
                    try {
                        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");   // FORMAT AND PARSE EACH INDEX SECTION (DATE)
                        LocalDate date = LocalDate.parse(parts[0], dateFormat);

                        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("H:mm:ss");      // TIME FORMAT
                        LocalTime time = LocalTime.parse(parts[1], timeFormat);

                        String description = parts[2];  // DECLARE DESCRIPTION SECTION
                        String vendor = parts[3];       // VENDOR SECTION
                        double amount = Double.parseDouble(parts[4].replace("$", "").replace("+", "").replace("-", ""));
                            // PARSE CURRENCY FOR FLOAT VALUE
                        if (parts[4].contains("-")) amount *= -1; // MAKES NEGATIVE DOUBLE VALUE

                        Transactions transaction = new Transactions(date, time, description, vendor, amount);
                        transactions.add(transaction);
                    } catch (Exception e) {
                        System.out.println("Error parsing transaction line: " + row);
                        e.printStackTrace();
                    }
                }
            }

            bReader.close();    // CLOSE READER

        } catch (Exception e) {
            System.out.println("❌ Error loading transactions: " + e.getMessage());
        }
        return transactions;
    }

    public static void entriesDisplay(ArrayList<Transactions> transactions) {
        // ACCESS CSV FOR ENTRIES
        for (Transactions t : transactions) {
            System.out.printf("%s | %s | %s | %s | $%.2f\n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
        }
        //OPTIONAL RETURN
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
        // ACCESS CSV FOR ENTRIES
        for (Transactions t : transactions) {
            if (t.getAmount() > 0) {
                System.out.printf("%s | %s | %s | %s | $%.2f\n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
        //OPTIONAL RETURN
        System.out.println("Return to Ledger Screen?\nY / N");

        Scanner scanner = new Scanner(System.in);
        String reply = scanner.nextLine().trim().toUpperCase();

        if (reply.equals("Y")) {
            viewLedger();
        } else {
            homeScreen();
        }
    }

    public static void displayPayments(ArrayList<Transactions> transactions) {
        //CSV ACCESS
        for (Transactions t : transactions) {
            if (t.getAmount() < 0) {
                System.out.printf("%s | %s | %s | %s | $%.2f\n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }

        System.out.println("Return to Ledger Screen?\nY / N");

        Scanner scanner = new Scanner(System.in);
        String reply = scanner.nextLine().trim().toUpperCase();

        if (reply.equals("Y")) {
            viewLedger();
        } else {
            homeScreen();
        }
    }

    public static void displayReports() {

        ArrayList<Transactions> transactions = readTransactions();
        // REPORTS SCREEN
        while (true) {
            System.out.println("┋".repeat(19) + "\uD800\uDF42 Welcome to your reports menu! \uD800\uDF42" + "┋".repeat(21));
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");

            System.out.print("SELECT「⌥=========⫸ ");
            String answer = scanner.nextLine().trim();
            // OPTIONS SELECT
            switch (answer) {
                case "1":
                    monthToDate(transactions);
                    break;
                case "2":
                    previousMonth(transactions);
                    break;
                case "3":
                    yearToDate(transactions);
                    break;
                case "4":
                    previousYear(transactions);
                    break;
                case "5":
                    searchByVendor(transactions);
                    break;
                case "0":
                    System.out.println("Returning...");
                    try {
                        Thread.sleep(2000);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                default:
                    System.out.println("Sorry that's not an option. Please try again.");
            }

        }


    }

    public static void monthToDate(ArrayList<Transactions> transactions) {
            // DATE FORMAT
        LocalDate present = LocalDate.now();
        LocalDate firstOfMonth = present.withDayOfMonth(1);
            // IF TRANSACTION IS ON OR AFTER THE FIRST OF MONTH
        for (Transactions t : transactions) {
            if (!t.getDate().isBefore(firstOfMonth)) {
                System.out.printf("%s | %s | %s | %s | $%.2f\n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
    }

    public static void previousMonth(ArrayList<Transactions> transactions) {
        // DATE FORMAT
        LocalDate present = LocalDate.now();
        LocalDate firstOfMonth = present.minusMonths(1).withDayOfMonth(1);
        LocalDate lastOfMonth = firstOfMonth.withDayOfMonth(firstOfMonth.lengthOfMonth());
            // DISPLAY WITHIN MONTH
        for (Transactions t : transactions) {
            if (!t.getDate().isBefore(firstOfMonth) && !t.getDate().isAfter(lastOfMonth)) {
                System.out.printf("%s | %s | %s | %s | $%.2f\n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
    }

    public static void yearToDate(ArrayList<Transactions> transactions) {
        LocalDate present = LocalDate.now();
        LocalDate startOfYear = present.withDayOfYear(1);
            // INCLUDE EVERYTHING FROM JAN 1
        for (Transactions t : transactions) {
            if (!t.getDate().isBefore(startOfYear)) {
                System.out.printf("%s | %s | %s | %s | $%.2f\n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
    }

    public static void previousYear(ArrayList<Transactions> transactions) {
        int lastYear = LocalDate.now().getYear() - 1;
            // 2025 - 1
        for (Transactions t : transactions) {

                //ONLY LAST YEAR ENTRIES
            if (t.getDate().getYear() == lastYear) {
                System.out.printf("%s | %s | %s | %s | $%.2f\n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
    }

    public static void searchByVendor(ArrayList<Transactions> transactions) {
        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine().trim().toLowerCase();
            // GET VENDOR INFO
        for (Transactions t : transactions) {
            if (t.getVendor().toLowerCase().contains(vendor)) {
                System.out.println(t);
            }
        }
    }
}

