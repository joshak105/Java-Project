import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * A class containing the main method for the taxi company program.
 * There are several test methods that will display the results of
 * different parts of the program.
 */
public class Main
{
    /**
     * The main method for the cab company program.
     * Three arguments are required:
     * 1. The name of the file of vehicle details.
     * 2. The name of the file of bookings.
     * 3. The name of the file for the report.
     * @param args The program arguments.
     * @throws IOException If there is an error reading the files.
     */
    public static void main(String[] args)
            throws IOException {
        if (args.length != 3) {
            System.out.println(
                    "There should be three program arguments: " +
                            "the name of the vehicles file, " +
                            "the name of the bookings file and " +
                            "the name of the output file for the report.");
            System.exit(1);
        } else {
            System.out.printf("Using files: %s, %s and %s", args[0], args[1], args[2]);
        }
        String vehiclesFilename = args[0];
        String bookingsFilename = args[1];
        String reportFilename = args[2];

        // Read the vehicle details.
        ArrayList<Vehicle> theVehicles = readVehicleDetails(vehiclesFilename);
        // TODO: Test the reading of the vehicle details.

        CabCompany cabCompany = new CabCompany(theVehicles);
        cabCompany.listAllVehicles();
        for (Vehicle vehicle : theVehicles) {
            System.out.println(vehicle.toString());
        }


        // TODO: List the vehicles.

        cabCompany.readBookings(bookingsFilename);

        cabCompany.listAllBookings();
        System.out.println();



        // TODO: Test the listing of the bookings.

        // TODO: Test the calculation of the takings.
        cabCompany.getTotalTakings(bookingsFilename);
        for (Vehicle vehicle : theVehicles) {
            String id = vehicle.getId();
            int takings = cabCompany.getTotalTakings(id);
            String type = (vehicle instanceof Bus) ? "Cab" : "Bus";
            System.out.println(type + " " + id + " has total takings of " + "£" + takings);
        }
        cabCompany.writeReport(reportFilename);
       List<String> reportContent = Files.readAllLines(Paths.get(reportFilename));
        for (String line : reportContent) {
            System.out.println(line);
        }


        // TODO: Test the writing of the report.
    }


    private static Vehicle decodeVehicleDetails(String vehicleDetails) {
        String[] parts = vehicleDetails.split(",");
        if (parts.length == 4) {
            String vehicle = parts[0];
            String id = parts[1];
            if (vehicle.equals("C")) {
                String driverName = parts[2];
                int pricePerJourney = Integer.parseInt(parts[3]);
                return new Cab(id, driverName, pricePerJourney);
            } else if (vehicle.equals("B")) {
                int pricePerStop = Integer.parseInt(parts[2]);
                String[] route = parts[3].split(":");
                return new Bus(id, pricePerStop, route);
            }


        }

        return null;
    }






    /**
     * Read the file of vehicle details.
     * DO NOT CHANGE THIS METHOD
     * @param vehiclesFilename The name of the file of vehicle details.
     * @throws IOException  If there is an error reading the file.
     */
    private static ArrayList<Vehicle> readVehicleDetails(String vehiclesFilename)
            throws IOException
    {
        Path filePath = Paths.get(vehiclesFilename);
        List<String> lines = Files.readAllLines(filePath);
        ArrayList<Vehicle> theVehicles = new ArrayList<>();
        for(String vehicleDetails : lines) {
            vehicleDetails = vehicleDetails.trim();
            Vehicle aVehicle = decodeVehicleDetails(vehicleDetails);
            if(aVehicle != null) {
                theVehicles.add(aVehicle);
            }
        }
        return theVehicles;
    }


}
