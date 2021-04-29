package ru.job4j.ood.lsp.parking;

public class ParkHandler {
    private ParkSpaceList spaces;
    private CarList cars;

    public ParkHandler(CarList cars) {
        this.cars = cars;
    }

    public void allocate(CarList cars, ParkSpaceList spaces) {

    }
}
