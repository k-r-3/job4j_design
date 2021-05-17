package ru.job4j.ood.isp.menu;

import java.util.List;

public class Check extends Item {

    public Check(String nodeName, Branch<List<Item>> children) {
        super(nodeName, children);
    }

    @Override
    public boolean action() {
        return this.getChildren().size() < 1;
    }
}
