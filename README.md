#  📚Library_Management_System

A *Java-based Library Management System* using *JDBC* and *MySQL*. This console-based application allows users to perform essential book management operations such as adding, updating, deleting, and searching for books in a database.

## 🚀 Features

**Add new books** – Insert book details like title, author, and availability into the database.
**Update book details by title** – Modify existing book records by providing the book title.
**Search for books by title** – Retrieve book information by searching with the book title.
**Delete books by title** – Remove books from the database using the title as input.
**List all books** – Display all books currently stored in the database.
**Prevent duplicate entries** – Automatically increases availability if the book already exists.
**Auto-reset book IDs** – Reassigns IDs sequentially after a book is deleted.
**User-friendly prompts** – Interactive prompts guide the user step by step through actions.

## 🛠️ Technologies Used

**Java**
**OOPS CONCEPT**
**JDBC (Java Database Connectivity)**
**MySQL**
**SQL QUERIES**
**CRUD OPERATION**
**IntelliJ IDEA (or any preferred IDE)**

## 📂 Project Structure


├── Book.java
├── BookDAO.java
├── DatabaseConnection.java
├── LibraryApp.java
└── README.md


## 📓 Database Setup

1. Create a MySQL database named library_db (or as preferred).

   **CREATE DATABASE library_db;
     USE library_db;**
   
2. Use the following table structure:

   **sql
     CREATE TABLE Books (
     id INT AUTO_INCREMENT PRIMARY KEY,
     title VARCHAR(255) NOT NULL,
     author VARCHAR(255) NOT NULL,
     available INT NOT NULL
     );**


3. Update the DatabaseConnection.java file with your MySQL username and password.
In DatabaseConnection.java, update your DB username and password:

   **String url = "jdbc:mysql://localhost:3306/library_db";
     String username = "your_username";
     String password = "your_password";**


## ▶️ How to Run

1. Clone the repository.
2. Set up the database as described above.
3. Compile and run LibraryApp.java from your IDE or command line.

## 📄 License

This project is licensed under the MIT License. See the LICENSE file for details.
