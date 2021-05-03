package ru.job4j.ood.lsp.parking;

public class CarParkingSpace implements Parking {
    private Car car;

    public CarParkingSpace(Car car) {
        this.car = car;
    }

    @Override
    public boolean park(Car car) {
        return false;
    }
}
