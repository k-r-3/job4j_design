package ru.job4j.generics;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private T[] array;
    private int cursor = 0;
    private int marker = 0;

    public SimpleArray(int size) {
        array = (T[]) new Object[size];
    }

    public void add(T model) {
        if (iterator().hasNext()) {
            array[cursor++] = model;
        } else {
            throw new IndexOutOfBoundsException();
        }
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
            @Override
            public boolean hasNext() {
                return cursor < array.length;
            }

            @Override
            public T next() {
                return array[marker++];
            }
        };
    }
}
