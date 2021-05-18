package ru.job4j.ood.isp.menu;

import java.util.List;
import java.util.Optional;
import java.util.Queue;

public interface ItemTree<E> {

    List<E> getParents(E start);

    void addChild(String parentName, E child);

    boolean deleteChild(String name);

    void addChildren(List<E> children, String targetItem);

    Optional<E> bfs(Queue<E> queue, String target);

}
