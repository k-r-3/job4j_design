package ru.job4j.design.srp;

import java.nio.file.Path;
import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);

    String generateHTML(Predicate<Employee> filter, Path path);
}
