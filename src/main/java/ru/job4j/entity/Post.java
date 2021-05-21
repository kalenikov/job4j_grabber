package ru.job4j.entity;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private String name;
    private String text;
    private String link;
    private LocalDateTime created;

    public Post(String name, String text, String link, LocalDateTime created) {
        this.name = name;
        this.text = text;
        this.link = link;
        this.created = created;
    }

    @Override
    public String toString() {
        return "Post{" +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", link='" + link + '\'' +
                ", created=" + created +
                '}';
    }
}
