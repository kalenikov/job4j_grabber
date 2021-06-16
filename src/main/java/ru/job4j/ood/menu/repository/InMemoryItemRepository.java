package ru.job4j.ood.menu.repository;

import ru.job4j.ood.menu.model.Item;
import ru.job4j.ood.menu.util.ItemUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryItemRepository implements ItemRepository {
    private final Map<Integer, Item> repo = new HashMap<>();

    {
        new ItemUtils().demo().forEach(this::save);
    }

    @Override
    public void save(Item item) {
        repo.put(item.getId(), item);
    }

    @Override
    public Item get(int id) {
        return repo.get(id);
    }

    @Override
    public List<Item> getAll() {
        return new ArrayList<>(repo.values());
    }
}
