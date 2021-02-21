package ru.job4j.collection;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Node<K, V>> {
    private static final float LOAD_FACTOR = 0.75f;
    private Node<K, V>[] table;
    private int capacity = 16;
    private int threshold = (int) (100 * (
            capacity / (100 / LOAD_FACTOR)
    ));
    private int amount = 0;
    private int modCount = 0;

    public SimpleHashMap() {
        table = new Node[capacity];
    }

    public int size() {
        return table.length;
    }

    public boolean insert(K key, V value) {
        if (amount == threshold) {
            resize();
        }
        int index = index(key);
        Node<K, V> node = table[index];
        if (node != null) {
            if (node.key == key) {
                return false;
            }
        }
        Node<K, V> newNode = new Node(key, value);
        table[index] = newNode;
        amount++;
        modCount++;
        return true;
    }

    public V get(K key) {
        V value = null;
        int index = index(key);
        Objects.checkIndex(index, capacity);
        if (check(key, index)) {
            value = table[index].value;
        }
        return value;
    }

    public boolean delete(K key) {
        int index = index(key);
        if (check(key, index)) {
            table[index] = null;
            amount--;
            modCount++;
            return true;
        }
        return false;
    }

    private boolean check(K key, int index) {
        return (table[index] != null
                && (table[index].key).equals(key));
    }

    private void resize() {
        SimpleHashMap newMap = new SimpleHashMap();
        int newCapacity = capacity << 1;
        int newThreshold = (int) (100 * (newCapacity / (100 / LOAD_FACTOR)));
        Node<K, V>[] newTable = new Node[newCapacity];
        newMap.table = newTable;
        newMap.capacity = newCapacity;
        newMap.threshold = newThreshold;
        Iterator<Node<K, V>> rIterator = iterator();
        while (rIterator.hasNext()) {
            Node<K, V> newNode = rIterator.next();
            K key = newNode.key;
            V value = newNode.value;
            newMap.insert(key, value);
        }
        threshold = newThreshold;
        capacity = newCapacity;
        table = newMap.table;
        newMap.table = null;
    }

    private int hash(K key) {
        int h = key.hashCode();
        return h == 0 ? 0 : h ^ (h >>> 16);
    }

    private int index(K key) {
        return (capacity - 1) & hash(key);
    }

    static class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key
                    + " = "
                    + value + '}';
        }
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            private int cursor;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (table[cursor] == null && cursor != size() - 1) {
                    cursor++;
                }
                return cursor != size() - 1;
            }

            @Override
            public Node<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<K, V> result = table[cursor];
                cursor++;
                return result;
            }
        };
    }
}