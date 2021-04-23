package ru.job4j.wrong;

public class Conveyor implements AutoFactory {
    @Override
    public Auto createAuto() {
        return new Auto(4, "Lada");
    }
}
