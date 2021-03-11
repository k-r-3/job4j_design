package ru.job4j.serialization;

import org.json.JSONPropertyIgnore;
import ru.job4j.serialization.xml.A;

public class Recurse {
    private A a;
    private String name;

    public Recurse(String name) {
        this.name = name;
    }

    public void setA(A a) {
        this.a = a;
    }

    @JSONPropertyIgnore
    public A getA() {
        return a;
    }

    @Override
    public String toString() {
        return "Recurse{" + "name='" + name + '\'' + '}';
    }
}
