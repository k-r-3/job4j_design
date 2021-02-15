package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size = 0;

    public void push(T value) {
        in.push(value);
        size++;
    }

    public T poll() {
        if (out.isEmpty()) {
            for (int i = 0; i < size; i++) {
                out.push(in.pop());
            }
            size = 0;
        }
        return out.pop();
    }
}
