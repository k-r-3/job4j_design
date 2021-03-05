package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analizy {
    public void unavailable(String source, String target) {
        Deque<String> diapason = new LinkedList<>();
        List<String> list = new ArrayList<>();
        String marker = "";
        try (BufferedReader reader = new BufferedReader(
                new FileReader(source))) {
            while (reader.ready()) {
                String line = reader.readLine();
                if (marker.isEmpty() && line.startsWith("400")
                        || line.startsWith("500")) {
                    marker = line;
                    diapason.offer(marker.split(" ")[1] + ";");
                } else if (!line.matches("(400.*)|(500.*)")
                        && !marker.equals("")) {
                    diapason.offer(line.split(" ")[1]);
                    list.add(diapason.peekFirst() + diapason.peekLast());
                    diapason.clear();
                    marker = "";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter writer = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            list.stream()
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
