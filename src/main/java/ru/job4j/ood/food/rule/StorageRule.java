package ru.job4j.ood.food.rule;

import ru.job4j.ood.food.model.Food;
import ru.job4j.ood.food.model.Storage;

import java.util.function.Predicate;

/**
 * класс, описывающий условия перемещения на склад
 */

public class StorageRule extends AbstractRule<Integer, Storage<Food>> {
    public StorageRule(Predicate<Integer> predicate, Storage<Food> result) {
        super(predicate, result);
    }
}
