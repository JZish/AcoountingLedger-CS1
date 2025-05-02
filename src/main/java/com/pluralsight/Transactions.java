package com.pluralsight;

import java.time.LocalDate;

import java.time.LocalTime;

    public class Transactions {
        // VARIABLES
            private LocalDate date;
            private LocalTime time;
            private String description;
            private String vendor;
            private double amount;
        // VARIABLES

        // CONSTRUCTOR
    public Transactions (LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }
        // CONSTRUCTOR
            // Override String Format
        public String toString() {
            return String.format("%s | %s | %s | %s | $%.2f", date, time, description, vendor, amount);
        }
            // Override String Format

        // Getters

        public LocalDate getDate() {
            return date;
        }

        public LocalTime getTime() {
            return time;
        }

        public String getDescription() {
            return description;
        }

        public String getVendor() {
            return vendor;
        }

        public double getAmount() {
            return amount;
        }
    }
