package ru.job4j.jdbc;

import java.util.stream.Stream;

public class ConfigParser {

    public String getConfig(String value, ConnectionPattern cp, Stream<String> stream) {
        return stream
                .filter(l -> cp.getPatt(value)
                        .matcher(l)
                        .find())
                .findFirst()
                .get()
                .split("=")[1];
    }
}
