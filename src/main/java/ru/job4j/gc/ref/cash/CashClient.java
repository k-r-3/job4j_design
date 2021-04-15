package ru.job4j.gc.ref.cash;

import java.lang.ref.SoftReference;

public class CashClient {
    public static void main(String[] args) {
        String key = "GCTest.txt";
        MyCash<String, SoftReference<String>> cash = new SystemCash();
        String value = cash.getValue(key).toString();
        System.out.println(value);
    }
}
