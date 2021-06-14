package ru.job4j.ood.food.model;

import java.util.List;

public interface Storage<T>{
    List<T> getAll();
    void add(T t);
    void clear();
}
