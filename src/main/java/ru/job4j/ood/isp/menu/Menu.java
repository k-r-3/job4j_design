package ru.job4j.ood.isp.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu implements Output<Item> {
    private List<Item> items;

    public Menu(List<Item> items) {
        this.items = items;
    }

    public boolean choice(String number) {
        List<String> in = new ArrayList<>();
        Matcher matcher = Pattern.compile("[0-9]").matcher(number);
        while (matcher.find()) {
            int literal = Integer.parseInt(matcher.group()) - 1;
            in.add(String.valueOf(literal));
        }
        Optional<Item> item = getItem(in);
        return item.map(Action::action)
                .orElse(false);
    }

    private Optional<Item> getItem(List<String> number) {
        Optional<Item> item = Optional.empty();
        List<Item> branch = items;
        for (int i = 0; i < number.size(); i++) {
            item = Optional.ofNullable(branch.get(Integer.parseInt(number.get(i))));
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
