package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArgZip {

    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        for (int i = 0; i < args.length; i++) {
            if (i % 2 == 0) {
                if (!args[i].matches("-\\w")) {
                    System.out.println("wrong argument #" + i);
                    return false;
                }
            }
        }
        return true;
    }

    public List<File> directory() throws IOException {
        Path root = Paths.get(args[1]);
        return Search.search(root, ".*")
                .stream()
                .filter(e -> !e.toString().matches(exclude()))
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    public String exclude() {
        return ".*" + args[3].split("\\*.")[1];
    }

    public File output() {
        Path out = Paths.get(args[5]);
        return out.toFile();
    }

    public void getArgs() {
        System.out.println(Arrays.toString(args));
    }
}
