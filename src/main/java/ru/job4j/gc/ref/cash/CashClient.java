package ru.job4j.gc.ref.cash;

public class CashClient {
    public static void main(String[] args) {
        String key = "GCTest.txt";
        SystemCash cash = new SystemCash();
        String value = cash.getValue(key);
        System.out.println(value);
    }
}
