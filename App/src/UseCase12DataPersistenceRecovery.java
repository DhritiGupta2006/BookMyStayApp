import java.io.*;
import java.util.*;

/**
 * UseCase12DataPersistenceRecovery implements state durability.
 * It demonstrates how to save and load system state using File I/O.
 * * @author Dhriti Gupta
 * @version 12.0
 */

class UC12PersistenceService {
    private static final String FILE_NAME = "inventory_state.txt";

    // Save state: Serializing a Map to a simple text format
    public void saveState(Map<String, Integer> inventory) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                writer.println(entry.getKey() + ":" + entry.getValue());
            }
            System.out.println("SUCCESS: System state serialized and saved to " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("ERROR: Could not save state: " + e.getMessage());
        }
    }

    // Load state: Deserializing data back into memory
    public Map<String, Integer> loadState() {
        Map<String, Integer> restoredInventory = new HashMap<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("NOTICE: No persistence file found. Starting with fresh state.");
            return restoredInventory;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(":");
                if (parts.length == 2) {
                    restoredInventory.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
            System.out.println("SUCCESS: System state recovered from " + FILE_NAME);
        } catch (Exception e) {
            System.err.println("ERROR: State recovery failed. Starting fresh. " + e.getMessage());
        }
        return restoredInventory;
    }
}

public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay App v12.0 ===");

        UC12PersistenceService persistence = new UC12PersistenceService();
        Map<String, Integer> currentInventory = new HashMap<>();

        // 1. Check for existing data (Recovery)
        System.out.println("Checking for persisted data...");
        currentInventory = persistence.loadState();

        if (currentInventory.isEmpty()) {
            System.out.println("Initializing default inventory...");
            currentInventory.put("Single Room", 10);
            currentInventory.put("Double Room", 5);
        }

        System.out.println("Current Inventory: " + currentInventory);

        // 2. Simulate a change in state
        System.out.println("\nProcessing a booking (Single Room -1)...");
        currentInventory.put("Single Room", currentInventory.get("Single Room") - 1);

        // 3. Persist state before shutdown
        System.out.println("Preparing for system shutdown...");
        persistence.saveState(currentInventory);

        System.out.println("\nSystem stopped. Run the program again to verify recovery.");
    }
}