package ru.job4j.kiss;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return compIt(value, comparator, -1);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return compIt(value, comparator, 1);
    }

    private <T> T compIt(List<T> value, Comparator<T> comparator, int order) {
        Iterator<T> iterator = value.iterator();
        T element = iterator.next();
        while (iterator.hasNext()) {
            T currElement = iterator.next();
            if (compPred(comparator.compare(element, currElement), order)) {
                element = currElement;
            }
        }
        return element;
    }

    private boolean compPred(int result, int order) {
        return (result > 0 && order > 0) || (result < 0 && order < 0);
    }
}
