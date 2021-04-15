package ru.job4j.gc.ref.cash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.stream.Collectors;

public class SystemCash extends MyCash<String, SoftReference<String>> {

    @Override
    void load(String key) {
        try (BufferedReader reader = new BufferedReader(new FileReader(key))) {
            super.getMap().put(key, new SoftReference<>(reader.lines()
                    .collect(Collectors.joining(System.lineSeparator()))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        return (String) super.getValue(key);
    }
}
