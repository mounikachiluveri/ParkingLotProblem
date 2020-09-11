package parkinglot;

public class AirportSecurity {
    public boolean parking;

    public void parkingFull() {
        this.parking = true;
    }

    public void parkingAvailable(){
        this.parking = false;
    }
    public boolean getParkingStatus(){
        return this.parking;
    }
}

