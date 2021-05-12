//package ru.job4j.ood.isp.menu;
//
//import org.junit.Test;
//
//import java.util.Collections;
//import java.util.List;
//
//public class ItemTest {
//
//    @Test
//    public void whenOneChild() {
//        Item rus = new Item("Russian", Collections.emptyList()) {
//            @Override
//            boolean function() {
//                return false;
//            }
//        };
//        Item eng2 = new Item("Britanish", Collections.emptyList()) {
//            @Override
//            boolean function() {
//                return false;
//            }
//        };
//        Item eng = new Item("English", List.of(eng2)) {
//            @Override
//            boolean function() {
//                return false;
//            }
//        };
//        Item language = new Item("Language", List.of(rus, eng)) {
//            @Override
//            boolean function() {
//                System.out.println("language changed");
//                return true;
//            }
//        };
//        Item settings = new Item("Settings", List.of(language)) {
//            @Override
//            boolean function() {
//                return getChildren().size() == 1;
//            }
//        };
//        System.out.println(settings);
//        System.out.println(settings.function());
//        settings.getChildren().get(0).function();
//    }
//
//}