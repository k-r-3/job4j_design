package ru.job4j.ood.lsp.parking;

public class Truck implements Car {
    private int carSize;

    public Truck(int carSize) {
        if (carSize <= 1) {
            throw new IllegalArgumentException("truck size more than 1");
        }
        this.carSize = carSize;
    }

    @Override
    public int getCarSize() {
        return carSize;
    }
}
