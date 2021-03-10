package ru.job4j.serialization.xml;

import java.util.Arrays;

public class MainClass {
    private final boolean tumbler;
    private final int amount;
    private final String name;
    private final InnerObject in;
    private final String[] array;

    public MainClass(boolean tumbler, int amount, String name, InnerObject in, String... array) {
        this.tumbler = tumbler;
        this.amount = amount;
        this.name = name;
        this.in = in;
        this.array = array;
    }

    @Override
    public String toString() {
        return "MainClass{"
                + "tumbler=" + tumbler
                + ", amount=" + amount
                + ", name='" + name + '\''
                + ", in=" + in
                + ", array=" + Arrays.toString(array)
                + '}';
    }

    public static void main(String[] args) {
        final MainClass mainClass = new MainClass(false, 123,
                "main class", new InnerObject(100), "First word", "Second word");
    }
}
