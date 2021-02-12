package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {

    @Test
    public void whenAddAndGet() {
        SimpleArray<String> array = new SimpleArray<>(4);
        array.add("S");
        assertThat(array.get(0), is("S"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenExceptionAdd() {
        SimpleArray<String> array = new SimpleArray<>(4);
        array.add("S");
        array.add("S");
        array.add("S");
        array.add("S");
        array.add("S");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenExceptionGet() {
        SimpleArray<String> array = new SimpleArray<>(4);
        array.add("S");
        array.get(1);
    }

    @Test
    public void whenSet() {
        SimpleArray<Integer> array = new SimpleArray<>(4);
        array.add(1);
        array.add(2);
        array.set(1, 0);
        assertThat(array.get(1), is(0));
    }



    @Test
    public void whenAddNull() {
        SimpleArray<Integer> array = new SimpleArray<>(4);
        assertThat(array.iterator().hasNext(), is(true));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenExceptionSet() {
        SimpleArray<Integer> array = new SimpleArray<>(4);
        array.add(1);
        array.add(2);
        array.set(2, 0);
    }

    @Test
    public void whenDelete() {
        SimpleArray<Integer> array = new SimpleArray<>(4);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.remove(3);
        array.remove(1);
        assertThat(array.get(1), is(3));
    }

    @Test
    public void whenNext() {
        SimpleArray<Integer> array = new SimpleArray<>(4);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        Iterator<Integer> iterator = array.iterator();
        iterator.next();
        iterator.next();
        assertThat(iterator.next(), is(array.get(2)));
    }

}