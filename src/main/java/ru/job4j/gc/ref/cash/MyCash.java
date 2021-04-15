package ru.job4j.gc.ref.cash;

import java.lang.ref.SoftReference;
import java.util.HashMap;

abstract class MyCash<K, V extends SoftReference<?>> {
    private HashMap<K, V> map = new HashMap<>();

    protected HashMap<K, V> getMap() {
        return map;
    }

    public Object getValue(String key) {
        Object value;
        if (map.get(key) == null) {
            load(key);
        }
        value = map.get(key).get();
        return value;
    }

    abstract void load(String key);
}
