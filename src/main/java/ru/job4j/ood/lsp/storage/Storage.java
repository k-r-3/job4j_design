package ru.job4j.ood.lsp.storage;

import java.util.List;

public interface Storage {

    void add(Food food);

    List<Food> getFoods();

    void validate(Food foods, float remainingDays);

}
