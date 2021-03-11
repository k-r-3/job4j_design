package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class SearchTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenSearchJava() throws IOException {
        Path rootDir = folder.newFolder().toPath();
        Path subDir = Files.createDirectory(Paths.get(rootDir.toAbsolutePath() + "/tempdir"));
        Path mdFile = Files.createTempFile(rootDir, null, ".md");
        Path javaFileOuter = Files.createTempFile(rootDir, null, ".java");
        Path javaFileInner = Files.createTempFile(subDir, null, ".java");
        assertEquals(Search.search(rootDir, ".*java").size(), 2);
    }

    @Test
    public void whenSearchReadme() throws IOException {
        Path rootDir = folder.newFolder().toPath();
        Path subDir = Files.createDirectory(Paths.get(rootDir.toAbsolutePath() + "/tempdir"));
        Path mdFile = Files.createTempFile(rootDir, null, ".md");
        Path javaFile = Files.createTempFile(subDir, null, ".java");
        assertEquals(Search.search(rootDir, ".*md").size(), 1);
    }

}