package ru.job4j.ood.dip.factory;

import java.util.List;

public interface Generator<T> {

    List<T> gen(int amount);
}
