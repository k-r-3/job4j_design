package ru.job4j;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static ru.job4j.Analize.*;

public class AnalizeTest {

    @Test
    public void whenDiffAdded() {
        Analize analize = new Analize();
        List<User> previous = new ArrayList<>(Arrays.asList(
                new User(1, "Ivan"),
                new User(2, "Boris")));
        List<User> current = new ArrayList<>(previous);
        current.add(new User(3, "Nikolay"));
        Info info = analize.diff(previous, current);
        String actual = info.toString();
        String expected = "added=1, changed=0, deleted=0";
        assertEquals(expected, actual);
    }

    @Test
    public void whenDiffChanged() {
        Analize analize = new Analize();
        List<User> previous = new ArrayList<>(Arrays.asList(
                new User(1, "Ivan"),
                new User(2, "Boris"),
                new User(3, "Nikolay")));
        List<User> current = new ArrayList<>(previous);
        current.set(2, new User(3, "Pavel"));
        Info info = analize.diff(previous, current);
        String actual = info.toString();
        String expected = "added=0, changed=1, deleted=0";
        assertEquals(expected, actual);
    }

    @Test
    public void whenMultiChanged() {
        Analize analize = new Analize();
        List<User> previous = new ArrayList<>(Arrays.asList(
                new User(1, "Ivan"),
                new User(2, "Boris"),
                new User(3, "Nikolay"),
                new User(4, "Pavel")));
        List<User> current = new ArrayList<>(previous);
        current.set(0, new User(1, "Stepan"));
        current.set(2, new User(3, "Pavel"));
        Info info = analize.diff(previous, current);
        String actual = info.toString();
        String expected = "added=0, changed=2, deleted=0";
        assertEquals(expected, actual);
    }

    @Test
    public void whenDiffDeleted() {
        Analize analize = new Analize();
        List<User> previous = new ArrayList<>(Arrays.asList(
                new User(1, "Ivan"),
                new User(2, "Boris"),
                new User(3, "Nikolay"),
                new User(4, "Pavel")));
        List<User> current = new ArrayList<>(previous);
        current.remove(2);
        Info info = analize.diff(previous, current);
        String actual = info.toString();
        String expected = "added=0, changed=0, deleted=1";
        assertEquals(expected, actual);
    }

    @Test
    public void whenMultiDeleted() {
        Analize analize = new Analize();
        List<User> previous = new ArrayList<>(Arrays.asList(
                new User(1, "Ivan"),
                new User(2, "Boris"),
                new User(3, "Nikolay"),
                new User(4, "Pavel")));
        List<User> current = new ArrayList<>(previous);
        current.remove(0);
        current.remove(2);
        Info info = analize.diff(previous, current);
        String actual = info.toString();
        String expected = "added=0, changed=0, deleted=2";
        assertEquals(expected, actual);
    }

    @Test
    public void whenAllOperations() {
        Analize analize = new Analize();
        List<User> previous = new ArrayList<>(Arrays.asList(
                new User(1, "Ivan"),
                new User(2, "Petr"),
                new User(3, "Boris")));
        List<User> current = new ArrayList<>(previous);
        current.set(0, new User(1, "Dmitry"));
        current.add(new User(4, "Nikolay"));
        current.add(new User(5, "Pavel"));
        current.remove(2);
        current.set(1, new User(2, "Dmiry"));
        Info info = analize.diff(previous, current);
        String actual = info.toString();
        String expected = "added=2, changed=2, deleted=1";
        assertEquals(expected, actual);
    }
}