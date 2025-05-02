# Java Ledger & Reporting System

This is a console-based Java application that acts as a simple ledger system. It allows users to input, read, and analyze financial transactions. Users can view income or payment records, generate reports by time period, and search by vendor.

---

## 📦 Features

- 📄 **Transaction Display**  
  View all transactions or filter by:
  - Income
  - Payments
  - ![Screenshot 2025-05-02 051828](https://github.com/user-attachments/assets/44d9fcb9-52e7-4143-800e-4c32ef288c34)


- 📊 **Reports Menu**
  Generate reports based on:
  - Month-to-date
  - Previous month
  - Year-to-date
  - Previous year
  - Vendor search
![Screenshot 2025-05-02 090813](https://github.com/user-attachments/assets/4ecdabce-22a1-4385-8b7b-e4995ae8dac4)

- 🔍 **Vendor Search**  
  Enter a keyword to filter all transactions associated with that vendor.
![Screenshot 2025-05-02 090844](https://github.com/user-attachments/assets/b2e52fb4-3524-400c-8cd0-558bbffab4f4)

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
## Interesting Code
![Screenshot 2025-05-02 052032](https://github.com/user-attachments/assets/d50eb9ed-4755-4b9c-b90a-fe6c09d1de1d)

Inserting the read method and breaking it down to recognize the core values needed.
---

## 🖥️ Sample Console Output
![Screenshot 2025-05-02 090927](https://github.com/user-attachments/assets/f538978f-fb7b-4bb9-97e6-6367cc118ed4)

