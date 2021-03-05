package ru.job4j.collection;

import java.util.Iterator;
import java.util.Random;

public class Runner {
    public static void main(String[] args) {
        Random rand = new Random(80);
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("a", "value");
        map.insert("c", "value");
        map.insert("o", "value");
        map.insert("d", "value");
        map.insert("q", "value");
        map.insert("e", "value");
        map.insert("u", "value");
        map.insert("g", "value");
        map.insert("w", "value");
        map.insert("k", "value");
        map.insert("z", "value");
        map.insert("n", "value");
        map.insert("h", "value");
        map.insert("fdgjkhlks", "value");
        map.insert("vdnslkl", "value");
        map.insert("vsdf;j", "value");
//        map.insert("l", "value");
//        map.insert("o", "value");
        Iterator<SimpleHashMap.Node<String, String>> iterator
                = map.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(map.size());
        System.out.println();
    }
}
