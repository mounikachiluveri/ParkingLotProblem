package parkinglot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ParkingLotSystem {

    private final List<String> vehicleList;

    public ParkingLotSystem() {
        this.vehicleList = new ArrayList<>();
    }

    public int vehicleParking(String[] vehicle) {
        addVehicle(vehicle);
        return vehicleList.size();
    }

    private void addVehicle(String[] vehicles) {
        vehicleList.addAll(Arrays.asList(vehicles));
    }

    public boolean vehicleUnparking(String vehicleNumber) {
        if(vehicleList.contains(vehicleNumber)) {
            vehicleList.remove(vehicleNumber);
            return true;
        }
        return false;
    }
}