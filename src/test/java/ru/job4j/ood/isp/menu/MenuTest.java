package ru.job4j.ood.isp.menu;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class MenuTest {

    @Test
    public void whenManyChild() {
        Item rus;
        Item eng = null;
        Item eng2;
        Item language = null;
        Item settings = null;
        rus = new Item("1.1.1 Russian", Collections.emptyList(), language) {
            @Override
            boolean function() {
                return false;
            }
        };
        eng2 = new Item("1.1.2.1 British", Collections.emptyList(), eng) {
            @Override
            boolean function() {
                return false;
            }
        };
         eng = new Item("1.1.2 English", List.of(eng2), language) {
            @Override
            boolean function() {
                return false;
            }
        };
         language = new Item("1.1 Language", List.of(rus, eng), settings) {
            @Override
            boolean function() {
                System.out.println("language changed");
                return true;
            }
        };
        settings = new Item("1 Settings", List.of(language), null) {
            @Override
            boolean function() {
                return getChildren().size() == 1;
            }
        };
        Item mailbox = new Item("2 Mail Box", Collections.emptyList(), null) {
            @Override
            boolean function() {
                return false;
            }
        };
        Menu menu = new Menu(List.of(settings, mailbox));
        System.out.println(menu.getNode(new int[] {0, 0, 1}));
        System.out.println(menu);

    }
}