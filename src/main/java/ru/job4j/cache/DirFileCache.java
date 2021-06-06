package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(cachingDir, key));
            return String.join("", lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}