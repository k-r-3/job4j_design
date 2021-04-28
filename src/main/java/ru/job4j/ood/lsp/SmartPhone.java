package ru.job4j.ood.lsp;

public class SmartPhone {
    protected int currentHour;

    public SmartPhone(int currentHour) {
        this.currentHour = currentHour;
    }

    public void doNotDisturbedMode() {
        if (currentHour > 22) {
            System.out.println("do not disturbed mode activated");
        }
    }
}

class Xiaomi extends SmartPhone {

    public Xiaomi(int currentHour) {
        super(currentHour);
    }

    @Override
    public void doNotDisturbedMode() {
        if (currentHour > 24) {
            System.out.println("do not disturbed mode activated");
        }
    }
}
