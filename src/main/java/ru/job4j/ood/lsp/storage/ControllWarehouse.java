package ru.job4j.ood.lsp.storage;

import java.util.List;

public class ControllWarehouse extends ControllQuality {

    public ControllWarehouse(List<Food> foods, Storage storage) {
        super(foods, storage);
    }

    @Override
    public void validate(List<Food> foods) {
        for (Food food : foods) {
            float days = getRemaining(food);
            if (days > 0
                    && (((days - food.getExpiryDate()) / food.getExpiryDate()) * 100) < 25) {
                storage.add(food);
            }
        }
    }
}