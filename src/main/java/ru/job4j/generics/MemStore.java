package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {
    private T t;
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (findById(id) != null) {
            T element = findById(id);
            mem.set(mem.indexOf(element), model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (findById(id) != null) {
            mem.remove(findById(id));
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (T i : mem) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }
}
