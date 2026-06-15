import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bus extends Vehicle {
    private int pricePerStop;
    private String[] route;

    public Bus(String id, int pricePerStop, String[] route) {
        super(id);
        this.pricePerStop = pricePerStop;
        this.route = route;
    }

    public int getPricePerStop() {
        return pricePerStop;
    }

    public String[] getRoute() {
        return route;
    }

    public String toString() {
        return "Bus " + getId() + " on route " + String.join(", ", route);
    }

    @Override
    public int getTakings() {
        int totalStops = 0;
        List<String> routeAsAList = Arrays.asList(route);
        for (Booking booking : getBookings()) {
            String pickupLocation = booking.pickupLocation();
            String destination = booking.destination();
            int pickupIndex = Arrays.asList(route).indexOf(pickupLocation);
            int destinationIndex = Arrays.asList(route).indexOf(destination);
            if (pickupIndex != -1 && destinationIndex != -1 && pickupIndex < destinationIndex) {
                totalStops += (destinationIndex - pickupIndex);
            } else{
                totalStops += (this.route.length - 1) + destinationIndex;
            }
        }
        return totalStops * this.pricePerStop;


    }
}




