package ru.job4j.generics;

public class Predator extends Animal {
    private String name = getClass().getSimpleName();

    @Override
    public String toString() {
        return name;
    }
}
