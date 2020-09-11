package parkinglot;
import com.bridgelabz.parkinglot.exception.ParkingLotServiceException;
import com.bridgelabz.parkinglot.model.SlotDetails;
import com.bridgelabz.parkinglot.model.Vehicle;
import com.bridgelabz.parkinglot.observer.AirportSecurityService;
import com.bridgelabz.parkinglot.observer.Owner;
import com.bridgelabz.parkinglot.service.ParkingLotService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

public class ParkingLotTest {

    ParkingLotService parkingLotService = null;

    @Before
    public void init() {
        parkingLotService = new ParkingLotService();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle();
        boolean isParked;
        try {
            parkingLotService.parkVehicle(vehicle);
            isParked = parkingLotService.isPresent(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenANullVehicle_WhenParked_ShouldThrowException() {
        Vehicle vehicle = null;
        try {
            parkingLotService.parkVehicle(vehicle);
        } catch (ParkingLotServiceException e) {
            Assert.assertEquals(ParkingLotServiceException.ExceptionType.INVALID_VEHICLE, e.exceptionType);
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldThrowException() {
        parkingLotService.parkingCapacity = 3;
        Vehicle vehicle = new Vehicle();
        try {
            parkingLotService.parkVehicle(vehicle);
            parkingLotService.parkVehicle(vehicle);
        } catch (ParkingLotServiceException e) {
            Assert.assertEquals(ParkingLotServiceException.ExceptionType.VEHICLE_ALREADY_PRESENT, e.exceptionType);
        }
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle();
        boolean isUnParked;
        try {
            parkingLotService.parkVehicle(vehicle);
            parkingLotService.unParkVehicle(vehicle);
            isUnParked = parkingLotService.isPresent(vehicle);
            Assert.assertFalse(isUnParked);
        } catch (ParkingLotServiceException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenVehicles_WhenParkingLotIsFull_ShouldInformSecurity() {
        parkingLotService.parkingCapacity = 1;
        Vehicle vehicle = new Vehicle();
        try {
            parkingLotService.parkVehicle(vehicle);
            AirportSecurityService security = (AirportSecurityService) parkingLotService.parkingLotListeners.get(ParkingLotService.SECURITY);
            Assert.assertEquals("Capacity is Full", security.getParkingLotStatus());
        } catch (ParkingLotServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenNotParkedVehicle_WhenUnParked_ShouldThrowException() {
        Vehicle vehicle = new Vehicle();
        try {
            parkingLotService.unParkVehicle(vehicle);
        } catch (ParkingLotServiceException e) {
            Assert.assertEquals(ParkingLotServiceException.ExceptionType.NO_SUCH_A_VEHICLE, e.exceptionType);
        }
    }

    @Test
    public void givenANullVehicle_WhenUnParked_ShouldThrowException() {
        Vehicle vehicle = null;
        try {
            parkingLotService.unParkVehicle(vehicle);
        } catch (ParkingLotServiceException e) {
            Assert.assertEquals(ParkingLotServiceException.ExceptionType.INVALID_VEHICLE, e.exceptionType);
        }
    }

    @Test
    public void givenVehicles_WhenParkingLotIsFull_ShouldThrowException() {
        parkingLotService.parkingCapacity = 1;
        try {
            parkingLotService.parkVehicle(new Vehicle());
            parkingLotService.parkVehicle(new Vehicle());
        } catch (ParkingLotServiceException e) {
            Assert.assertEquals(ParkingLotServiceException.ExceptionType.PARKING_LOT_IS_FULL, e.exceptionType);
        }
    }

    @Test
    public void givenCapacity_WhenAvailableShould_InformToOwner() {
        Vehicle vehicle = new Vehicle();
        try {
            parkingLotService.parkVehicle(vehicle);
            parkingLotService.unParkVehicle(vehicle);
            Owner owner = (Owner) parkingLotService.parkingLotListeners.get(ParkingLotService.OWNER);
            Assert.assertEquals("Capacity Available", owner.getParkingLotStatus());
        } catch (ParkingLotServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleToAttendant_WhenParkedAsPerProvidedSlot_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle();
        try {
            parkingLotService.parkVehicle(1, vehicle);
            boolean isPresent = parkingLotService.isPresent(vehicle);
            Assert.assertTrue(isPresent);
        } catch (ParkingLotServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenVehicleFound_ShouldReturnVehicleSlot() {
        Vehicle vehicle = new Vehicle();
        try {
            parkingLotService.parkVehicle(4, vehicle);
            int slotNumber = parkingLotService.findVehicle(vehicle);
            Assert.assertEquals(4, slotNumber);
        } catch (ParkingLotServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicle_WhenTimeAllotted_ShouldReturnParkingTime() {
        Vehicle vehicle = new Vehicle();
        try {
            LocalTime testTime = LocalTime.now().withNano(0);
            parkingLotService.parkVehicle(vehicle);
            int slotNumber = parkingLotService.findVehicle(vehicle);
            SlotDetails slotDetails = this.parkingLotService.parkedVehicles.get(slotNumber);
            Assert.assertEquals(testTime, slotDetails.getParkingTime());
        } catch (ParkingLotServiceException e) {
            e.printStackTrace();
        }
    }
}





