package ru.job4j.gc.ref.cash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.stream.Collectors;

public class SystemCache extends MyCache<String, String> {

    @Override
     void load(String key) {
        try (BufferedReader reader = new BufferedReader(new FileReader(key))) {
            super.getMap().put(key, new SoftReference<>(reader.lines()
                    .collect(Collectors.joining(System.lineSeparator())),
                    super.getQueue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        return super.getValue(key);
    }
}
