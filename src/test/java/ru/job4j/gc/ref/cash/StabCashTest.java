package ru.job4j.gc.ref.cash;

import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class StabCashTest {
//-Xms50m -Xmx120m
    @Test
    public void whenOverLoad() throws InterruptedException {
//        HashMap<String, SoftReference<String>> map = new HashMap<>();
//        ReferenceQueue<String> queue = new ReferenceQueue<>();
//        String key1 = "GCTest.txt";
//        String key2 = "GCTest2.txt";
//        String key3 = "GCTest3.txt";
//        String key4 = "GCTest4.txt";
//        StabCash cash = new StabCash(map, queue);
////        cash.load(key1);
////        cash.load(key2);
////        cash.load(key3);
//        System.out.println(cash.load(key1));
//        System.out.println(cash.load(key2));
//        System.out.println(cash.load(key3));
//        System.out.println(cash.load(key4));
//        assertThat(Objects.nonNull(queue.poll()), is(true));
        String key = "GCTest.txt";
        String key2 = "GCTest2.txt";
        String key3 = "GCTest3.txt";
        String key4 = "GCTest4.txt";
        SystemCache cash = new SystemCache();
        String value = cash.getValue(key).toString();
//        System.out.println(value);
        String value2 = cash.getValue(key2).toString();
//        System.out.println(value2);
        String value3 = cash.getValue(key3).toString();
//        System.out.println(value3);
        String value4 = cash.getValue(key4).toString();
        System.out.println(value4);
    }
}