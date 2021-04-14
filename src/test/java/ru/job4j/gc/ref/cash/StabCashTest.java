package ru.job4j.gc.ref.cash;

import org.junit.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Objects;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class StabCashTest {
//-Xms50m -Xmx120m
    @Test
    public void whenOverLoad() {
        HashMap<String, SoftReference<String>> map = new HashMap<>();
        ReferenceQueue<String> queue = new ReferenceQueue<>();
        String key1 = "GCTest.txt";
        String key2 = "GCTest2.txt";
        String key3 = "GCTest3.txt";
        String key4 = "GCTest4.txt";
        StabCash cash = new StabCash(map, queue);
        cash.load(key1);
        cash.load(key2);
        cash.load(key3);
        cash.load(key4);
//        assertThat(Objects.nonNull(queue.poll()), is(true));
    }
}