package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Storage {
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
        if (getRemaining(food) <= 0) {
            add(food);
        }
    }
}
