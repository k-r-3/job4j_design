package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenUnavailableOnce() throws IOException {
        Analizy analizy = new Analizy();
        File source = folder.newFile("unavailable once.txt");
        File target = folder.newFile("once log.csv");
        List<String> actual = new ArrayList<>();
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("500 10:56:01");
            writer.println("200 10:57:01");
            writer.println("200 10:58:01");
            writer.println("200 10:59:01");
            writer.println("200 11:01:02");
            writer.println("200 11:02:02");
        }
        analizy.unavailable(source.getAbsolutePath());
        analizy.writeLog(target.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(
                new FileReader(target))) {
            actual = reader.lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(List.of("10:56:01;10:57:01").containsAll(actual), is(true));
    }

    @Test
    public void whenUnavailableFewTime() throws IOException {
        Analizy analizy = new Analizy();
        File source = folder.newFile("unavailable few time.txt");
        File target = folder.newFile("few time log.csv");
        List<String> actual = new ArrayList<>();
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("500 10:56:01");
            writer.println("200 10:57:01");
            writer.println("200 10:58:01");
            writer.println("500 10:59:01");
            writer.println("500 11:01:02");
            writer.println("200 11:02:02");
        }
        analizy.unavailable(source.getAbsolutePath());
        analizy.writeLog(target.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(
                new FileReader(target))) {
            actual = reader.lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(List.of("10:56:01;10:57:01",
                "10:59:01;11:02:02").containsAll(actual), is(true));
    }

    @Test
    public void whenNothing() throws IOException {
        Analizy analizy = new Analizy();
        File source = folder.newFile("available.txt");
        File target = folder.newFile("available.csv");
        List<String> actual = new ArrayList<>();
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("200 10:56:01");
            writer.println("200 10:57:01");
            writer.println("200 10:58:01");
            writer.println("200 10:59:01");
            writer.println("200 11:01:02");
            writer.println("200 11:02:02");
        }
        analizy.unavailable(source.getAbsolutePath());
        analizy.writeLog(target.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(
                new FileReader(target))) {
            actual = reader.lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(actual.isEmpty(), is(true));
    }

}