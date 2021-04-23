package ru.job4j.wrong;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Predicate;

public class Visitor  extends SimpleFileVisitor<Path> {
    private Predicate<Path> pred;
    private Path sought;

    public Visitor(Predicate<Path> pred) {
        this.pred = pred;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (pred.test(file)) {
            sought = file;
            return FileVisitResult.CONTINUE;
        }
        return super.visitFile(file, attrs);
    }

    public Path getSought() {
        return sought;
    }
}
