package ru.job4j.gc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyClass implements Cloneable {
    public int i = 10;
    private Profiling profiling;
    List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

    public MyClass(Profiling profiling) {
        this.profiling = profiling;
    }

    public MyClass clone() throws CloneNotSupportedException {
        MyClass clone = (MyClass) super.clone();
        clone.setProfiling((Profiling) clone.getProfiling().clone());
        return clone;
    }

    public Profiling getProfiling() {
        return profiling;
    }

    public void setProfiling(Profiling profiling) {
        this.profiling = profiling;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}

class Client {
    public static void main(String[] args) throws CloneNotSupportedException {
        Profiling first = new Profiling();
        first.i = 9;
        MyClass orig = new MyClass(first);
        MyClass clone = orig.clone();
        System.out.println(orig.list);
        System.out.println(clone.list);
        System.out.println(clone.equals(orig));
        System.out.println(clone.getProfiling().i);
        System.out.println(first.i);
        clone.getProfiling().i = 11;
        System.out.println(clone.getProfiling().i);
        System.out.println(orig.getProfiling().i);
        System.out.println(first.i);
    }
}

