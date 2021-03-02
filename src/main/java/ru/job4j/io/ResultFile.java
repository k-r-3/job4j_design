package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ResultFile {

    public String table() {
        StringBuilder sb = new StringBuilder();
        int[][] table = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
            sb.append(Arrays.toString(table[i]))
            .append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ResultFile result = new ResultFile();
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write(result.table().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
