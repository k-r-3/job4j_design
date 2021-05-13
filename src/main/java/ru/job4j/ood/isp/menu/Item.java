package ru.job4j.ood.isp.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Item implements ItemTree<Item>, Action {
    private Item parent;
    private List<Item> children;
    private String name;

    public Item(String name) {
        this.name = name;
    }

    @Override
    public Item getParent() {
        return parent;
    }

    @Override
    public List<Item> getParents() {
        List<Item> parents = new ArrayList<>();
        Item parent = this.getParent();
        if (parent != null) {
            parents.add(parent);
            parents.addAll(parent.getParents());
        }
        return parents;
    }

    @Override
    public void addChildren(List<Item> children) {
        for (int i = 0; i < children.size(); i++) {
            children.get(i).setParent(this);
        }
        this.children = children;
    }

    @Override
    public List<Item> getChildren() {
        if (children == null) {
            return Collections.emptyList();
        }
        return children;
    }

    private void setParent(Item parent) {
        this.parent = parent;
    }

    public String toString() {
        return name;
    }
}
