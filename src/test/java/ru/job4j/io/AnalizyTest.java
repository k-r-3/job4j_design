package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class AnalizyTest {

    @Test
    public void whenUnavailableOnce() {
        Analizy analizy = new Analizy();
        String path = "unavailable once.txt";
        String log = "once log.csv";
        List<String> actual = new ArrayList<>();
        analizy.unavailable(path, log);
        try (BufferedReader reader = new BufferedReader(
                new FileReader(log))) {
            actual = reader.lines()
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(List.of("10:56:01;10:57:01").containsAll(actual), is(true));
    }


    @Test
    public void whenUnavailableFewTime() {
        Analizy analizy = new Analizy();
        String path = "unavailable few time.txt";
        String log = "few time log.csv";
        List<String> actual = new ArrayList<>();
        analizy.unavailable(path, log);
        try (BufferedReader reader = new BufferedReader(
                new FileReader(log))) {
            actual = reader.lines()
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(List.of("10:56:01;10:57:01",
                "10:59:01;11:02:02").containsAll(actual), is(true));
    }
    @Test
    public void whenNothing() {
        Analizy analizy = new Analizy();
        String path = "available.txt";
        String log = "available.csv";
        List<String> actual = new ArrayList<>();
        analizy.unavailable(path, log);
        try (BufferedReader reader = new BufferedReader(
                new FileReader(log))) {
            actual = reader.lines()
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(actual.get(0), is(""));
    }

}