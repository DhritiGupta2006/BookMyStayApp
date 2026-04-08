import java.util.*;

/**
 * UseCase11ConcurrentBookingSimulation demonstrates Thread Safety.
 * It uses synchronized blocks to prevent Race Conditions during allocation.
 * * @author Dhriti Gupta
 * @version 11.0
 */

class UC11BookingProcessor {
    private int availableRooms = 1; // Only one room left
    private final Object lock = new Object(); // Shared lock for synchronization

    /**
     * Processes a booking request. 
     * The synchronized block ensures only one thread enters the Critical Section.
     */
    public void processRequest(String guestName) {
        System.out.println(guestName + " is checking availability...");

        synchronized (lock) {
            if (availableRooms > 0) {
                // Simulate processing time
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                availableRooms--;
                System.out.println("SUCCESS: Room allocated to " + guestName);
            } else {
                System.out.println("FAILED: Room sold out for " + guestName);
            }
        }
    }
}

class GuestThread extends Thread {
    private UC11BookingProcessor processor;
    private String name;

    public GuestThread(UC11BookingProcessor processor, String name) {
        this.processor = processor;
        this.name = name;
    }

    @Override
    public void run() {
        processor.processRequest(name);
    }
}

public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay App v11.0 ===");
        System.out.println("Simulating concurrent booking for the last available room...\n");

        UC11BookingProcessor sharedProcessor = new UC11BookingProcessor();

        // Creating multiple threads (Guests) reaching for the same resource
        Thread t1 = new GuestThread(sharedProcessor, "Guest_Alice");
        Thread t2 = new GuestThread(sharedProcessor, "Guest_Bob");
        Thread t3 = new GuestThread(sharedProcessor, "Guest_Charlie");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nSimulation complete. Thread safety maintained system integrity.");
    }
}