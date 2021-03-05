package ru.job4j.collection.map;

import java.util.HashMap;
import java.util.Map;

public class MapRunner {
    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        User first = new User();
        User second = new User();
        map.put(first, null);
        map.put(second, null);
        System.out.println(map);
        System.out.println("////////");
        System.out.println(first.equals(second));
        System.out.println(first == second);
        System.out.println(System.identityHashCode(first));
        System.out.println(System.identityHashCode(second));
        System.out.println(first.hashCode()); //11967
        System.out.println(second.hashCode());
        System.out.println(1 % 5);
    }

}
