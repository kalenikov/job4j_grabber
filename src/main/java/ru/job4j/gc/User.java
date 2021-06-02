package ru.job4j.gc;

public class User {
    private int id;
    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    protected void finalize() {
        System.out.printf("Removed %d %n", id);
    }
}
