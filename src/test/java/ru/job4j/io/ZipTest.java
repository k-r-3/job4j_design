package ru.job4j.io;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class ZipTest {

    @Test
    public void whenZipInOtherDir() throws IOException {
        ArgZip argZip = new ArgZip(new String[]
                {"java", "-jar", "pack.jar", "-d=c:/JavaProgects", "-e=class",
                        "-o=project.zip"});
        Zip zip = new Zip();
        zip.packFiles(argZip.directory(), argZip.output());
        Path dir = Paths.get("./project.zip");
        assertThat(dir.toFile().exists(), is(true));
    }

}