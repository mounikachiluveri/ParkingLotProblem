package parkinglot;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        String[] vehicleNumber = { "AAA", "BBB"};
        int parked = parkingLotSystem.vehicleParking(vehicleNumber);
        Assert.assertEquals(2,parked);
    }

    @Test
    public void givenVehicleNumber_WhenParked_ShouldUnParkedReturnTrue() throws ParkingLotException {
        ParkingLotSystem parkingLot = new ParkingLotSystem();
        String[] vehicleNumber = { "111", "222"};
        parkingLot.vehicleParking(vehicleNumber);
        boolean isRemoved = parkingLot.vehicleUnparking("111");
        Assert.assertTrue(isRemoved);
    }

    @Test
    public void givenVehicleNumber_WhenVehicleNotInLot_ShouldReturnFalse() throws ParkingLotException {
        ParkingLotSystem parkingLot = new ParkingLotSystem();
        String[] vehicleNumber = { "111", "222"};
        parkingLot.vehicleParking(vehicleNumber);
        boolean isRemoved = parkingLot.vehicleUnparking("333");
        Assert.assertFalse(isRemoved);
    }

    @Test
    public void givenVehicles_WhenCapacityExceeds_ShouldThrowException() {
        try {
            ParkingLotSystem parkingLot = new ParkingLotSystem();
            String[] vehicleNumber = {"111", "222", "333"};
            parkingLot.vehicleParking(vehicleNumber);
        } catch (ParkingLotException e) {
            Assert.assertEquals(e.type, ParkingLotException.ExceptionType.CAPACITY_EXCEEDED);
        }
    }
}
