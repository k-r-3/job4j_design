package ru.job4j.ood.lsp.parking;

public class PassengerCar implements Car {
    static final int SIZE = 1;

    @Override
    public int getCarSize() {
        return SIZE;
    }
}
