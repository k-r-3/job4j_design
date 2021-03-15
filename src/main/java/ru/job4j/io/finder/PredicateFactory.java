package ru.job4j.io.finder;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class PredicateFactory {
   private ArgsParser parser;

    public PredicateFactory(ArgsParser parser) {
        this.parser = parser;
    }

    public Predicate<Path> getPredicate() {
        Pattern pattern = Pattern.compile(parser.extension()
                .replaceAll("\\*", ".*"));
        switch (parser.type()) {
            case "name":
                return path -> path.toFile()
                        .getName()
                        .equals(pattern.toString());
            case "mask":
                return path -> pattern.matcher(path.toFile()
                        .getName()).find();
            case "regex":
                return path -> pattern.matcher(path.toFile()
                        .getName()).find();
            default:
                return null;
        }
    }
}
