import java.util.*;

/**
 * UseCase8BookingHistoryReport implements chronological tracking of bookings.
 * It uses a List to act as an in-memory audit trail for reporting.
 * * @author Dhriti Gupta
 * @version 8.0
 */

// Model for a Historical Record
class UC8BookingRecord {
    private String reservationId;
    private String guestName;
    private String roomType;
    private double finalAmount;

    public UC8BookingRecord(String id, String guest, String type, double amount) {
        this.reservationId = id;
        this.guestName = guest;
        this.roomType = type;
        this.finalAmount = amount;
    }

    @Override
    public String toString() {
        return String.format("ID: %-6s | Guest: %-10s | Type: %-12s | Total: Rs.%.2f",
                reservationId, guestName, roomType, finalAmount);
    }
}

class UC8ReportService {
    // List preserves the insertion (chronological) order
    private List<UC8BookingRecord> history = new ArrayList<>();

    public void archiveBooking(UC8BookingRecord record) {
        history.add(record);
    }

    public void generateAdminReport() {
        System.out.println("\n========== ADMINISTRATIVE BOOKING REPORT ==========");
        if (history.isEmpty()) {
            System.out.println("No records found.");
        } else {
            double totalRevenue = 0;
            for (UC8BookingRecord record : history) {
                System.out.println(record);
                // In a real scenario, we'd extract the amount properly
            }
            System.out.println("====================================================");
            System.out.println("Total Transactions Processed: " + history.size());
        }
    }
}

public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay App v8.0 ===");

        UC8ReportService reportService = new UC8ReportService();

        // 1. Simulating the archive process after successful confirmations
        System.out.println("Archiving confirmed bookings...");
        reportService.archiveBooking(new UC8BookingRecord("S-101", "Alice", "Single Room", 2800.0));
        reportService.archiveBooking(new UC8BookingRecord("D-201", "Bob", "Double Room", 3500.0));
        reportService.archiveBooking(new UC8BookingRecord("S-102", "Charlie", "Single Room", 1500.0));

        // 2. Admin requests the report
        reportService.generateAdminReport();

        System.out.println("\nReporting completed. Historical data is persisted in memory.");
    }
}