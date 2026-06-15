import java.util.List;

public class Cab extends Vehicle {
    private String driverName;
    private int pricePerJourney;

    public Cab(String id, String driverName, int pricePerJourney) {
        super(id);
        this.driverName = driverName;
        this.pricePerJourney = pricePerJourney;
    }
    public String getDriverName() {
        return driverName;
    }
    public int getPricePerJourney() {
        return pricePerJourney;
    }
    public String toString() {
        return "Cab " + getId() + " driven by " + driverName;
    }

    @Override
    public int getTakings() {
       int numberOfBookings = getBookings().size();
       return numberOfBookings * pricePerJourney;
    }
    }







