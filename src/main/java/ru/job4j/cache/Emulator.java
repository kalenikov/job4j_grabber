package ru.job4j.cache;

public class Emulator {

    private AbstractCache<String, String> cacheDir;

    public void setCacheDir(AbstractCache<String, String> cacheDir) {
        this.cacheDir = cacheDir;
    }

    public String get(String key) {
        return cacheDir.get(key);
    }

    public String load(String key) {
        return cacheDir.load(key);
    }

    public static void main(String[] args) {

        String dir = "src/main/resources/cache";

        Emulator emulator = new Emulator();
        emulator.setCacheDir(new DirFileCache(dir));
        emulator.load("1");

        System.out.println(emulator.get("1"));
        System.out.println(emulator.get("2"));
        System.out.println(emulator.get("3"));

    }
}
