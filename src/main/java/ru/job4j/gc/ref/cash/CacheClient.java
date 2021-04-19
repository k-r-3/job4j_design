package ru.job4j.gc.ref.cash;

public class CacheClient {
    public static void main(String[] args) throws InterruptedException {
        SystemCache cash = new SystemCache();
        System.out.println(cash.getValue("GCTest.txt"));
        System.out.println(cash.getValue("GCTest2.txt"));
        System.out.println(cash.getValue("GCTest3.txt"));
        System.out.println(cash.getValue("GCTest4.txt"));
        System.out.println(cash.getValue("GCTest4.txt"));
        System.out.println(cash.getValue("GCTest3.txt"));
        System.out.println(cash.getValue("404.txt"));
        System.out.println(cash.getValue("log.txt"));
        System.out.println(cash.getValue("result.txt"));
    }
}
