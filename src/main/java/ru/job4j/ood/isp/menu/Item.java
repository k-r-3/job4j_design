package ru.job4j.ood.isp.menu;

import java.util.List;

public abstract class Item implements OutputFormat {
    private List<Item> children;
    private Item parent;
    private String name;

    public Item(String name, List<Item> children, Item parent) {
        this.children = children;
        this.name = name;
        this.parent = parent;
    }

    abstract boolean function();

    List<Item> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return format();
    }

    @Override
    public String format() {
        StringBuilder sb = new StringBuilder();
        if (this.parent != null) {
            sb.append("\n");
            format();
        }
        sb.append(name);
        if (!children.isEmpty()) {
            sb.append(System.lineSeparator());
            for (int i = 0; i < children.size(); i++) {
                sb.append("\t")
                        .append(children.get(i).format());
            }
        } else {
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
