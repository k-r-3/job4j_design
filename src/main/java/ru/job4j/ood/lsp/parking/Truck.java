package ru.job4j.ood.lsp.parking;

public class Truck implements Car {
    private int size;

    public Truck(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
