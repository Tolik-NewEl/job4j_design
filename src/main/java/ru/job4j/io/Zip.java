package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path file : sources) {
                zip.putNextEntry(new ZipEntry(file.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Some args is missing!");
        }
        ArgsName argsName = ArgsName.of(args);
        Zip zip = new Zip();
        zip.validate(argsName);
        Path dir = Paths.get(argsName.get("d"));
        String exclude = argsName.get("e");
        File output = new File(argsName.get("o"));
        Search search = new Search();
        List<Path> files = search.search(dir, s -> s.toFile().isFile());
        List<Path> excludeFiles = files
                .stream()
                .filter(s -> !s.toFile().getName().endsWith(exclude))
                .collect(Collectors.toList());
        zip.packFiles(excludeFiles, output);
    }

    public void validate(ArgsName argsName) {
        if (!argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Second arg is not extension!");
        }
        if (!Path.of(argsName.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException("Directory to zip is not found!");
        }
    }
}
