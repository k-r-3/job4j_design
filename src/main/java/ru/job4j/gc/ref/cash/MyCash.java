package ru.job4j.gc.ref.cash;

import java.lang.ref.SoftReference;
import java.util.HashMap;

abstract class MyCash {
    private HashMap<String, SoftReference<String>> map = new HashMap<>();

    protected HashMap<String, SoftReference<String>> getMap() {
        return map;
    }

    public String getValue(String key) {
        String value;
        if (map.get(key) == null) {
            load(key);
        }
        value = map.get(key).get();
        return value;
    }

    abstract void load(String key);
}
