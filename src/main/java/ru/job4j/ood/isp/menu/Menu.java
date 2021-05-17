package ru.job4j.ood.isp.menu;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu implements ItemTree<Item>, Output<Item> {
    private static final Pattern PATTERN = Pattern.compile("[0-9]");
    private List<Item> items;

    public Menu(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean choice(String number) {
        List<String> in = new ArrayList<>();
        Matcher matcher = PATTERN.matcher(number);
        while (matcher.find()) {
            int literal = Integer.parseInt(matcher.group()) - 1;
            in.add(String.valueOf(literal));
        }
        Optional<Item> item = getItemByNumber(in);
        return item.map(Action::action)
                .orElse(false);
    }

    private Optional<Item> getItemByNumber(List<String> number) {
        Optional<Item> item = Optional.empty();
        List<Item> branch = items;
        for (int i = 0; i < number.size(); i++) {
            int point = Integer.parseInt(number.get(i));
            if (branch.size() > point) {
                item = Optional.ofNullable(branch.get(Integer.parseInt(number.get(i))));
                if (item.isPresent()) {
                    branch = item.get().getChildren();
                }
            } else {
                break;
            }
        }
        return item;
    }

    @Override
    public List<Item> getParents(Item start) {
        List<Item> parents = new ArrayList<>();
        Item parent = start.getParent();
        if (parent != null) {
            parents.add(parent);
            parents.addAll(getParents(parent));
        }
        return parents;
    }

    public Optional<Item> getElement(String name) {
        Queue<Item> queue = new ArrayDeque<>(getItems());
        Optional<Item> result = bfs(queue, name);
        if (result.isPresent()) {
            return result;
        }
        throw new IllegalArgumentException("item not found");
    }

    @Override
    public Optional<Item> bfs(Queue<Item> queue, String target) {
        Optional<Item> result = Optional.empty();
        while (!queue.isEmpty()) {
            Item item = queue.poll();
            if (item.getName().equals(target)) {
                return Optional.of(item);
            }
            queue.addAll(item.getChildren());
        }
        return result;
    }

    @Override
    public void addChild(String parentName, Item child) {
        getParentBranch(parentName).add(child);
        child.setParent(getElement(parentName).get());
    }

    @Override
    public void addChildren(List<Item> children, String targetItem) {
        Optional<Item> parent = getElement(targetItem);
        if (parent.isPresent()) {
            List<Item> branch = parent.get().getChildren();
            branch.addAll(children);
        }
        for (int i = 0; i < children.size(); i++) {
            children.get(i).setParent(parent.get());
        }
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
    public boolean deleteChild(String itemName) {
        Optional<Item> item = getElement(itemName);
        List<Item> list =  getParentBranch(item.get().getParent().getName());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(itemName)) {
                list.remove(i);
                return true;
            }
        }
        return false;
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
                for (int j = 0; j < getParents(child).size(); j++) {
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
