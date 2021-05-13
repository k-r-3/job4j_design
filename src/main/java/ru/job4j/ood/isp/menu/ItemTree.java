package ru.job4j.ood.isp.menu;

import java.util.List;

public interface ItemTree<E> {

    E getParent();

    List<E> getParents();

    void addChildren(List<E> children);

    List<E> getChildren();

}
