package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;;
import java.util.List;
import java.util.stream.Collectors;

public class ArgZip {

    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
            for (int i = 3; i < args.length; i++) {
                    if (!args[i].matches("-.*")) {
                        System.out.println("wrong argument #" + i);
                        return false;
                    }
                }
        return true;
    }

    public List<File> directory() throws IOException {
        String rootArg = args[3].split("=")[1];
        Path root = Paths.get(rootArg);
        return Search.search(root, ".*")
                .stream()
                .filter(e -> !e.toString().matches(exclude()))
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    public String exclude() {
        String excArg = args[4].split("=")[1];
            return ".*" + excArg;
    }

    public File output() {
        Path out;
        String outArg = args[5].split("=")[1];
            out = Paths.get(outArg);
        return out.toFile();
    }
}
