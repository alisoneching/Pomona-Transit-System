import java.sql.*;
import java.util.*;

public class DriverBusOperations {
    public static void displayDriverSchedule(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter the driver name: ");
        String driver = scanner.nextLine();
        System.out.print("Enter the date in the format YYYY-MM-DD: ");
        String date = scanner.nextLine();

        String query = "SELECT TripNumber, ScheduledStartTime, ScheduledArrivalTime, BusID FROM TripOffering WHERE DriverName = ? AND Date = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, driver);
            stmt.setDate(2, java.sql.Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("\nTrip #: " + rs.getString(1) + ", Start: " + rs.getTime(2) + ", Arrive: " + rs.getTime(3) + ", BusID: " + rs.getInt(4));
            }
        }
    }

    public static void addDriver(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Driver Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Driver Telephone Number: ");
        String phone = scanner.nextLine();

        String insert = "INSERT INTO Driver VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insert)) {
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.executeUpdate();
            System.out.println("\nDriver added.");
        }
    }

    public static void addBus(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter the bus ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the model: ");
        String model = scanner.nextLine();
        System.out.print("Enter the year: ");
        int year = Integer.parseInt(scanner.nextLine());

        String insert = "INSERT INTO Bus VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insert)) {
            stmt.setInt(1, id);
            stmt.setString(2, model);
            stmt.setInt(3, year);
            stmt.executeUpdate();
            System.out.println("\nBus added.");
        }
    }

    public static void deleteBus(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter the bus ID to be deleted: ");
        int id = Integer.parseInt(scanner.nextLine());

        String delete = "DELETE FROM Bus WHERE BusID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(delete)) {
            stmt.setInt(1, id);
            int result = stmt.executeUpdate();
            System.out.println(result > 0 ? "\nBus deleted." : "\nBus ID does not exist.");
        }
    }
}
