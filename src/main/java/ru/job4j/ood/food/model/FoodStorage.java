package ru.job4j.ood.food.model;

import java.util.ArrayList;
import java.util.List;

public class FoodStorage implements Storage<Food> {
    private List<Food> foods = new ArrayList<>();

    @Override
    public List<Food> getAll() {
        return foods;
    }

    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public void clear() {
        foods.clear();
    }
}
