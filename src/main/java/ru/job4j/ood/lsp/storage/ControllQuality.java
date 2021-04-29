package ru.job4j.ood.lsp.storage;

import java.time.LocalDate;
import java.util.List;

public class ControllQuality {
    private final static LocalDate DATE = LocalDate.of(2021, 4, 29);
    protected List<Food> foods;
    protected Storage storage;

    public ControllQuality(List<Food> foods, Storage storage) {
        this.foods = foods;
        this.storage = storage;
        validate(foods);
    }

    public Storage getStorage() {
        return storage;
    }

    protected float getRemaining(Food food) {
        return food.getExpiryDate() - (DATE
                .getDayOfYear() - food.getCreateDate()
                .getDayOfYear());
    }

    void validate(List<Food> foods) {
        for (Food food : foods) {
            if (getRemaining(food) > 0) {
                storage.add(food);
            }
        }
    }
}