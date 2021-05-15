package ru.job4j.ood.isp.menu;

import java.util.List;
import java.util.Optional;

public interface ItemTree<E> {

    E getParent();

    Optional<E> getElement(String name);

    List<E> getParents();

    void addChild(String parentName, E child);

    boolean deleteChild(String name);

    void addChildren(List<E> children);

    List<E> getChildren();

}
