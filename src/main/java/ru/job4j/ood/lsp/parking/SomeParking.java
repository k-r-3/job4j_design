package ru.job4j.ood.lsp.parking;

public class SomeParking implements Parking {
    private static final int PASSENGER_CAR_SIZE = PassengerCar.SIZE;
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
        if (car.getCarSize() > PASSENGER_CAR_SIZE) {
            if (truckOccupied < trucks.length) {
                trucks[truckOccupied++] = true;
                return true;
            }
        }
        return passengerCarPlace(car);
    }

    private boolean passengerCarPlace(Car car) {
        int freePlaces = cars.length - carOccupied;
        if (freePlaces >= car.getCarSize()) {
            for (int i = 0; i < car.getCarSize(); i++) {
                cars[carOccupied++] = true;
            }
            return true;
        }
        return false;
    }
}
