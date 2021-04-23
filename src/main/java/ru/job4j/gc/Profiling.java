package ru.job4j.gc;

public class Profiling implements Cloneable {
    public int i = 1;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Profiling clone = (Profiling) super.clone();
        return clone;
    }

    //    public static void small() {
//        for (int j = 0; j < 100; j++) {
//            i += j;
//        }
//    }

//    public static void big() {
//        for (long j = 0; j < 10000000L; j++) {
//           small();
//        }
//    }

//    public static void warmUp() {
//        System.out.println("start warm up");
//        for (int j = 0; j < 1000; j++) {
//            for (int k = 0; k < 1000; k++) {
//                i++;
//                i--;
//            }
//        }
//        System.out.println("end warm up");
//    }

    public static void benchMark1() {
        double r = 1D;
        System.out.println("start test1");
        long start = System.nanoTime();
        for (double j = 1L; j < 100000D; j++) {
            j = Math.pow(j, 2D);
        }
        System.out.println(System.nanoTime() - start);
        System.out.println("end test1");
    }

    public static void benchMark2() {
        int r = 1;
        System.out.println("start test2");
        long start = System.nanoTime();
        for (long j = 1; j < 100000L; j++) {
            j *= j;
        }
        System.out.println(System.nanoTime() - start);
        System.out.println("end test2");
    }

    public static final void main(String[] args) {
//        warmUp();
//        while (true) {
//            big();
//        }
        benchMark1();
        benchMark2();
        benchMark2();
        benchMark1();
        benchMark1();
        benchMark2();
    }
}

