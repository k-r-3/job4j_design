package ru.job4j.ood.isp.menu;

import java.util.ArrayList;
import java.util.List;

public class Children implements Branch<List<Item>> {
    @Override
    public ArrayList<Item> returnStorage() {
        return new ArrayList<>();
    }
}
