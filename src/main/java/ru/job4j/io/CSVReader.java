package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        valid(argsName);
        String delimiter = argsName.get("delimiter");
        String source = argsName.get("path");
        String target = argsName.get("out");
        String[] filters = argsName.get("filter").split(",");
        int[] indexes = new int[filters.length];
        StringJoiner sj = new StringJoiner("");
        try (Scanner sc = new Scanner(new FileReader(source, StandardCharsets.UTF_8))) {
             while (sc.hasNext()) {
                 String[] csvHeaders = sc.nextLine().split(delimiter);
                 for (int i = 0; i < csvHeaders.length; i++) {
                     String a = csvHeaders[i];
                     for (int j = 0; j < filters.length; j++) {
                         if (a.equals(filters[j])) {
                             indexes[j] = i;
                         }
                     }
                 }
                 for (int j : indexes) {
                     if (j < indexes.length - 1) {
                         sj.add(csvHeaders[j] + ";");
                     } else {
                         sj.add(csvHeaders[j]);
                     }
                 }
                 sj.add(System.lineSeparator());
             }
        }
        printTo(sj.toString(), target);
    }

    private static void printTo(String result, String out) {
        if ("stdout".equals(out)) {
            System.out.println(result);
        } else {
            try (PrintWriter pw = new PrintWriter(new FileWriter(out, StandardCharsets.UTF_8))) {
                pw.write(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void valid(ArgsName argsName) {
        Path path = Paths.get(argsName.get("path"));
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Input file is not CSV!");
        }
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("File not found!");
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            throw new IllegalArgumentException("Some args are missing!");
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
