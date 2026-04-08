import java.util.*;

/**
 * UseCase10BookingCancellation implements state reversal logic.
 * It uses a Stack to track allocated room IDs for LIFO rollback behavior.
 * * @author Dhriti Gupta
 * @version 10.0
 */

class UC10CancellationService {
    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, String> activeBookings = new HashMap<>(); // ResID -> RoomType
    private Stack<String> releasedRoomIds = new Stack<>(); // LIFO Rollback

    public void setup(String type, int count) {
        inventory.put(type, count);
    }

    public void confirmBooking(String resId, String roomType) {
        activeBookings.put(resId, roomType);
        inventory.put(roomType, inventory.get(roomType) - 1);
        System.out.println("Confirmed: " + resId + " for " + roomType);
    }

    /**
     * Performs a controlled rollback of a booking.
     * Demonstrates 'State Reversal' and 'Inventory Restoration'.
     */
    public void cancelBooking(String resId) {
        // 1. Validation: Ensure reservation exists
        if (!activeBookings.containsKey(resId)) {
            System.err.println("CANCELLATION ERROR: Reservation " + resId + " not found.");
            return;
        }

        // 2. Rollback Logic
        String roomType = activeBookings.remove(resId);

        // Simulating Room ID recovery using Stack (LIFO)
        String releasedId = "RM-" + resId.hashCode();
        releasedRoomIds.push(releasedId);

        // 3. Inventory Restoration
        inventory.put(roomType, inventory.get(roomType) + 1);

        System.out.println("SUCCESS: Cancelled " + resId + ". Room " + releasedId + " returned to pool.");
        System.out.println("Updated Inventory for " + roomType + ": " + inventory.get(roomType));
    }
}

public class UseCase10BookingCancellation {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay App v10.0 ===");

        UC10CancellationService service = new UC10CancellationService();
        service.setup("Suite Room", 2);

        // 1. Create bookings to establish state
        service.confirmBooking("RES-001", "Suite Room");
        service.confirmBooking("RES-002", "Suite Room");

        // 2. Perform cancellation (State Reversal)
        System.out.println("\nInitiating Cancellation...");
        service.cancelBooking("RES-002");

        // 3. Attempt invalid cancellation
        System.out.println("\nInitiating Invalid Cancellation...");
        service.cancelBooking("RES-999");

        System.out.println("\nCancellation cycle complete. System state restored consistently.");
    }
}