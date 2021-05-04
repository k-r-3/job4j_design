package ru.job4j.ood.lsp.parking;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CarParkingTest {

    @Test
    public void whenParking() {
        Car toyota = new PassengerCar();
        Car bmw = new PassengerCar();
        Car kia = new PassengerCar();
        Parking parking = new CarParking(3);
        parking.park(toyota);
        parking.park(bmw);
        assertThat(parking.park(kia), is(true));
    }

    @Test
    public void whenOccupied() {
        Car toyota = new PassengerCar();
        Car bmw = new PassengerCar();
        Car kia = new PassengerCar();
        Parking parking = new CarParking(3);
        parking.park(toyota);
        parking.park(bmw);
        parking.park(kia);
        assertThat(parking.park(new PassengerCar()), is(false));
    }

    @Test
    public void whenTruckPoss() {
        Car volvo = new Truck(3);
        Parking parking = new CarParking(3);
        assertThat(parking.park(volvo), is(true));
    }

    @Test
    public void whenTruckImposs() {
        Car volvo = new Truck(3);
        Parking parking = new CarParking(2);
        assertThat(parking.park(volvo), is(false));
    }

}