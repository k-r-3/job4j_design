package ru.job4j.io.finder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Walker extends SimpleFileVisitor<Path> {
    private static final Logger LOG = LoggerFactory.getLogger(Finder.class);
    private Predicate<Path> pred;
    private List<Path> list = new ArrayList<>();

    public Walker(Predicate<Path> pred) {
        this.pred = pred;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isRegularFile(file)) {
            if (pred.test(file)) {
                list.add(file);
            }
            return super.visitFile(file, attrs);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        LOG.debug("Access denied : " + file.toString());
        return FileVisitResult.SKIP_SUBTREE;
    }

    public List<Path> getList() {
        return list;
    }
}
