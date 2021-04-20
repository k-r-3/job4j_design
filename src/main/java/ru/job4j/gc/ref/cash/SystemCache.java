package ru.job4j.gc.ref.cash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.stream.Collectors;

public class SystemCache extends MyCache<String, String> {

    @Override
     protected void load(String key) {
        try (BufferedReader reader = new BufferedReader(new FileReader(key))) {
            SoftReference<String> ref = new SoftReference<>(reader.lines()
                    .collect(Collectors.joining(System.lineSeparator())));
            super.getMap().put(key, ref);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public String getValue(String key) {
//        return super.getValue(key);
//    }
}
