package parkinglot;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        String[] vehicleNumber = { "AAA", "BBB"};
        int parked = parkingLotSystem.vehicleParking(vehicleNumber);
        Assert.assertEquals(2,parked);
    }
}