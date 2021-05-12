package ru.job4j.ood.isp.menu;

import java.util.List;
import java.util.Optional;

public class Menu implements Tree<Item> {
    private List<Item> items;

    public Menu(List<Item> items) {
        this.items = items;
    }

    @Override
    public Item getNode(int[] itemPoint) {
        Optional<Item> item = Optional.empty();
        List<Item> current = items;
        for (int i = 0; i < itemPoint.length; i++) {
            item = Optional.ofNullable(current.get(itemPoint[i]));
            current = item.get().getChildren();
        }
        return item.get();
    }

    @Override
    public String toString() {
        return "Menu : "
                + System.lineSeparator()
                + items;
    }
}
