package parkinglot;

public class ParkingLotOwner implements IInformingAuthority {
    private boolean parking;

    public boolean getParkingStatus() {
        return parking;
    }

    public void setParkingAvailability(boolean parking) {
        this.parking = parking;
    }
}

