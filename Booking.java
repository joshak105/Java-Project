public record Booking(String vehicleId, String pickupLocation, String destination) {
@Override
    public String toString() {
        return "Vehicle: " + vehicleId  + " from " + pickupLocation + " to " + destination;

}



}
