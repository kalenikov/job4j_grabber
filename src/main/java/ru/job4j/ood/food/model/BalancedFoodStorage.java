package ru.job4j.ood.food.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BalancedFoodStorage implements Storage<Food> {
    private static final Map<Food, Storage<Food>> foodOnStorages = new HashMap<>();

    @Override
    public List<Food> getAll() {
        return getAllByStorage(this);
    }

    public List<Food> getAllByStorage(Storage<Food> storage) {
        return foodOnStorages.entrySet().stream()
                .filter(entry -> entry.getValue().equals(storage))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public void add(Food food) {
        foodOnStorages.put(food, this);
    }

    @Override
    public void clear() {
        foodOnStorages.clear();
    }
}
