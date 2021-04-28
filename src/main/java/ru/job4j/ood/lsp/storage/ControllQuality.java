package ru.job4j.ood.lsp.storage;

import java.util.List;

public class ControllQuality {
    private List<Food> foods;
    private Storage storage;

    public ControllQuality(List<Food> foods, Storage storage) {
        this.foods = foods;
        this.storage = storage;
        validate(foods);
    }

    public Storage getStorage() {
        return storage;
    }

    void validate(List<Food> foods) {
        for (Food food : foods) {
            storage.validate(food);
        }
    }
}
