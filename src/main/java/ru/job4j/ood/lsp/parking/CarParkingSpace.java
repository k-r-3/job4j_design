package ru.job4j.ood.lsp.parking;

public class CarParkingSpace implements ParkingSpace {
    private Car car;

    public CarParkingSpace(Car car) {
        this.car = car;
    }

    @Override
    public void place(Car car) {

    }
}
