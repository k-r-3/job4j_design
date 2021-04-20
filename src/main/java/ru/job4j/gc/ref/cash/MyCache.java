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
        if (!map.containsKey(key)) {
            load(key);
        }
        SoftReference<V> ref = map.get(key);
        value = ref.get();
        while (value == null) {
            load(key);
            ref = map.get(key);
            value = ref.get();
        }
        return value;
    }

    abstract V load(K key);
}
