import java.util.*;

/**
 * UseCase7AddOnServiceSelection handles optional service selection.
 * It uses a Map and List combination to associate services with a Reservation ID.
 * * @author Dhriti Gupta
 * @version 7.0
 */

// Model for an Add-On Service
class UC7Service {
    private String serviceName;
    private double price;

    public UC7Service(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() { return serviceName; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return serviceName + " (Rs." + price + ")";
    }
}

class UC7AddOnManager {
    // One-to-Many: One Reservation ID mapped to multiple Services
    private Map<String, List<UC7Service>> bookingAddOns = new HashMap<>();

    public void addServiceToBooking(String reservationId, UC7Service service) {
        bookingAddOns.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
        System.out.println("Added " + service.getServiceName() + " to Reservation: " + reservationId);
    }

    public void displayBill(String reservationId, double roomBasePrice) {
        System.out.println("\n--- Final Bill for " + reservationId + " ---");
        System.out.println("Room Base Price: Rs." + roomBasePrice);

        double totalAddOnCost = 0;
        List<UC7Service> services = bookingAddOns.getOrDefault(reservationId, new ArrayList<>());

        for (UC7Service s : services) {
            System.out.println("+ " + s);
            totalAddOnCost += s.getPrice();
        }

        System.out.println("Total Amount: Rs." + (roomBasePrice + totalAddOnCost));
    }
}

public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay App v7.0 ===");

        // 1. Initialize Manager and Services
        UC7AddOnManager addOnManager = new UC7AddOnManager();
        UC7Service wifi = new UC7Service("High-Speed WiFi", 500.0);
        UC7Service breakfast = new UC7Service("Buffet Breakfast", 800.0);
        UC7Service spa = new UC7Service("Spa Session", 2000.0);

        // 2. Assign services to a specific reservation ID (e.g., from UC6)
        String resId = "S-101";
        System.out.println("Guest selecting add-ons...");
        addOnManager.addServiceToBooking(resId, wifi);
        addOnManager.addServiceToBooking(resId, breakfast);

        // 3. Calculate and display final costs
        addOnManager.displayBill(resId, 1500.0);

        System.out.println("\nAdd-on selection completed. Core inventory remains unchanged.");
    }
}