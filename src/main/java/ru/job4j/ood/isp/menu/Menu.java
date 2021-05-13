package ru.job4j.ood.isp.menu;

import java.util.List;
import java.util.Optional;

public class Menu implements Output<Item> {
    private List<Item> items;

    public Menu(List<Item> items) {
        this.items = items;
    }

    public boolean choice(String[] number) {
//        String[] in = number.split("/d");
        Optional<Item> item = getItem(number);
        return item.map(Action::action)
                .orElse(false);
    }

    private Optional<Item> getItem(String[] number) {
        Optional<Item> item = Optional.empty();
        List<Item> branch = items;
        for (int i = 0; i < number.length; i++) {
            item = Optional.ofNullable(branch.get(Integer.parseInt(number[i])));
            if (item.isPresent()) {
                branch = item.get().getChildren();
            }
        }
        return item;
    }

    @Override
    public String formatOut(Item item) {
        StringBuilder sb = new StringBuilder();
        sb.append(item)
                .append(System.lineSeparator());
        List<Item> children = item.getChildren();
        int length = children.size();
        if (length != 0) {
            for (int i = 0; i < length; i++) {
                Item child = children.get(i);
                for (int j = 0; j < child.getParents().size(); j++) {
                    sb.append("\t");
                }
                sb.append(formatOut(child));
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Menu : "
                + System.lineSeparator()
                + formatOut(items.get(0));
    }
}
