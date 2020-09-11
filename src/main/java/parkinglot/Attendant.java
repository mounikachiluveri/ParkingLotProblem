package parkinglot;

public class Attendant {
    ParkingLotOwner parkingLotOwner = new ParkingLotOwner();

    public int parkVehicle() {
        return parkingLotOwner.getKey();
    }
}