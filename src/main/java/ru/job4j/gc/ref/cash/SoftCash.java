package ru.job4j.gc.ref.cash;

import java.lang.ref.SoftReference;

public class SoftCash {
    private MyCash cash;

    public SoftCash(MyCash cash) {
        this.cash = cash;
    }

    public String getValue(String key) {
        return cash.getValue(key);
    }

    public static void main(String[] args) {
        String key = "GCTest.txt";
        MyCash cash = new SystemCash();
        SoftCash storage = new SoftCash(cash);
        String value = storage.getValue(key);
        System.out.println(new SoftReference<>(value).get());
    }
}
