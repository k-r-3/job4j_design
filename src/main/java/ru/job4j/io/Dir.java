package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        int count = 0;
        if (args.length < 2) {
            throw new IllegalArgumentException("Root folder is null. "
                    + "Usage java -jar dir.jar ROOT_FOLDER.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String
                    .format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String
                    .format("Not directory %s", file.getAbsolutePath()));
        }
        for (File subFile : file.listFiles()) {
            if (subFile.getName().endsWith(args[1])) {
                count++;
                System.out.format("%s size = %s\n", subFile.getName(), subFile.length());
            }
        }
        if (count == 0) {
            System.out.println("filename extension "
                    + args[1] + " not exist in directory " + args[0]);
        }
    }

}
