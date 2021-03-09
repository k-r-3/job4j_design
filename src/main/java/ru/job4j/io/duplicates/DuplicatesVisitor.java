package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private static HashMap<String, Path> duplicateMap = new HashMap<>();
    private HashMap<FileProperty, Path> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty check = new FileProperty(
                file.toFile().length(), file.toFile().getName());
        if (!map.containsKey(check)) {
            map.put(check, file.toAbsolutePath());
        } else {
            duplicateMap.put(check.getName(), file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }

    public static String getDuplicateMap() {
        return duplicateMap.entrySet().stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("\n"));
    }
}