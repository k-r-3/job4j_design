package ru.job4j.generics;

public class Tiger extends Predator {
    private String name = getClass().getSimpleName();

    @Override
    public String toString() {
        return name;
    }
}
