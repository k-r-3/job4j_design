package ru.job4j.serialization.json;

import org.json.JSONPropertyName;

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
