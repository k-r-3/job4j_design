package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> storage = new SimpleArray<>();
    private int size;

    public boolean add(T element) {
        if (!Objects.nonNull(element)) {
            return false;
        }
        if (size == 0) {
            storage.add(element);
            size++;
            return true;
        }
        for (int i = 0; i < size; i++) {
            if (storage.get(i).equals(element)) {
                return false;
            }
        }
        storage.add(element);
        size++;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return storage.iterator();
    }
}
