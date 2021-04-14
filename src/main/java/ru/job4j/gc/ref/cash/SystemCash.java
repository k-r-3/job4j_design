package ru.job4j.gc.ref.cash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.stream.Collectors;

public class SystemCash extends MyCash {
    private HashMap<String, SoftReference<String>> map;

    public SystemCash(HashMap<String, SoftReference<String>> map) {
        this.map = map;
    }

    @Override
    void load(String key) {
            try (BufferedReader reader = new BufferedReader(new FileReader(key))) {
                map.put(key, new SoftReference<>(reader.lines()
                        .collect(Collectors.joining(System.lineSeparator()))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
