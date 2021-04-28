package ru.job4j.ood.lsp.storage;

import java.time.LocalDate;

public interface Storage {
    final static LocalDate DATE = LocalDate.of(2021, 4, 29);

    void add(Food food);

    String getFoods();

    void validate(Food food);

    default float getRemaining(Food food) {
        return food.getExpiryDate() - (DATE
                .getDayOfYear() - food.getCreateDate()
                .getDayOfYear());
    }
}
