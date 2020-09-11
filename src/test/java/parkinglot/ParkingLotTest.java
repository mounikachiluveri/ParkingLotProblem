package parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    private ParkingLotSystem parkingLot;

    @Before
    public void setup(){
        parkingLot = new ParkingLotSystem();
    }

    //UC1
    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle();
        parkingLot.parkVehicle(vehicle1);
        boolean isParked = parkingLot.isVehicleParked();
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenVehicle_IfPresent_ShouldThrowException() {
        try {
            Vehicle vehicle1 = new Vehicle();
            parkingLot.parkVehicle(vehicle1);
            parkingLot.parkVehicle(vehicle1);
        }catch (ParkingLotException e){
            Assert.assertEquals(e.type, ParkingLotException.ExceptionType.ALREADY_PRESENT);
        }
    }

    //UC2
    @Test
    public void givenVehicleNumber_WhenUnParked_ShouldUnParkAndReturnFalse() {
        Vehicle vehicle1 = new Vehicle();
        parkingLot.unParkVehicle(vehicle1);
        boolean isParked = parkingLot.isVehicleParked();
        Assert.assertFalse(isParked);
    }


    //UC3
    @Test
    public void givenVehiclesToPark_WhenCapacityExceeds_ShouldThrowException() {
        try {
            Vehicle vehicle1 = new Vehicle();
            Vehicle vehicle2 = new Vehicle();
            Vehicle vehicle3 = new Vehicle();
            parkingLot.parkVehicle(vehicle1);
            parkingLot.parkVehicle(vehicle2);
            parkingLot.parkVehicle(vehicle3);
        } catch (ParkingLotException e) {
            Assert.assertEquals(e.type, ParkingLotException.ExceptionType.CAPACITY_EXCEEDED);
        }
    }
}