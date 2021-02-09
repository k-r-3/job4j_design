package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = row; i < data.length; i++) {
            if (data[i].length > 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (data[row].length - (column) == 0) {
            row++;
            column = 0;
        }
        while (data[row].length == 0) {
            row++;
        }
        return data[row][column++];
    }
}