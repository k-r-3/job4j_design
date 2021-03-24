package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ConfigParser {
    private Map<String, String> map = new HashMap<>();

    public ConfigParser(BufferedReader reader) {
        Pattern pattern = Pattern.compile("(?!#.*|!.*).*=.*");
        map = reader.lines()
                .filter(f -> f.matches(String.valueOf(pattern)))
                .map(s -> s.split("="))
                .collect(Collectors.toMap(m -> m[0], m -> m[1]));
    }

    public String getConfig(String key, ConnectionPattern cp) {
        return map.get(map.keySet().stream()
                .filter(l -> cp.getPatt(key)
                        .matcher(l)
                        .find())
                .findFirst()
        .get());

    }
}
