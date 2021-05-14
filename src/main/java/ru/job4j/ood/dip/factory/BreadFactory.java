package ru.job4j.ood.dip.factory;

import java.util.ArrayList;
import java.util.List;

public class BreadFactory implements Generator<WholeWheatBread> {

    @Override
    public List<WholeWheatBread> gen(int amount) {
        List<WholeWheatBread> result = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            result.add(new WholeWheatBread(4, "cellophane"));
        }
        return result;
    }
}
