package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<T> implements Iterable<T> {
    private int modCount;
    private int size;
    private Node<T> head;
    private Node<T> tail;

    private static class Node<T> {
        private T item;
        private Node<T> next;

        public Node(T item) {
            this.item = item;
        }
    }

    public void add(T element) {
        if (head == null) {
            head = new Node<T>(element);
        }
            tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = new Node<T>(element);
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> result = head;
        Node<T> currentResult;
        for (int i = 0; i <= index; i++) {
            currentResult = result.next;
            result = currentResult;
        }
        return result.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedModCount = modCount;
            private int cursor;

            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (cursor >= size) {
                    throw new NoSuchElementException();
                }
                return get(cursor++);
            }
        };
    }
}
