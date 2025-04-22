import java.sql.*;
import java.util.*;

public class TripOfferingOperations {
    public static void deleteTripOffering(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter the trip number: ");
        int trip = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the date in the format YYYY-MM-DD: ");
        String date = scanner.nextLine();
        System.out.print("Enter the scheduled start time in the format HH:MM:SS: ");
        String start = scanner.nextLine();

        String delete = "DELETE FROM TripOffering WHERE TripNumber = ? AND Date = ? AND ScheduledStartTime = ?";
        try (PreparedStatement stmt = conn.prepareStatement(delete)) {
            stmt.setInt(1, trip);
            stmt.setDate(2, java.sql.Date.valueOf(date));
            stmt.setTime(3, Time.valueOf(start));
            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "\nTrip offering deleted." : "\nNo trip found.");
        }
    }

    public static void addTripOffering(Connection conn, Scanner scanner) throws SQLException {
        String insert = "INSERT INTO TripOffering VALUES (?, ?, ?, ?, ?, ?)";

        while (true) {
            System.out.print("Enter the trip number: ");
            int trip = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter the date in the format YYYY-MM-DD: ");
            String date = scanner.nextLine();
            System.out.print("Enter the scheduled start time in the format HH:MM:SS: ");
            String start = scanner.nextLine();
            System.out.print("Enter the scheduled arrival time in the format HH:MM:SS: ");
            String arrival = scanner.nextLine();
            System.out.print("Enter the driver name: ");
            String driver = scanner.nextLine();
            System.out.print("Enter the bus ID: ");
            int busId = Integer.parseInt(scanner.nextLine());

            try (PreparedStatement stmt = conn.prepareStatement(insert)) {
                stmt.setInt(1, trip);
                stmt.setDate(2, java.sql.Date.valueOf(date));
                stmt.setTime(3, Time.valueOf(start));
                stmt.setTime(4, Time.valueOf(arrival));
                stmt.setString(5, driver);
                stmt.setInt(6, busId);
                stmt.executeUpdate();
                System.out.println("\nTrip offering added.");
            }

            System.out.print("Would you like to add another trip offering? Type yes or no: ");
            if (!scanner.nextLine().equalsIgnoreCase("yes"))
                break;
        }
    }

    public static void changeDriver(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter the trip Number: ");
        int trip = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the date in the format YYYY-MM-DD: ");
        String date = scanner.nextLine();
        System.out.print("Enter the scheduled start time in the format HH:MM:SS: ");
        String start = scanner.nextLine();
        System.out.print("Enter the new driver name: ");
        String newDriver = scanner.nextLine();

        String update = "UPDATE TripOffering SET DriverName = ? WHERE TripNumber = ? AND Date = ? AND ScheduledStartTime = ?";
        try (PreparedStatement stmt = conn.prepareStatement(update)) {
            stmt.setString(1, newDriver);
            stmt.setInt(2, trip);
            stmt.setDate(3, java.sql.Date.valueOf(date));
            stmt.setTime(4, Time.valueOf(start));
            stmt.executeUpdate();
            System.out.println("\nDriver updated.");
        }
    }

    public static void changeBus(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter the trip number: ");
        int trip = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the date in the format YYYY-MM-DD: ");
        String date = scanner.nextLine();
        System.out.print("Enter the scheduled start time in the format HH:MM:SS: ");
        String start = scanner.nextLine();
        System.out.print("Enter New Bus ID: ");
        int newBus = Integer.parseInt(scanner.nextLine());

        String update = "UPDATE TripOffering SET BusID = ? WHERE TripNumber = ? AND Date = ? AND ScheduledStartTime = ?";
        try (PreparedStatement stmt = conn.prepareStatement(update)) {
            stmt.setInt(1, newBus);
            stmt.setInt(2, trip);
            stmt.setDate(3, java.sql.Date.valueOf(date));
            stmt.setTime(4, Time.valueOf(start));
            stmt.executeUpdate();
            System.out.println("\nBus updated.");
        }
    }
}