package ru.job4j.ood.isp.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Item implements ItemTree<Item>, Action {
    private Item parent;
    private List<Item> children = new ArrayList<>();
    private String name;
    private boolean visited = false;

    public Item(String name) {
        this.name = name;
    }

    @Override
    public Item getParent() {
        return parent;
    }

    @Override
    public Optional<Item> getElement(String name) {
        Optional<Item> element = Optional.of(this);
        if (element.get().getName().equals(name)) {
            return element;
        }
        List<Item> branch = element.get().getChildren();
        if (!branch.isEmpty()) {
            for (Item item : branch) {
                if (!visited) {
                    visited = true;
                    Optional<Item> child = item.getElement(name);
                    if (child.isPresent()) {
                        visited = false;
                        return child;
                    }
                }
                visited = false;
            }
        }
        Optional<Item> parent = Optional.ofNullable(element.get().getParent());
        if (parent.isPresent()) {
            return parent.get().getElement(name);
        }
        return Optional.empty();
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
    public void addChild(String parentName, Item child) {
        Optional<Item> parent = getElement(parentName);
        if (parent.isPresent()) {
            parent.get().getChildren().add(child);
        } else {
            throw new IllegalArgumentException("parent not found");
        }
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

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public boolean deleteChild(String itemName) {
        return false;
    }
}
