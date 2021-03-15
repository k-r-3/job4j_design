package ru.job4j.io.finder;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Finder {
    private static final Logger LOG = LoggerFactory.getLogger(Finder.class);
    private Path rootDir;
    private Predicate<Path> pred;

    public Finder(Path rootDir, Predicate<Path> pred) {
        this.rootDir = rootDir;
        this.pred = pred;
    }

    public Finder() { }

    public void writer(Path out, List<Path> paths) {
        try (PrintWriter writer = new PrintWriter(out.toFile())) {
            paths.forEach(writer::println);
        } catch (IOException e) {
            LOG.error("write exception", e);
        }
    }

    public List<Path> searcher() {
        Walker walker = new Walker(pred);
        try {
            Files.walkFileTree(rootDir, walker);
        } catch (IOException e) {
            LOG.error("search exception", e);
        }
        return walker.getList();
    }

    public String help() {
        return String.format("%s:\r\n%s\r\n%s\r\n%s\r\n%s",
                "arguments should be formatted as "
                        + "java -jar find.jar -d=c:/ -n=*.txt -t=regex -o=log.txt ",
                "-d is the root directory",
                "-n is the name of file, regex for search file or mask file",
                "-t is the type of query -n, "
                        + "must be 'name', 'regex' or 'mask'",
                "-o is the path of result file");
    }

    public static void main(String[] args) {
        ArgsParser parser = new ArgsParser(args);
        if (parser.validateArgs()) {
            Finder finder = new Finder(parser.getDir(),
                    new PredicateFactory(parser).getPredicate());
            finder.writer(parser.outFile(), finder.searcher());
        } else {
            System.out.println(new Finder().help());
        }
    }
}
