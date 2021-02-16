package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> storage = new SimpleArray<>();
    private int size;

    public boolean add(T element) {
        if (!checkSimilarity(element)) {
            return false;
        }
        if (size == 0) {
            storage.add(element);
            size++;
            return true;
        }
        storage.add(element);
        size++;
        return true;
    }

    private boolean checkSimilarity(T element) {
        for (int i = 0; i < size; i++) {
            if (storage.get(i).equals(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return storage.iterator();
    }
}
