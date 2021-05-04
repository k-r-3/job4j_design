package ru.job4j.ood.lsp.parking;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingServiceTest {

    @Test
    public void whenPassengerParking() {
        ParkingService service = new ParkingService(List.of(new CarParking(3)));
        service.park(new PassengerCar());
        service.park(new PassengerCar());
        assertThat(service.park(new PassengerCar()), is(true));
    }

    @Test
    public void whenPassengerOccupied() {
        ParkingService service = new ParkingService(List.of(new CarParking(3)));
        service.park(new PassengerCar());
        service.park(new PassengerCar());
        service.park(new PassengerCar());
        assertThat(service.park(new PassengerCar()), is(false));
    }

    @Test
    public void whenPassengerImposs() {
        ParkingService service = new ParkingService(List.of(new TruckParking(4)));
        assertThat(service.park(new PassengerCar()), is(false));
    }

    @Test
    public void whenTruckParking() {
        ParkingService service = new ParkingService(List.of(
                new CarParking(3),
                new TruckParking(4))
        );
        service.park(new Truck(2));
        service.park(new Truck(2));
        assertThat(service.park(new Truck(3)), is(true));
    }

    @Test
    public void whenTruckImposs() {
        ParkingService service = new ParkingService(List.of(
                new CarParking(3),
                new TruckParking(3))
        );
        service.park(new PassengerCar());
        service.park(new PassengerCar());
        service.park(new PassengerCar());
        assertThat(service.park(new Truck(4)), is(false));
    }

}