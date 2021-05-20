package ru.job4j.entity;

import java.time.LocalDateTime;

public class Post {
    private String text;
    private LocalDateTime created_at;

    public Post(String text, LocalDateTime created_at) {
        this.text = text;
        this.created_at = created_at;
    }
}
