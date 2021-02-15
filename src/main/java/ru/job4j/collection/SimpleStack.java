package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();
    private int count = 0;

    public T pop() {
        count--;
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
        count++;
    }

    public boolean isEmpty() {
        return count == 0;
    }
}
