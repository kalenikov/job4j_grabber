package ru.job4j.entity;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private String name;
    private String text;
    private String link;
    private LocalDateTime created;

    public Post(int id, String name, String text, String link, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.link = link;
        this.created = created;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public LocalDateTime getCreated() {
        return created;
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
