package ru.job4j.ood.lsp.parking;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TruckParkingTest {

    @Test
    public void whenParking() {
        Car volvo = new Truck(3);
        Car man = new Truck(2);
        Car scania = new Truck(2);
        Parking parking = new CarParking(3);
        Parking truckParking = new TruckParking(4, parking);
        truckParking.park(scania);
        truckParking.park(man);
        assertThat(truckParking.park(volvo), is(true));
    }

    @Test
    public void whenOccupied() {
        Car volvo = new Truck(3);
        Car man = new Truck(2);
        Car scania = new Truck(2);
        Car toyota = new PassengerCar();
        Car bmw = new PassengerCar();
        Parking parking = new CarParking(3);
        Parking truckParking = new TruckParking(4, parking);
        truckParking.park(scania);
        truckParking.park(man);
        parking.park(toyota);
        parking.park(bmw);
        assertThat(truckParking.park(volvo), is(false));
    }

    @Test
    public void whenPassengerCar() {
        Car volvo = new PassengerCar();
        Parking parking = new CarParking(3);
        Parking truckParking = new TruckParking(4, parking);
        assertThat(truckParking.park(volvo), is(false));
    }

}