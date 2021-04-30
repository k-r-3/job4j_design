package ru.job4j.ood.lsp.storage;

import java.time.LocalDate;
import java.util.List;

public class ControllQuality {
    private final static LocalDate DATE = LocalDate.now();
    private List<Food> foods;
    private List<Storage> storages;

    public ControllQuality(List<Food> foods, List<Storage> storages) {
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
            for (Storage storage : storages) {
                storage.add(food, daysLeft);
            }
        }
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        for (Storage storage : storages) {
            sb.append("Foods in " + storage.getClass().getSimpleName() + " : ")
                    .append(System.lineSeparator())
                    .append(storage.getFoods().toString())
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}