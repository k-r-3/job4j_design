package ru.job4j.io;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(
                "c:\\Users\\Professional\\Documents\\");
        for (Path p : search(start, "черновик.*")) {
            System.out.println(p.getFileName());
            System.out.println(p.toAbsolutePath());
        }
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().matches(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
