package ru.job4j.ood.isp.menu;

public class Check extends Item {

    public Check(String name) {
        super(name);
    }

    @Override
    public boolean action() {
        System.out.println("check!");
        return true;
    }
}
