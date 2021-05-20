package ru.job4j.gc;

import org.openjdk.jmh.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class Article {

    public String origin = "Мой дядя самых честных правил, "
            + "Когда не в шутку занемог, "
            + "Он уважать себя заставил "
            + "И лучше выдумать не мог. "
            + "Его пример другим наука; "
            + "Но, боже мой, какая скука "
            + "С больным сидеть и день и ночь, "
            + "Не отходя ни шагу прочь! "
            + "Какое низкое коварство "
            + "Полуживого забавлять, "
            + "Ему подушки поправлять, "
            + "Печально подносить лекарство, "
            + "Вздыхать и думать про себя: "
            + "Когда же черт возьмет тебя!";

    public String line = "Мой дядя мог думать про тебя и день и ночь";

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Fork(value = 3, warmups = 3)
    @BenchmarkMode(Mode.All)
    public boolean generateBy(Article origin, Article line) {
        Map<String, Integer> map = new HashMap<>();
        int amount = 0;
        origin.origin = origin.origin.toUpperCase();
        line.line = line.line.toUpperCase();
        String[] lineArr = line.line.split(" ");
        String[] originArr = origin.origin.replaceAll("[,!.]", "")
                .split(" ");
        for (String o : originArr) {
            if (map.containsKey(o)) {
                map.put(o, map.get(o) + 1);
            } else {
                map.put(o, 1);
            }
        }
        for (String l : lineArr) {
            if (map.containsKey(l)) {
                map.put(l, map.get(l) - 1);
                amount++;
                if (map.get(l) == 0) {
                    map.remove(l);
                }
            }
        }
        return amount == lineArr.length;
    }
}
