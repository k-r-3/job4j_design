package ru.job4j;

import java.util.ArrayList;
import java.util.List;

public class Sort {

    void merge(int[] a, int[] b, int[] c, int bS, int cS) {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        while (count1 < bS && count2 < cS) {
            if (b[count1] <= c[count2]) {
                a[count3++] = b[count1++];
            } else {
                a[count3++] = c[count2++];
            }
        }
        while (count1 < bS) {
            a[count3++] = b[count1++];
        }
        while (count2 < cS) {
            a[count3++] = c[count2++];
        }
    }

    void mergeSort(int[] i, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];
        for (int j = 0; j < mid; j++) {
            l[j] = i[j];
        }
        for (int j = mid; j < n; j++) {
            r[j - mid] = i[j];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);
        merge(i, l, r, mid, n - mid);
    }

    List<Integer> range(int[] ar) {
        int i = 0;
        var list = new ArrayList<Integer>();
        list.add(ar[0]);
        while (i < ar.length) {
            i++;
            for (int j = i + 1; j < ar.length; j++) {
                if ((ar[j] - ar[i]) != 1) {
                    list.add(ar[i]);
                    list.add(ar[j]);
                    break;
                } else {
                    i++;
                }
            }
        }
        list.add(ar[ar.length - 1]);
        return list;
    }

    List<Integer> range2(int[] ar) {
        var list = new ArrayList<Integer>();
        var result  = new ArrayList<Integer>();
        int number = ar[0];
        int current = number;
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar.length; j++) {
                if (ar[j] - number == 1) {
                    list.add(number);
                    current = j;
                    number = ar[j];
                    break;
                }
                if (j == ar.length - 1 && number == ar[current]) {
                    list.add(number);
                    current++;
                    number = ar[current];
                }
            }
        }
        int f = list.get(0);
        result.add(f);
        for (int i = 1; i < list.size(); i++) {
            var t = list.get(i);
            if (t - f == 1) {
                f = t;
            } else {
                result.add(f);
                f = t;
                result.add(f);
            }
            if (i == list.size() - 1) {
                result.add(list.get(list.size() - 1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] ar = {1, 3, 2, 4, 9, 11, 13, 12};
        Sort sort = new Sort();
        sort.mergeSort(ar, ar.length);
        System.out.println(sort.range(ar));
//        System.out.println(sort.range2(ar));
    }
}
