package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input path to checked directory");
        Files.walkFileTree(Path.of(sc.nextLine()), new DuplicatesVisitor());
    }
}
