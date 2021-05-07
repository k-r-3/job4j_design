package ru.job4j.ood.lsp.parking;

public class SomeParking implements Parking {
    private final boolean[] cars;
    private final boolean[] trucks;
    private int carOccupied;
    private int truckOccupied;

    public SomeParking(int carAmount, int truckAmount) {
        cars = new boolean[carAmount];
        trucks = new boolean[truckAmount];
    }

    @Override
    public boolean park(Car car) {
        return false;
    }
}
