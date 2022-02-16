package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public void main(String[] args) throws IOException {
        Search search = new Search();
        search.validation(args);
        search(Paths.get(args[0]), p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private void validation(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Root folder or extension is null. "
                    + "Usage java -jar Search.jar ROOT_FOLDER FILE_EXTENSION");
        }
        Path start = Paths.get(args[0]);
        if (!Files.exists(start) && !Files.isDirectory(start)) {
            throw new IllegalArgumentException("Folder not found. "
                    + "Or it's not folder!");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException(args[1] + " IS NOT EXTENSION");
        }
    }
}
