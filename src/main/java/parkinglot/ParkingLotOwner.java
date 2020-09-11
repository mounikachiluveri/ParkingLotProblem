package parkinglot;

public class ParkingLotOwner implements ParkingLotObserver {
    private boolean parking;

    public boolean getParkingCapacity() {
        return parking;
    }

    @Override
    public void setParkingAvailability(boolean parkingStatus) {
        this.parking = parking;
    }
}