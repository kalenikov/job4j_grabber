package ru.job4j.ood.menu.repository;

import ru.job4j.ood.menu.model.Item;

import java.util.List;

public interface ItemRepository {
    void save(Item item);
    Item get(int id);
    List<Item> getAll();
}
