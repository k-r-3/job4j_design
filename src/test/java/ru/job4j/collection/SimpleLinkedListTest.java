package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleLinkedListTest {

    @Test
    public void whenAddAndGet() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(5);
        list.add(3);
        list.add(2);
        list.add(1);
        assertThat(list.get(2), is(2));
    }

    @Test
    public void whenHasNext() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(5);
        list.add(3);
        list.add(2);
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext(), is(true));
    }

    @Test
    public void whenMultiHasNext() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(5);
        list.add(3);
        list.add(2);
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        iterator.hasNext();
        iterator.hasNext();
        assertThat(iterator.hasNext(), is(true));
    }

    @Test
    public void whenNextAndHasNext() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(5);
        list.add(3);
        list.add(2);
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.next();
        assertThat(iterator.hasNext(), is(true));
    }

    @Test
    public void whenNotHasNext() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(5);
        list.add(3);
        list.add(2);
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextThenException() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(5);
        list.add(3);
        list.add(2);
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    @Test
    public void whenNext() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(5);
        list.add(3);
        list.add(2);
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.next();
        assertThat(iterator.next(), is(2));
    }

}