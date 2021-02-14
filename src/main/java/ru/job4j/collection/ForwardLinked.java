package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;

    public void add(T value) {
        Node<T> node = new Node<T>(null, value, null);
        if (head == null) {
            head = node;
            tail = node;
            head.next = tail;
            tail.prev = head;
            return;
        }
        tail = new Node<T>(tail.prev, tail.prev.value, node);
        node = new Node<T>(tail, value, null);
        tail = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T value = head.value;
        head = head.next;
        return value;
    }

    public T deleteLast() {
        if (tail == null || tail.prev == null) {
            T result = head.value;
            head = null;
            return result;
        }
        T result = tail.value;
        tail = tail.prev;
        tail.prev = tail.prev.prev;
        return result;
    }

    public void revert() {
        Node<T> previous = null;
        Node<T> next = null;
        Node<T> current = head;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;

        public Node(Node<T> prev, T value, Node<T> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;

        }
    }
}
