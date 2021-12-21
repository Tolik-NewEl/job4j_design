package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        int size = 9;
        try (FileOutputStream out = new FileOutputStream("matrix.txt")) {
            for (int row = 1; row <= size; row++) {
                for (int cell = 1; cell <= size; cell++) {
                    out.write(Integer.toString(row * cell).getBytes());
                    out.write(" ".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
