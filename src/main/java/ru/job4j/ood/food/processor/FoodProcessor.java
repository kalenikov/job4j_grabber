package ru.job4j.ood.food.processor;

import ru.job4j.ood.food.model.Food;

import java.util.List;

public interface FoodProcessor extends Processor<Food> {
    @Override
    void process(List<Food> foods);
}
