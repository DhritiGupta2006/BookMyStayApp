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

---

## Use Case 3: Centralized Room Inventory Management

### 🎯 Objective
To introduce centralized inventory management by replacing scattered availability variables with a single, consistent **HashMap** data structure. This demonstrates how to solve real-world state management problems through encapsulation.

### 🔄 Application Flow
1. **Initialization:** The system initializes the `RoomInventory` component.
2. **Registration:** Room types (Single, Double, Suite) are registered with their initial available counts.
3. **Centralized Storage:** Availability is stored and retrieved from a `HashMap<String, Integer>`.
4. **Controlled Updates:** Updates to availability are performed through dedicated methods, ensuring data integrity.
5. **Display:** The current state of the entire inventory is printed to the console.

---

## Use Case 4: Room Search & Availability Check

### 🎯 Objective
To enable guests to view available rooms and their details without modifying the system state. This use case reinforces **Read-Only Access** and ensures a clear separation between searching for data and modifying inventory.

### 🔄 Application Flow
1. **Request:** The Guest initiates a search for available rooms.
2. **Retrieval:** The system fetches counts from the `RoomInventory` and descriptions from `Room` domain objects.
3. **Filtering:** Validation logic ensures that only room types with availability > 0 are presented as bookable.
4. **Display:** Room details (price, capacity) are shown for available rooms; unavailable rooms are marked as "Sold Out".
5. **Stability:** No inventory variables are modified during the search process.

---

## Use Case 5: Booking Request (First-Come-First-Served)

### 🎯 Objective
To handle multiple booking requests fairly by introducing a request intake mechanism. By using a **Queue**, the system ensures that guest requests are stored and eventually processed in the exact order they arrived (**FIFO**).

### 🔄 Application Flow
1. **Submission:** A Guest submits a booking request (Reservation object).
2. **Enqueuing:** The request is added to the `BookingRequestQueue`.
3. **Ordering:** Requests are stored in arrival order automatically by the Queue data structure.
4. **Waiting:** Requests remain in the queue, decoupled from the allocation logic.
5. **State Preservation:** No inventory mutation or room assignment occurs at this stage.

---

## Use Case 6: Reservation Confirmation & Room Allocation

### 🎯 Objective
To confirm booking requests by assigning rooms safely while ensuring inventory consistency and preventing double-booking. This is achieved by introducing the **Set** data structure to enforce uniqueness.

### 🔄 Application Flow
1. **Dequeue:** A booking request is retrieved from the FIFO queue.
2. **Availability Check:** The system verifies the `RoomInventory` for the requested type.
3. **ID Generation:** A unique Room ID is generated (e.g., S-101).
4. **Uniqueness Enforcement:** The system checks a **Set** of allocated IDs to prevent reuse/double-booking.
5. **Synchronization:** The inventory count is decremented and the reservation is confirmed simultaneously.

---

## Use Case 7: Add-On Service Selection

### 🎯 Objective
To extend the booking model to support optional services. This demonstrates **Business Extensibility** by allowing guests to add features like WiFi or Breakfast to an existing reservation without modifying the core booking or allocation logic.

### 🔄 Application Flow
1. **Selection:** The Guest selects one or more optional add-on services.
2. **Mapping:** Selected services are stored in a `List` and mapped to a specific Reservation ID using a `Map`.
3. **Cost Calculation:** The system aggregates the base room price with the total cost of all selected services.
4. **Isolation:** Core booking data and room inventory counts remain strictly unchanged.

---

## Use Case 8: Booking History & Reporting

### 🎯 Objective
To introduce historical tracking of confirmed bookings. By utilizing a **List** data structure, the system maintains a chronological audit trail of all transactions, providing operational visibility and supporting administrative reporting without the need for external databases.

### 🔄 Application Flow
1. **Confirmation:** A booking is finalized and confirmed in the system.
2. **Archiving:** The confirmed reservation details are added to the `Booking History` list.
3. **Chronological Storage:** Records are maintained in the exact order they were confirmed (Insertion Order).
4. **Request:** An Admin triggers the `Report Service` to view system usage.
5. **Reporting:** The system retrieves stored records and generates a summary report.

---

## Use Case 9: Error Handling & Validation

### 🎯 Objective
To strengthen system reliability by introducing structured validation and error handling. This ensures that invalid inputs (such as incorrect casing or non-existent room types) and inconsistent states (like negative inventory) are detected early using a **Fail-Fast** approach.

### 🔄 Application Flow
1. **Input:** A Guest provides booking details.
2. **Validation:** The `Invalid Booking Validator` checks input values against system constraints.
3. **Exception Handling:** If validation fails, a `BookingException` is thrown immediately.
4. **Communication:** The system catches the exception and displays a meaningful error message.
5. **Stability:** The application prevents invalid state changes and continues running for subsequent requests.

---

## Use Case 10: Booking Cancellation & Inventory Rollback

### 🎯 Objective
To enable the safe cancellation of confirmed bookings by correctly reversing system state changes. This ensures **Inventory Consistency** and predictable recovery behavior by using a **Stack** data structure to model the rollback of allocated resources.

### 🔄 Application Flow
1. **Request:** A Guest initiates a cancellation for an existing booking.
2. **Validation:** The system verifies the reservation exists and is in a cancellable state.
3. **Rollback:** The allocated room ID is pushed to a rollback structure (Stack).
4. **Restoration:** The inventory count for the specific room type is incremented immediately.
5. **Update:** Booking records are updated to reflect the cancellation, restoring system state consistently.

