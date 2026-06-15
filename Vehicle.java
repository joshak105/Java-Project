import java.util.ArrayList;
import java.util.HashMap;

public class Vehicle {
    private String id;
    private ArrayList<Booking> bookings;

    public Vehicle(String id) {
        this.id = id;
        this.bookings = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public ArrayList<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }

    public int getTakings() {
        return 0;
    }

    public String toString() {
        return "Vehicle ID:" + id;
    }
}











