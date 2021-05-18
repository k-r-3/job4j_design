package ru.job4j.ood.isp.menu;

import java.util.*;

public class Item implements Action {
    private Node node;
    private List<Item> children;

    public Item(String nodeName, Branch<List<Item>> children) {
        this.node = new Node(nodeName);
        this.children = children.returnStorage();
    }

    public List<Item> getChildren() {
        return children;
    }

    public String getName() {
        return node.name;
    }

    public void setParent(Item parent) {
        node.parent = parent;
    }

    public Item getParent() {
        return node.parent;
    }

    public String toString() {
        return getName();
    }

    private static class Node {
        private Item parent;
        private String name;

        public Node(String name) {
            this.name = name;
        }
    }
}
