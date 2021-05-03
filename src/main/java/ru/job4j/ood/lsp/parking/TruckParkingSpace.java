package ru.job4j.ood.lsp.parking;

public class TruckParkingSpace implements Parking {
    private Car car;

    public TruckParkingSpace(Car car) {
        this.car = car;
    }

    @Override
    public boolean park(Car car) {
        return false;
    }
}
