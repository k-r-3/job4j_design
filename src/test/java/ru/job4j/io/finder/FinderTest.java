package ru.job4j.io.finder;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class FinderTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenValidate() {
        ArgsParser parser = new ArgsParser(new String[] {
                "-d=c:/", "-n=война?и?мир.?*",
                "-o=c:/projects/log2.txt"
        });
        assertFalse(parser.validateArgs());
    }

    @Test
    public void logFileExist() throws IOException {
        Path rootDir = folder.newFolder().toPath();
        Path subDir = Files.createDirectory(Paths.get(rootDir.toAbsolutePath() + "/tempdir"));
        Path mdFile = Files.createTempFile(subDir, "README", ".md");
        ArgsParser parser = new ArgsParser(new String[]{
                "-d=" + rootDir, "-n=README.md",
                "-t=name", "-o=" + rootDir + "/log.txt"
        });
        Finder finder = new Finder(parser.getDir(), parser.type());
        finder.writer(parser.outFile());
        assertThat(parser.outFile().toFile().exists(), is(true));
    }

    @Test
    public void whenName() throws IOException {
        Path rootDir = folder.newFolder().toPath();
        Path subDir = Files.createDirectory(Paths.get(rootDir.toAbsolutePath() + "/tempdir"));
        Path mdFile = Files.createTempFile(subDir, null, "README.md");
        String name = mdFile.getFileName().toString();
        ArgsParser parser = new ArgsParser(new String[] {
                "-d=" + rootDir, "-n=" + name,
                "-t=name", "-o=" + rootDir + "/log.txt"
        });
        Finder finder = new Finder(parser.getDir(), parser.type());
        finder.writer(parser.outFile());
        try (BufferedReader reader = new BufferedReader(
                new FileReader(parser.outFile().toAbsolutePath().toString()))) {
            String actual = reader.lines().collect(Collectors.joining());
            String expected = mdFile.toAbsolutePath().toString();
            assertThat(actual.equals(expected), is(true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenRegex() throws IOException {
        Path rootDir = folder.newFolder().toPath();
        Path subDir = Files.createDirectory(Paths.get(rootDir.toAbsolutePath() + "/tempdir"));
        Path mdFile = Files.createTempFile(subDir, null, "README.md");
        ArgsParser parser = new ArgsParser(new String[] {
                "-d=" + rootDir, "-n=.*.md",
                "-t=regex", "-o=" + rootDir + "/log.txt"
        });
        Finder finder = new Finder(parser.getDir(), parser.type());
        finder.writer(parser.outFile());
        try (BufferedReader reader = new BufferedReader(
                new FileReader(parser.outFile().toAbsolutePath().toString()))) {
            String actual = reader.lines().collect(Collectors.joining());
            String expected = mdFile.toAbsolutePath().toString();
            assertThat(actual.equals(expected), is(true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenMask() throws IOException {
        Path rootDir = folder.newFolder().toPath();
        Path subDir = Files.createDirectory(Paths.get(rootDir.toAbsolutePath() + "/tempdir"));
        Path mdFile = Files.createTempFile(subDir, null, "README.md");
        ArgsParser parser = new ArgsParser(new String[] {
                "-d=" + rootDir, "-n=*R**DME.md",
                "-t=mask", "-o=" + rootDir + "/log.txt"
        });
        Finder finder = new Finder(parser.getDir(), parser.type());
        finder.writer(parser.outFile());
        try (BufferedReader reader = new BufferedReader(
                new FileReader(parser.outFile().toAbsolutePath().toString()))) {
            String actual = reader.lines().collect(Collectors.joining());
            String expected = mdFile.toAbsolutePath().toString();
            assertThat(actual.equals(expected), is(true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}