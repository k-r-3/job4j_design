package ru.job4j.gc.ref.cash;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;

abstract class MyCache<K, V> {
    private HashMap<K, SoftReference<V>> map = new HashMap<>();
    private ReferenceQueue<V> queue = new ReferenceQueue<>();

    protected HashMap<K, SoftReference<V>> getMap() {
        return map;
    }

    public ReferenceQueue<V> getQueue() {
        return queue;
    }

    public V getValue(K key) {
        V value;
        if (map.get(key) == null || map.get(key).get() == null) {
            load(key);
            if (map.get(key).get() == null) {
                return getValue(key);
                }
        }
        value = map.get(key).get();
        return value;
    }

    abstract void load(K key);
}
