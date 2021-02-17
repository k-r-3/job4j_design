package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> storage = new SimpleArray<>();
    private int size;

    public boolean add(T element) {
        if (!checkSimilarity(element)) {
            return false;
        }
        storage.add(element);
        size++;
        return true;
    }

    private boolean checkSimilarity(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(storage.get(i), element)) {
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
