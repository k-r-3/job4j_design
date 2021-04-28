package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {
    private List<Food> foods = new ArrayList<>();

    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public String getFoods() {
        return foods.toString();
    }

    @Override
    public void validate(Food food) {
        float days = getRemaining(food);
            if (days > 0
                    && (((days - food.getExpiryDate()) / food.getExpiryDate()) * 100) < 25) {
                add(food);
        }
    }
}
