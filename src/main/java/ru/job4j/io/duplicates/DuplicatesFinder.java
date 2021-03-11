package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        DuplicatesVisitor duplicate = new DuplicatesVisitor();
        System.out.println("Enter the path to the directory that be checked");
        Files.walkFileTree(Path.of(sc.nextLine()), duplicate);
        System.out.println("Duplicates : ");
        System.out.println(duplicate.getDuplicateMap());
    }
}
