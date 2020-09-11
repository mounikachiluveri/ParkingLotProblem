package parkinglot;

import java.util.ArrayList;

public class ParkingLotSystem {

    private static final int MAX_CAPACITY = 2;
    private final ArrayList<Vehicle> parkingList;

    private final ArrayList<ParkingLotObserver> observerList;

    public ParkingLotSystem() {
        this.parkingList = new ArrayList<>();
        this.observerList = new ArrayList<>();
    }

    public void addObserver(ParkingLotObserver observer) {
        observerList.add(observer);
    }

    public void parkVehicle(Vehicle vehicle) throws ParkingLotException {
        if (parkingList.contains(vehicle))
            throw new ParkingLotException("Present in parking lot",
                    ParkingLotException.ExceptionType.ALREADY_PRESENT);
        if (parkingList.size() < MAX_CAPACITY)
            parkingList.add(vehicle);
        else if (parkingList.size() == MAX_CAPACITY) {
            throw new ParkingLotException("Parking Capacity is full",
                    ParkingLotException.ExceptionType.CAPACITY_EXCEEDED);
        }
        if (parkingList.size() == MAX_CAPACITY)
            this.notifyAllObservers(false);
    }

    public void unParkVehicle(Vehicle vehicle) {
        if (parkingList.contains(vehicle)) {
            parkingList.remove(vehicle);
            this.notifyAllObservers(true);
        }
    }

    private void notifyAllObservers(boolean parkingStatus) {
        for (ParkingLotObserver observer : observerList)
            observer.setParkingAvailability(parkingStatus);
    }

    public boolean isVehicleParked(Vehicle vehicle) {
        return parkingList.contains(vehicle);
    }
}
