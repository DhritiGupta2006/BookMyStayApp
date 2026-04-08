import java.util.*;

/**
 * UseCase9ErrorHandlingValidation introduces structured validation.
 * It uses custom exceptions to implement a Fail-Fast design.
 * * @author Dhriti Gupta
 * @version 9.0
 */

// Custom Exception for Business Logic Failures
class BookingException extends Exception {
    public BookingException(String message) {
        super(message);
    }
}

class UC9Validator {
    private Map<String, Integer> inventory = new HashMap<>();

    public void addInventory(String type, int count) {
        inventory.put(type, count);
    }

    /**
     * Validates input and system state before any allocation occurs.
     * Demonstrates 'Fail-Fast' and 'Input Validation'.
     */
    public void validateBooking(String roomType) throws BookingException {
        // 1. Validate Input (Case Sensitive)
        if (!inventory.containsKey(roomType)) {
            throw new BookingException("ERROR: Invalid Room Type '" + roomType + "'. Please check spelling/casing.");
        }

        // 2. Guarding System State
        if (inventory.get(roomType) <= 0) {
            throw new BookingException("ERROR: " + roomType + " is currently SOLD OUT.");
        }
    }

    public void processAllocation(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
        System.out.println("SUCCESS: Allocation confirmed for " + roomType);
    }
}

public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay App v9.0 ===");

        UC9Validator validator = new UC9Validator();
        validator.addInventory("Single Room", 1);

        // Test Cases for Validation
        String[] testRequests = {"single room", "Deluxe Room", "Single Room", "Single Room"};

        for (String request : testRequests) {
            System.out.println("\nProcessing Request: " + request);
            try {
                // Perform validation before processing
                validator.validateBooking(request);

                // If validation passes, proceed to allocation
                validator.processAllocation(request);

            } catch (BookingException e) {
                // Graceful Failure Handling
                System.err.println(e.getMessage());
            }
        }

        System.out.println("\nValidation cycle complete. System remains stable.");
    }
}