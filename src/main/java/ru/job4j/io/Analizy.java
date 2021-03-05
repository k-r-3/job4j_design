package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> list = new ArrayList<>();
        String marker = "";
        int count = 0;
        try (BufferedReader reader = new BufferedReader(
                new FileReader(source))) {
            while (reader.ready()) {
                String line = reader.readLine();
                if (marker.isEmpty() && line.startsWith("400")
                        || line.startsWith("500")) {
                    marker = line;
                    list.add(marker.split(" ")[1] + ";");
                } else if (!line.matches("(400.*)|(500.*)")
                        && !marker.equals("")) {
                    list.set(count, list.get(count) + line.split(" ")[1]);
                    count++;
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
