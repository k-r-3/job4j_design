package ru.job4j.io;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class SearchTest {

    @Test
    public void whenSearchJava() throws IOException {
        Path root = Paths.get(
                "c:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\collection\\");
        assertEquals(Search.search(root, "java").size(), 10);
    }

    @Test
    public void whenSearchReadme() throws IOException{
        Path root = Paths.get("c:\\projects\\job4j_design\\");
        assertEquals(Search.search(root, "md").size(), 1);
    }

}