package ru.job4j.gc.ref.cash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.stream.Collectors;

public class StabCash extends MyCash {
    private HashMap<String, SoftReference<String>> map;
    private ReferenceQueue<String> queue;

    public StabCash(HashMap<String, SoftReference<String>> map, ReferenceQueue<String> queue) {
        this.map = map;
        this.queue = queue;
    }

    @Override
    void load(String key) {
        try (BufferedReader reader = new BufferedReader(new FileReader(key))) {
            map.put(key, new SoftReference<>(reader.lines()
                    .collect(Collectors.joining(System.lineSeparator())), queue));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
