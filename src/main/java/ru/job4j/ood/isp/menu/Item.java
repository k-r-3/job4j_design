package ru.job4j.ood.isp.menu;

import java.util.*;

public class Item implements ItemTree<Item>, Action {
    private Item parent;
    private ArrayList<Item> children = new ArrayList<>();
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
    public Optional<Item> getElement(String name) {
        Optional<Item> element = Optional.of(this);
        if (element.get().getName().equals(name)) {
            return element;
        }
        List<Item> branch = element.get().getChildren();
        if (!branch.isEmpty()) {
            for (Item item : branch) {
                if (!item.visited) {
                    item.visited = true;
                    Optional<Item> child = item.getElement(name);
                    if (child.isPresent()) {
                        item.visited = false;
                        return child;
                    }
                }
            }
        }
        Optional<Item> parent = Optional.ofNullable(element.get().getParent());
        if (parent.isPresent()) {
            return parent.get().getElement(name);
        }
        return Optional.empty();
    }

    @Override
    public void addChild(String parentName, Item child) {
        getParentBranch(parentName).add(child);
    }

    public boolean deleteChild(String itemName) {
        Optional<Item> item = getElement(itemName);
        List<Item> list = getParentBranch(item.get().getParent().getName());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(itemName)) {
                list.remove(i);
            }
        }
        return true;
    }

    private List<Item> getParentBranch(String parentName) {
        Optional<Item> item = getElement(parentName);
        if (item.isPresent()) {
            return item.get()
                    .getChildren();
        } else {
            throw new IllegalArgumentException("parent not found");
        }
    }

    @Override
    public void addChildren(List<Item> children) {
        for (int i = 0; i < children.size(); i++) {
            children.get(i).setParent(this);
        }
        this.children = new ArrayList<>(children);
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
}
