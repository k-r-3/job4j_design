package ru.job4j.ood.isp.menu;

public interface Action {

    default boolean action() {
        return false;
    }
    
}
