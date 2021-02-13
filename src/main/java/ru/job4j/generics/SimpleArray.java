package ru.job4j.generics;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private T[] array;
    private int cursor = 0;

    public SimpleArray(int size) {
        array = (T[]) new Object[size];
    }

    public void add(T model) {
        array[cursor++] = model;
    }

    public T set(int index, T model) {
        Objects.checkIndex(index, cursor);
        T oldCell = array[index];
        array[index] = model;
        return oldCell;
    }

    public T get(int index) {
        Objects.checkIndex(index, cursor);
        return array[index];
    }

    public void remove(int index) {
        Objects.checkIndex(index, cursor);
            array[index] = null;
            System.arraycopy(array, index + 1, array, index,
                    array.length - index - 1);
        cursor--;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int marker = 0;
            @Override
            public boolean hasNext() {
                return marker < cursor;
            }

            @Override
            public T next() {
                if (marker < array.length) {
                    return array[marker++];
                }
                return null;
            }
        };
    }
}
