package ru.job4j.ood.lsp.storage;

import java.time.LocalDate;

public class Date<T extends LocalDate> implements DateOfProduct<T> {
    private T localDate;
    private int year;
    private int month;
    private int dayOfMonth;

    @Override
    public T getDate() {
        return localDate;
    }

    @Override
    public void setNow() {
        localDate = (T) LocalDate.now();
        year = localDate.getYear();
        month = localDate.getMonthValue();
        dayOfMonth = localDate.getDayOfMonth();

    }

    @Override
    public void setDate(int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        localDate = (T) LocalDate.of(year, month, dayOfMonth);
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public int getMonth() {
        return month;
    }

    @Override
    public int getDayOfMonth() {
        return dayOfMonth;
    }

    @Override
    public int getDayOfYear() {
        T ld = (T) LocalDate.of(getYear(), getMonth(), getDayOfMonth());
        return ld.getDayOfYear();
    }

}
