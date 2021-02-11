package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class RoleStore<T extends User> implements Store<T> {
    private T t;
    private final List<T> role = new ArrayList<>();

    @Override
    public void add(T model) {
        role.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (findById(id) != null) {
            T element = findById(id);
            role.set(role.indexOf(element), model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (findById(id) != null) {
            role.remove(findById(id));
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
}

