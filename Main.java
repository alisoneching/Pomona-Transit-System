
// File: Main.java
import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/pomona_transit?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            while (true) {
                System.out.println("\nPomona Transit System Menu:");
                System.out.println("Please choose a transaction from the following: ");
                System.out.println(
                        "1. Display the schedule of all trips for a given name and date");
                System.out.println("2. Edit the schedule i.e. edit the table of Trip Offering");
                System.out.println("3. Display the stops of a given trip");
                System.out.println("4. Display the weekly schedule of a given driver and date");
                System.out.println("5. Add a driver");
                System.out.println("6. Add a bus");
                System.out.println("7. Delete a bus");
                System.out.println("8. Record the actual trip stop data");
                System.out.println("0. Exit\n");
                System.out.print("Enter a number: ");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> TripOperations.displayTripSchedule(conn, scanner);
                    case 2 -> editTripOfferingMenu(conn, scanner);
                    case 3 -> TripOperations.displayTripStops(conn, scanner);
                    case 4 -> DriverBusOperations.displayDriverSchedule(conn, scanner);
                    case 5 -> DriverBusOperations.addDriver(conn, scanner);
                    case 6 -> DriverBusOperations.addBus(conn, scanner);
                    case 7 -> DriverBusOperations.deleteBus(conn, scanner);
                    case 8 -> TripOperations.recordActualTripStopInfo(conn, scanner);
                    case 0 -> {
                        System.out.println("Exiting program.");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void editTripOfferingMenu(Connection conn, Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\nPlease choose a sub-transaction from the following");
            System.out.println("1. Delete a trip offering");
            System.out.println("2. Add a set of trip offerings");
            System.out.println("3. Change the driver for a given Trip offering");
            System.out.println("4. Change the bus for a given Trip offering");
            System.out.println("0. Go back\n");
            System.out.print("Enter a number: ");

            int sub = Integer.parseInt(scanner.nextLine());
            switch (sub) {
                case 1 -> TripOfferingOperations.deleteTripOffering(conn, scanner);
                case 2 -> TripOfferingOperations.addTripOffering(conn, scanner);
                case 3 -> TripOfferingOperations.changeDriver(conn, scanner);
                case 4 -> TripOfferingOperations.changeBus(conn, scanner);
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}