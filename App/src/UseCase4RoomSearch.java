import java.util.HashMap;
import java.util.Map;

/**
 * UseCase4RoomSearch enables guests to view available rooms.
 * Classes are prefixed with 'UC4' to avoid duplicate class errors.
 * * @author Dhriti Gupta
 * @version 4.0
 */

// Renamed to UC4Room to avoid conflict with UC2/UC3
abstract class UC4Room {
    protected String type;
    protected double price;

    public UC4Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public abstract void displayDetails();
    public String getType() { return type; }
}

class UC4SingleRoom extends UC4Room {
    public UC4SingleRoom() { super("Single Room", 1500.0); }
    @Override
    public void displayDetails() {
        System.out.println("Type: " + type + " | Price: Rs." + price + " | Capacity: 1 Adult");
    }
}

class UC4DoubleRoom extends UC4Room {
    public UC4DoubleRoom() { super("Double Room", 2500.0); }
    @Override
    public void displayDetails() {
        System.out.println("Type: " + type + " | Price: Rs." + price + " | Capacity: 2 Adults");
    }
}

// Renamed to UC4RoomInventory to avoid "duplicate class: RoomInventory"
class UC4RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

public class UseCase4RoomSearch {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay App v4.0 ===");

        // 1. Initialize Inventory using the renamed class
        UC4RoomInventory hotelInventory = new UC4RoomInventory();
        hotelInventory.addRoomType("Single Room", 5);
        hotelInventory.addRoomType("Double Room", 0);

        // 2. Initialize Room Objects
        UC4Room single = new UC4SingleRoom();
        UC4Room dual = new UC4DoubleRoom();

        // 3. Perform Room Search
        System.out.println("\n--- Guest Room Search Results ---");

        searchAndDisplay(single, hotelInventory);
        searchAndDisplay(dual, hotelInventory);

        System.out.println("\nSearch session ended. Inventory state remained unchanged.");
    }

    /**
     * Helper method using the unique UC4 types
     */
    public static void searchAndDisplay(UC4Room room, UC4RoomInventory inventory) {
        int count = inventory.getAvailability(room.getType());

        if (count > 0) {
            room.displayDetails();
            System.out.println("Current Availability: " + count);
        } else {
            System.out.println("Type: " + room.getType() + " | Status: SOLD OUT");
        }
    }
}