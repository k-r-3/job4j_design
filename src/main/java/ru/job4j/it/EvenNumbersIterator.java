package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] array;
    private int marker;

    public EvenNumbersIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        var result = position(marker);
        if (result != -1) {
            marker = result;
            return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer result = array[marker];
        marker = position(marker) + 1;
        return result;
    }

    private int position(int iterator) {
        for (int i = iterator; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                return i;
            }
        }
        return -1;
    }
}
