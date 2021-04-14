package ru.job4j.gc.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeakDemo {

    public static void main(String[] args) throws InterruptedException {
        example1();
//        example2();
//        example3();
    }

    private static void example1() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        WeakReference<Object> weak = new WeakReference<Object>(object, queue);
        object = null;
        System.gc();
        TimeUnit.SECONDS.sleep(5);
        System.out.println(weak.get());
        System.out.println("from queue " + queue.poll());
    }

    private static void example2() throws InterruptedException {
        List<WeakReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            objects.add(new WeakReference<Object>(new Object() {
                private String name = "name";
                private int num = finalI;

                @Override
                public String toString() {
                    return "$classname{"
                            + "name='" + name + finalI + '\'' + '}';
                }

                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Removed");
                }
            }));
        }
        Object obj = objects.get(0).get();
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        obj = null;
        System.out.println(obj);
    }

    private static void example3() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        WeakReference<Object> weak = new WeakReference<>(object, queue);
        object = null;

        System.gc();

        TimeUnit.SECONDS.sleep(3);
        System.out.println("from link " + weak.get());
        System.out.println("from queue " + queue.poll());
    }
}
