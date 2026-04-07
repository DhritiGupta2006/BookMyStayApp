# 📘 Book My Stay App: Hotel Booking Management System

## 🏨 Project Overview
The **Book My Stay App** demonstrates the design and implementation of a Hotel Booking Management System. It showcases the practical application of **Core Java** and fundamental data structures in real-world scenarios.

The system is developed step by step, where each use case introduces a specific concept that solves common software engineering challenges such as:
- Fair request handling
- Inventory consistency
- Prevention of double-booking

This project focuses on core logic and system behavior rather than UI, helping learners understand not only how data structures are used, but also why they are important for scalable and maintainable systems.

---

## 🚀 Use Case 1: Application Entry & Welcome Message

### 🎯 Objective
To establish a clear starting point for the application by demonstrating how a Java program begins execution and displays output.

---

### 🔄 Application Flow
1. The user runs the application using command line or an IDE.
2. The Java Virtual Machine (JVM) looks for the method:  
   `public static void main(String[] args)`
3. The JVM invokes the main method.
4. The program prints a welcome message with application name and version.
5. The program ends after execution.

---

### 🛠️ Running the Application

**Step 1: Compile the Program**
javac UseCase1HotelBookingApp.java

**Step 2: Run the Program**
java UseCase1HotelBookingApp

---

### 📌 Expected Output
    Welcome to Book My Stay App v1.0
    Your trusted hotel booking system

---

### 📖 Learning Outcome
- Understand how Java program execution starts
- Learn the role of JVM
- Understand the importance of the `main` method
- Practice basic console output

---

## 🚀 Use Case 2: Room Search Functionality

### 🎯 Objective
To implement a room search feature using data structures that allows users to view available rooms.

---

### 🔄 Application Flow
1. The user requests to view available rooms.
2. The system initializes room data using a data structure (`ArrayList`).
3. Each room contains details like room ID, type, price, and availability.
4. The program loops through the list and filters available rooms.
5. Available rooms are displayed to the user.

---

### 🛠️ Implementation Details
- Use `ArrayList` to store Room objects
- Each Room object contains:
    - Room ID
    - Room Type (Single, Double, Deluxe)
    - Price
    - Availability (true/false)

---

### 📌 Sample Output
    Available Rooms:
    Room ID: 101 | Type: Single | Price: 2000
    Room ID: 102 | Type: Double | Price: 3500
    Room ID: 105 | Type: Deluxe | Price: 5000

---

### 📖 Learning Outcome
- Learn how to use `ArrayList` in Java
- Understand basic filtering logic
- Practice object-oriented programming (Room class)
- Understand real-world usage of data structures

---

## 🔜 Upcoming Use Cases
- Booking System with Availability Check
- Preventing Double Booking
- Queue-based Request Handling

---

## 👨‍💻 Technologies Used
- Core Java
- Data Structures (`ArrayList`, `List`, `Queue`, `Map`)

---

## 📌 Author
This project is developed as a learning exercise to understand real-world system design using Java.