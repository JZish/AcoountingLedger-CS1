# 💸 Java Ledger & Reporting System

This is a console-based Java application that acts as a simple ledger system. It allows users to input, read, and analyze financial transactions. Users can view income or payment records, generate reports by time period, and search by vendor.

---

## 📦 Features

- 📄 **Transaction Display**  
  View all transactions or filter by:
  - Income
  - Payments

- 📊 **Reports Menu**
  Generate reports based on:
  - Month-to-date
  - Previous month
  - Year-to-date
  - Previous year
  - Vendor search

- 🔍 **Vendor Search**  
  Enter a keyword to filter all transactions associated with that vendor.

- 💾 **File I/O Ready (via `readTransactions`)**  
  Uses an external data source (such as a file or database) to read in the transaction history.

---

## 🛠 Technologies Used

- Java 8+
- Java Collections (ArrayList)
- Java Time (LocalDate, LocalTime, DateTimeFormatter)
- Console-based UI with Scanner

---

## 🧾 Example Transaction Format

Each transaction includes the following fields:
- `Date` (LocalDate)
- `Time` (LocalTime)
- `Description` (String)
- `Vendor` (String)
- `Amount` (double)

---

## 🖥️ Sample Console Output

