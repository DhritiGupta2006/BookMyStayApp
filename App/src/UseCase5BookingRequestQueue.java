import java.util.LinkedList;
import java.util.Queue;

/**
 * UseCase5BookingRequestQueue manages incoming booking requests.
 * It uses a Queue to ensure First-Come-First-Served (FIFO) fairness.
 * * @author Dhriti Gupta
 * @version 5.0
 */

// Reservation model representing guest intent
class UC5Reservation {
    private String guestName;
    private String roomType;

    public UC5Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Reservation [Guest: " + guestName + ", Room Type: " + roomType + "]";
    }
}

// Booking Request Queue manager
class UC5BookingQueue {
    private Queue<UC5Reservation> requestQueue;

    public UC5BookingQueue() {
        this.requestQueue = new LinkedList<>();
    }

    // Add a request to the end of the queue
    public void addRequest(UC5Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Enqueued: " + reservation);
    }

    // Display all pending requests in arrival order
    public void displayPendingRequests() {
        System.out.println("\n--- Current Booking Request Queue (FIFO) ---");
        if (requestQueue.isEmpty()) {
            System.out.println("No pending requests.");
        } else {
            for (UC5Reservation res : requestQueue) {
                System.out.println(res);
            }
        }
    }
}

public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay App v5.0 ===");

        // 1. Initialize the Request Queue
        UC5BookingQueue bookingQueue = new UC5BookingQueue();

        // 2. Simulate guests submitting requests at different times
        System.out.println("Receiving incoming booking requests...");
        bookingQueue.addRequest(new UC5Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new UC5Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new UC5Reservation("Charlie", "Suite Room"));

        // 3. Display the queue to verify arrival order
        bookingQueue.displayPendingRequests();

        System.out.println("\nRequests are held in order. No inventory has been modified yet.");
    }
}
