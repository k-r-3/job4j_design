package ru.job4j.serialization.json;

public class ProductFacts {
    private final String name;

    public ProductFacts(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductFacts{" + "name='"
                + name + '\'' + '}';
    }
}
