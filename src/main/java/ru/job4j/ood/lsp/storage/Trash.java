package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Storage {
    private List<Food> foods;

    public Trash(List<Food> foods) {
        this.foods = foods;
    }

    @Override
    public boolean add(Food food, float remainingDays) {
        if (validate(food, remainingDays)) {
            foods.add(food);
            return true;
        }
        return false;
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }

    @Override
    public boolean validate(Food food, float remainingDays) {
        return remainingDays <= 0;
    }
}
