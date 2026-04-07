import java.util.*;

/**
 * UseCase6RoomAllocationService handles the transition from request to confirmed booking.
 * It uses a Set to ensure unique Room IDs and prevent double-booking.
 * * @author Dhriti Gupta
 * @version 6.0
 */

// Model for a confirmed Booking
class UC6Booking {
    String guestName;
    String roomType;
    String roomId;

    public UC6Booking(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Confirmed: [Guest: " + guestName + " | Type: " + roomType + " | Room ID: " + roomId + "]";
    }
}

class UC6AllocationService {
    private Map<String, Integer> inventory = new HashMap<>();
    private Set<String> allocatedRooms = new HashSet<>(); // Ensures uniqueness

    public void setupInventory(String type, int count) {
        inventory.put(type, count);
    }

    public void processBooking(String guestName, String roomType) {
        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            // Generate a unique Room ID (e.g., SR-101, DR-201)
            String prefix = roomType.substring(0, 1).toUpperCase();
            String roomId = prefix + "-" + (100 + allocatedRooms.size() + 1);

            // Double-booking prevention check
            if (!allocatedRooms.contains(roomId)) {
                allocatedRooms.add(roomId); // Record unique ID
                inventory.put(roomType, available - 1); // Decrement inventory

                UC6Booking confirmed = new UC6Booking(guestName, roomType, roomId);
                System.out.println(confirmed);
            }
        } else {
            System.out.println("Failed: No availability for " + guestName + " (" + roomType + ")");
        }
    }
}

public class UseCase6RoomAllocationService {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay App v6.0 ===");

        // 1. Setup Services
        UC6AllocationService allocationService = new UC6AllocationService();
        allocationService.setupInventory("Single Room", 2);

        // 2. Simulated Queue (from UC5)
        Queue<String[]> requestQueue = new LinkedList<>();
        requestQueue.add(new String[]{"Alice", "Single Room"});
        requestQueue.add(new String[]{"Bob", "Single Room"});
        requestQueue.add(new String[]{"Charlie", "Single Room"}); // Should fail (3rd request for 2 rooms)

        // 3. Processing FIFO Requests
        System.out.println("Processing FIFO Allocation...");
        while (!requestQueue.isEmpty()) {
            String[] request = requestQueue.poll();
            allocationService.processBooking(request[0], request[1]);
        }
    }
}