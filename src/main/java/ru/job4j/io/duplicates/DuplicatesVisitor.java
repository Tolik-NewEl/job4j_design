package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    final private Map<FileProperty, List<Path>> files = new HashMap<>();
    final private List<Path> duplicates = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.toFile().getName());
        if (files.containsKey(fileProperty)) {
            files.get(fileProperty).add(file);
        } else {
            List<Path> path = new ArrayList<>();
            path.add(file);
            files.put(fileProperty, path);
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getDuplicates() {
        for (FileProperty file : files.keySet()) {
            List<Path> tmp = files.get(file);
            if (tmp.size() > 1) {
                duplicates.addAll(tmp);
            }
        }
        return duplicates;
    }
}
