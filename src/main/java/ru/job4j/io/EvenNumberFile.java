package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder sb = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                sb.append((char) read);
            }
            for (String text : sb.toString().split(System.lineSeparator())) {
                    System.out.println(Integer.parseInt(text) % 2 == 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
