package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<T> implements Iterable<T> {
    private int modCount;
    private int size;
    private Node<T> head;

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
            size++;
            modCount++;
            return;
        }
        Node<T> currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = new Node<T>(element);
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> result = head;
        Node<T> currentResult;
        for (int i = 0; i < index; i++) {
            currentResult = result.next;
            result = currentResult;
        }
        return result.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;
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
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = current.item;
                current = current.next;
                cursor++;
                return value;
            }
        };
    }
}
