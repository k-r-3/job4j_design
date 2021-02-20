package ru.job4j.collection;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleHashMapTest {

    @Test
    public void whenAdd() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        String key = "example key";
        String value = "example value";
        assertThat(map.insert(key, value), is(true));
    }

    @Test
    public void whenAddBothKeys() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        String key = "example key";
        String value = "example value";
        String key2 = "example key";
        String value2 = "example value2";
        map.insert(key, value);
        assertThat(map.insert(key2, value), is(false));
    }

    @Test
    public void whenGet() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        String key = "example key";
        String key2 = "example key2";
        String value = "example value";
        String value2 = "example value2";
        map.insert(key, value);
        map.insert(key2, value2);
        assertThat(map.get(key), is(value));
        assertThat(map.get(key2), is(value2));
    }

    @Test
    public void whenCorruptedGet() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        String key = "example key";
        String key2 = "example key2";
        String value = "example value";
        map.insert(key, value);
        assertThat(Objects.isNull(map.get(key2)), is(true));
    }

    @Test
    public void whenHasNext() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        String key = "example key";
        String value = "example value";
        map.insert(key, value);
        Iterator<SimpleHashMap.Node<String, String>> iterator = map.iterator();
        assertThat(iterator.hasNext(), is(true));
    }

    @Test
    public void whenNextOnce() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        String key = "example key";
        String value = "example value";
        map.insert(key, value);
        Iterator<SimpleHashMap.Node<String, String>> iterator = map.iterator();
        String expected = "{example key = example value}";
        String actual = iterator.next().toString();
        assertThat(actual, is(expected));
    }

    @Test
    public void whenMultiNext() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        String key = "example key";
        String key2 = "example key2";
        String value = "example value";
        String value2 = "example value2";
        map.insert(key, value);
        map.insert(key2, value2);
        Iterator<SimpleHashMap.Node<String, String>> iterator = map.iterator();
        String expected = "{example key = example value}";
        String expected2 = "{example key2 = example value2}";
        String actual = iterator.next().toString();
        assertThat(actual, is(expected));
        String actual2 = iterator.next().toString();
        assertThat(actual2, is(expected2));
    }

    @Test
    public void whenHasNextFalse() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        String key = "example key";
        String value = "example value";
        map.insert(key, value);
        Iterator<SimpleHashMap.Node<String, String>> iterator = map.iterator();
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void whenDelete() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        String key = "example key";
        String key2 = "example key2";
        String value = "example value";
        String value2 = "example value2";
        map.insert(key, value);
        map.insert(key2, value2);
        map.delete(key2);
        Iterator<SimpleHashMap.Node<String, String>> iterator = map.iterator();
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenDeleteAfterIterator() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        String key = "example key";
        String value = "example value";
        map.insert(key, value);
        Iterator<SimpleHashMap.Node<String, String>> iterator = map.iterator();
        iterator.next();
        map.delete("example key");
        iterator.hasNext();
    }

    @Test (expected = NoSuchElementException.class)
    public void whenNoNext() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        String key = "example key";
        String value = "example value";
        map.insert(key, value);
        Iterator<SimpleHashMap.Node<String, String>> iterator = map.iterator();
        iterator.next();
        iterator.next();
    }

    @Test
    public void whenLoadThenResize() {
        Random rand = new Random();
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        assertEquals(16, map.size());
        for (int i = 0; i < 13; i++) {
            map.insert(rand.nextInt(), "Value");
        }
        assertEquals(32, map.size());
    }

}