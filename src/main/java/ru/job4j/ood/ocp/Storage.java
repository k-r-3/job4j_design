package ru.job4j.ood.ocp;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static List<Integer> elements = new ArrayList<>();
    private int element;

    public Storage(int element) {
        this.element = element;
    }

    public void add() {
        elements.add(element);
    }
}
