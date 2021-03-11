package ru.job4j.serialization.xml;

import org.json.JSONObject;
import ru.job4j.serialization.Recurse;

public class A {
    private Recurse r;
    private String a;

    public void setR(Recurse r) {
        this.r = r;
    }

    public Recurse getR() {
        return r;
    }

    @Override
    public String toString() {
        return "A{" + "r=" + r + ", a='" + a + '\'' + '}';
    }

    public static void main(String[] args) {
        Recurse r = new Recurse("qwerty");
        A a = new A();
        r.setA(a);
        a.setR(r);
        System.out.println(new JSONObject(a));

    }
}
