package ru.job4j.generics;

public class Animal {
    private String name = getClass().getSimpleName();

    @Override
    public String toString() {
        return name;
    }
}
