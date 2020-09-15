package com.bridgelabz.parkinglot.service;

import com.bridgelabz.parkinglot.enums.VehicleColour;
import com.bridgelabz.parkinglot.enums.VehicleCompany;
import com.bridgelabz.parkinglot.exception.ParkingLotException;
import com.bridgelabz.parkinglot.enums.DriverType;
import com.bridgelabz.parkinglot.model.ParkingVehicleDetails;
import com.bridgelabz.parkinglot.enums.VehicleSize;
import com.bridgelabz.parkinglot.model.Vehicle;

import java.util.*;

public class ParkingLotSystem {
    public List<ParkingLot> parkingLots;
    private Object Vehicle;

    public ParkingLotSystem(ParkingLot... parkingLot) {
        this.parkingLots = new ArrayList<>(Arrays.asList(parkingLot));
    }

    public void addParking(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    public int getNumberOfParkingLots() {
        return this.parkingLots.size();
    }

    public void park(ParkingVehicleDetails vehicle) throws ParkingLotException {
        ParkingLot parkingLotAlLot = null;
        List<ParkingLot> lots = this.parkingLots;
        for (ParkingLot parkingLot : lots) {
            parkingLot.checkVehicleAlreadyPresent(vehicle);
        }
        if (vehicle.getVehicleSize().equals(VehicleSize.LARGE)) {
            parkingLotAlLot = LotAllotmentService.getLotForLarge(this.parkingLots);
        }
        if (vehicle.getDriverType().equals(DriverType.HANDICAPPED)) {
            parkingLotAlLot = LotAllotmentService.getLotForHandicapped(this.parkingLots);
        }
        if (vehicle.getDriverType().equals(DriverType.NORMAL)) {
            parkingLotAlLot = LotAllotmentService.getLotForNormal(this.parkingLots);
        }
        parkingLotAlLot.parkVehicle(vehicle);
    }

    public void unPark(ParkingVehicleDetails vehicle) throws ParkingLotException {
        ParkingLot parkingLot = this.getParkingLotInWhichVehicleIsParked(vehicle);
        parkingLot.unParkVehicle(vehicle);
    }

    public ParkingLot getParkingLotInWhichVehicleIsParked(ParkingVehicleDetails vehicle) throws ParkingLotException {
        for (ParkingLot parkingLot : this.parkingLots) {
            if (parkingLot.isPresent(vehicle)) {
                return parkingLot;
            }
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_A_VEHICLE, "No Vehicle Found");
    }

    public Integer getParkingSlot(ParkingVehicleDetails vehicle) throws ParkingLotException {
        ParkingLot parkingLot = this.getParkingLotInWhichVehicleIsParked(vehicle);
        return parkingLot.getPositionOfVehicle(vehicle);
    }

    public Map<ParkingLot, List<Integer>> getLotAndSlotListOfVehiclesByColor(VehicleColour vehicleColour) {
        Map<ParkingLot, List<Integer>> vehiclesWithSpecificColor = new HashMap<>();
        for (ParkingLot parkingLot : this.parkingLots) {
            List<Integer> listOfSlots = parkingLot.getListOfSlotsByColour(vehicleColour);
            if (listOfSlots.size() > 0) {
                vehiclesWithSpecificColor.put(parkingLot, listOfSlots);
            }
        }
        return vehiclesWithSpecificColor;
    }

    public Map<ParkingLot, List<Integer>> getLotAndSlotNumberByCompanyAndColor(VehicleCompany vehicleCompany, VehicleColour vehicleColour) {
        Map<ParkingLot, List<Integer>> vehicleByCompanyAndColour = new HashMap<>();
        for (ParkingLot parkingLot : this.parkingLots) {
            List<Integer> listOfSlots = parkingLot.getSlotNumbersByCompanyAndColour(vehicleCompany, vehicleColour);
            if (listOfSlots.size() > 0) {
                vehicleByCompanyAndColour.put(parkingLot, listOfSlots);
            }
        }
        return vehicleByCompanyAndColour;
    }

    public Map<ParkingLot, List<Integer>> getLotAndSlotNumberBySizeAndHandicapped(VehicleSize vehiclesize, DriverType drivertype) {
        Map<ParkingLot, List<Integer>> vehicleBySizeAndType = new HashMap<>();
        for (ParkingLot parkingLot : this.parkingLots) {
            List<Integer> listOfSlots = parkingLot.getSlotNumbersBySizeAndType(vehiclesize, drivertype);
            if (listOfSlots.size() > 0) {
                vehicleBySizeAndType.put(parkingLot, listOfSlots);
            }
        }
        return vehicleBySizeAndType;
    }

}
