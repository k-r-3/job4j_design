package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inSize = 0;
    private int outSize = 0;

    public void push(T value) {
        in.push(value);
        inSize++;
    }

    public T poll() {
        T element = null;
        int length = 0;
        if (inSize >= outSize) {
            length = inSize;
            for (int i = 0; i < length; i++) {
                element = in.pop();
                out.push(element);
                outSize++;
                inSize--;
            }
        } else {
            length = outSize;
            for (int i = 0; i < length; i++) {
                element = out.pop();
                in.push(element);
                outSize--;
            }
        }
        if (element == null) {
            throw new NoSuchElementException();
        }
        return element;
    }
}
