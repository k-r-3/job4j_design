package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analizy {
    private List<String> list = new ArrayList<>();

    public void unavailable(String source) {
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
    }

    public void writeLog(String target) {
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
        analizy.unavailable("unavailable log.txt");
        analizy.writeLog("unavailableLog.csv");
    }
}
