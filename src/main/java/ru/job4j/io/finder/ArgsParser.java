package ru.job4j.io.finder;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ArgsParser {
    private String[] args;

    public ArgsParser(String[] args) {
        this.args = args;
    }

    public Path getDir() {
        return Paths.get(args[0].split("=")[1]);
    }

    public String extension() {
        return args[1].split("=")[1];
    }

    public String type() {
        return args[2].split("=")[1];
    }

    public Path outFile() {
        return Paths.get(args[3].split("=")[1]);
    }

    public boolean validateArgs() {
        return args.length == 4
                && args[0].startsWith("-d")
                && args[1].startsWith("-n")
                && args[2].startsWith("-t")
                && args[3].startsWith("-o");
    }
}
