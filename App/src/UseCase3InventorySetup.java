import java.util.HashMap;
import java.util.Map;

/**
 * UseCase3InventorySetup introduces centralized inventory management.
 * It replaces scattered variables with a HashMap for better scalability.
 * * @author Dhriti Gupta
 * @version 3.0
 */

class RoomInventory {
    // HashMap acting as the Single Source of Truth
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Register a room type with its initial count
    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Get current availability for a specific room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Controlled update of room availability
    public void updateAvailability(String roomType, int count) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, count);
        }
    }

    // Display the entire inventory state
    public void displayInventory() {
        System.out.println("--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " rooms available");
        }
    }
}

public class UseCase3InventorySetup {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay App v3.0 ===");

        // Initializing the inventory component
        RoomInventory hotelInventory = new RoomInventory();

        // Registering room types with available counts
        hotelInventory.addRoomType("Single Room", 10);
        hotelInventory.addRoomType("Double Room", 5);
        hotelInventory.addRoomType("Suite Room", 2);

        // Displaying initial state
        hotelInventory.displayInventory();

        // Simulating a controlled update (e.g., a booking occurred)
        System.out.println("\nUpdating Double Room inventory...");
        hotelInventory.updateAvailability("Double Room", 4);

        // Displaying updated state
        hotelInventory.displayInventory();
    }
}

