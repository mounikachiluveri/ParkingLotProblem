package parkinglot;

public class ParkingLotOwner implements ParkingLotObserver {
    private boolean parking;

    public boolean getParkingStatus() {
        return parking;
    }

    public void setParkingAvailability(boolean parking) {
        this.parking = parking;
    }
}

