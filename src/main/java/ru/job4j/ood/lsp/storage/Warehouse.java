package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {
    private List<Food> foods = new ArrayList<>();

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
        float expirationPercent = (
                ((food.getExpiryDate() - remainingDays) / food.getExpiryDate()) * 100
        );
        if (remainingDays > 0 && expirationPercent < 25) {
            return true;
        }
        return false;
    }
}
