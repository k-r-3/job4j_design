package ru.job4j.ood.lsp.parking;

public class Truck implements Car {
    private int size;

    public Truck(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("truck size more than 1");
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
