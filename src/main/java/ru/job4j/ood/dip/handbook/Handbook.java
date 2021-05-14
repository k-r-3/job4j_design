package ru.job4j.ood.dip.handbook;

import java.util.List;

public class Handbook {
    private List<Page> pages;
    private String name;

    public Handbook(String name, List<Page> pages) {
        this.name = name;
        this.pages = pages;
    }

    public String getPage(int pageNumber) {
        return pages.get(pageNumber).formatOut();
    }
}
