package ru.job4j.ood.lsp.storage;

import java.util.List;

public class ControllTrash extends ControllQuality {

    public ControllTrash(List<Food> foods, Storage storage) {
        super(foods, storage);
    }

    @Override
    public void validate(List<Food> foods) {
        for (Food food : foods) {
            if (getRemaining(food) <= 0) {
                storage.add(food);
            }
        }
    }
}
