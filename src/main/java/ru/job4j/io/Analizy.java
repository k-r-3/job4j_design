package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analizy {
    public void unavailable(String source, String target) {
        Deque<String> diapason = new LinkedList<>();
        StringJoiner joiner = new StringJoiner(",");
        String marker = "";
        try (BufferedReader reader = new BufferedReader(
                new FileReader(source))) {
            while (reader.ready()) {
                String line = reader.readLine();
                if (line.startsWith("400")
                        || line.startsWith("500")) {
                    marker = line;
                    diapason.offer(marker.split(" ")[1] + ";");
                } else if (marker.matches("(400.*)|(500.*)")
                && !line.equals("")) {
                    diapason.offer(line.split(" ")[1]);
                    joiner.add(diapason.peekFirst() + diapason.peekLast());
                    diapason.clear();
                    marker = line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter writer = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
                Arrays.stream(joiner.toString()
                        .split(","))
                        .forEach(writer::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("unavailable log.txt", "unavailableLog.csv");
    }
}
