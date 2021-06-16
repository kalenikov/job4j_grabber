package ru.job4j.ood.menu.model;

public interface Item extends Callbackable, Hierarchical {
    String getLabel();

    int getId();

    int getLevel();
}
