package ru.job4j.ood.lsp.storage;

public interface DateOfProduct<T> {

    public T getDate();

    public void setNow();

    public void setDate(int year, int month, int dayOfMonth);

    public int getYear();

    public int getMonth();

    public int getDayOfMonth();

    public int getDayOfYear();
}
