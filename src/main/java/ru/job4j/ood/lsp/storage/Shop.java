package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
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
        float expirationPercent = (((food.getExpiryDate() - days) / food.getExpiryDate()) * 100);
        if (days > 0 && (expirationPercent > 25)) {
            if (expirationPercent > 75) {
                food.setDiscount(30);
            }
            add(food);
        }
    }
}
