package ru.job4j.collection;

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
    int length = inSize;
    if (outSize == 0) {
        for (int i = 0; i < length; i++) {
            out.push(in.pop());
            outSize++;
            inSize--;
        }
    }
    T element = out.pop();
    outSize--;
    return element;
}
}
