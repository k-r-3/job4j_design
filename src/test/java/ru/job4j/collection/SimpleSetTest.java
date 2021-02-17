package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.Objects;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleSetTest {

    @Test
    public void whenAdd() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        it.next();
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddBoth() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        it.next();
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddBothNulls() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add(null);
        set.add(null);
        Iterator<String> it = set.iterator();
        it.next();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenAddBothThenFalse() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        assertThat(set.add(2), is(false));
    }

    @Test
    public void whenAddNull() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(1);
        set.add(null);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(1));
        assertThat(Objects.isNull(it.next()), is(true));
    }
}