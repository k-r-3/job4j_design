package ru.job4j.io;

import org.junit.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.Assert.*;

public class ZipTest {

    @Test
    public static void main(String[] args) throws IOException {
        Path temp = Paths.get("./unzip");
        int amount = Search.search(Paths
                .get("c:\\projects\\job4j_design\\"), ".*").size();
        int amountExc = Search.search(Paths
                .get("c:\\projects\\job4j_design\\"), ".*java").size();
        ArgZip argZip = new ArgZip(args);
        new Zip().packFiles(argZip.directory(), argZip.output());
        try (ZipInputStream zip = new ZipInputStream(new BufferedInputStream(
                new FileInputStream(argZip.output())))) {
            ZipEntry zipEntry = zip.getNextEntry();
            while (zipEntry != null) {
                File file = new File(String.valueOf(temp), String.valueOf(zipEntry));
                try (BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream("./unzip"))) {
//                    out.write(file);
                }
        }
            zipEntry = zip.getNextEntry();

        }
        int zipAmount = Search.search(Paths.get("./target"), ".*").size();
        assertEquals(zipAmount, amount - amountExc);
    }
}