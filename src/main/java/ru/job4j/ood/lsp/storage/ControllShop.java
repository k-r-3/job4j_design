package ru.job4j.ood.lsp.storage;

import java.util.List;

public class ControllShop extends ControllQuality {

    public ControllShop(List<Food> foods, Storage storage) {
        super(foods, storage);
    }

    @Override
    public void validate(List<Food> foods) {
        for (Food food : foods) {
            float days = getRemaining(food);
            float expirationPercent = (
                    ((food.getExpiryDate() - days) / food.getExpiryDate()) * 100
            );
            if (days > 0 && (expirationPercent > 25)) {
                if (expirationPercent > 75) {
                    food.setDiscount(30);
                }
                storage.add(food);
            }
        }
    }
}
