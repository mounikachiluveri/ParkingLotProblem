package parkinglot;

public class AirportSecurity implements  ParkingLotObserver{
    private boolean parkingStatus;

    public boolean getParkingCapacity() {
        return parkingStatus;
    }

    @Override
    public void setParkingAvailability(boolean parkingStatus) {
        this.parkingStatus = parkingStatus;
    }
}