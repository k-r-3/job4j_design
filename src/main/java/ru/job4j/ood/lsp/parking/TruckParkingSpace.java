package ru.job4j.ood.lsp.parking;

public class TruckParkingSpace implements ParkingSpace {
    private Car car;

    public TruckParkingSpace(Car car) {
        this.car = car;
    }

    @Override
    public void place(Car car) {

    }
}
