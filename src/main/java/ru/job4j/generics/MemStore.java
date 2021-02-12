package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int index = getIndex(id);
        if (index != -1) {
            mem.set(index, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        int index = getIndex(id);
        if (index != -1) {
            mem.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        int index = getIndex(id);
        if (index != -1) {
         return mem.get(index);
        }
        return null;
    }

    private int getIndex(String id) {
        Iterator<T> iterator = mem.iterator();
        int index = -1;
        for (int i = 0; i < mem.size(); i++) {
            if (iterator.next().getId().equals(id)) {
                index =  i;
                break;
            }
        }
        return index;
    }
}
