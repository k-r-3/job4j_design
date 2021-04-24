package ru.job4j.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class MaxMinTest {

    @Test
    public void whenMax() {
        List<Integer> list = List.of(1, 3, 9, 2);
        Comparator<Integer> comp = Integer::compare;
        MaxMin maxmin = new MaxMin();
        int actual = maxmin.max(list, comp);
        int expect = 9;
        assertThat(actual, is(expect));
    }

    @Test
    public void whenMin() {
        List<Integer> list = List.of(1, 3, 9, 2);
        Comparator<Integer> comp = Integer::compare;
        MaxMin maxmin = new MaxMin();
        int actual = maxmin.min(list, comp);
        int expect = 1;
        assertThat(actual, is(expect));
    }

    @Test
    public void whenMaxString() {
        List<String> list = List.of("one", "three", "nine", "two");
        Comparator<String> comp = String::compareTo;
        MaxMin maxmin = new MaxMin();
        String actual = maxmin.max(list, comp);
        String expect = "two";
        assertThat(actual, is(expect));
    }

    @Test
    public void whenMinSring() {
        List<String> list = List.of("one", "three", "nine", "two");
        Comparator<String> comp = String::compareTo;
        MaxMin maxmin = new MaxMin();
        String actual = maxmin.min(list, comp);
        String expect = "nine";
        assertThat(actual, is(expect));
    }

}