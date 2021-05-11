package ru.job4j.ood.lsp.parking;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SomeParkingTest {

    @Test
    public void whenPassengerCarPark() {
        SomeParking parking = new SomeParking(3, 0);
        parking.park(new PassengerCar());
        parking.park(new PassengerCar());
        assertThat(parking.park(new PassengerCar()), is(true));
    }

    @Test
    public void whenPassengerCarDoNot() {
        SomeParking parking = new SomeParking(2, 1);
        parking.park(new PassengerCar());
        parking.park(new PassengerCar());
        assertThat(parking.park(new PassengerCar()), is(false));
    }

    @Test
    public void whenTruckPark() {
        SomeParking parking = new SomeParking(3, 1);
        parking.park(new PassengerCar());
        parking.park(new Truck(2));
        assertThat(parking.park(new Truck(2)), is(true));
    }

    @Test
    public void whenTruckDoNot() {
        SomeParking parking = new SomeParking(2, 1);
        parking.park(new PassengerCar());
        parking.park(new Truck(2));
        assertThat(parking.park(new Truck(2)), is(false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTruckIncorrectSize() {
        Car scania = new Truck(1);
    }

}