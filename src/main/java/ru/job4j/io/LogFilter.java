package ru.job4j.io;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .filter(s -> s.matches("(.*)*404\\s.*"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter writer = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}
