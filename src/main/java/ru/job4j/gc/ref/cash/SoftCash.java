package ru.job4j.gc.ref.cash;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;

public class SoftCash {
    private HashMap<String, SoftReference<String>> map;
    private MyCash cash;

    public SoftCash(MyCash cash, HashMap<String, SoftReference<String>> map) {
        this.cash = cash;
        this.map = map;
    }

    public SoftReference<String> getValue(String key) {
        return cash.getValue(map, key);
    }

    public static void main(String[] args) {
        HashMap<String, SoftReference<String>> map = new HashMap<>();
        String key = "GCTest.txt";
        MyCash cash = new SystemCash(map);
        SoftCash storage = new SoftCash(cash, map);
        String value = storage.getValue(key).get();
        System.out.println(new SoftReference<>(value).get());
        value = null;
    }
}
