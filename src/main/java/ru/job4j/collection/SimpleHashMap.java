package ru.job4j.collection;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Node<K, V>> {
    private Node<K, V>[] table;
    private Node<K, V> head;
    private Node<K, V> tail;
    private int size = 16;
    private int amount = 0;
    private int modCount = 0;

    public SimpleHashMap() {
        table = new Node[size];
    }

    public boolean insert(K key, V value) {
        if (amount == size) {
            grow();
        }
        if (get(key) != null) {
            return false;
        }
        int index = index(key);
        Node<K, V> newNode = new Node(key, value, tail);
        if (head == null) {
            head = newNode;
            tail = newNode;
            table[index] = head;
            amount++;
            return true;
        }
        tail.next = newNode;
        tail = tail.next;
        table[index] = tail;
        amount++;
        modCount++;
        return true;
    }

    public V get(K key) {
        V value = null;
        int index = index(key);
        Objects.checkIndex(index, size);
        if (table[index] != null) {
            value = table[index].value;
        }
        return value;
    }

    public boolean delete(K key) {
        int index = index(key);
        table[index] = null;
        amount--;
        modCount++;
        return true;
    }

    private void grow() {
        table = Arrays.copyOf(table, (size + 1) * 2);
    }

    private int hash(K key) {
        int h = key.hashCode();
        return h == 0 ? 0 : h ^ (h >>> 16);
    }

    private int index(K key) {
        return (size - 1) & hash(key);
    }

    static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
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
            private Node<K, V> current = head;
            private int expectedModCount = modCount;
            private int cursor;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor != amount;
            }

            @Override
            public Node<K, V> next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (cursor >= amount) {
                    throw new NoSuchElementException();
                }
                if (cursor != 0) {
                    current = current.next;
                }
                cursor++;
                return current;
            }
        };
    }
}