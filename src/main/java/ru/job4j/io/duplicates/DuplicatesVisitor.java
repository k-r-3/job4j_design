package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private HashMap<FileProperty, Path> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty check = new FileProperty(
                file.toFile().length(), file.toFile().getName());
        if (!map.containsKey(check)) {
            map.put(check, file.toAbsolutePath());
        } else {
            System.out.println("Duplicate : " + check.getName() + " in " + map.get(check));
        }
        return super.visitFile(file, attrs);
    }
}