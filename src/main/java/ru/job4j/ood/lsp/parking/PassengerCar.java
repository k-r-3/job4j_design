package ru.job4j.ood.lsp.parking;

public class PassengerCar implements Car {
    private int size;

    public PassengerCar(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
