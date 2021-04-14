package ru.job4j.gc.ref.cash;

import java.lang.ref.SoftReference;
import java.util.HashMap;

 abstract class MyCash {
    private String key;

     public void setKey(String key) {
         this.key = key;
     }

     public SoftReference<String> getValue(HashMap<String, SoftReference<String>> map, String key) {
        if (map.get(key) == null) {
            load(key);
        }
        return new SoftReference<>(map.get(key).get());
    }

    abstract void load(String key);
}
