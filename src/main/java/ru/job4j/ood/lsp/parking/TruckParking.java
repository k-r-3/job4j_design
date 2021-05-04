package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class TruckParking implements Parking {
    private List<Car> cars  = new ArrayList<>();
    private Parking extraParking;
    private int size;

    public TruckParking(int size, Parking extraParking) {
        this.size = size;
        this.extraParking = extraParking;
    }

    @Override
    public boolean park(Car car) {
        return false;
    }
}
