package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class RoleStore<T extends User> implements Store<T> {
    private final List<T> role = new ArrayList<>();

    @Override
    public void add(T model) {
        role.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int index = getIndex(id);
        if (index != -1) {
            role.set(index, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        int index = getIndex(id);
        if (index != -1) {
            role.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (T i : role) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }

    private int getIndex(String id) {
        return role.indexOf(findById(id));
    }
}

