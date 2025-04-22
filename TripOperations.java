import java.sql.*;
import java.util.*;

public class TripOperations {
    public static void displayTripSchedule(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter the start location name: ");
        String start = scanner.nextLine();
        System.out.print("Enter the destination name: ");
        String dest = scanner.nextLine();
        System.out.print("Enter the date in the format YYYY-MM-DD: ");
        String date = scanner.nextLine();

        String query = "SELECT toff.ScheduledStartTime, toff.ScheduledArrivalTime, toff.DriverName, toff.BusID " +
                "FROM TripOffering toff JOIN Trip t ON toff.TripNumber = t.TripNumber " +
                "WHERE t.StartLocationName = ? AND t.DestinationName = ? AND toff.Date = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, start);
            stmt.setString(2, dest);
            stmt.setDate(3, java.sql.Date.valueOf(date));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("\nStart Time: " + rs.getString(1) +
                        "\nArrival Time: " + rs.getString(2) +
                        "\nDriver: " + rs.getString(3) +
                        "\nBusID: " + rs.getString(4));
            }
        }
    }

    public static void displayTripStops(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter the trip number: ");
        int trip = Integer.parseInt(scanner.nextLine());

        String query = "SELECT StopNumber, SequenceNumber, DrivingTime FROM TripStopInfo WHERE TripNumber = ? ORDER BY SequenceNumber";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, trip);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(
                        "\nStop #: " + rs.getInt(1) + ", Seq #: " + rs.getInt(2) + ", Drive Time: " + rs.getTime(3));
            }
        }
    }

    public static void recordActualTripData(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter the trip number: ");
        int trip = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the date in the format YYYY-MM-DD: ");
        String date = scanner.nextLine();
        System.out.print("Enter the scheduled start time in the format HH:MM:SS: ");
        String startTime = scanner.nextLine();
        System.out.print("Enter the stop number: ");
        int stop = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the scheduled arrival time in the format HH:MM:SS: ");
        String schedArrival = scanner.nextLine();
        System.out.print("Enter the actual start time in the format HH:MM:SS: ");
        String actualStart = scanner.nextLine();
        System.out.print("Enter the actual arrival time in the format HH:MM:SS: ");
        String actualArrival = scanner.nextLine();
        System.out.print("Enter the num of passengers in: ");
        int in = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the num of passengers out: ");
        int out = scanner.nextInt();
        scanner.nextLine();

        String insert = "INSERT INTO ActualTripStopInfo VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insert)) {
            stmt.setInt(1, trip);
            stmt.setDate(2, java.sql.Date.valueOf(date));
            stmt.setTime(3, Time.valueOf(startTime));
            stmt.setInt(4, stop);
            stmt.setTime(5, Time.valueOf(schedArrival));
            stmt.setTime(6, Time.valueOf(actualStart));
            stmt.setTime(7, Time.valueOf(actualArrival));
            stmt.setInt(8, in);
            stmt.setInt(9, out);
            stmt.executeUpdate();
            System.out.println("\nActual trip stop data inserted.");
        }
    }

    public static void recordActualTripStopInfo(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter the trip Number: ");
        int trip = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter the date in the format YYYY-MM-DD: ");
        String date = scanner.nextLine();

        System.out.print("Enter the scheduled start time in the format HH:MM:SS: ");
        String scheduledStartTime = scanner.nextLine();

        System.out.print("Enter the stop number: ");
        int stopNumber = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter the scheduled arrival time in the format HH:MM:SS: ");
        String scheduledArrivalTime = scanner.nextLine();

        System.out.print("Enter the actual start time in the format HH:MM:SS: ");
        String actualStartTime = scanner.nextLine();

        System.out.print("Enter the actual arrival time in the format HH:MM:SS: ");
        String actualArrivalTime = scanner.nextLine();

        System.out.print("Enter the number of passengers in: ");
        int numIn = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter the number of passengers out: ");
        int numOut = Integer.parseInt(scanner.nextLine());

        String sql = "INSERT INTO ActualTripStopInfo VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, trip);
        stmt.setDate(2, java.sql.Date.valueOf(date));
        stmt.setString(3, scheduledStartTime);
        stmt.setInt(4, stopNumber);
        stmt.setString(5, scheduledArrivalTime);
        stmt.setString(6, actualStartTime);
        stmt.setString(7, actualArrivalTime);
        stmt.setInt(8, numIn);
        stmt.setInt(9, numOut);
        stmt.executeUpdate();

        System.out.println("\nActual trip stop info recorded.");
    }
}