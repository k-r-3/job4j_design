package ru.job4j.ood.lsp.storage;

import java.time.LocalDate;
import java.util.List;

public class ControllQuality {
    private final static LocalDate DATE = LocalDate.of(2021, 4, 29);
    protected List<Food> foods;
    private AvailableStorage storages;

    public ControllQuality(List<Food> foods, AvailableStorage storages) {
        this.foods = foods;
        this.storages = storages;
        validateSort(foods);
    }

    private float getRemaining(Food food) {
        return food.getExpiryDate() - (DATE
                .getDayOfYear() - food.getCreateDate()
                .getDayOfYear());
    }

    void validateSort(List<Food> foods) {
        for (Food food : foods) {
            float daysLeft = getRemaining(food);
            for (Storage storage : storages.getStorageList()) {
                storage.validate(food, daysLeft);
            }
        }
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        for (Storage storage : storages.getStorageList()) {
            sb.append("Foods in " + storage.getClass().getSimpleName() + " : ")
                    .append(System.lineSeparator())
                    .append(storage.getFoods().toString())
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}