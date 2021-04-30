package ru.job4j.ood.lsp.storage;

import java.util.List;

public interface Storage {

    boolean add(Food food, float remainingDays);

    List<Food> getFoods();

    boolean validate(Food foods, float remainingDays);

}
