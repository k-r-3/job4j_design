package ru.job4j.ood.lsp;

class Vehicle {
    private int wheelsAmount;

    private int seatAmount;

    private boolean engine;

    public Vehicle(int wheelsAmount, int seatAmount, boolean engine) {
        this.wheelsAmount = wheelsAmount;
        this.seatAmount = seatAmount;
        this.engine = engine;
    }

    public int getWheelsAmount() {
        return wheelsAmount;
    }

    public int getSeatAmount() {
        return seatAmount;
    }

    public boolean isEngine() {
        return engine;
    }
}

class CarConveyor {
    protected Vehicle vehicle;

    public CarConveyor(Vehicle vehicle) {
        this.vehicle = vehicle;
        validate(vehicle);
    }

    protected void validate(Vehicle vehicle) {
        if (vehicle.getWheelsAmount() < 4) {
            throw new IllegalArgumentException("Invalid amount of wheels");
        }
        if (vehicle.getSeatAmount() < 2 && vehicle.getSeatAmount() > 5) {
            throw new IllegalArgumentException("Invalid amount of seats");
        }
        if (!vehicle.isEngine()) {
            throw new IllegalArgumentException("The Car needs an engine");
        }
    }

    public void setVehicle(Vehicle vehicle) {
        validate(vehicle);
        this.vehicle = vehicle;
    }
}

class SomeConveyor extends CarConveyor {

    public SomeConveyor(Vehicle vehicle) {
        super(vehicle);
    }

    @Override
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}

public class Invariant {
    public static void main(String[] args) {
        CarConveyor conveyor = new SomeConveyor(new Vehicle(4, 4, true));
        conveyor.setVehicle(new Vehicle(2, 1, false));

    }
}
