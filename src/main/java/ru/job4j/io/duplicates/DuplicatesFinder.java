package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        List<Path> duplicates = duplicatesVisitor.getDuplicates();
        for (Path file : duplicates) {
            System.out.println(file);
        }
    }
}
