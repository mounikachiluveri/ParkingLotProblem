package parkinglot;

public class ParkingLotOwner implements ParkingLotObserver {
    private boolean parking;
    static int lotNumber = 1;

    public boolean getParkingCapacity() {
        return parking;
    }

    @Override
    public void setParkingCapacity(boolean parking) {
        this.parking = parking;
    }

    public int getKey() {
        return lotNumber++;
    }
}