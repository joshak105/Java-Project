import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CabCompany {

    private HashMap<String, Vehicle> vehicles;

    /**
     * Store the vehicles.
     *
     * @param theVehicles The company's vehicles.
     */
    public CabCompany(ArrayList<Vehicle> theVehicles) {
        this.vehicles = new HashMap<>();
        for (Vehicle vehicle : theVehicles) {
            String vehicleId = vehicle.getId();
            vehicles.put(vehicleId, vehicle);


        }
    }

    public void listAllVehicles() {
        Collection<Vehicle> vehicleCollection = vehicles.values();
        for (Vehicle vehicle : vehicleCollection) {
            System.out.println(vehicle.toString());
        }


    }


    public int getTotalTakings(String id) {
        Vehicle vehicle = vehicles.get(id);
        if (vehicle != null) {
            return vehicle.getTakings();
        }
    return 0;
    }

    public void readBookings(String bookingsFilename) throws IOException {
        Path filePath = Paths.get(bookingsFilename);
        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String vehicleId = parts[0].trim();
                String pickupLocation = parts[1].trim();
                String destination = parts[2].trim();
                Booking newBooking = new Booking(vehicleId, pickupLocation, destination);
                Vehicle vehicle = vehicles.get(vehicleId);
                if (vehicle != null) {
                    vehicle.addBooking(newBooking);


                }
            }
        }
    }

    public void writeReport(String reportFilename) throws IOException {

        try (PrintWriter writer = new PrintWriter(reportFilename)) {
            writer.println();
            writer.println("Cab Company Report");
            writer.println();
            int totalDayTakings = 0;
            for (Vehicle vehicle : vehicles.values()) {
                int takings = vehicle.getTakings();
                int numberOfBookings = vehicle.getBookings().size();
                totalDayTakings += takings;
                if (vehicle instanceof Cab) {
                    writer.println("Cab " + vehicle.getId() + " has " + numberOfBookings + " bookings and total takings of £" + takings);
                } else if (vehicle instanceof Bus) {
                    writer.println("Bus " + vehicle.getId() + " has " + numberOfBookings + " bookings and total takings of £" + takings);
                }
            }
            writer.println("Total takings for the day: £" + totalDayTakings);




        }
    }
    public void listAllBookings() {
        for (Vehicle vehicle : vehicles.values()) {
            System.out.println("Bookings for " + vehicle.toString() + ":");
            List<Booking> bookings = vehicle.getBookings();
            for (Booking booking : bookings) {
                System.out.println( "  " + booking.toString());
            }
        }
                System.out.println();
    }
}


