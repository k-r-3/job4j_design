package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingService {
    private List<Parking> parking = new ArrayList<>();

    public ParkingService(List<Parking> parking) {
        this.parking = parking;
    }

    public boolean park(Car car) {
        return false;
    }
}
