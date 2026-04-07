/**
 * UseCase2RoomInitialization demonstrates Room Type modeling using Inheritance.
 * It uses an abstract class to define common room properties.
 * * @author Dhriti Gupta
 * @version 2.0
 */

// Abstract Class representing a general Room
abstract class Room {
    protected String type;
    protected double price;

    public Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public abstract void displayDetails();
}

// Concrete Class for Single Room
class SingleRoom extends Room {
    public SingleRoom() { super("Single Room", 1500.0); }
    @Override
    public void displayDetails() {
        System.out.println("Type: " + type + " | Price: Rs." + price + " | Capacity: 1 Adult");
    }
}

// Concrete Class for Double Room
class DoubleRoom extends Room {
    public DoubleRoom() { super("Double Room", 2500.0); }
    @Override
    public void displayDetails() {
        System.out.println("Type: " + type + " | Price: Rs." + price + " | Capacity: 2 Adults");
    }
}

// Main Application Class
public class UseCase2RoomInitialization {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay App v2.0 ===");

        // Static availability variables (Pre-data structure approach)
        int singleRoomAvailability = 5;
        int doubleRoomAvailability = 3;

        // Polymorphism: Using abstract type to refer to concrete objects
        Room r1 = new SingleRoom();
        Room r2 = new DoubleRoom();

        r1.displayDetails();
        System.out.println("Available: " + singleRoomAvailability);

        r2.displayDetails();
        System.out.println("Available: " + doubleRoomAvailability);
    }
}