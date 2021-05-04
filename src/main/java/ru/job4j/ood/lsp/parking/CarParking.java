package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class CarParking implements Parking {
    private List<Car> cars  = new ArrayList<>();
    private int size;
    private int occupied;

    public CarParking(int size) {
        this.size = size;
    }

    @Override
    public boolean park(Car car) {
        if (size > occupied) {
            occupied++;
            return cars.add(car);
        }
        return false;
    }
}
